package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateProject {

	public void execute(String sessionID, String sourceFile) throws Exception {
		Path path = Paths.get(sourceFile);
		String projectDef = new String(Files.readAllBytes(path));

		com.ivs.api.WSCreateProject gs = new com.ivs.api.WSCreateProject();
		gs.execute(sessionID, projectDef);
	}

}
