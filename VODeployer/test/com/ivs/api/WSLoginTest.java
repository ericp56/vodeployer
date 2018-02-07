package com.ivs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ivs.api.model.LoginResponse;

import constants.TestData;

class WSLoginTest {

	@Test
	void testLoginFail() {
		WSLogin l = new WSLogin() {
			public String getResponseText(String user, String siteId, String pw, String lang) {
				return TestData.loginFail;
			}
			
		};
		LoginResponse result = l.login("foo", "bar");
		assertEquals(false, result.isSuccess());		
	}

	@Test
	void testLoginSucceed() {
		WSLogin l = new WSLogin() {
			public String getResponseText(String user, String siteId, String pw, String lang) {
				return TestData.loginSucceed;
			}
			
		};
		LoginResponse result = l.login("foo", "bar");
		assertEquals(true, result.isSuccess());		
	}
}
