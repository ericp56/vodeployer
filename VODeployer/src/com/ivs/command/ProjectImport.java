package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class ProjectImport {

	private final static Logger logger = Logger.getLogger("com.ivs.command.ProjectImport");

	public String execute(String sessionID, String sourceFile, String projectName, String projectVersion)
			throws Exception {
		Path path = Paths.get(sourceFile);
		String projectDef = new String(Files.readAllBytes(path));
		logger.finest("project source=" + projectDef);

		com.ivs.api.WSProjectImport gs = new com.ivs.api.WSProjectImport();
		gs.execute(sessionID, projectDef, projectName, projectVersion);
		return gs.getExResult().equals("0")?"SUCCESS":"FAILURE";

	}

}
