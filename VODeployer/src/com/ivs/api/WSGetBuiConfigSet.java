package com.ivs.api;

import java.util.logging.Logger;

import com.voiceobjects.webservices.WSProviderPortType;

/**
 * This class calls the hosted Voxeo redeploy function.
 * 
 * @author ericp
 *
 */
public class WSGetBuiConfigSet extends HostedVoxeo {
	private final Logger logger = Logger.getLogger(this.getClass().getName().split("\\$")[0]);
	private String sessionID, serverRefID, vsn;

	public WSGetBuiConfigSet() {
		super();
	}

	@Override
	public String getResponseText() throws Exception {
		WSProviderPortType port = super.getP().getWSProviderHttpPort();
		boolean fullDetails = true;
		String buiConfigurationSet = port.getBUIConfigurationSet(sessionID, serverRefID, vsn, fullDetails);
		buiConfigurationSet = buiConfigurationSet.replaceAll("\\n", "");

		return buiConfigurationSet;
	}

	/**
	 * 
	 * @param sessionID
	 * @param serverRefID
	 * @param vsn
	 * @return
	 * @throws Exception
	 */
	public String execute(String sessionID, String serverRefID, String vsn) throws Exception {
		return execute(sessionID, serverRefID, vsn);
	}
}
