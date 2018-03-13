package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateService {

	public String execute(String sessionID, String sourceFile) throws Exception {
		Path path = Paths.get(sourceFile);
		String serviceDef = new String(Files.readAllBytes(path));

		com.ivs.api.WSCreateService gs = new com.ivs.api.WSCreateService();
		gs.execute(sessionID, serviceDef);
		return gs.getExResult().equals("0")?"SUCCESS":"FAILURE";
	}

}
