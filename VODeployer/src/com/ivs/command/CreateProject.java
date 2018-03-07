package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class CreateProject {

	private final static Logger logger = Logger.getLogger("com.ivs.command.CreateProject");

	public void execute(String sessionID, String sourceFile) throws Exception {
		Path path = Paths.get(sourceFile);
		String projectDef = new String(Files.readAllBytes(path));
		logger.finest("project source=" + projectDef);

		com.ivs.api.WSCreateProject gs = new com.ivs.api.WSCreateProject();
		gs.execute(sessionID, projectDef);
	}

}
