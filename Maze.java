/**
 * Maze.java
 * A class for loading and printing mazes from files.
 *
 * @author YOUR NAME AND YOUR PARTNER'S NAME
 */
import java.util.*;
import java.io.*;


public class Maze {
    private ArrayList<ArrayList<MazeSquare>> rowList;
    private int w;
    private int h;
    private int s1;
    private int s2;
    private int f1;
    private int f2;
    // OTHER INSTANCE VARIABLES IF YOU NEED THEM
    
    /**
     * Constructor for the Maze class
     */
    public Maze(){//int width, int height, int start1, int start2, int finish1, int finish2) {
        // w = width;
        // h = height;
        // s1 = start1;
        // s2 = start2;
        // f1 = finish1;
        // f2 = finish2;
        rowList = new ArrayList<ArrayList<MazeSquare>>();
            //join the mazesquares here

    }

    /**
     * Load in a Maze from a given file
     *
     * @param fileName the name of the file containing the maze
     */
    public void load(String fileName) {
        // Create a scanner for the given file
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        // First line of file is "w h"
        String[] lineParams = scanner.nextLine().split(" ");
        w = Integer.parseInt(lineParams[0]);
        h = Integer.parseInt(lineParams[1]);
        
        lineParams = scanner.nextLine().split(" ");

        s1 = Integer.parseInt(lineParams[0]);
        s2 = Integer.parseInt(lineParams[1]);
        
        lineParams = scanner.nextLine().split(" ");

        f1 = Integer.parseInt(lineParams[0]);
        f2 = Integer.parseInt(lineParams[1]);

        ArrayList<ArrayList<String>> inputList = new ArrayList<>();
        for(int i = 1; i<=h; i++){
            lineParams = scanner.nextLine().split("");
            ArrayList<String> strList = new ArrayList<String>(Arrays.asList(lineParams));
            inputList.add(strList);
        }
        System.out.println(inputList.get(0).get(0));

    }

    /**
     * Print the Maze to System.out
     */
    public void print() {
        for (int i=1; i<=w; i++)
        {
            System.out.print("+-----");
        }
        System.out.println("+");
    }
    //add the right wall here

    // MORE METHODS AS YOU NEED THEM

public int calc_size(){
    return w * h;
}


    // This main program acts as a simple unit test for the
    // load-from-file and print-to-System.out Maze capabilities.
    public static void main(String[] args) {
        // if (args.length != 1) {
        //     System.err.println("Usage: java Maze mazeFile");
        //     System.exit(1);
        // }

        Maze maze = new Maze();
        MazeSquare maze1 = new MazeSquare("L");
        maze1.printShapeType();

        maze.load("maze.txt");
        maze.print();
    }
}
