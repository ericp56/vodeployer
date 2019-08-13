package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.ProjectDeleteObject;

public class OptionProjectDeleteObject implements CommandLineOption{

	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);

	public Option getOption() {

		Builder builder = Option.builder("pdo");
		Option opt = builder
				.longOpt("project_delete_object")
				.desc("Delete an unused object from a project")
				.numberOfArgs(4)
				.optionalArg(true)
				.argName("<project_id> <project_version> <object_ref> <object_type> <session_id")
				.build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String projectName = option.getValue(0);
		String projectVer = getOption(option, 1, "Version 1.0");
		String refId = getOption(option, 2, null);
		String refType = getOption(option, 3, null);

		String sessionId =getOption(option, 4, null);
		if (sessionId == null) {
			logger.log(Level.FINE, "using environment variable ASPECT_SESSID");
			sessionId = System.getenv("ASPECT_SESSID");
		}

		ProjectDeleteObject pd = new ProjectDeleteObject();
		try {
			String result = pd.execute(sessionId, projectName, projectVer, refId, refType);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	private String getOption(Option option, int position, String defaultValue) {
		if (option.getArgs() > position) {
			try {
				return option.getValue(position);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

}