package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

public class OptionServiceStart implements CommandLineOption {

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionServiceStart");

	public Option getOption() {
		Builder builder = Option.builder("ss");
		Option opt = builder.longOpt("service_start").desc("Start a service.").numberOfArgs(2).optionalArg(true)
				.argName("vsn> <session_id").build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) throws Exception {
		Option option = cmd.getOptions()[0];

		String serviceName = option.getValue(0);

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

		com.ivs.command.StartService gs = new com.ivs.command.StartService();
		String result = gs.execute(sessionId, serviceName, CommandLineOption.serverRefId);
		System.out.println(result);
	}

}