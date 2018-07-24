package com.arguments;

import org.apache.commons.cli.*;

public class ArgumentsParser {

    public static Arguments parse(Options options, String[] args) {
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

        return new Arguments(accessLogPath, startDateValue, durationValue, thresholdValue);
    }
}
