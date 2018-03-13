package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeployService {

	public String execute(String sessionID, String sourceFile, String serverRefId) throws Exception {
		Path path = Paths.get(sourceFile);
		String serviceDef = new String(Files.readAllBytes(path));

		com.ivs.api.WSDeployService gs = new com.ivs.api.WSDeployService();
		gs.execute(sessionID, serviceDef, serverRefId);
		return gs.getExResult().equals("0")?"SUCCESS":"FAILURE";
	}

}
