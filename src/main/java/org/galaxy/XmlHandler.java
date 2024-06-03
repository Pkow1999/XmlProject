package org.galaxy;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which handles parsing XML file into Java objects.
 * It is based on a Simple API for XML (SAX) parser which is accessing elements from XML file sequentially.
 * @author Paweł Kowalczyk
 */

public final class XmlHandler extends DefaultHandler {
    /**
     * List of all Products in XML file
     */
    private List<Product> ProductsList;
    /**
     * A single Product with information given from XML file
     */
    private Product product = null;
    /**
     * String builder in which all data from XML elements are stored
     */
    private StringBuilder data = null;

    boolean bName = false;
    boolean bCategory = false;
    boolean bPartNumber = false;
    boolean bCompanyName = false;
    boolean bActive = false;

    /***
     * Method which returns products
     * @return list of all products
     */
    public List<Product> getProductsList() {
        return ProductsList;
    }

    /**
     * Method which is called at the start of an XML element
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if(qName.equalsIgnoreCase("Product")) {
            product = new Product();
            product.setId(Integer.parseInt(attributes.getValue("id")));
            if(ProductsList == null){
                ProductsList = new ArrayList<>();
            }
        }
        else if(qName.equalsIgnoreCase("Name")){
            bName = true;
        }
        else if(qName.equalsIgnoreCase("category")){
            bCategory = true;
        }
        else if(qName.equalsIgnoreCase("PartNumberNR")){
            bPartNumber = true;
        }
        else if(qName.equalsIgnoreCase("CompanyName")){
            bCompanyName = true;
        }
        else if(qName.equalsIgnoreCase("Active")){
            bActive = true;
        }
        data = new StringBuilder();
    }

    /***
     * Method which is called at the end of an XML element
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if(bName){
            product.setName(data.toString());
            bName = false;
        } else if (bCategory) {
            product.setCategory(data.toString());
            bCategory = false;
        } else if (bPartNumber) {
            product.setPartNumberNR(data.toString());
            bPartNumber = false;
        } else if (bCompanyName) {
            product.setCompanyName(data.toString());
            bCompanyName = false;
        } else if (bActive) {
            product.setActive(Boolean.parseBoolean(data.toString()));
            bActive = false;
        }

        if(qName.equalsIgnoreCase("Product")){
            ProductsList.add(product);
        }
    }

    /***
     * Method which is called with the text content in between XML elements.
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    @Override
    public void characters(char[] ch, int start, int length){
        data.append(new String(ch, start, length));
    }
}
