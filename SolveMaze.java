import java.io.*;
import java.util.*;

/**
 * Maze.java
 * Solution to the first Maze Assignment (HW3).
 * CS 201: Data Structures - Winter 2018
 *
 * @author Eric Alexander
 */
public class Maze {
    private ArrayList<ArrayList<MazeSquare>> rowList;
    private int w, h;
    private int startRow, startCol, endRow, endCol;

    // I am including MazeSquare as an inner class
    // to simplify the file structure a little bit.
    private class MazeSquare {
        private int r, c;
        private boolean top, bottom, left, right,
                start, end, visited, hasStar;

        private MazeSquare(int r, int c,
                           boolean top, boolean bottom, boolean left, boolean right,
                           boolean start, boolean end, boolean visited, boolean hasStar) {
            this.r = r;
            this.c = c;
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
            this.start = start;
            this.end = end;
            this.visited = visited;
            this.hasStar = hasStar;
        }

        boolean hasTopWall() {
            return top;
        }
        boolean hasBottomWall() {
            return bottom;
        }
        boolean hasLeftWall() {
            return left;
        }
        boolean hasRightWall() {
            return right;
        }
        boolean isStart() {
            return start;
        }
        boolean isEnd() {
            return end;
        }
        int getRow() {
            return r;
        }
        int getCol() {
            return c;
        }
        boolean isVisited() {
            return visited;
        }
        void visit(){
            visited = true;
        }

        boolean hasStar(){
            return hasStar;
        }

        void deleteStar(){
            hasStar = false;
        }
    }

    /**
     * Construct a new Maze
     */
    public Maze() {
        rowList = new ArrayList<ArrayList<MazeSquare>>();
    }

    /**
     * Load Maze in from given file
     *
     * @param fileName the name of the file containing the Maze structure
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

        // Second line of file is "startCol startRow"
        lineParams = scanner.nextLine().split(" ");
        startCol = Integer.parseInt(lineParams[0]);
        startRow = Integer.parseInt(lineParams[1]);

        // Third line of file is "endCol endRow"
        lineParams = scanner.nextLine().split(" ");
        endCol = Integer.parseInt(lineParams[0]);
        endRow = Integer.parseInt(lineParams[1]);

        // Read the rest of the lines (L or | or _ or -)
        String line;
        int rowNum = 0;
        boolean top, bottom, left, right;
        boolean start, end;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            rowList.add(new ArrayList<MazeSquare>());

            // Loop through each cell, creating MazeSquares
            for (int i = 0; i < line.length(); i++) {
                // For top, check row above, if there is one
                if (rowNum > 0) {
                    top = rowList.get(rowNum-1).get(i).hasBottomWall();
                } else {
                    top = true;
                }

                // For right, check cell to the right, if there is one
                if (i < line.length() - 1 ) {
                    char nextCell = line.charAt(i+1);
                    if (nextCell == 'L' || nextCell == '|') {
                        right = true;
                    } else {
                        right = false;
                    }
                } else {
                    right = true;
                }

                // For left and bottom, switch on the current character
                switch (line.charAt(i)) {
                    case 'L':
                        left = true;
                        bottom = true;
                        break;
                    case '_':
                        left = false;
                        bottom = true;
                        break;
                    case '|':
                        left = true;
                        bottom = false;
                        break;
                    case '-':
                        left = false;
                        bottom = false;
                        break;
                    default:
                        left = false;
                        bottom = false;
                }

                // Check to see if this is the start or end spot
                start = startCol == i && startRow == rowNum;
                end = endCol == i && endRow == rowNum;

                // Add a new MazeSquare
                rowList.get(rowNum).add(new MazeSquare(rowNum, i, top, bottom, left, right, start, end, false, true));
            }

            rowNum++;
        }
    }

    /**
     * Print the Maze to the Console
     */
    public void print() {
	
	// YOUR CODE WILL GO HERE:
	// Before printing, use your getSolution() method
	//  to get the solution to the Maze.
	
        ArrayList<MazeSquare> currRow;
        MazeSquare currSquare;


        // Print each row of text based on top and left
        for (int r = 0; r < rowList.size(); r++) {
            currRow = rowList.get(r);

            // First line of text: top wall
            for (int c = 0; c < currRow.size(); c++) {
                System.out.print("+");
                if (currRow.get(c).hasTopWall()) {
                    System.out.print("-----");
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println("+");

            // Second line of text: left wall then space
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");

            // Third line of text: left wall, then space, then start/end/sol, then space
            for (int c = 0; c < currRow.size(); c++) {
                currSquare = currRow.get(c);

                if (currSquare.hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }

                System.out.print("  ");

		// YOU WILL ADD CODE HERE

		// If currSquare is part of the solution, mark it with *

       

        
                if (currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("SE ");
                } else if (currSquare.isStart() && !currSquare.isEnd()) {
                    System.out.print("S  ");
                } else if (!currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("E  ");
                } else {
                    System.out.print("*  ");
                }
            
        if (currSquare.hasStar() == false){

            System.out.print("  ");
                
        }
        }
            System.out.println("|");

            // Fourth line of text: same as second
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");
        }

        // Print last row of text as straight wall
        for (int c = 0; c < rowList.get(0).size(); c++) {
            System.out.print("+-----");
        }
        
        System.out.println("+");
    }

    public MazeSquare getSquare(int row, int col ){
        return  rowList.get(row).get(col);
    }


    public MazeSquare getNeighbor(MazeSquare s, String direction){
        //find the row and column of s, and change the row and column depending on the direction
        int row = s.getRow();
        int col = s.getCol();
        if (direction.equals("left")){
            return rowList.get(row).get(col - 1);
        }
        if (direction.equals("right")){
            return rowList.get(row).get(col + 1);
        }
        if (direction.equals("top")){
            return rowList.get(row - 1).get(col);
        }
        if (direction.equals("bottom")){
            return rowList.get(row + 1).get(col);
        }
        return null;
    }

    public MazeSquare findStartSquare() {
        MazeSquare current = null;
        for (int r = 0; r < h; r++) {
            for(int c = 0; c < w; c++) {
                current = getSquare(r, c);
                if (current.isStart() == true) {
                    return current;
                }
            }
        }

        /**
        for (ArarryList<MazeSquare> row : rowList){
            for (MazeSquare current : row){
               if (current.isStat()){
                    return current;
              }
            }
        }
        return null;
        */

        return current;
    }
//get the stack
//iterate thru stack to do contains in 
public LLStack getSolution(){
    LLStack stack = new LLStack<>();
    return stack;
   

}

    public void search(){
        MazeSquare startSquare = findStartSquare();

        //Make an LL Stack
        LLStack<MazeSquare> stack = new LLStack<MazeSquare>();
        stack.push(startSquare);
        startSquare.visit();
        //startSquare.deleteStar();


        while (stack.isEmpty() == false){
            MazeSquare T = stack.peek();
            if (T.isEnd() == true){
                //T.deleteStar();
                break;
            }
                System.out.print(T.getCol() + "      " + T.getRow());

            if (T.getRow() != 0 && getNeighbor(T, "top").isVisited() == false && getNeighbor(T, "top").hasBottomWall() == false){
                System.out.println("up");
                T.visit();
                T = getNeighbor(T, "top");
                stack.push(T);
                T.visit();
                System.out.print(T.getCol() + "      " + T.getRow());
            
            }
        
            else if (T.getCol() != h && getNeighbor(T, "right").hasLeftWall() == false && getNeighbor(T, "right").visited == false){ // why is it h, not w?
                System.out.println("right");
                T.visit();
                T = getNeighbor(T, "right");
                stack.push(T);
                T.visit();
                System.out.print(T.getCol() + "      " + T.getRow());
            }
            else if (T.getRow() != w && T.hasBottomWall() == false && getNeighbor(T, "bottom").visited == false){
                System.out.println("down");
                T.visit();
                T = getNeighbor(T, "bottom");
                stack.push(T);
                T.visit();
                System.out.print(T.getCol() + "      " + T.getRow());
            }
            else if (T.getCol() != 0 && getNeighbor(T, "left").visited == false && T.hasLeftWall() == false){
                System.out.println("left");
                T.visit();
                T = getNeighbor(T, "left");
                stack.push(T);
                T.visit();
                System.out.print(T.getCol() + "      " + T.getRow());
            }
            else{
                T.deleteStar();
                System.out.print("DO YOU HAVE A STAR:      " + T.hasStar());
                stack.pop();
                System.out.println("popped");
            }
    

        //if it returns to the s and is empty, (E is hidden/unreachable), unsovable maze
        //add stars for path 
    //}

}
    }

    // This main program acts as a simple unit test for the
    // load-from-file and print-to-System.out Maze capabilities.
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Maze mazeFile");
            System.exit(1);
        }

        Maze maze = new Maze();
        maze.load(args[0]);
        maze.print();
        maze.search();
    
    }
}
