package com.ivs.command;

public class StartService {

	public String execute(String sessionID, String serviceName, String serverRefId) throws Exception {
		com.ivs.api.WSStartService gs = new com.ivs.api.WSStartService();
		gs.execute(sessionID, serviceName, serverRefId);
		return gs.getExResult().equals("0")?"SUCCESS":"FAILURE";

	}

}
