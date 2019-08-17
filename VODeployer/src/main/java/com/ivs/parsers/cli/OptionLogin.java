package com.ivs.parsers.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import com.ivs.api.WSLogin;
import com.ivs.api.model.LoginResponse;

public class OptionLogin implements CommandLineOption {

	private final static Logger logger = Logger.getLogger("com.ivs.parsers.cli.OptionLogin");

	@Override
	public Option getOption() {
		Option login = Option.builder("li").longOpt("login").desc("Log in to Evolution").numberOfArgs(2)
				.optionalArg(false).argName("username> <password").optionalArg(true).build();
		return login;
	}

	@Override
	public void process(CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String username = null;
		if (option.getArgs() > 0) {
			try {
				username = option.getValue(0);
			} catch (Exception e) {
			}
		}
		if (username == null) {
			logger.log(Level.FINE, "using environment variable ASPECT_UID");
			username = System.getenv("ASPECT_UID");
		}

		String password = null;
		if (option.getArgs() > 1) {
			try {
				password = option.getValue(1);
			} catch (Exception e) {
			}
		}
		if (password == null) {
			logger.log(Level.FINE, "using environment variable ASPECT_PWD");
			password = System.getenv("ASPECT_PWD");
		}
		WSLogin l = new WSLogin();

		LoginResponse loginResult = l.login(username, password);

		if (loginResult.isSuccess()) {
			System.out.println("ASPECT_SESSID=" + loginResult.getSessionId() + System.lineSeparator() + "ASPECT_SITEID="
					+ loginResult.getSiteID());
		} else {
			System.out.println(loginResult.getMessage());
		}

	}

}
