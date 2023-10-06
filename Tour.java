/**
 * This class creates a Tour of Points using a 
 * Linked List implementation.  The points can
 * be inserted into the list using two heuristics.
 *
 * @author ANIKA RAJBHANDARY AND AIMEE YUAN. modified code 10-05-2023
 * @author Eric Alexander, modified code 01-12-2018
 * @author Layla Oesper, modified code 09-22-2017
 */
public class Tour {
    private class Node {
        private Point p;
        private Node next;

        public Node(Point newP) {
            p = newP;
            next = null;
        }

        public Node(Point newP, Node nextNode) {
            p = newP;
            next = nextNode;
        }
    
    // get Point (x,y)
    public Point getData(){
        return p;
    }

    // setNext node to given Node next
    public void setNext(Node next){
        this.next = next;
    }
    }
    
    // instance variables
    private Node head;
    private int size; 
    
    // constructor Tour, initialize head and size
    public Tour() {
        head = null;
        size = 0;
        
    }
    
    // This is insertNearest method where we insert the new node point p to the point nearest to the existing points
    public void insertNearest(Point p) {
        Node newNodeP = new Node(p);

        // If there is no points in the linked list, initialize the new node as head
        if (head == null) {
            head = newNodeP;
            // increment size
            size++;
            return;
        }
        
        // variables for insertNearest
        Node current = head;
        double minDist = current.getData().distanceTo(p);
        Node minNode = current;
        Node prev = null;

        // Iterating through the linked list until we reach the tail
        while (current.next != null) {
            double distance = current.next.getData().distanceTo(p);
            // If the distance between the current point and the new point is less than the minimum distance, set the minimum distance to the new distance
            if (distance < minDist) {
                minDist = distance;
                // store current node as prev to use as point that points to new node P
                prev = current;
                minNode = current.next;
            }
            //move curent to the net node in the list
            current = current.next;
        }

        //If the prev is not null, then set prev to the newNodeP
        if (prev != null) {
            prev.setNext(newNodeP);
        // Else set head as the new NodeP 
        } else {
            head = newNodeP; 
        }

        // Point newNodeP to the minNode which is the node closest to newNodeP afterward
        newNodeP.setNext(minNode);
        // increment size
        size++;
    }

    // size method which keeps track of number of points
    public int size(){
        return size;
    }
    
    // method that calculates total distance of the tour by looping through the nodes in the linked list
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

    // method that inserts the new point between two existing points in the linked list
    public void insertSmallest(Point p) {
        // If there are no points in the linked list, make the new point the head
        if (size() == 0) {
            head = new Node(p);
            size++;
            return;
        // Else, if there is a point already in the linked list, point the node to the new node
        } else if (size() == 1) {
            head.next = new Node(p);
            size++;
            return;
        }
        //Variables
        Node newNodeP = new Node(p);
        Node current = head;
        Node next = current.next;
        Node setNode = null;

        // While loop through the linked list looking at the minimum distances of the sum
        double minSum = Double.MAX_VALUE;
        while (next != null){
            // the distance between the current node and the next node in the linked list
            double originalDistance = current.getData().distanceTo(next.getData());
            // the sum between the current node to the new point and the new point to the next node
            double newPossibleDistance = current.getData().distanceTo(p) + p.distanceTo(next.getData());
            //If the sum distance (newPossibleDistance) is less than minSum, set it as minSum. 
            if (newPossibleDistance - originalDistance < minSum){
                minSum = newPossibleDistance - originalDistance;
                setNode = current;
            }
            current = current.next;
            next = next.next;
        }
        //For the edge case of the distance of the current (last node) to the head
        double originalDistance = current.getData().distanceTo(head.getData());
        double newPossibleDistance = current.getData().distanceTo(p) + p.distanceTo(head.getData());
        if (newPossibleDistance - originalDistance < minSum){
            minSum = newPossibleDistance - originalDistance;
            setNode = current;
        }
        //Setting the previous node to the newNodeP, then setting the newNodeP to the node setNode was originally pointing to (so the node after setNode)
        Node tempNode = setNode.next;
        setNode.setNext(newNodeP);
        newNodeP.setNext(tempNode);
    
        //increment size
        size++;
    }
    
    // method that iterates through the linked list and prints out each point in the order of travel in a new line
    public String toString(Point p){
        Node current = head;
        String str = "";
        while (current != null){
            str += current.p.toString();
            current = current.next;
        }
        return str;
    }

    // draw method that iterates through linked list and draw line between each point
    public void draw(){
        Node current = head;
        while (current.next!= null){
            current.getData().draw();
            current.getData().drawTo(current.next.getData());
            current = current.next;
        }
        // draw line from last node to head node
        current.getData().drawTo(head.getData());
    }

    // main function
    public static void main(String[] args) {
        // create instance of Tour
        Tour tour = new Tour();

        // create new point then call insertSmallest or insertNearest function on each point
        Point p = new Point(0,0);
        tour.insertSmallest(p);
        p = new Point(0,100);
        tour.insertSmallest(p);
        p = new Point(100, 100);
        tour.insertSmallest(p);
        
        //print out final tour distance, number of points/size of tour/travel stops, and then order of each point in the tour
        System.out.println("Tour distance =  " + tour.distance());
        System.out.println("Number of points = "+ tour.size());
        System.out.println(tour.toString(p));
        
        // set width and height of frame that the program draws to, set the X and Y scale, set the pen radius, then call draw function and draw the tour
        int w = 500 ; 
        int h = 500 ;
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(.005);
        tour.draw(); 
        
    }
   
}
