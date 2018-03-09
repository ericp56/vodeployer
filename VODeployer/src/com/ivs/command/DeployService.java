package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeployService {

	public void execute(String sessionID, String sourceFile) throws Exception {
		Path path = Paths.get(sourceFile);
		String serviceDef = new String(Files.readAllBytes(path));

		com.ivs.api.WSDeployService gs = new com.ivs.api.WSDeployService();
		gs.execute(sessionID, serviceDef);
	}

}
