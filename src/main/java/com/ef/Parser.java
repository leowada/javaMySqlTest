package com.ef;

import com.ef.accesslog.AccessLogManager;
import com.ef.arguments.ArgumentsDTO;
import com.ef.arguments.ArgumentsParser;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Parser {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(Parser.class, args);

		AccessLogManager accessLogManager = (AccessLogManager) applicationContext.getBean("accessLogManager");
		ArgumentsParser argumentsParser = (ArgumentsParser) applicationContext.getBean("argumentsParser");

		Options options = new Options();

		addOption(options,"a", "accesslog", true, "Path to the access log file.", false);
		addOption(options,"s", "startDate", true, "Start date to search IPs.", true);
		addOption(options,"d", "duration", true, "Duration of the period to search IPs. Can be \"hourly\" or \"daily\".", true);
		addOption(options,"t", "threshold", true, "Threshold to define IP that deserves to be blocked.", true);

		ArgumentsDTO argumentsDTO = argumentsParser.parse(options, args);

		if (argumentsDTO.getAccessLogPath() != null) {
			accessLogManager.loadAccessLog(argumentsDTO);
		}

	}

	private static void addOption(Options options, String opt, String longOpt, boolean hasArg, String description, boolean required) {
		Option accesslogOption = new Option(opt, longOpt, hasArg, description);
		accesslogOption.setRequired(required);
		options.addOption(accesslogOption);
	}

}
