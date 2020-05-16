package farmProject;

public class CropItem extends Item{
	private String type;
    private Double benefit = 0.0;
    
    
    public CropItem(String typee, String descriptionn, Double benefitt){
        super(typee, descriptionn);
        type = typee;
        benefit = benefitt;
    }
    
    
    /**
     * Returns the items benefit
     * @return double
     */
    public Double getBenefit(){
        return benefit;
    }
    
    
    @Override
    public String toString() {
      return type;
    }
}