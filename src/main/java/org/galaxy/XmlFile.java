package org.galaxy;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which handles file inputs, initialize parser and object mapper.
 * It also stores methods are used in endpoints for acceptance criteria.
 * For handling json files it is using Jackson Library
 * @author Paweł Kowalczyk
 */
public final class XmlFile {
    private final XmlHandler xmlHandler;
    private final File XmlFile;
    private final ObjectMapper objectMapper;

    /**
     * Constructor in which parser and object mapper is initialized.
     * @param file local XML file
     * @throws ParserConfigurationException if a parser cannot be created which satisfies the requested configuration
     * @throws SAXException if SAX error occurs
     * @throws IOException when file does not exist
     */
    XmlFile(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XmlFile = file;
        xmlHandler = new XmlHandler();
        saxParser.parse(file, xmlHandler);
        objectMapper = new ObjectMapper();
    }

    /**
     * Method which converts Product List into Json Array
     * @return JsonNode with array of Products in Json format
     */
    public JsonNode xmlToJson() {
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for(Product product : xmlHandler.getProductsList())
        {
            arrayNode.addPOJO(product);
        }
        return arrayNode;
    }

    /**
     * Method which convert Product List into List of String
     * @return List of String which contains Products in Json format
     */
    public List<String> xmlToJsonList() {
        ObjectNode node = objectMapper.createObjectNode();
        List<String> listOfProductsInJson = new ArrayList<>();
        for(Product product : xmlHandler.getProductsList())
        {
            listOfProductsInJson.add(node.pojoNode(product).toPrettyString());
        }
        return listOfProductsInJson;
    }

    /**
     * Method to find record by name
     * @param name name of a product to search for
     * @return JsonNode with product information in json format or null
     */
    public JsonNode findRecordByName(String name){
        ObjectNode node = objectMapper.createObjectNode();
        Product foundProduct = null;
        for(Product product : xmlHandler.getProductsList())
        {
            if(name.equalsIgnoreCase(product.getName())){
                foundProduct = product;
                break;
            }
        }
        if(foundProduct != null){
            return node.pojoNode(foundProduct);
        }
        else{
            return null;
        }
    }

    /**
     * Method which gets a total number of products in XML file
     * @return number of products in XML file
     */
    public int getRecordsSize(){
        return xmlHandler.getProductsList().size();
    }

}
