package farmProject;

public class CropItem extends Item{
    private Double benefit = 0.0;
    
    public CropItem(String typee, String descriptionn, Double benefitt){
        super(typee, descriptionn);
        benefit = benefitt;
    }
    
    public Double getBenefit(){
        return benefit;
    }
}