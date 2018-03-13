package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

public class OptionBuiApply implements CommandLineOption {

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionProjectCreate");

	public Option getOption() {

		Builder builder = Option.builder("bap");
		Option opt = builder.longOpt("bui_apply").desc("Apply the BUI configuration for runtime usage.").numberOfArgs(2)
				.optionalArg(true).argName("service_name> <session_id").build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String vsn = option.getValue(0);

		String sessionId = null;
		if (option.getArgs() > 1) {
			try {
				sessionId = option.getValue(1);
			} catch (Exception e) {
			}
		}
		if (sessionId == null) {
			logger.log(Level.FINE, "using environment variable ASPECT_SESSID");
			sessionId = System.getenv("ASPECT_SESSID");
		}

		com.ivs.command.ApplyBui ai = new com.ivs.command.ApplyBui();
		try {
			String result = ai.execute(sessionId, vsn, CommandLineOption.serverRefId);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}