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
public class WSProjectImport extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID, projectSource, projectName, versionName;

	public WSProjectImport() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		boolean overwrite = true;
		boolean isURI = false;
		boolean generateNewGuids = false;
		return port.importObject(sessionID, projectName, versionName, generateNewGuids , overwrite, projectSource, isURI);
	}

	/**
	 * 
	 * @param sessionID
	 * @param projectSource
	 *            raw source of project.
	 * @return
	 */
	public String execute(String sessionID, String projectSource, String projectName, String versionName) {

		this.sessionID = sessionID;
		this.projectSource = projectSource;
		this.projectName = projectName;
		this.versionName = versionName;

		return prepareResponse(logger);
	}

	/**
	 * 
	 * @param sessionID
	 * @param path
	 *            path to file to load
	 * @return
	 */
	public String execute(String sessionID, Path path, String projectName, String versionName) throws Exception {
		return execute(sessionID, new String(Files.readAllBytes(path)), projectName, versionName);
	}

}
