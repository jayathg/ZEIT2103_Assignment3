
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * this class is used to test all methods implemented in this assignment........
 * To enable assertions in Apache NetBeans, follow these steps: 1- Open the
 * project properties by right-clicking on the project name in the Projects
 * window and selecting "Properties" from the context menu. 2- In the Properties
 * window, select the "Run" category from the left-hand menu. 3- In the "Run"
 * category, locate the "VM Options" field and enter "-ea" (without quotes) in
 * the field. This enables assertions for the project. 4- Click the "OK" button
 * to save the changes.
 *
 * use F6 to run the default main class
 */
/**
 *
 * @author Saber Elsayed, 2023
 */
public class Harness {

    public static void main(String[] args) {
        System.out.println("----------TASK 1.1-----------");
        testNode();
        System.out.println("----------TASK 1.2-----------");
        testEdge();
        System.out.println("----------TASK 2-----------");
        testTask2();
        System.out.println("----------TASK 3-----------");
        testTask3();
        System.out.println("----------TASK 4-----------");
        testTask4();
        System.out.println("----------TASK 5-----------");
        testTask5();

        System.out.println("----------TASK 6-----------");
        testTask6();
    }

    /**
     * test Node class
     */
    public static void testNode() {
        Node node1 = new Node(2914, "Amaroo");
        Node node2 = new Node(2914, "Bonner");
        Node node3 = new Node(2914, "Gungahlin");

        try {
            // Test getSuburb() method
            String suburb = node1.getSuburb();
            assert suburb.equals("Amaroo");
            System.out.println("getSuburb() test passed");
        } catch (AssertionError e) {
            System.out.println("getSuburb() test failed: " + e.getMessage());
        }

        try {
            // Test getPostcode() method
            int postcode = node1.getPostcode();
            assert postcode == 2914;
            System.out.println("getPostcode() test passed");
        } catch (AssertionError e) {
            System.out.println("getPostcode() test failed: " + e.getMessage());
        }

        try {
            // Test setPostcode() method
            node1.setPostcode(2600);
            int newPostcode = node1.getPostcode();
            assert newPostcode == 2600;
            System.out.println("setPostcode() test passed");
        } catch (AssertionError e) {
            System.out.println("setPostcode() test failed: " + e.getMessage());
        }

        try {
            // Test getAdj() method
            ArrayList<Edge> adj = node1.getAdj();
            assert adj.isEmpty();
            System.out.println("getAdj() test passed");
        } catch (AssertionError e) {
            System.out.println("getAdj() test failed: " + e.getMessage());
        }

        try {
            // Test addAmenity() and getAmenity() methods
            node1.addAmenity("park");
            node1.addAmenity("pool");
            List<String> amenities = node1.getAmenity();
            assert amenities.size() == 2 && amenities.contains("park") && amenities.contains("pool");
            System.out.println("addAmenity() and getAmenity() test passed");
        } catch (AssertionError e) {
            System.out.println("addAmenity() and getAmenity() test failed: " + e.getMessage());
        }

        try {
            // Test removeAmenity() method
            node1.removeAmenity("pool");
            List<String> amenities = node1.getAmenity();
            assert amenities.size() == 1 && amenities.contains("park");
            System.out.println("removeAmenity() test passed");
        } catch (AssertionError e) {
            System.out.println("removeAmenity() test failed: " + e.getMessage());
        }
    }

    /**
     * test Edge class
     *
     */
    public static void testEdge() {
        Node node1 = new Node(1, "Node1");
        Node node2 = new Node(2, "Node2");

        Edge edge1 = new Edge(node2, 5.0);
        Edge edge2 = new Edge(node1, 3.0);

        try {
            // Test the getDestination method
            assert edge1.getDestination() == node2 : "getDestination failed";
            assert edge2.getDestination() == node1 : "getDestination failed";

            // Test the getDistance method
            assert edge1.getDistance() == 5.0 : "getDistance failed";
            assert edge2.getDistance() == 3.0 : "getDistance failed";

            System.out.println("Edge class: all tests passed!");
        } catch (AssertionError ee) {
            System.err.println("Test failed: " + ee.getMessage());
        }

    }

    /**
     * test hashCode
     */
    public static void testTask2() {

        Node n = new Node(2914, "Amaroo");
        Node n2 = new Node(2914, "Bonner");
        System.out.println(n.hashCode());
        System.out.println(n2.hashCode());

        try {
            // Test the getDestination method
            // assert ....
            System.out.println("hashCode() test passed");
        } catch (AssertionError ee) {
            System.err.println("Test failed: " + ee.getMessage());
        }

    }

    /**
     * test task 3 -- it is not complete
     */
    public static void testTask3() {

        Node Amaroo = new Node(2914, "Amaroo");
        Node Bonner = new Node(2914, "Bonner");
        Node Ford = new Node(2914, "Ford");

        // Create a new graph
        Graph graph = new Graph();

        // Test adding nodes to the graph
        graph.addNode(Amaroo);
        System.out.println(graph.getNodeNames().size());

        System.out.println(graph.getNode("Amaroo"));

        // Test adding edges to the graph
        graph.addEdge(Amaroo, Bonner, 2.0);
        graph.getNode("Amaroo").getAdj().size();

        // Test removing edges from the graph
        graph.removeEdge(Amaroo, Bonner, 2.0);

        graph.addEdge(Amaroo, Bonner, 2.0);

        graph.getNode("Amaroo").getAdj().size();

        // Test removing nodes from the graph
        try{
            graph.removeNode(Ford);
        }catch ( IllegalArgumentException e){
            System.out.println("Unable to remove Ford as it does not exist in the graph");
        }


        graph.getNode("Amaroo").getAdj().size();
        graph.getNodeNames().size();

        graph.getNode("Amaroo").equals(Amaroo);
        HashMap<String, Node> nodeNamesOutput = graph.getNodeNames();
        System.out.println(nodeNamesOutput);
    }

    /**
     * testing Task 4
     */
    private static void testTask4() {
        Navigation n = new Navigation();

        Set<String> resultAmenities = n.getAllAmenities(2914);
        System.out.println(resultAmenities);
        List<String> result = n.getAllSuburbs("Swimming Pool");
        System.out.println(result);
    }

    /**
     * Testing Task 5 -- it is not complete
     */
    private static void testTask5() {

        PlacesVisited p1 = new PlacesVisited("Canberra", LocalDate.of(2023, 2, 17));

        try {
            assert "Canberra".equals(p1.getLocation())
                    && p1.getDate().isEqual(LocalDate.of(2023, 2, 17)) : "PlacesVisited class failed";
            System.out.println("PlacesVisited class passed");

        } catch (AssertionError e) {
            System.err.println("PlacesVisited class test failed: " + e.getMessage());
        }

        Navigation n = new Navigation();
        n.addToVisitedPlaces("Amaroo", LocalDate.parse("2019-10-30"));
        n.addToVisitedPlaces("Gungahlin", LocalDate.parse("2018-10-30"));
        n.addToVisitedPlaces("Ford", LocalDate.parse("2023-10-10"));
        List<PlacesVisited> output = n.getVisitedPlaces();
        System.out.println(output);
        List<LocalDate> outputDate = n.getDate("Ford");
        System.out.println(outputDate);
    }

    /**
     * Testing Task 6 -- it is not complete
     */
    private static void testTask6() {

        Navigation n = new Navigation();

        Node source = n.graph.getNode("Amaroo");
        Node target = n.graph.getNode("Gungahlin");

        List<String> path = n.getShortestPath(source, target);
        System.out.println(path);
        path = n.calculateDistanceToAmenity(source, "Hospital");
        System.out.println(path);
    }
}
