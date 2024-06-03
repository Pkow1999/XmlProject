package org.galaxy;

/**
 * Class which holds all Product informations with appropriate getters and setters
 *
 * @author Paweł Kowalczyk
 */

public final class Product {
    /**
     * Id of a given Product in XML file
     */
    private int id = -1;
    /**
     * Name of a given Product
     */
    private String Name = null;
    /**
     * Category of a given Product
     */
    private String Category = null;
    /**
     * Identifier of a particular Product
     */
    private String PartNumberNR = null;
    /**
     * Name of the company of a given Product
     */
    private String CompanyName = null;
    /**
     *  Information if a given Product is active
     */
    private boolean Active = false;

    /**
     * Default constructor
     */
    Product(){}
    /**
     * Constructor with given parameters
     * @param id id of a given product in XML file
     * @param Name name of given product
     * @param Category category of a given Product
     * @param PartNumberNr identifier of a particular product
     * @param CompanyName name of the company of a given product
     * @param Active information if a given product is active
     */
    Product(int id, String Name, String Category, String PartNumberNr, String CompanyName, boolean Active){
        this.id = id;
        this.Name = Name;
        this.Category = Category;
        this.PartNumberNR = PartNumberNr;
        this.CompanyName = CompanyName;
        this.Active = Active;
    }

    /**
     * public getter of Product id in XML file
     * @return Product XML id as int
     */
    public int getId() {
        return id;
    }
    /**
     * public setter of Product id in XML file
     * @param id new Product id as int
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * public getter of Product name
     * @return Product name as String
     */
    public String getName() {
        return Name;
    }
    /**
     * public setter of Product name
     * @param name new Product name as String
     */
    public void setName(String name) {
        Name = name;
    }
    /**
     * public getter of Product category
     * @return Product category as String
     */
    public String getCategory() {
        return Category;
    }
    /**
     * public setter of particular Product identifier
     * @param category new particular Product identifier as String
     */
    public void setCategory(String category) {
        Category = category;
    }
    /**
     * public getter of particular Product identifier
     * @return particular Product identifier as String
     */
    public String getPartNumberNR() {
        return PartNumberNR;
    }
    /**
     * public setter of Product name
     * @param partNumberNR new Product name as String
     */
    public void setPartNumberNR(String partNumberNR) {
        PartNumberNR = partNumberNR;
    }
    /**
     * public getter of Product company name
     * @return Product company name as String
     */
    public String getCompanyName() {
        return CompanyName;
    }
    /**
     * public setter of Product company name
     * @param companyName new Product company name as String
     */
    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
    /**
     * public getter of information if a given Product is active
     * @return information if a given Product is active as boolean
     */
    public boolean isActive() {
        return Active;
    }
    /**
     * public setter of information if a given Product is active
     * @param active new information if a given Product is active
     */
    public void setActive(boolean active) {
        Active = active;
    }

    /***
     * Override of toString function so that function will print content of the object instead of its reference
     * @return Object content as String
     */
    @Override
    public String toString() {
        return "Product:: ID="+this.id+" | Name=" + this.Name + " | Category=" + this.Category + " | PartNumberNR ="
                + this.PartNumberNR + " | CompanyName=" + this.CompanyName + " | Active=" + this.Active;
    }
}
