package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

public class OptionBuiSet implements CommandLineOption {

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionProjectCreate");

	public Option getOption() {

		Builder builder = Option.builder("bs");
		Option opt = builder.longOpt("bui_set").desc("Set the BUI configuration set using the bui_config_file")
				.numberOfArgs(3).optionalArg(true).argName("bui_config> <service_name> <session_id").build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String project_xdk = option.getValue(0);
		String vsn = option.getValue(1);

		String sessionId = null;
		if (option.getArgs() > 2) {
			try {
				sessionId = option.getValue(2);
			} catch (Exception e) {
			}
		}
		if (sessionId == null) {
			logger.log(Level.FINE, "using environment variable ASPECT_SESSID");
			sessionId = System.getenv("ASPECT_SESSID");
		}

		com.ivs.command.SetBui pi = new com.ivs.command.SetBui();
		try {
			pi.execute(sessionId, CommandLineOption.serverRefId, vsn, project_xdk);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}