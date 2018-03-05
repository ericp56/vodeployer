package com.ivs.api;

import java.util.logging.Logger;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class GetProject extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	
	private String sessionID, projectName, versionName;

	public GetProject() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		String response = "";
		try {
			String format = "VoiceObjectsXMLWithoutIDs";
			boolean exportLib = false;
			boolean zipped = false;
			response = super.getP().getWSProviderHttpPort().exportProjectVersion(sessionID, projectName, versionName, format, zipped, exportLib);
			response = response.replaceAll("\\n", "");
			logger.finer("Raw response: " + response);
		} catch (Exception e) {
			response = "<error>" + e.getLocalizedMessage() + "</error>";
		}
		return response;
	}

	public String execute(String sessionID, String projectName) {

		return execute(sessionID, projectName, "Version 1.0");

}

	public String execute(String sessionID, String projectName, String versionName) {

		this.sessionID = sessionID;
		this.projectName = projectName;
		this.versionName = versionName;
				
		return prepareResponse(logger);

}

}
