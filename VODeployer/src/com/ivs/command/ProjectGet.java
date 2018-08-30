package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectGet {

	public String execute(String sessionID, String projectName, String projectVersion, String destinationFile)
			throws Exception {
		com.ivs.api.WSProjectGet gp = new com.ivs.api.WSProjectGet();
		gp.execute(sessionID, projectName, projectVersion);
		if (gp.getExResult().equals("0") && gp.getVdk() != null) {
			Path path = Paths.get(destinationFile);
			Files.write(path, gp.getVdk().getBytes());
		}
		return gp.getExResult().equals("0")?"SUCCESS":"FAILURE";

	}

}
