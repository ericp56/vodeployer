package com.ivs.parsers.cli;

import org.apache.commons.cli.Option;

public interface CommandLineOption {

	public Option getOption();

	public void process(org.apache.commons.cli.CommandLine cmd) throws Exception;
	
	public static String serverRefId = "VOServer@System";
}
