package com.ef.accesslog;

import com.ef.arguments.ArgumentsDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
public class AccessLogManager {

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static String START_DATE_FORMAT = "yyyy-MM-dd.HH:mm:ss";
    private static String HOURLY = "hourly";
    private static String DAILY = "daily";

    @PersistenceContext
    private EntityManager entityManager;

    public void loadAccessLog(ArgumentsDTO argumentsDTO) {
        String accessLogPath = argumentsDTO.getAccessLogPath();
        List<String> accessLogList = new ArrayList<>();
        LocalDateTime startDate = this.convertStringToLocalDate(argumentsDTO.getStartDate(), START_DATE_FORMAT);
        LocalDateTime endDate = this.getEndDate(startDate, argumentsDTO.getDuration());
        try (BufferedReader bufferedReaderFiles = Files.newBufferedReader(Paths.get(accessLogPath))) {
            accessLogList = bufferedReaderFiles.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error while reading the access log files!");
            e.printStackTrace();
        }

        AccessLogBetweenDuration accessLogBetweenDuration = new AccessLogBetweenDuration();

        this.parseToAccessLog(accessLogList, argumentsDTO, startDate, accessLogBetweenDuration, endDate);

        this.findAccessExceedThreshold(accessLogBetweenDuration, argumentsDTO.getThreshold(), startDate, endDate);

    }

    private void parseToAccessLog(List<String> accessLogList, ArgumentsDTO argumentsDTO, LocalDateTime startDate,
                                  AccessLogBetweenDuration accessLogBetweenDuration, LocalDateTime endDate) {
        for (String accessLogAsString : accessLogList) {
            String[] accessLogValues = accessLogAsString.split("\\|");
            String date = accessLogValues[0];
            String ip = accessLogValues[1];
            String request = accessLogValues[2];
            Integer status = Integer.parseInt(accessLogValues[3]);
            String userAgent = accessLogValues[4];
            LocalDateTime localDate = this.convertStringToLocalDate(date, DATE_FORMAT);

            AccessLog accessLog = new AccessLog(localDate, ip, request, status, userAgent);
            this.entityManager.persist(accessLog);
            boolean isAccessInDuration = validateAccessInDuration(argumentsDTO.getDuration(), startDate, argumentsDTO.getThreshold(),
                    accessLog, endDate);
            if (isAccessInDuration) {
                accessLogBetweenDuration.add(accessLog);
            }
        }
    }

    private void findAccessExceedThreshold(AccessLogBetweenDuration accessLogBetweenDuration, Integer threshold, LocalDateTime startDate,
                                           LocalDateTime endDate) {
        for (String ip : accessLogBetweenDuration.getMapIpAccessLogs().keySet()) {
            int accessSize = accessLogBetweenDuration.getMapIpAccessLogs().get(ip).size();
            if (accessSize > threshold) {
                System.out.println(ip + " has " + accessSize + " access.");
                BlockedIp blockedIp =
                        new BlockedIp(ip, "Blocked because has " + accessSize + " access between " + startDate + " and " + endDate);
                this.entityManager.persist(blockedIp);
            }
        }
    }

    private LocalDateTime convertStringToLocalDate(String localDateAsString, String dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDateTime.parse(localDateAsString, dateTimeFormatter);
    }

    public boolean validateAccessInDuration(String duration, LocalDateTime startDate, Integer threshold, AccessLog accessLog,
                                            LocalDateTime endDate) {
        if ((accessLog.getLocalDate().isAfter(startDate) || accessLog.getLocalDate().isEqual(startDate)) &&
                accessLog.getLocalDate().isBefore(endDate)) {
            return true;
        }
        return false;
    }

    private LocalDateTime getEndDate(LocalDateTime startDate, String duration) {
        LocalDateTime endDate = startDate;
        if (HOURLY.equals(duration)) {
            return endDate.plusHours(1);
        } else if (DAILY.equals(duration)) {
            return endDate.plusDays(1);
        }
        throw new RuntimeException("Value of duration is wrong!");
    }


    private class AccessLogBetweenDuration {
        final Map<String, List<AccessLog>> mapIpAccessLogs = new HashMap<>();

        public Map<String, List<AccessLog>> getMapIpAccessLogs() {
            return mapIpAccessLogs;
        }

        public void add(AccessLog accessLog) {
            if (mapIpAccessLogs.containsKey(accessLog.getIp())) {
                mapIpAccessLogs.get(accessLog.getIp()).add(accessLog);
            } else {
                List<AccessLog> accessLogList = new ArrayList<>();
                accessLogList.add(accessLog);
                mapIpAccessLogs.put(accessLog.getIp(), accessLogList);
            }
        }
    }

}
