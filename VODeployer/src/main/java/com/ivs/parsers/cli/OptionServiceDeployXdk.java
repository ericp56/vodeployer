package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.DeployService;

public class OptionServiceDeployXdk implements CommandLineOption{

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionServiceDeployXdk");

	public Option getOption() {
		Builder builder = Option.builder("sd");
		Option opt = builder
				.longOpt("service_deploy")
				.desc("Deploy a CXP service using the service_xdk_file")
				.numberOfArgs(2)
				.optionalArg(true)
				.argName("service_xdk> <session_id")
				.build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String service_xdk = option.getValue(0);

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

		com.ivs.command.DeployService gs = new DeployService();
		try {
			String result = gs.execute(sessionId, service_xdk, CommandLineOption.serverRefId);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}