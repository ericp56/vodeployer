package com.ivs.command;

public class ApplyBui {

	public String execute(String sessionID, String serviceName, String serverRefId) throws Exception {
		com.ivs.api.WSApplyBui gs = new com.ivs.api.WSApplyBui();
		gs.execute(sessionID, serverRefId, serviceName);
		return gs.getExResult().equals("0")?"SUCCESS":"FAILURE";
	}

}
