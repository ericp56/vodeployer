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
public class WSCreateProject extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID;
	private String projectSource;

	public WSCreateProject() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		return port.createProject(sessionID, projectSource, true, false);
	}

	/**
	 * 
	 * @param sessionID
	 * @param fileName
	 *            used if not null, not empty, to load a XDK project definition file
	 * @param projectSource
	 *            not used if fileName file is loaded and used.
	 * @return
	 */
	public String createProject(String sessionID, String fileName, String projectSource) {

		if (fileName != null && fileName.length() != 0l) {
			try {
				projectSource = new String(Files.readAllBytes(Paths.get(fileName)));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		this.sessionID = sessionID;
		this.projectSource = projectSource;

		return prepareResponse(logger);
	}

}
