package com.ivs.parsers.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionHelp implements CommandLineOption {
	private Options options;

	public OptionHelp(Options options) {
		this.options = options;
	}

	public Option getOption() {
		Option help = Option.builder("help").longOpt("help").desc("print this help message").build();
		help.setValueSeparator(' ');
		return help;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("vodeployer", options);
	}

}
