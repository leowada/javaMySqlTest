package com.ef.arguments;

import org.apache.commons.cli.*;
import org.springframework.stereotype.Component;

@Component
public class ArgumentsParser {

    public ArgumentsDTO parse(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String accessLogPath = cmd.getOptionValue("accesslog");
        String startDateValue = cmd.getOptionValue("startDate");
        String durationValue = cmd.getOptionValue("duration");
        Integer thresholdValue = Integer.valueOf(cmd.getOptionValue("threshold"));

        return new ArgumentsDTO(accessLogPath, startDateValue, durationValue, thresholdValue);
    }
}
