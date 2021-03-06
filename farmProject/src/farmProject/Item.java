package farmProject;

/** 
* This class implements items that are stored in the farm
* @author Alec, Christian
*/

public abstract class Item {
    private String type;
    private String description;
    
    /**
     * Creates item object
     * @param typee name of item
     * @param descriptionn items description
     */
    public Item(String typee, String descriptionn){
        type = typee;
        description = descriptionn;
    }
    
    
    /** 
    * Gets the type of item
    * @return type 
    */
    public String getType(){
        return type;
    }
    
    
    /** 
    * Describes the item
    * @return description the items effect
    */
    public String getDescription(){
        return description;
    }
    
    
    /**
    * Print the item and its description
    */ 
    public void printDetails(){
        System.out.println("Item: " + type + ", description: " + description);
    }
    
    
    /**
    * Returns the items benefit
    * @return double
    */ 
    public abstract double getBenefit();
}


