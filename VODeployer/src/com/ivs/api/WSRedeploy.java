package com.ivs.api;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ivs.parsers.VoxeoXmlResponseParser;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSRedeploy extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);

	public WSRedeploy() {
		super();
	}

	public String getResponseText(String sessionID, String serverRefID, String vsn) {
		String response = "";
		try {
			response = super.getP().getWSProviderHttpPort().redeployService(sessionID, serverRefID, vsn);
		} catch (Exception e) {
			response = "<error>" + e.getLocalizedMessage() + "</error>";
		}
		return response;
	}

	public String redeploy(String sessionID, String serverRefID, String vsn) {

		try {
			String response = getResponseText(sessionID, serverRefID, vsn);

			VoxeoXmlResponseParser par = new VoxeoXmlResponseParser();
			HashMap<String, String> wsResponse = par.parseVoxeoXml(response);
			if (wsResponse.containsKey("error")) {
				return (wsResponse.get("error"));
			} else if (wsResponse.containsKey("root.commandDetails.message")) {
				return wsResponse.get("root.commandDetails.message");
			} else {
				return response;
			}

		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
			return (ex.getLocalizedMessage());
		}
	}

}
