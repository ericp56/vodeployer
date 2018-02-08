package com.ivs.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.voiceobjects.webservices.WSProviderPortType;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSUpdateBui extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID, serverRefID, vsn, configSet;

	public WSUpdateBui() {
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
	 * @param serverRefID usually VOServer@System
	 * @param vsn service name
	 * @param configFileName
	 *            used if not null, not empty, to load a XDK BUI definition file
	 * @param configSet
	 *            not used if configFileName file is loaded and used.
	 * @return
	 */
	public String createProject(String sessionID, String serverRefID, String vsn, String configFileName,
			String configSet) {

		if (configFileName != null && configFileName.length() != 0l) {
			try {
				configSet = new String(Files.readAllBytes(Paths.get(configFileName)));
			} catch (Exception e) {
			}
		}

		this.sessionID = sessionID;
		this.configSet = configSet;
		this.serverRefID = serverRefID;
		this.vsn = vsn;

		return prepareResponse(logger);
	}

}
