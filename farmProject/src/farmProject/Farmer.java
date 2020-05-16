package farmProject;

public class Farmer {
    
    // variables are required to be static to allow for without an instanc of the class
    private String firstName;
    private String lastName;
    
    public Farmer(String name1, String name2) //init method, throws error if incorrect values
    {   
    	firstName = name1;
    	lastName = name2;
    }
    
    public String getName(){
        return firstName + " " + lastName;
    } 
    
    public String getFirst(){
        return firstName;
    } 
    
    public String getLast(){
        return lastName;
    } 
}
