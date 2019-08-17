package com.ivs.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SetBui {

	/**
	 * 
	 * @param sessionID
	 * @param projectName
	 * @param projectVersion
	 * @param sourceFile
	 * @throws Exception
	 */
	public String execute(String sessionID, String serverRefID, String vsn, String configSet) throws Exception {
		Path path = Paths.get(configSet);
		String buiDef = new String(Files.readAllBytes(path));

		com.ivs.api.WSSetBuiConfigSet gp = new com.ivs.api.WSSetBuiConfigSet();
		gp.execute(sessionID, serverRefID, vsn, buiDef);
		return gp.getExResult().equals("0") ? "SUCCESS" : "FAILURE";

	}

}
