package org.galaxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class which contains test cases for XmlProject
 * @author Paweł Kowalczyk
 */
final class MainTest {
    /***
     * Checks if method getRecordSize returns appropriate number of records
     */
    @Test
    void getRecordSizeTest() {
        XmlFile xmlFile = null;
        try {
            xmlFile = new XmlFile(new File("Files/test.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        assert xmlFile != null;
        assertEquals(3, xmlFile.getRecordsSize());
    }
    /***
     * Checks if method xmlToJson returns appropriate data in Json format
     */
    @Test
    void xmlToJsonTest() {
        XmlFile xmlFile = null;
        try {
            xmlFile = new XmlFile(new File("Files/test.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        String example = "[ {\n" +
                "  \"id\" : 1,\n" +
                "  \"name\" : \"apple\",\n" +
                "  \"active\" : true,\n" +
                "  \"partNumberNR\" : \"2303-E1A-G-M-W209B-VM\",\n" +
                "  \"category\" : \"fruit\",\n" +
                "  \"companyName\" : \"FruitsAll\"\n" +
                "}, {\n" +
                "  \"id\" : 2,\n" +
                "  \"name\" : \"orange\",\n" +
                "  \"active\" : false,\n" +
                "  \"partNumberNR\" : \"5603-J1A-G-M-W982F-PO\",\n" +
                "  \"category\" : \"fruit\",\n" +
                "  \"companyName\" : \"FruitsAll\"\n" +
                "}, {\n" +
                "  \"id\" : 3,\n" +
                "  \"name\" : \"glass\",\n" +
                "  \"active\" : true,\n" +
                "  \"partNumberNR\" : \"9999-E7R-Q-M-K287B-YH\",\n" +
                "  \"category\" : \"dish\",\n" +
                "  \"companyName\" : \"HomeHome\"\n" +
                "} ]";
        ObjectMapper mapper = new ObjectMapper();
        try {
            assert xmlFile != null;
            assertEquals(mapper.readTree(example), mapper.readTree(xmlFile.xmlToJson().toString()));
        } catch (JsonProcessingException e) {
           e.printStackTrace();
        }
    }

    /***
     * Checks if method xmlToJsonList returns appropriate data in Json format
     */
    @Test
    void xmlToJsonListTest() {
        XmlFile xmlFile = null;
        try {
            xmlFile = new XmlFile(new File("Files/test.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        assert xmlFile != null;
        ObjectMapper mapper = new ObjectMapper();

        List<String> exampleList = new ArrayList<>();
        exampleList.add("{\n" +
                "  \"id\" : 1,\n" +
                "  \"name\" : \"apple\",\n" +
                "  \"active\" : true,\n" +
                "  \"category\" : \"fruit\",\n" +
                "  \"partNumberNR\" : \"2303-E1A-G-M-W209B-VM\",\n" +
                "  \"companyName\" : \"FruitsAll\"\n" +
                "}");
        exampleList.add("{\n" +
                "  \"id\" : 2,\n" +
                "  \"name\" : \"orange\",\n" +
                "  \"active\" : false,\n" +
                "  \"partNumberNR\" : \"5603-J1A-G-M-W982F-PO\",\n" +
                "  \"category\" : \"fruit\",\n" +
                "  \"companyName\" : \"FruitsAll\"\n" +
                "}");
        exampleList.add("{\n" +
                "  \"id\" : 3,\n" +
                "  \"name\" : \"glass\",\n" +
                "  \"active\" : true,\n" +
                "  \"partNumberNR\" : \"9999-E7R-Q-M-K287B-YH\",\n" +
                "  \"category\" : \"dish\",\n" +
                "  \"companyName\" : \"HomeHome\"\n" +
                "}");

        List<String> xmlJsonList = xmlFile.xmlToJsonList();

        Iterator<String> xmlJsonIterator = xmlJsonList.iterator();
        Iterator<String> exampleIterator = exampleList.iterator();

        while (xmlJsonIterator.hasNext() && exampleIterator.hasNext()){
            try {
                assertEquals(mapper.readTree(exampleIterator.next()), mapper.readTree(xmlJsonIterator.next()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check if method FindRecordByName returns null when record does not exist
     */
    @Test
    void recordDoesNotExistTest(){
        XmlFile xmlFile = null;
        try {
            xmlFile = new XmlFile(new File("Files/test.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        assert xmlFile != null;
        assertNull(xmlFile.findRecordByName("Banana"));
    }

    /**
     * Checks if method findRecordByName works as intended
     */
    @Test
    void recordDoesExistTest(){
        XmlFile xmlFile = null;
        try {
            xmlFile = new XmlFile(new File("Files/test.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();

        String example = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"name\" : \"apple\",\n" +
                "  \"active\" : true,\n" +
                "  \"category\" : \"fruit\",\n" +
                "  \"partNumberNR\" : \"2303-E1A-G-M-W209B-VM\",\n" +
                "  \"companyName\" : \"FruitsAll\"\n" +
                "}";

        try {
            assert xmlFile != null;
            assertEquals(mapper.readTree(example), mapper.readTree(xmlFile.findRecordByName("ApPle").toString()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if constructor throws an exception when file does not exist
     */
    @Test
    void fileDoesNotExistIOExceptionTest() {
        assertThrows(IOException.class, ()->{
            var xmlFile = new XmlFile(new File("RandomFile.xml"));
        });
    }

    /**
     * Checks if constructor throws an exception when an argument is null
     */
    @Test
    void fileIsNullTest(){
        assertThrows(IllegalArgumentException.class, ()->{
            var xmlFile = new XmlFile(null);
        });
    }

    /**
     * Checks if constructor throws an exception when data inside file are not in xml format
     */
    @Test
    void fileIsNotXMLTest() {
        assertThrows(SAXParseException.class, ()->{
           var xmlFile = new XmlFile(new File("Files/testBad.json"));
        });
    }
}