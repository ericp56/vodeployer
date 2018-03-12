package com.ivs.api;

import java.util.logging.Logger;

import com.voiceobjects.webservices.WSProviderPortType;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSApplyBui extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID, serverRefID, vsn;

	public WSApplyBui() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		return port.applyBUIConfiguration(sessionID, serverRefID, vsn);
	}

	/**
	 * 
	 * @param sessionID
	 * @param serverRefID
	 * @param vsn
	 * @param referenceId
	 * @param message
	 * @param configSet
	 * @return
	 */
	public String execute(String sessionID, String serverRefID, String vsn) {

		this.sessionID = sessionID;
		this.serverRefID = serverRefID;
		this.vsn = vsn;

		return prepareResponse(logger);
	}

}
