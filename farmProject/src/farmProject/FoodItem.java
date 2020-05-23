package farmProject;


/** 
* This class extends the Item class and is used when feeding animals
* @author Alec, Christian
*/

public class FoodItem extends Item{
    private Double benefit = 0.0;
    
    /**
     * Food item that extends item class
     * @param typee name of item
     * @param descriptionn items description
     * @param benefitt the benefit the food item holds
     */
    public FoodItem(String typee, String descriptionn, Double benefitt){
        super(typee, descriptionn);
        benefit = benefitt;
    	
    
    /**
    * Returns the items benefit
    * @return double
    */    
    }
    public double getBenefit(){
        return benefit;
    }
    
    
    @Override
    public String toString() {
      return super.getType();
    }
}