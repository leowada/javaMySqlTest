package com.accesslog;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccessLogManager {

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static void loadAccessLog(String accessLogPath) {
        List<String> accessLogList = new ArrayList<>();
        try (BufferedReader bufferedReaderFiles = Files.newBufferedReader(Paths.get(accessLogPath))) {
            accessLogList = bufferedReaderFiles.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error while reading the access log files!");
            e.printStackTrace();
        }

        for (String accessLogAsString : accessLogList) {
            String[] accessLogValues = accessLogAsString.split("|");
            String date = accessLogValues[0];
            String ip = accessLogValues[1];
            String request = accessLogValues[2];
            Integer status = Integer.parseInt(accessLogValues[3]);
            String userAgent = accessLogValues[4];
            LocalDate localDate = convertStringToLocalDate(date);

            AccessLog accessLog = new AccessLog(localDate, ip, request, status, userAgent);
            persist(accessLog);
        }
    }

    private static LocalDate convertStringToLocalDate(String localDateAsString) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(localDateAsString, dateTimeFormatter);
    }

    public static void persist(AccessLog accessLog) {
        //TODO: persistir accessLog
    }
}
