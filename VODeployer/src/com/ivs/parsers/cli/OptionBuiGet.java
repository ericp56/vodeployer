package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.GetBui;

public class OptionBuiGet implements CommandLineOption {

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionProjectGet");

	public Option getOption() {

		Builder builder = Option.builder("bg");
		Option opt = builder.longOpt("bui_get").desc("Get a BUI configuration set and save it to file_path")
				.numberOfArgs(3).optionalArg(true).argName("file_path> <project_id> <session_id").build();
		return opt;

	}

	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String destination = option.getValue(0);
		String projectName = option.getValue(1);

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

		com.ivs.command.GetBui gb = new GetBui();
		try {
			gb.execute(sessionId, CommandLineOption.serverRefId, projectName, destination);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}