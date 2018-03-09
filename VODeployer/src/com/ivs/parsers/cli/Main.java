package com.ivs.parsers.cli;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
	private static boolean doHelp = false;

	public static void main(String[] args) throws Exception {

		// apache command line options
		Options options = new Options();

		// local array of option helper classes
		CommandLineOption[] clOptions = {
				new OptionHelp(options), 
				new OptionLogin(), 
				new OptionProjectGet(), 
				new OptionProjectCreate(),
				new OptionProjectImport(), 
				new OptionServiceGet(), 
				new OptionServiceCreate(),
				new OptionServiceRedeploy() 
		};

		// a map of the helper classes, to make usage easy
		Map<String, CommandLineOption> clMap = new HashMap<String, CommandLineOption>();

		// load up the options
		for (CommandLineOption clo : clOptions) {
			options.addOption(clo.getOption());
			clMap.put(clo.getOption().getLongOpt(), clo);
		}

		// parse the command line arguments
		org.apache.commons.cli.CommandLine cmd = parseCommandLine(args, options);

		String longOpt = null;

		if (cmd != null && cmd.getOptions() != null && cmd.getOptions().length > 0) {
			longOpt = cmd.getOptions()[0].getLongOpt();
		}

		if (doHelp) {
			longOpt = "help";
		}

		if (longOpt != null) {
			CommandLineOption commandLineOption = clMap.get(longOpt);
			commandLineOption.process(cmd);
		}

	}

	private static org.apache.commons.cli.CommandLine parseCommandLine(String[] args, Options options) {
		CommandLineParser parser = new DefaultParser();
		org.apache.commons.cli.CommandLine cmd = null;
		if (args.length == 0) {
			doHelp = true;
		} else {
			try {
				cmd = parser.parse(options, args);
				if (cmd.hasOption("help"))
					doHelp = true;
			} catch (ParseException e) {
				System.out.println(e.getLocalizedMessage());
				doHelp = true;
			}
		}
		if (cmd == null)
			doHelp = true;
		return cmd;
	}

}
