package com.ef;

import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaMySqlTestApplication {

	public static void main(String[] args) {

		Options options = new Options();

		Option accesslogOption = new Option("a", "accesslog", true, "Path to the access log file.");
		accesslogOption.setRequired(false);
		options.addOption(accesslogOption);

		Option startDateOption = new Option("s", "startDate", true, "Start date to search IPs.");
		startDateOption.setRequired(true);
		options.addOption(startDateOption);

		Option durationOption = new Option("d", "duration", true, "Duration of the period to search IPs. Can be \"hourly\" or \"daily\".");
		durationOption.setRequired(true);
		options.addOption(durationOption);

		Option thresholdOption = new Option("t", "threshold", true, "Threshold to define IP that deserves to be blocked.");
		thresholdOption.setRequired(true);
		options.addOption(thresholdOption);

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

		String accesslogValue = cmd.getOptionValue("accesslog");
		String startDateValue = cmd.getOptionValue("startDate");

		System.out.println(accesslogValue);
		System.out.println(startDateValue);

		SpringApplication.run(JavaMySqlTestApplication.class, args);
	}
}
