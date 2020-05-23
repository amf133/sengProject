package farmProject;



/** 
* This class is the games farmer, or the user
* @author Alec, Christian
*/
public class Farmer {
    
    // variables are required to be static to allow for without an instance of the class
    private String firstName;
    private int age;
    
    /**
     * Creates the users farmer object
     * @param incomingName name of the farmer
     * @param incomingAge age of the farmer
     */
    public Farmer(String incomingName, int incomingAge) //init method, throws error if incorrect values
    {   
    	firstName = incomingName;
    	age = incomingAge;
    }
    
    
    /**
     * Returns farmers name
     * @return string
     */
    public String getName(){
        return firstName;
    }
    
    
    /**
     * Returns farmers age
     * @return int
     */
    public int getAge(){
        return age;
    }
}
