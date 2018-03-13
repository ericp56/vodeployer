package com.ivs.command;

public class ApplyBui {

	public void execute(String sessionID, String serviceName, String serverRefId) throws Exception {
		com.ivs.api.WSApplyBui gs = new com.ivs.api.WSApplyBui();
		gs.execute(sessionID, serviceName, serverRefId);
	}

}
