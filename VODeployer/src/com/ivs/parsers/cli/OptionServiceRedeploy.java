package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.api.WSRedeploy;

public class OptionServiceRedeploy implements CommandLineOption{

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionServiceRedeploy");

	public Option getOption() {

		Builder builder = Option.builder("sr");
		Option opt = builder
				.longOpt("service_redeploy")
				.desc("Redeploy the CXP service")
				.numberOfArgs(2)
				.optionalArg(true)
				.argName("service> <session_id")
				.build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String service = option.getValue(0);

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

		String serverRefId = "VOServer@System";

		WSRedeploy rd = new WSRedeploy();

		String result = rd.redeploy(sessionId, serverRefId, service);

		System.out.println(result);

	}
}