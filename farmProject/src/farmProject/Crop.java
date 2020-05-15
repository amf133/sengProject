package farmProject;

/** 
* This class implements crops that are stored in the users farm
* @author Alec, Christian
*/
public class Crop {
    private Double growth = 0.0;
    private double growthRate;
    private Double worth;
    private String type;
    private int quantity;
    
    public Crop(String typee, Double cost, int quantityy) //init method, throws error if incorrect values
    {    
        
        
        if (typee.equals("Carrot")){
            worth = cost;
            growthRate = 0.2; //5 days to grow
            type = typee;
            quantity = quantityy;
        }
        else if (typee.equals("Wheat")){
            worth = cost;
            growthRate = 0.34; //3 days to grow
            type = typee;
            quantity = quantityy;
        }
        else if (typee.equals("Potatoes")){
            worth = cost;
            growthRate = 0.5; //2 days to grow
            type = typee;
            quantity = quantityy;
        }
        else if (typee.equals("Pumpkins")){
            worth = cost;
            growthRate = 0.2; //5 days
            type = typee;
            quantity = quantityy;
        }
        else if (typee.equals("Melon")){
            worth = cost;
            growthRate = 0.25; //4 days to grow
            type = typee;
            quantity = quantityy;
        }
        else if (typee.equals("Beetroot")){
            worth = cost;
            growthRate = 1.0; //grows overnight
            type = typee;
            quantity = quantityy;
        }
        else{
            throw new IllegalArgumentException("Make sure the crop exists!");
        }
    }
    
    /**
    * Returns the type of crop eg. Carrot, Melon etc
    * @return type type of crop.
    */
    public String getType(){
        return type;
    }
    
    
    /** 
    * Increases the rate the crops grows by when using item.
    * @param amount the items effect on the crop
    */
    public void increaseRate(Double amount){
        if (growthRate + amount > 1.0){
            growthRate = 1.0;
;        }
        else if (growthRate + amount < 0.0) {
        	growthRate = 0.0;
        }
        else{
            growthRate += amount;
        }
    }
    
    /**
    * Gets the worth of the crop, how much it can be sold for once ready to harvest
    * @return crops worth
    */
    public Double getWorth(){
        double multiplier = 0.0;
        if (type.equals("Carrot")){
            multiplier = 2.0;
        }
        else if (type.equals("Wheat")){
            multiplier = 1.4;
        }
        else if (type.equals("Potatoes")){
            multiplier = 1.3;
        }
        else if (type.equals("Pumpkins")){
            multiplier = 1.8;
        }
        else if (type.equals("Melon")){
            multiplier = 1.6;
        }
        else{
            multiplier = 1.2;
        }
        
        if (growth > 1.0){ //making sure it cant return more than worth*1
            return (worth * quantity)*multiplier;
        }
        else {
            return ((worth * growth) * quantity)*multiplier;
        }
    }
    
    /** 
    * Gets how much the crop has grown, fully grown = 1
    * @return crop growth
    */
    public Double getGrowth(){
        return growth;
    }
    /**
     * Gets the daily rate of growth of crops
     * @return growthRate percent per day it grows
     */
    public double getRate() {
    	return growthRate;
    }
    
    /** 
    * Gets how much that type of crop is owned
    * @return num of crops
    */
    public int getQuantity(){
        return quantity;
    }
    
    /** 
    * Changes the growth of new crop based on how much the crop grows in a days
    * @param farmRate base rate for farms crop growth rate
    */
    public void newDay(Double farmRate){
        growth += farmRate * growthRate;
        if (growth >= 1.0){
            growth = 1.0;
        }
    }
    /**
    * When a drought occurs, destroy half of the crops
    */
    public void halfQuantity(){
        quantity *= 0.5;
    } 
    
    @Override
    public String toString() {
    	String string = quantity + " " + type + " " + growthRate;
    	return string;
    }
}