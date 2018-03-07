package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetService {

	public void execute(String sessionID, String serviceName, String destinationFile) throws Exception {
		com.ivs.api.WSGetService gs = new com.ivs.api.WSGetService();
		gs.execute(sessionID, serviceName);
		if (gs.getExResult().equals("0") && gs.getVdk() != null) {
			Path path = Paths.get(destinationFile);
			Files.write(path, gs.getVdk().getBytes());
		}
	}

}
