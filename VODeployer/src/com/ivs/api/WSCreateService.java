package com.ivs.api;

import java.nio.file.Files;
import java.nio.file.Path;
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
	public String createService(String sessionID, String serviceDef) {

		this.sessionID = sessionID;
		this.serviceDef = serviceDef;

		return prepareResponse(logger);
	}

	/**
	 * 
	 * @param sessionID
	 * @param path
	 *            path to service file to load
	 * @return
	 */
	public String createService(String sessionID, Path path) throws Exception {
		return createService(sessionID, new String(Files.readAllBytes(path)));
	}

}
