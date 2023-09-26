/**
 * MazeSquare.java
 * A helper class for maze solving assignment.
 * Represents a single square within a rectangular maze.
 *
 * @author YOUR NAME AND YOUR PARTNER'S NAME
 */
public class MazeSquare {
    String data;

    public String getData(){
        return data;
    }

    //This changes the mazeSquare that is stored in the Letter object
    public void setData( String x){
        //We only want to store a single letter 
        //so it is an error if someone tries to store a longer string
        if(x.length() != 1){
            System.out.println("Error in Letter setData method");
            System.out.println("Attempt to store more than one character");
            System.exit(1);
        }
        else{
            data = x;
        }
    }

    //This is a basic constructor that makes an empty MazeSquare object
    public MazeSquare(){
        data = null;
    }
}
