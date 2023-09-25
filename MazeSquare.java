import java.util.ArrayList;

/**
 * MazeSquare.java
 * A helper class for maze solving assignment.
 * Represents a single square within a rectangular maze.
 *
 * @author Aimee Yuan and Anika Rajbhandary
 */
public class MazeSquare {
    // Instance variables of your choosing
    String shape;


    // A constructor, taking whatever parameters you decide:
    public MazeSquare(String inputshape) {
        shape = inputshape;
    }
    
    public String getSquareType(){
        return shape;
    }

    public void setSquareType(String x) {
        if(x.length()!= 1){
            System.out.println("Error (length)");
            System.exit(1);
        }
        else{
            shape = x;
        }

    }

    // public String getShape() {
    //     if (shape.equals("L")){
    //         String LShape = ("|     \n|     \n|     \n+-----");
    //         mazeSquareArray.add(LShape);
    //         System.out.println(LShape);
    //         return LShape;

    //     }
    //     if (shape.equals("_")){
    //         String downShape = ("      \n      \n      \n+-----");
    //         mazeSquareArray.add(downShape);
    //         System.out.println(downShape);
    //         return downShape;
    //     }
    //     if (shape.equals("-")){
    //         String emptyShape =  ("      \n      \n      \n+     ");
    //         mazeSquareArray.add(emptyShape);
    //         System.out.println(emptyShape);
    //         return emptyShape;
    //     }
    //     if (shape.equals("|")){
    //         String leftShape = ("|     \n|     \n|     \n+     ");
    //         mazeSquareArray.add(leftShape);
    //         System.out.println(leftShape);
    //         return leftShape;
    //     }
    //     return("");
    // }
       

    // Whatever methods you want, such as:
    public boolean hasRightWall() {
        // if (x = w and y <= h) {
        //     Square[x][y];
        //     return true;
        // }
        return true;
    }


    public boolean hasTopWall() {
        return true;
    }

}
