import java.util.ArrayList;

/**
 * This class creates a Tour of Points using a 
 * Linked List implementation.  The points can
 * be inserted into the list using two heuristics.
 *
 * @author ANIKA RAJBHANDARY AND AIMEE YUAN
 * @author Eric Alexander, modified code 01-12-2018
 * @author Layla Oesper, modified code 09-22-2017
 */

 public class Tour {
    /** 
     * A helper class that defines a single node for use in a tour.
     * A node consists of a Point, representing the location of that
     * city in the tour, and a pointer to the next Node in the tour.
     */
    private class Node {
        private Point p;
        private Node next;

        
        
        /** 
         * Constructor creates a new Node at the given Point newP
         * with an initial next value of null.
         * 
         * @param newP the point to associate with the Node.
         */
        public Node(Point newP) {
            p = newP;
            next = null;
        }

        /** 
         * Constructor creates a new Node at the given Point newP
         * with the specified next node.
         *
         * @param newP the point to associate with the Node.
         * @param nextNode the nextNode this node should point to.
         */
        public Node(Point newP, Node nextNode) {
            p = newP;
            next = nextNode;
        }
    // public int size(){
    //     return 0;
    // }
    public Point getData(){
        return p;
    }

    public void setData(Point p){
        this.p = p;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }
    
    
    } // End Node class
    

    // Tour class Instance variables
    private Node head;
    private int size; //number of nodes
    //Add other instance variables you think might be useful.
    
    
    /**
     * Constructor for the Tour class.  By default sets head to null.
     */
    public Tour() {
        head = null;
        size = 0;
        
    }
    
    // ADD YOUR METHODS BELOW HERE
    
    public void insertNearest(Point p) {
        Node newNodeP = new Node(p);
        if (head == null) {
            head = newNodeP;
            size++;
            return;
        }
    
        Node current = head;
        double minDist = current.getData().distanceTo(p);
        Node minNode = current;
        Node prev = null;

        while (current.next != null) {
            double distance = current.next.getData().distanceTo(p);
            if (distance < minDist) {
                minDist = distance;
                prev = current;
                minNode = current.next;
            }
            current = current.next;
        }
    
        if (prev != null) {
            prev.setNext(newNodeP);
        } else {
            head = newNodeP; 
        }
        newNodeP.setNext(minNode);
    
        size++;
    }

    public int size(){
        return size;
    }
    
    public double distance(){
        Node current = head;
        double length = 0;
        while (current != null && current.next != null){
            double newdistance = current.getData().distanceTo(current.next.getData());
            length = length + newdistance;
            current = current.next;
        }
        double moreDistance = current.getData().distanceTo(head.getData());
        length = length + moreDistance;
        return length;
    }
    
    public void insertSmallest(Point p) {

    }
    
    public String toString(Point p){
        Node current = head;
        String str = "";
        while (current != null){
            str += current.p.toString();
            current = current.next;
        }
        return str;
    }

    public void draw(){
        Node current = head;
        while (current.next!= null){
            current.getData().draw();
            current.getData().drawTo(current.next.getData());
            current = current.next;
        }
        current.getData().drawTo(head.getData());
    }
    public static void main(String[] args) {
        /* Use your main() function to test your code as you write it. 
         * This main() will not actually be run once you have the entire
         * Tour class complete, instead you will run the NearestInsertion
         * and SmallestInsertion programs which call the functions in this 
         * class. 
         */
        
        
        //One example test could be the follow (uncomment to run):
        
        Tour tour = new Tour();
        
        Point p = new Point(0,0);
        tour.insertNearest(p);
        p = new Point(0,100);
        tour.insertNearest(p);
        p = new Point(100, 100);
        tour.insertNearest(p);
        
        System.out.println("Tour distance =  " + tour.distance());
        System.out.println("Number of points = "+ tour.size());
        System.out.println(tour.toString(p));
        
         
	
        int w = 500 ; //Set this value to the max that x can take on
        int h = 500 ; //Set this value to the max that y can take on
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(.005);
        tour.draw(); 
        
    }
   
}
