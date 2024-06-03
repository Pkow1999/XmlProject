package org.galaxy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/***
 * Main class which contains all endpoint methods
 * @author Paweł Kowalczyk
 */
public class Main {

    static XmlFile xmlFile = null;

    /**
     * Endpoint which triggers the process of reading the file and respond with the number of records found in the file
     * @param file a File object with path to XML file
     * @return number of records found in the file, if exception was caught it returns -1
     */
    public static int Endpoint1(File file){
        try {
            xmlFile = new XmlFile(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return -1;
        }
        return xmlFile.getRecordsSize();
    }

    /**
     * Endpoint returning a list of all products in json format
     * @return List of Strings in json format, if there was an error with xmlFile it returns null
     */
    public static List<String> Endpoint2List() {
        if (xmlFile != null)
            return xmlFile.xmlToJsonList();
        return null;
    }

    /**
     * Endpoint returning a list of all products in json format
     * @return json Array of products in json format
     */
    public static String Endpoint2JsonArray() {
        return xmlFile.xmlToJson().toString();
    }

    /**
     * Endpoint returning the product by the given name
     * @param givenName name of a product
     * @return String of product in json format, if given product does not exist or there was an error with xmlFile it returns null
     */
    public static String Endpoint3 (String givenName){
        if(xmlFile == null)
            return null;

        var record = xmlFile.findRecordByName(givenName);
        if(record != null)
            return record.toPrettyString();

        return null;
    }

    /**
     * Main function in which endpoints are called
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Endpoint 1:");
        System.out.println(Endpoint1(new File("Files/test.xml")) + "\n");
        System.out.println("Endpoint 2:");
        System.out.println(Endpoint2List() + "\n");
        System.out.println(Endpoint2JsonArray() + "\n");
        System.out.println("Endpoint 3:");
        System.out.println(Endpoint3("Apple"));
    }
}
