package farmProject;

public class FoodItem extends Item{
    private Double benefit = 0.0;
    
    public FoodItem(String typee, String descriptionn, Double benefitt){
        super(typee, descriptionn);
        benefit = benefitt;
    }
    
    public Double getBenefit(){
        return benefit;
    }
}