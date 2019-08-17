package com.ivs.command;

public class ProjectDeleteObject {

	public String execute(String sessionID, String projectName, String projectVersion, String refId, String refType)
			throws Exception {
		com.ivs.api.WSProjectDeleteObject gs = new com.ivs.api.WSProjectDeleteObject();
		gs.execute(sessionID, projectName, projectVersion, refId, refType);
		return gs.getExResult().equals("0") ? "SUCCESS" : "FAILURE";

	}

}
