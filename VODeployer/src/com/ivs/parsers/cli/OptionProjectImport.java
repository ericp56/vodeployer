package com.ivs.parsers.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;

import com.ivs.command.ImportProject;

public class OptionProjectImport implements CommandLineOption{

	public Option getOption() {
		Builder builder = Option.builder("pi");
		Option opt = builder
				.longOpt("project_import")
				.desc("Import a project using the project_source_xdk_file")
				.numberOfArgs(4)
				.optionalArg(true)
				.argName("project_xdk> <project_id> <project_version> <session_id")
				.build();
		return opt;

	}
	public void process(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];
		String[] values = option.getValues();
		String projectXdk = values[0];
		String projectName = values[1];
		String projectVersion = values.length>2?values[2]:"Version 1.0";
		String sessionId = values.length>3?values[3]:System.getenv("ASPECT_SESSID");


		com.ivs.command.ImportProject is = new ImportProject();
		try {
			is.execute(sessionId, projectXdk, projectName, projectVersion);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}		 

		
	}

}