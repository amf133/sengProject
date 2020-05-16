package farmProject;

public class Farmer {
    
    // variables are required to be static to allow for without an instanc of the class
    private String firstName;
    private int age;
    
    public Farmer(String incomingName, int incomingAge) //init method, throws error if incorrect values
    {   
    	firstName = incomingName;
    	age = incomingAge;
    }
    
    public String getName(){
        return firstName;
    } 
}
