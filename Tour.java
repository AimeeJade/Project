public class Tour2 {
    /** 
     * A helper class that defines a single node for use in a tour.
     * A node consists of a Point, representing the location of that
     * city in the tour, and a pointer to the next Node in the tour.
     */
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
    
    
    }
    
    private Node head;
    private int size; 
    
    public Tour2() {
        head = null;
        size = 0;
        
    }
    
    
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
    //-------------------------------------------------------------------------------------------------------------
    public void insertSmallest(Point p) {
        if (size() == 0) {
            head = new Node(p);
            size++;
            return;
        } else if (size() == 1) {
            head.next = new Node(p);
            size++;
            return;
        }

        Node newNodeP = new Node(p);
        Node current = head;
        Node next = current.next;
        Node setNode = null;

        double minSum = Double.MAX_VALUE;
        while (next != null){
            double originalDistance = current.getData().distanceTo(next.getData());
            double newPossibleDistance = current.getData().distanceTo(p) + p.distanceTo(next.getData());
            if (newPossibleDistance - originalDistance < minSum){
                minSum = newPossibleDistance - originalDistance;
                setNode = current;
            }
            current = current.next;
            next = next.next;
        }
        //next = head;
        double originalDistance = current.getData().distanceTo(head.getData());
        double newPossibleDistance = current.getData().distanceTo(p) + p.distanceTo(head.getData());
        if (newPossibleDistance - originalDistance < minSum){
            minSum = newPossibleDistance - originalDistance;
            setNode = current;
        }
        Node tempNode = setNode.next;
        setNode.setNext(newNodeP);
        newNodeP.setNext(tempNode);
    
        size++;
    }
    //-------------------------------------------------------------------------------------------------------------
    
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

        Tour2 tour = new Tour2();
        
        Point p = new Point(50,0);
        tour.insertSmallest(p);
        p = new Point(50,100);
        tour.insertSmallest(p);
        p = new Point(100, 50);
        tour.insertSmallest(p);
        p = new Point(100, 0);
        tour.insertSmallest(p);
        p = new Point(0, 50);
        tour.insertSmallest(p);
        // p = new Point(200,105);
        // tour.insertSmallest(p);
        // p = new Point(5, 50);
        // tour.insertSmallest(p);
        
        System.out.println("Tour distance =  " + tour.distance());
        System.out.println("Number of points = "+ tour.size());
        System.out.println(tour.toString(p));
        
         
	
        int w = 500 ; 
        int h = 500 ;
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(.005);
        tour.draw(); 
        
    }
   
}
