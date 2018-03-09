package com.ivs.command;

public class StartService {

	public void execute(String sessionID, String serviceName) throws Exception {
		com.ivs.api.WSStartService gs = new com.ivs.api.WSStartService();
		gs.execute(sessionID, serviceName);
	}

}
