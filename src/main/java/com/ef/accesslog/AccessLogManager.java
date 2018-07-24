package com.ef.accesslog;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class AccessLogManager {

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    @PersistenceContext
    private EntityManager entityManager;

    public void loadAccessLog(String accessLogPath) {
        List<String> accessLogList = new ArrayList<>();
        try (BufferedReader bufferedReaderFiles = Files.newBufferedReader(Paths.get(accessLogPath))) {
            accessLogList = bufferedReaderFiles.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error while reading the access log files!");
            e.printStackTrace();
        }

        for (String accessLogAsString : accessLogList) {
            String[] accessLogValues = accessLogAsString.split("\\|");
            String date = accessLogValues[0];
            String ip = accessLogValues[1];
            String request = accessLogValues[2];
            Integer status = Integer.parseInt(accessLogValues[3]);
            String userAgent = accessLogValues[4];
            LocalDate localDate = this.convertStringToLocalDate(date);

            AccessLog accessLog = new AccessLog(localDate, ip, request, status, userAgent);
            this.persist(accessLog);
        }
    }

    private LocalDate convertStringToLocalDate(String localDateAsString) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(localDateAsString, dateTimeFormatter);
    }

    public void persist(AccessLog accessLog) {
        this.entityManager.persist(accessLog);
    }
}
