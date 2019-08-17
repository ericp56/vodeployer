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
public class WSSetBuiConfigSet extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID, serverRefID, vsn, configSet;

	public WSSetBuiConfigSet() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		return port.setBUIConfigurationSet(sessionID, serverRefID, vsn, configSet);
	}

	/**
	 * 
	 * @param sessionID
	 * @param serverRefID
	 *            usually VOServer@System
	 * @param vsn
	 *            service name
	 * @param configSet
	 *            source xml
	 * @return 
	 */
	public String execute(String sessionID, String serverRefID, String vsn, String configSet) {

		this.sessionID = sessionID;
		this.configSet = configSet;
		this.serverRefID = serverRefID;
		this.vsn = vsn;

		return prepareResponse(logger);
	}

	public String execute(String sessionID, String serverRefID, String vsn, Path configFileName) throws Exception{
		return execute(sessionID, serverRefID, vsn, new String(Files.readAllBytes(configFileName)));
	}
}
