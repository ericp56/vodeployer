package com.ivs.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import constants.TestData;

class VoxeoXmlResponseParserTest {

	@Test
	void testSuccessfulLogin() throws Exception{
		String voxeoResponse = TestData.loginSucceed;
		VoxeoXmlResponseParser p = new VoxeoXmlResponseParser();
		HashMap<String, String> results = p.parseVoxeoXml(voxeoResponse);
		HashMap<String, String> results2 = p.parseVoxeoXml(results.get("Envelope.Body.logInResponse.out"));
		String sessionid = results2.get("root.commandResult");
		assertEquals(sessionid, "OVAP00006a2afc88406d47b13844000001616d2a3c94");
	}

	@Test
	void testFailedLogin() throws Exception{
		String voxeoResponse = TestData.loginFail;
		VoxeoXmlResponseParser p = new VoxeoXmlResponseParser();
		HashMap<String, String> results = p.parseVoxeoXml(voxeoResponse);
		assertEquals(results.get("Envelope.Body.Fault.faultstring"), "org.springframework.security.authentication.BadCredentialsException: Bad credentials");
	}

}
