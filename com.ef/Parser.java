package com.ef;

import org.apache.commons.cli.*;

/**
 * Created by leonardo on 22/07/18.
 *
 * @since
 */
public class Parser {

    public static void main(String[] args) {
        int argsLength = args.length;
        if (argsLength == 0) {
            System.out.println("Parser of logs\n" +
                    "To use this program, provide\n" +
                    "--accesslog Path to the accesslog file\n" +
                    "--startDate Start date in format \"yyyy-MM-dd.HH:mm:ss\"\n" +
                    "--duration Duration to search access. Can be \"hourly\" or \"daily\"\n" +
                    "--threshold Threshold to define IP that deserve to be blocked");
        }
        // Check the size of args
        if (argsLength < 3 || argsLength > 4) {
            System.out.println("Number of arguments is wrong. Please, try to run without arguments to see which arguments are needed.");
        }

        // Load the log file to MySQL

        // Run a query to find any IPs, based on the duration and threshold

        // Print the blocked IPs to console

        // Load another MySQL table with blocked IPs and the comments about why they are blocked

    }


}
