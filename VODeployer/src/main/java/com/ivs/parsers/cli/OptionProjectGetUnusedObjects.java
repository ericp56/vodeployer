package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.ProjectGetUnusedObjects;

public class OptionProjectGetUnusedObjects implements CommandLineOption{

	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);

	public Option getOption() {

		Builder builder = Option.builder("pgu");
		Option opt = builder
				.longOpt("project_get_unused")
				.desc("Get a project's unused object list and save it to file_path")
				.numberOfArgs(3)
				.optionalArg(true)
				.argName("file_path> <project_id> <project_version> <session_id")
				.build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String destination = option.getValue(0);
		String projectName = option.getValue(1);
		String projectVer = null;
		if (option.getArgs() > 2) {
			try {
				projectVer = option.getValue(2);
			} catch (Exception e) {
			}
		}
		if (projectVer == null) {
			projectVer = "Version 1.0";
		}

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

		ProjectGetUnusedObjects gp = new ProjectGetUnusedObjects();
		try {
			String result = gp.execute(sessionId, projectName, projectVer, destination);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}