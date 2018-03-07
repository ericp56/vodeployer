package com.ivs.api;

import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ivs.parsers.VoxeoXmlResponseParser;
import com.voiceobjects.webservices.WSProvider;

/**
 * This is the base class for calling the Aspect hosted web API and processing
 * the result. Subclasses override the getResponseText() method. The response
 * from the API call can be retrieved from the various properties of this class.
 * 
 * @author ericp
 *
 */
public class HostedVoxeo {

	private String wsdl = "https://api.voxeo.com/vo-proxy/VoxeoCXP/md/Services/WSProvider?wsdl";
	private final WSProvider p;
	private String commandResult;
	private String vdk;
	private String exResult;
	private String exMessage;

	public String getVdk() {
		return vdk;
	}

	public void setVdk(String vdk) {
		this.vdk = vdk;
	}

	public String getCommandResult() {
		return commandResult;
	}

	public void setCommandResult(String commandResult) {
		this.commandResult = commandResult;
	}

	public String getExResult() {
		return exResult;
	}

	public void setExResult(String exResult) {
		this.exResult = exResult;
	}

	public String getExMessage() {
		return exMessage;
	}

	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}

	public HostedVoxeo() {
		p = new WSProvider();
	}

	public HostedVoxeo(String wsdl) throws Exception {
		this.wsdl = wsdl;
		p = new WSProvider(new URL(this.wsdl));
	}

	/**
	 * Subclasses override this method, to perform the actual API call.
	 * 
	 * @return The raw response text from the API call.
	 * @throws Exception
	 */
	public String getResponseText() throws Exception {
		return "";
	}

	/**
	 * This will call {@link getResponseText()} and parse the results. It will
	 * provide a simple String response while populating the properties of this
	 * class with more details.
	 * 
	 * @param logger
	 * @return
	 */
	protected String prepareResponse(Logger logger) {
		String response;
		try {
			response = getResponseText();
		} catch (Exception e) {
			response = "<error>" + e.getLocalizedMessage() + "</error>";
		}

		try {
			VoxeoXmlResponseParser par = new VoxeoXmlResponseParser();
			HashMap<String, String> wsResponse = par.parseVoxeoXml(response);

			if (wsResponse.containsKey("root.commandResult")) {
				commandResult = wsResponse.get("root.commandResult");
			}
			if (wsResponse.containsKey("xdk")) {
				vdk = wsResponse.get("xdk");
			}

			if (wsResponse.containsKey("error")) {
				return (wsResponse.get("error"));
			} else if (wsResponse.containsKey("root.commandDetails.message")) {
				exMessage = wsResponse.get("root.commandDetails.message");
				exResult = wsResponse.get("root.commandDetails.executionResult");
				return wsResponse.get("root.commandDetails.message");
			} else {
				return response;
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
			return (ex.getLocalizedMessage());
		}

	}

	public WSProvider getP() {
		return p;
	}
}
