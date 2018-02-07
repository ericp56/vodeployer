package com.ivs.parsers;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class VoxeoXmlResponseParser {

	private final Logger logger;

	public VoxeoXmlResponseParser() {
		logger = Logger.getLogger(this.getClass().getCanonicalName());
	}

	public static void main(String[] args) throws Exception {
		String voxeoResponse = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns1:logInResponse xmlns:ns1=\"http://webservices.voiceobjects.com\"><out><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><creationDetails><user>SSA_Heartland_DVARU</user><role>siteAdministrator</role><counter>83066</counter><timestamp>2018-02-06T22:27:17+0000</timestamp><serverVersion>10.0.0.1947</serverVersion><serverPatchVersion>GA</serverPatchVersion><encoding>UTF-8</encoding><metadataVersion>9.4</metadataVersion><repositoryName>VoiceObjects</repositoryName><repositoryID>Default Metadata Repository (20111014175735)</repositoryID><siteID>46759</siteID></creationDetails><commandDetails><command>authenticateUser</command><executionResult>0</executionResult><message>SUCCESS</message></commandDetails><commandResult>OVAP00006a2afc88406d47b13844000001616d2a3c94</commandResult></root>]]></out></ns1:logInResponse></soap:Body></soap:Envelope>";
		VoxeoXmlResponseParser p = new VoxeoXmlResponseParser();
		HashMap<String, String> results = p.parseVoxeoXml(voxeoResponse);
		if (results.containsKey("Envelope.Body.logInResponse.out")) {
			HashMap<String, String> results2 = p.parseVoxeoXml(results.get("Envelope.Body.logInResponse.out"));
			if (results2.containsKey("root.commandDetails.message")) {
				String sessionid = results2.get("root.commandResult");
				System.out.println(sessionid);
			}
		}
	}

	public HashMap<String, String> parseVoxeoXml(String xml) throws FactoryConfigurationError, XMLStreamException {
		HashMap<String, String> ret = new HashMap<>();
		StringBuilder content = null;
		List<String> parents = new ArrayList<>();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new ByteArrayInputStream(xml.getBytes()));

		while (reader.hasNext()) {
			int event = reader.next();

			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				content = new StringBuilder();
				if (!"config".equalsIgnoreCase(reader.getLocalName())) {
					parents.add(reader.getLocalName());
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				if (content != null) {
					content.append(reader.getText().trim());
				}
				break;

			case XMLStreamConstants.END_ELEMENT:
				String elementName = "";
				for (String el : parents) {
					elementName = elementName + el + ".";
				}
				if (elementName.length() > 1)
					elementName = elementName.substring(0, elementName.length() - 1);
				if (content != null) {
					ret.put(elementName, content.toString());
					logger.log(Level.FINER, elementName + " = " + content.toString());
				}
				parents.remove(reader.getLocalName());
				content = null;
				break;

			case XMLStreamConstants.START_DOCUMENT:
				break;
			}

		}
		return ret;
	}

}
