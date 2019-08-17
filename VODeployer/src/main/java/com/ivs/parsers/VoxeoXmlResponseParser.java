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

	/**
	 * Parse the xml response, and build a properties has that holds all the
	 * results. If there is an Aspect XDK XML body (VoiceObjectsXML definition) put
	 * it in the "xdk" property of the map.
	 * 
	 * @param xml The XML response from an Aspect hosted API call
	 * @return A Map of properties, based on the XML structure 
	 * @throws FactoryConfigurationError
	 * @throws XMLStreamException
	 */
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
			default:
				logger.log(Level.WARNING, "event not XMLStreamConstants not handled: " + event);
				break;
			}

		}

		if (xml.indexOf("<commandResult") != -1) {
			int start = xml.indexOf("<VoiceObjectsXML");
			int end = xml.indexOf("</VoiceObjectsXML>") + 18;
			String fullXML = xml.substring(start, end);
			logger.log(Level.FINER, "xdk = " + fullXML);
			ret.put("xdk", fullXML);
		}
		return ret;
	}

}
