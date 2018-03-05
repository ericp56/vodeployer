package com.ivs.api;

import java.util.logging.Logger;

import com.voiceobjects.webservices.WSProviderPortType;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSCreateService extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID;
	private String serviceDef;

	public WSCreateService() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		boolean overwrite = true;
		boolean isURI = false;
		return port.createService(sessionID, serviceDef, overwrite, isURI);
	}

	/**
	 * 
	 * @param sessionID
	 * @param serviceDef
	 *            raw source of service definition.
	 * @return
	 */
	public String execute(String sessionID, String serviceDef) {

		this.sessionID = sessionID;
		this.serviceDef = serviceDef;

		return prepareResponse(logger);
	}

}
