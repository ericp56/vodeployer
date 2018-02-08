package com.ivs.api;

import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ivs.parsers.VoxeoXmlResponseParser;
import com.voiceobjects.webservices.WSProvider;

/**
 * This class calls the hosted Voxeo provisioning web services.
 * 
 * @author ericp
 *
 */
public class HostedVoxeo {

	private String wsdl = "https://api.voxeo.com/vo-proxy/VoxeoCXP/md/Services/WSProvider?wsdl";
	private final WSProvider p;

	public HostedVoxeo() {
		p = new WSProvider();
	}

	public HostedVoxeo(String wsdl) throws Exception {
		this.wsdl = wsdl;
		p = new WSProvider(new URL(this.wsdl));
	}
	public String getResponseText() throws Exception {return "";}
	
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

	public WSProvider getP() {
		return p;
	}

	/*
	 * public void getLatestProject(String projectName) {
	 * 
	 * try { String response = p.getWSProviderHttpPort().getProjectDef(sessionID,
	 * projectName);
	 * 
	 * parseResponse(response); } catch (Exception ex) { logError(ex); } }
	 * 
	 * public boolean importProject(String projectFile, String projectName, String
	 * versionName) {
	 * 
	 * try { logInfo("create project"); String projectSource = new
	 * String(Files.readAllBytes(Paths.get(projectFile)));
	 * 
	 * String response = p.getWSProviderHttpPort().importObject(sessionID,
	 * projectName, versionName, false, true, projectSource, false);
	 * 
	 * boolean ret = parseResponse(response);
	 * 
	 * return ret; } catch (Exception ex) { logError(ex); return false; } }
	 * 
	 * public boolean createProject(String projectFile) {
	 * 
	 * try { logInfo("create project"); String projectSource = new
	 * String(Files.readAllBytes(Paths.get(projectFile)));
	 * 
	 * return createProjectWithString(projectSource); } catch (Exception ex) {
	 * logError(ex); return false; } }
	 * 
	 * public boolean createProjectWithString(String projectString) {
	 * 
	 * try { logInfo("create project w string"); String response =
	 * p.getWSProviderHttpPort().createProject(sessionID, projectString, true,
	 * false);
	 * 
	 * boolean ret = parseResponse(response);
	 * 
	 * return ret; } catch (Exception ex) { logError(ex); return false; } }
	 * 
	 * public void publishProject(String projectName, String currentVersion, String
	 * newVersion, boolean createEmpty) { try {
	 * 
	 * String response = p.getWSProviderHttpPort().publishProjectVersion(sessionID,
	 * projectName, currentVersion, newVersion, createEmpty);
	 * 
	 * parseResponse(response); } catch (Exception ex) { logError(ex); } }
	 * 
	 * public void publishProject(String projectDef, boolean overwrite) { try {
	 * 
	 * String response = p.getWSProviderHttpPort().createProject(sessionID,
	 * projectDef, overwrite, false);
	 * 
	 * parseResponse(response); } catch (Exception ex) { logError(ex); } }
	 * 
	 * public void writeAudit(boolean granted, String message) { try {
	 * 
	 * String response = p.getWSProviderHttpPort().writeAuditTrail(sessionID,
	 * message, granted); parseResponse(response); } catch (Exception ex) {
	 * logError(ex); } }
	 * 
	 * public String getServiceDefinition(String vsn) { try { String response =
	 * p.getWSProviderHttpPort().getServiceDef(sessionID, vsn);
	 * parseResponse(response); return response; } catch (Exception ex) {
	 * logError(ex); return ""; } }
	 * 
	 * public void createService(String projectDef, boolean overwrite, String
	 * serviceXml) { try { String response =
	 * p.getWSProviderHttpPort().createService(sessionID, serviceXml, overwrite,
	 * false); parseResponse(response); refreshService(sessionID); } catch
	 * (Exception ex) { logError(ex); } }
	 * 
	 * public void refreshService(String projectDef) {
	 * 
	 * try { String response =
	 * p.getWSProviderHttpPort().reloadServiceList(sessionID, serverRefID);
	 * parseResponse(response); } catch (Exception ex) { logError(ex); } }
	 * 
	 * public void deleteService(String vsn) {
	 * 
	 * try { String response = p.getWSProviderHttpPort().deleteService(sessionID,
	 * vsn); parseResponse(response); } catch (Exception ex) { logError(ex); } }
	 * 
	 * public void deployService(String vsn, String serviceDef, String projectName,
	 * String versionName, String templateName) {
	 * 
	 * try { String response =
	 * p.getWSProviderHttpPort().deployProjectVersion(sessionID, serverRefID, vsn,
	 * serviceDef, false, projectName, versionName, templateName);
	 * parseResponse(response); } catch (Exception ex) { logError(ex); } }
	 * 
	 * public void redeployService(String vsn) {
	 * 
	 * try { String response = p.getWSProviderHttpPort().redeployService(sessionID,
	 * serverRefID, vsn); parseResponse(response); } catch (Exception ex) {
	 * logError(ex); } }
	 * 
	 * public void modifyBUI(String vsn, String config, String referenceID) {
	 * 
	 * try { String response =
	 * p.getWSProviderHttpPort().modifyBUIConfiguration(sessionID, serverRefID, vsn,
	 * referenceID, config); parseResponse(response); } catch (Exception ex) {
	 * logError(ex); } }
	 * 
	 * public void activateBUI(String vsn, String referenceID, String message) {
	 * 
	 * try { String response =
	 * p.getWSProviderHttpPort().activateBUIConfiguration(sessionID, serverRefID,
	 * vsn, referenceID, message); parseResponse(response); } catch (Exception ex) {
	 * logError(ex); } }
	 * 
	 * public void getObject(String projectName, String versionName, String
	 * objectType) {
	 * 
	 * try { String response = p.getWSProviderHttpPort().getObjectList(sessionID,
	 * projectName, versionName, objectType); parseResponse(response); } catch
	 * (Exception ex) { logError(ex); } } public void loadProjectList() {
	 * 
	 * try { String response = p.getWSProviderHttpPort().getObjectList(sessionID,
	 * "", "", "Project"); parseProjectsResponse(response); } catch (Exception ex) {
	 * logError(ex); } }
	 * 
	 * 
	 */
}
