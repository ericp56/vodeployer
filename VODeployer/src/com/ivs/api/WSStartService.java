package com.ivs.api;

import java.util.logging.Logger;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSStartService extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);

	private String sessionID, serviceName, serverRefId;

	public WSStartService() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		String response = "";
		try {
			response = super.getP().getWSProviderHttpPort().startService(sessionID, serverRefId, serviceName);
			response = response.replaceAll("\\n", "");
			logger.finer("Raw response: " + response);
		} catch (Exception e) {
			response = "<error>" + e.getLocalizedMessage() + "</error>";
		}
		return response;
	}

	public String execute(String sessionID, String serviceName, String serverRefId) {

		this.sessionID = sessionID;
		this.serviceName = serviceName;
		this.serverRefId = serverRefId;

		return prepareResponse(logger);

	}

}
