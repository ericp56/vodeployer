package com.ivs.api;

import java.util.logging.Logger;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSRedeploy extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	
	private String sessionID, serverRefID, vsn;

	public WSRedeploy() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		String response = "";
		try {
			response = super.getP().getWSProviderHttpPort().redeployService(sessionID, serverRefID, vsn);
		} catch (Exception e) {
			response = "<error>" + e.getLocalizedMessage() + "</error>";
		}
		return response;
	}

	public String redeploy(String sessionID, String serverRefID, String vsn) {

			this.sessionID = sessionID;
			this.serverRefID = serverRefID;
			this.vsn = vsn;
					
			return prepareResponse(logger);

	}

}
