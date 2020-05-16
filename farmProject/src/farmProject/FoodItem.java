package farmProject;

public class FoodItem extends Item{
    private Double benefit = 0.0;
    /*
    private String description;
    private String type;
    */
    
    public FoodItem(String typee, String descriptionn, Double benefitt){
        super(typee, descriptionn);
        benefit = benefitt;
    	
    	/*
    	if (typee.equals("Grub")) {
    		type = typee;
    		description = "Does this";
    		benefit = 0.2;
    	}
    	else if (typee.equals("Seeds")) {
    		type = typee;
    		description = "Does this";
    		benefit = 0.5;
    	}
    	else if (typee.equals("Medicine")) {
    		type = typee;
    		description = "Does this";
    		benefit = 1.0;
    	}
    	*/
    }
    public Double getBenefit(){
        return benefit;
        
    }
    
    /*
    public String getType() {
    	return type;
    }


    public String getDescription() {
    	return description;
    }
    */
}