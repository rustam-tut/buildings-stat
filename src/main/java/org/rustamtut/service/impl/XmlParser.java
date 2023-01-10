package org.rustamtut.service.impl;

import org.rustamtut.entity.Building;
import org.rustamtut.service.Parser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class XmlParser implements Parser {

    private final File file;
    private List<Building> data;

    private static final Logger logger = Logger.getLogger(XmlParser.class.getName());

    public XmlParser(File file) {
        this.file = file;
    }

    class SaxParserHandler extends DefaultHandler {

        @Override
        public void startDocument() {
            logger.info("Starting XML file parsing");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("item")) {
                Building building = new Building();
                building.setCity(attributes.getValue("city"));
                building.setStreet(attributes.getValue("street"));
                building.setHouse(Integer.parseInt(attributes.getValue("house")));
                building.setFloor(Integer.parseInt(attributes.getValue("floor")));
                data.add(building);
            }
        }

        @Override
        public void endDocument() {
            logger.info("Finishing XML file parsing. Handled " + data.size() + " items.");
        }
    }

    @Override
    public void parse() {
        data = new LinkedList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler();
        SAXParser saxParser;
        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(file, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Building> getData() {
        return data;
    }
}
