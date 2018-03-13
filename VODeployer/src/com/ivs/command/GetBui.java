package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetBui {

	public String execute(String sessionID, String serverRefID, String vsn, String destinationFile)
			throws Exception {
		com.ivs.api.WSGetBuiConfigSet gp = new com.ivs.api.WSGetBuiConfigSet();
		gp.execute(sessionID, serverRefID, vsn);
		if (gp.getExResult().equals("0") && gp.getVdk() != null) {
			Path path = Paths.get(destinationFile);
			Files.write(path, gp.getVdk().getBytes());
		}
		return gp.getExResult().equals("0")?"SUCCESS":"FAILURE";

	}

}
