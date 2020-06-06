package com.hr.xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlReader {

	public static void main(String[] args) {
	    try {
	    	SAXParserFactory saxFactory = SAXParserFactory.newInstance();

	    	// validate against XSD    	
	    	SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    	Schema schema = schemaFactory.newSchema(new File("src/main/resources/schema.xsd"));
	    	saxFactory.setSchema(schema);
	    	
	    	SAXParser saxParser = saxFactory.newSAXParser();

	    	DefaultHandler handler = new DefaultHandler() {
	    		static final String XML_NODE_NAME = "item";
	    		int contextParamCount = 0;
	    		
	    		@Override
		    	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		    		// System.out.println("Start Element :" + qName);
		    		if (qName.equalsIgnoreCase(XML_NODE_NAME)) {
		    			contextParamCount++;
		    		}
		    	}
	
		    	@Override
		    	public void endElement(String uri, String localName, String qName) throws SAXException {
		    		// System.out.println("End Element :" + qName);
		    	}
		    	
		    	@Override
		    	public void endDocument () throws SAXException {
		    		System.out.println(String.format("Number of %s: %d", XML_NODE_NAME, contextParamCount));
		    	}
		    	
		    	@Override
		    	public void error(SAXParseException e) throws SAXException {
		    		throw e;
		    	}
	         };

	         saxParser.parse("src/main/resources/content-test.xml", handler);
         } catch (Exception e) {
        	 e.printStackTrace();
         }  
	}

}
