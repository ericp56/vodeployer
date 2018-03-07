package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class ImportProject {

	private final static Logger logger = Logger.getLogger("com.ivs.command.CreateProject");

	public void execute(String sessionID, String sourceFile, String projectName, String projectVersion)
			throws Exception {
		Path path = Paths.get(sourceFile);
		String projectDef = new String(Files.readAllBytes(path));
		logger.finest("project source=" + projectDef);

		com.ivs.api.WSImportProject gs = new com.ivs.api.WSImportProject();
		gs.execute(sessionID, projectDef, projectName, projectVersion);
	}

}
