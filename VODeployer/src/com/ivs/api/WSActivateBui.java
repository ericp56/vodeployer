package com.ivs.api;

import java.util.logging.Logger;

import com.voiceobjects.webservices.WSProviderPortType;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSActivateBui extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID, serverRefID, vsn, referenceID, message;

	public WSActivateBui() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		return port.activateBUIConfiguration(sessionID, serverRefID, vsn, referenceID, message);
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
	public String execute(String sessionID, String serverRefID, String vsn, String referenceId, String message) {

		this.sessionID = sessionID;
		this.serverRefID = serverRefID;
		this.vsn = vsn;
		this.referenceID = referenceId;
		this.message = message;

		return prepareResponse(logger);
	}

}
