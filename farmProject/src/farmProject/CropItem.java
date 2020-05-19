package farmProject;

public class CropItem extends Item{
    private Double benefit = 0.0;
    
    public CropItem(String typee, String descriptionn, Double benefitt){
        super(typee, descriptionn);
        benefit = benefitt;
    }
    
    
    /**
     * Returns the items benefit
     * @return double
     */
    public double getBenefit(){
        return benefit;
    }
    
    
    @Override
    public String toString() {
      return super.getType();
    }
}