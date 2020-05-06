package farmProject;

public class Farmer {
    
    // variables are required to be static to allow for without an instanc of the class
    private String name;
    
    public Farmer(String namee) //init method, throws error if incorrect values
    {   
        name = namee;
    }
    
    public String getName(){
        return name;
    } 
}
