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
    private int startColumn;
    private int startRow;
    private int finishColumn;
    private int finishRow;
    // OTHER INSTANCE VARIABLES IF YOU NEED THEM
    
    /**
     * Constructor for the Maze class
     */
    public Maze() {
        rowList = new ArrayList<ArrayList<MazeSquare>>();
    }

    public void makeEmptyGrid(int h){

        //Do this size times (once for each row)
        int r = 0;
        while(r<h){
            r += 1;
            
            //We make a row and then add it to our grid
            ArrayList<MazeSquare> row = new ArrayList<MazeSquare>();
            for(int c= 0; c<=h; c += 1){
                //We are adding size number of Letters to the row
                row.add( new MazeSquare() );
            }

            rowList.add(row);
        }
        System.out.print("\n"+rowList+"\n");

    }

     // This returns the character in position row, col of our grid
     public String getData(int row, int col){
        MazeSquare temp = rowList.get(row).get(col);
        return temp.getData();
    }

    // This changes the character in position row, col of our grid
    public void setData(int row, int col, String s){
        MazeSquare temp = rowList.get(row).get(col);
        temp.setData(s);
    }

    /**
     * Load in a Maze from a given file
     *
     * @param fileName the name of the file containing the maze
     */
    public void load(String fileName) {
        // Create a scanner for the given file
        //System.out.print("hello");
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

        // First we read in the size and create an empty grid
        makeEmptyGrid(h);

        lineParams = scanner.nextLine().split(" ");

        startColumn = Integer.parseInt(lineParams[0]);
        startRow = Integer.parseInt(lineParams[1]);
        
        lineParams = scanner.nextLine().split(" ");

        finishColumn = Integer.parseInt(lineParams[0]);
        finishRow = Integer.parseInt(lineParams[1]);

        for(int row = 0; row < h; row += 1){
            lineParams = scanner.nextLine().split("");
            // We split(" ") because the letters are separated by spaces
            // ... in the input file

            for(int col=0; col < w; col += 1){
                setData(row, col, lineParams[col]);
            }
        }
        
        // YOUR CODE TO FINISH LOADING FILE HERE
    }

    /**
     * Print the Maze to System.out
     */
    public void print(){

        for (int loop = 0; loop <= h; loop++){
            System.out.print("+-----");
        }
        System.out.print("+");

        for(int row = 0; row < h; row += 1){
            
            System.out.print("\n");
            int x = 0;

            while (x<= 3){
                if (x<=2){
                    for(int col =0; col < w; col +=1){
            
                        StringJoiner joiner = new StringJoiner("");
                        
                            //System.out.print("ENTER:\n");
                            //System.out.print(getData(row,col));
                        if (getData(row, col).equals("L")){
                            if (col == startColumn && row == startRow && x == 1){
                                joiner.add("|  S  ");
                            }
                            else if (col == finishColumn && row == finishRow && x == 1){
                                joiner.add("|  F  ");
                            }
                            else{
                                joiner.add("|     ");
                            if (col == h)
                            {
                                joiner.add("|");
                            }
                            }

                        }
                        if (getData(row, col).equals("_")){
                            if (col == startColumn && row == startRow && x == 1){
                                joiner.add("|  S  ");
                            }
                            else if (col == finishColumn && row == finishRow && x == 1){
                                joiner.add("|  F  ");
                            }
                            else{
                            joiner.add("      ");
                            if (col == h)
                            {
                                joiner.add("|");
                            }
                        }
                    }
                        if (getData(row, col).equals("-")){
                            if (col == startColumn && row == startRow && x == 1){
                                joiner.add("|  S  ");
                            }
                            else if (col == finishColumn && row == finishRow && x == 1){
                                joiner.add("|  F  ");
                            }
                            else{
                            joiner.add("      ");
                            if (col == h)
                            {
                                joiner.add("|");
                            }
                        }
                    }
                        if (getData(row, col).equals("|")){
                            if (col == startColumn && row == startRow && x == 1){
                                joiner.add("|  S  ");
                            }
                            else if (col == finishColumn && row == finishRow && x == 1){
                                joiner.add("|  F  ");
                            }
                            else{
                            joiner.add("|     ");
                            if (col == h)
                            {
                                joiner.add("|");
                            }
                        }
                    }
                        System.out.print(joiner.toString() );
                   
                    }
                x++;
                System.out.print("\n");
                }
                

                else if (x == 3){
                    for(int col =0; col < w; col +=1){
                        StringJoiner joiner2 = new StringJoiner("");
                        if (getData(row, col).equals("L")){
                            joiner2.add("+-----");
                            if (col == h)
                            {
                                joiner2.add("+");
                            }
                        }
                        if (getData(row, col).equals("_")){
                            joiner2.add("+-----");
                            if (col == h)
                            {
                                joiner2.add("+");
                            }
                        }
                        if (getData(row, col).equals("-")){
                            joiner2.add("+     ");
                            if (col == h)
                            {
                                joiner2.add("+");
                            }
                        }
                        if (getData(row, col).equals("|")){
                            joiner2.add("+     ");
                            if (col == h)
                            {
                                joiner2.add("+");
                            }
                        }
                    System.out.print(joiner2.toString() );
                }
                x++;
            }
            // Println takes us to a new line at the end of the row
            }
    }
    }

    // MORE METHODS AS YOU NEED THEM

    // This main program acts as a simple unit test for the
    // load-from-file and print-to-System.out Maze capabilities.
    public static void main(String[] args) {
        // if (args.length != 1) {
        //     System.err.println("Usage: java Maze mazeFile");
        //     System.exit(1);
        // }
        //how many h's
       
        Maze maze = new Maze();
        maze.load("maze.txt");
        maze.print();
    }
}
