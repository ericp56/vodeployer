package com.ivs.api;

import java.util.logging.Logger;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSGetService extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);

	private String sessionID, serviceName;

	public WSGetService() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		String response = "";
		try {
			response = super.getP().getWSProviderHttpPort().getServiceDef(sessionID, serviceName);
			response = response.replaceAll("\\n", "");
			logger.finer("Raw response: " + response);
		} catch (Exception e) {
			response = "<error>" + e.getLocalizedMessage() + "</error>";
		}
		return response;
	}

	public String execute(String sessionID, String serviceName) {

		this.sessionID = sessionID;
		this.serviceName = serviceName;

		return prepareResponse(logger);

	}

}
