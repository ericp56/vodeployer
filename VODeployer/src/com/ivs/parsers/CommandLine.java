package com.ivs.parsers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.ivs.api.WSLogin;
import com.ivs.api.WSRedeploy;
import com.ivs.api.model.LoginResponse;

public class CommandLine {
	private final static Logger logger = Logger.getLogger("com.ivs.parsers.CommandLine");

	private static boolean doHelp = false;

	/**
	 * 
	 * parse command lines using
	 * http://commons.apache.org/proper/commons-cli/usage.html
	 * 
	 * 
	 * Use cases 1.
	 */
	public static void main(String[] args) throws Exception {

		Options options = new Options();
		options.addOption("help", "print this message");
		Option login = Option.builder("li").longOpt("login").desc("Log in to Evolution").numberOfArgs(2)
				.optionalArg(false).argName("username> <password").optionalArg(true).build();
		login.setValueSeparator(' ');
		options.addOption(login);

		Option redeploy = Option.builder("rd").longOpt("redeploy_service").desc("Redeploy the CXP service")
				.numberOfArgs(3).optionalArg(true).argName("service> <session_id> <site_id").build();
		redeploy.setValueSeparator(' ');
		options.addOption(redeploy);

		org.apache.commons.cli.CommandLine cmd = parseCommandLine(args, options);

		if (doHelp) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("vodeployer", options);
			return;
		}

		if (cmd.hasOption("login")) {
			System.out.println(doLogin(cmd));
		} else if (cmd.hasOption("redeploy_service")) {
			System.out.println(doRedeploy(cmd));
		}

	}

	private static String doLogin(org.apache.commons.cli.CommandLine cmd) {
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
			return ("ASPECT_SESSID=" + loginResult.getSessionId()) + System.lineSeparator() + "ASPECT_SITEID="
					+ loginResult.getSiteID();
		} else {
			return (loginResult.getMessage());
		}
	}

	private static String doRedeploy(org.apache.commons.cli.CommandLine cmd) {
		Option option = cmd.getOptions()[0];

		String service = option.getValue(0);

		String sessionId = null;
		if (option.getArgs() > 1) {
			try {
				sessionId = option.getValue(1);
			} catch (Exception e) {
			}
		}
		if (sessionId == null) {
			logger.log(Level.FINE, "using environment variable ASPECT_SESSID");
			sessionId = System.getenv("ASPECT_SESSID");
		}
		
		String serverRefId = "VOServer@System";
		
		WSRedeploy rd = new WSRedeploy();

		String result = rd.redeploy(sessionId, serverRefId, service);
		
		return result;

	}

	private static org.apache.commons.cli.CommandLine parseCommandLine(String[] args, Options options) {
		CommandLineParser parser = new DefaultParser();
		org.apache.commons.cli.CommandLine cmd = null;
		if (args.length == 0) {
			doHelp = true;
		} else {
			try {
				cmd = parser.parse(options, args);
				if (cmd.hasOption("help"))
					doHelp = true;
			} catch (ParseException e) {
				System.out.println(e.getLocalizedMessage());
				doHelp = true;
			}
		}
		if (cmd == null)
			doHelp = true;
		return cmd;
	}

}
