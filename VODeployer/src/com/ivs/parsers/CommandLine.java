package com.ivs.parsers;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLine {
	/**
	 * 
	 * parse command lines using
	 * http://commons.apache.org/proper/commons-cli/usage.html
	 * 
	 * Use cases 1.
	 */
	public static void main(String[] args) {
		Options options = new Options();
		options.addOption("help", "print this message");
		options.addOption("u", false, "account username (or use ASPECT_UID env)");
		options.addOption("p", false, "account password (or use ASPECT_PW env)");
		options.addOption("sid", false, "account session ID (or use ASPECT_SESSID env)");
		String sessionID = System.getenv("ASPECT_SESSID");

		CommandLineParser parser = new DefaultParser();
		boolean doHelp = false;

		try {
			org.apache.commons.cli.CommandLine cmd = parser.parse(options, args);
			if (cmd.hasOption("help"))
				doHelp = true;
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
			doHelp = true;
		}

		if (doHelp) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("vodeployer", options);
			return;
		}
		System.out.println("done");
	}

}
