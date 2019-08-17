package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.CreateService;

public class OptionServiceCreate implements CommandLineOption{
	//TODO: don't output SUCCESS if error code is not 0
	//TODO: service create file needs //projectInfo@projectSID and //module@guid of starting object
	//project://dispatcher3/Version 1.0/#[projectSID]:[module guid]_BVO_Application

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionServiceCreate");

	public Option getOption() {
		Builder builder = Option.builder("sc");
		Option opt = builder
				.longOpt("service_create")
				.desc("Create a CXP service using the service_xdk_file")
				.numberOfArgs(3)
				.optionalArg(true)
				.argName("service_xdk> <service> <session_id")
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

		com.ivs.command.CreateService gs = new CreateService();
		try {
			String result = gs.execute(sessionId, service_xdk);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}