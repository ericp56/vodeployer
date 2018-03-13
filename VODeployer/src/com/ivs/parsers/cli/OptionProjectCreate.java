package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.ImportProject;

public class OptionProjectCreate implements CommandLineOption{

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionProjectCreate");

	public Option getOption() {

		Builder builder = Option.builder("pc");
		Option opt = builder
				.longOpt("project_create")
				.desc("Create a project using the project_xdk_file")
				.numberOfArgs(3)
				.optionalArg(true)
				.argName("project_xdk> <project_id> <session_id")
				.build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String project_xdk = option.getValue(0);
		String projectName = option.getValue(1);
		String projectVersion = option.getValue(2);

		String sessionId = null;
		if (option.getArgs() > 3) {
			try {
				sessionId = option.getValue(3);
			} catch (Exception e) {
			}
		}
		if (sessionId == null) {
			logger.log(Level.FINE, "using environment variable ASPECT_SESSID");
			sessionId = System.getenv("ASPECT_SESSID");
		}

		com.ivs.command.ImportProject pi = new ImportProject();
		try {
			String result = pi.execute(sessionId, project_xdk, projectName, projectVersion);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}