package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.GetService;

public class OptionServiceGet implements CommandLineOption{

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionServiceGet");

	public Option getOption() {
		Builder builder = Option.builder("sg");
		Option opt = builder
				.longOpt("service_get")
				.desc("Get a service definition and save it to file_path")
				.numberOfArgs(4)
				.optionalArg(true)
				.argName("file_path> <vsn> <session_id")
				.build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String destination = option.getValue(0);
		String serviceName = option.getValue(1);

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

		com.ivs.command.GetService gs = new GetService();
		try {
			String result = gs.execute(sessionId, serviceName, destination);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}