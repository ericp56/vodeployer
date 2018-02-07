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

	public WSLogin() {
		super();
	}

	public String getResponseText(String user, String siteId, String pw, String lang) {
		return super.getP().getWSProviderHttpPort().logIn(user, "", pw, lang);
	}
	public LoginResponse login(String username, String password) {
		LoginResponse lr = new LoginResponse();

		try {
			String response = getResponseText(username, "", password, "en-us"); 

			VoxeoXmlResponseParser par = new VoxeoXmlResponseParser();
			HashMap<String, String> wsResponse = par.parseVoxeoXml(response);
			if (wsResponse.containsKey("Envelope.Body.Fault.faultstring")) {

			} else if (wsResponse.containsKey("Envelope.Body.logInResponse.out")) {
				HashMap<String, String> results = par.parseVoxeoXml(wsResponse.get("Envelope.Body.logInResponse.out"));
				lr.setSuccess(true);
				lr.setSessionId(results.get("root.commandResult"));
			}

		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
			lr.setMessage(ex.getLocalizedMessage());
		}
		return lr;
	}

}
