package com.ivs.api;

import java.util.logging.Logger;

import com.voiceobjects.webservices.WSProviderPortType;

/**
 * This class calls the hosted Voxeo deploy function.
 * 
 * @author ericp
 *
 */
public class WSDeployService extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID;
	private String serviceDef;
	private String serverRefId;

	public WSDeployService() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		boolean isURI = false;
		return port.deployXDKService(sessionID, serverRefId, serviceDef, isURI);
	}

	/**
	 * 
	 * @param sessionID
	 * @param serviceDef
	 *            raw source of service definition.
	 * @return
	 */
	public String execute(String sessionID, String serviceDef, String serverRefId) {

		this.sessionID = sessionID;
		this.serviceDef = serviceDef;
		this.serverRefId = serverRefId;
		

		return prepareResponse(logger);
	}

}
