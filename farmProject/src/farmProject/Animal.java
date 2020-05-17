package farmProject;

/** 
* This class implements individual animals that are stored in the farm
* @author Alec, Christian
*/

public class Animal {
    private Double health = 1.0;
    private Double happiness = 1.0;
    private Double worth;
    private String type;
    
    public Animal(String typee, Double cost) //init method, throws error if incorrect values
    {    
        worth = cost;
        type = typee;

    }
    
    
    /** 
    * Gets the type of animal eg. Sheep, Cow, Pig
    * @return type of animal
    */
    public String getType(){
        return type;
    }
    
    
    /** 
    * Edits the happiness of the animal
    * @param hap the amount the happiness of animal is changed by
    */
    public void editHappiness(Double hap){
        if (happiness + hap > 1){
            happiness = 1.0;
        }
        else if (happiness + hap < 0){
            happiness = 0.0;
        }
        else{
            happiness += hap;
        }
    }
    
    
    
    /** 
     * Edits the happiness of the animal
     * @param hap the amount the health of animal is changed by
     */
     public void editHealth(Double incomingHealth){
         if (health + incomingHealth > 1){
             health = 1.0;
         }
         else if (health + incomingHealth < 0){
             health = 0.0;
         }
         else{
             health += incomingHealth;
         }
     }
    
    
    
    /** 
    * Gets how much the animal is worth
    * @return animal worth
    */
    public Double getWorth(){
        return (worth * happiness * health);
    }
    
    /** 
    * Returns the current health of the animal
    * @return health
    */
    public Double getHealth(){
        return health;
    }
    
    /** 
    * Gets the happiness value of the animal
    * @return happiness happiness of animal
    */
    public Double getHappy(){
        return happiness;
    }
    
    
    /** 
    * Returns the daily bonus generated by the animal
    * @return bonus bonus money
    */
    public Double getDailyBonus(){
        return (getWorth() * 0.2); //daily bonus is 1/5 of the animals worth
    }
    
    
    @Override
    public String toString() {
    	String string = type + " health:" + health;
    	return string;
    }
}