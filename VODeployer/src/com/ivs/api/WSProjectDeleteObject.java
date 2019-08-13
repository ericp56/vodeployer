package com.ivs.api;

import java.util.logging.Logger;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSProjectDeleteObject extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);

	private String sessionID, projectName, versionName, refId, refType;

	public WSProjectDeleteObject() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		String response = "";
		try {
			response = super.getP().getWSProviderHttpPort().deleteObject(sessionID, projectName, versionName, refId,
					refType);
			response = response.replaceAll("\\n", "");
			logger.finer("Raw response: " + response);
		} catch (Exception e) {
			response = "<error>" + e.getLocalizedMessage() + "</error>";
		}
		return response;
	}

	public String execute(String sessionID, String projectName, String versionName, String refId, String refType) {

		this.sessionID = sessionID;
		this.projectName = projectName;
		this.versionName = versionName;
		this.refId = refId;
		this.refType = refType;

		return prepareResponse(logger);

	}

}
