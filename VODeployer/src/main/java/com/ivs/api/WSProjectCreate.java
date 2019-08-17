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
public class WSProjectCreate extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID;
	private String projectSource;

	public WSProjectCreate() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		boolean overwrite = true;
		boolean isURI = false;
		return port.createProject(sessionID, projectSource, overwrite, isURI);
	}

	/**
	 * 
	 * @param sessionID
	 * @param projectSource
	 *            raw source of project.
	 * @return
	 */
	public String execute(String sessionID, String projectSource) {

		this.sessionID = sessionID;
		this.projectSource = projectSource;

		return prepareResponse(logger);
	}

	/**
	 * 
	 * @param sessionID
	 * @param path
	 *            path to file to load
	 * @return
	 */
	public String execute(String sessionID, Path path) throws Exception {
		return execute(sessionID, new String(Files.readAllBytes(path)));
	}

}
