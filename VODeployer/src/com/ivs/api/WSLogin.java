package com.ivs.api;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ivs.api.model.LoginResponse;
import com.ivs.parsers.VoxeoXmlResponseParser;

/**
 * This class calls the hosted Voxeo login function.
 * 
 * @author ericp
 *
 */
public class WSLogin extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);

	private String user, siteId, pw, lang;

	public WSLogin() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		return super.getP().getWSProviderHttpPort().logIn(user, siteId, pw, lang);
	}

	public LoginResponse login(String username, String password) {
		LoginResponse lr = new LoginResponse();

		try {
			this.user = username;
			this.pw = password;
			this.siteId = "";
			this.lang = "en-us";

			String response;
			try {
				response = getResponseText();
			} catch (Exception e) {
				response = "<error>" + e.getLocalizedMessage() + "</error>";
			}

			VoxeoXmlResponseParser par = new VoxeoXmlResponseParser();
			HashMap<String, String> wsResponse = par.parseVoxeoXml(response);
			if (wsResponse.containsKey("error")) {
				lr.setSuccess(false);
				lr.setMessage(wsResponse.get("error"));

			} else if (wsResponse.containsKey("root.commandDetails.message")
					&& wsResponse.get("root.commandDetails.message").equals("SUCCESS")) {
				lr.setSuccess(true);
				lr.setSessionId(wsResponse.get("root.commandResult"));
				lr.setSiteID(wsResponse.get("root.creationDetails.siteID"));
			}

		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
			lr.setSuccess(false);
			lr.setMessage(ex.getLocalizedMessage());
		}
		return lr;
	}

}
