package com.ef;

import com.accesslog.AccessLog;
import com.accesslog.AccessLogManager;
import com.arguments.Arguments;
import com.arguments.ArgumentsParser;
import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JavaMySqlTestApplication {

	public static void main(String[] args) {

		Options options = new Options();

		addOption(options,"a", "accesslog", true, "Path to the access log file.", false);
		addOption(options,"s", "startDate", true, "Start date to search IPs.", true);
		addOption(options,"d", "duration", true, "Duration of the period to search IPs. Can be \"hourly\" or \"daily\".", true);
		addOption(options,"t", "threshold", true, "Threshold to define IP that deserves to be blocked.", true);

		Arguments arguments = ArgumentsParser.parse(options, args);

		if (arguments.getAccessLogPath() != null) {
			AccessLogManager.loadAccessLog(arguments.getAccessLogPath());
		}

		//TODO: buscar no banco

	}

	private static void addOption(Options options, String opt, String longOpt, boolean hasArg, String description, boolean required) {
		Option accesslogOption = new Option(opt, longOpt, hasArg, description);
		accesslogOption.setRequired(required);
		options.addOption(accesslogOption);
	}

}
