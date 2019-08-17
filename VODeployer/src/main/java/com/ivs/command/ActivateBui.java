package com.ivs.command;

import com.ivs.api.WSActivateBui;

public class ActivateBui {

	public String execute(String sessionID, String serviceName, String serverRefId, String serverRefID, String message)
			throws Exception {
		com.ivs.api.WSActivateBui ab = new WSActivateBui();
		ab.execute(sessionID, serverRefID, serviceName, serverRefID, message);
		return ab.getExResult().equals("0")?"SUCCESS":"FAILURE";
	}

}
