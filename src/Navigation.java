import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;


/**
 * The Navigation class handles how the graph itself is used
 * This is done through loading the graph from a file, calculating shortest path,
 * and getting various data from the graph.
 *
 * @author Jayath Gunawardena
 * created on 18/05/2023
 */
public class Navigation {


    //Attributes

    /**
     * Graph used for the Navigation
     */
    protected Graph graph;

    /**
     * ArrayList of PlacesVisited representing when and where places where visited
     */
    protected ArrayList<PlacesVisited> visited;

    //Methods


    /**
     * Navigation class Constructor
     * Creates a new graph and visited ArrayList as well as loads data from the files.
     */
    public Navigation() {
        this.graph = new Graph();
        this.visited = new ArrayList<>();
        loadData();
    }

    /**
     * Load Data method
     * Handles reading data from the files
     * After the data has been read, it then populates the Graph with Nodes, Edges and Amenities
     */
    public void loadData(){
        try{
            //Add Nodes to the graph
            File suburbsFile = new File("Files/au_postcodesNew.txt");
            BufferedReader br = new BufferedReader(new FileReader(suburbsFile));
            String suburbDets = br.readLine();
            List<Node> nodesInOrder = new ArrayList<>();

            // First pass - add nodes
            while(suburbDets != null){
                String[] lineItems = suburbDets.split("\t");
                Node n = new Node(Integer.parseInt(lineItems[0]), lineItems[1]);
                graph.addNode(n);
                // Keeps track of the order of nodes
                nodesInOrder.add(n);
                suburbDets = br.readLine();
            }

            // Close and reopen the reader to prevent excess memory being used.
            br.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * GetAllAmenities method
     * @param postcode Integer representation of the area to search for amenities
     * @return Set of Strings, containing all the amenities within the postcode
     */
    public Set<String> getAllAmenities(int postcode){
        HashSet<String> amenitiesSet = new HashSet<>();
        for(Node node: graph.getNodeNames().values()){
            if(node.getPostcode() == postcode){
                amenitiesSet.addAll(node.getAmenity());
            }
        }
        if(amenitiesSet.isEmpty()){
            throw new IllegalArgumentException("Postcode does not have any amenities");
        }
        return amenitiesSet;
    }

    /**
     * GetAllSuburbs method
     * @param amenity String representation of the amenity to search for
     * @return List of Strings, containing all the suburbs where the amenity can be found
     */
    public List<String> getAllSuburbs(String amenity){
        ArrayList<String> locationList = new ArrayList<>();
        for(Node node: graph.getNodeNames().values()){
            if(node.getAmenity().contains(amenity)){
                locationList.add(node.getSuburb());
            }
        }
        if(locationList.isEmpty()){
            throw new IllegalArgumentException("Amenity is unavailable");
        }
        return locationList;
    }

    /**
     * AddToVisitedPlaces method
     * @param location String representation of the Node name that has been visited
     * @param time LocalDate representation the date of the visit
     */
    public void addToVisitedPlaces(String location, LocalDate time){
        if(graph.nodeNames.containsKey(location)){
            PlacesVisited placesVisited = new PlacesVisited(location, time);
            visited.add(placesVisited);
        }else {
            throw new IllegalArgumentException("Invalid Details");
        }

    }

    /**
     * GetVisitedPlaces method
     * @return List of PlacesVisited, containing the Dates and Location that have been visited,
     * sorted by date visited
     */
    public List <PlacesVisited> getVisitedPlaces(){
        List<PlacesVisited> visitedPlaces = new ArrayList<>();
        for (PlacesVisited placesVisited : visited) {
            boolean isInserted = false;
            for (int i = 0; i < visitedPlaces.size(); i++) {
                if (placesVisited.getDate().isAfter(visitedPlaces.get(i).getDate())) {
                    visitedPlaces.add(i, placesVisited);
                    isInserted = true;
                    break;
                }
            }
            if (!isInserted) {
                visitedPlaces.add(placesVisited);
            }
        }
        if(visitedPlaces.isEmpty()){
            throw new IllegalArgumentException("Empty visitedPlaces ArrayList");
        }
        return visitedPlaces;

    }

    /**
     * GetDate method
     * @param location String representation of the Node name to search for
     * @return List of LocalDate, containing the dates when the Node was visited
     */
    public List <LocalDate> getDate(String location){
        if(!graph.nodeNames.containsKey(location)){
            throw new IllegalArgumentException("Location does not exist");
        }
        List<LocalDate> daysVisited = new ArrayList<>();
        for (PlacesVisited placesVisited : visited) {
            if(placesVisited.getLocation().equals(location)){
                daysVisited.add(placesVisited.getDate());
            }
        }
        if(daysVisited.isEmpty()){
            throw new IllegalArgumentException("The place has never been visited");
        }
        return daysVisited;
    }

    /**
     * CalculateShortestDistance method
     * Implements Dijkstra's algorithm to determine the shortest distance
     * between Nodes.
     * @param source Node object, representing the place to start point for
     *              the shortest distance search
     * @return Map of containing Strings and Object, this actually stores two
     * Maps in it which contain
     * details about how far away each location is and what the previous Node
     * is.
     */
    public Map<String, Object> calculateShortestDistances(Node source) {
        if(!graph.nodeNames.containsKey(source.getSuburb())){
            throw new IllegalArgumentException("Source is not part of graph");
        }
        Map<String, Double> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(node -> distance.get(node.getSuburb())));

        // Initialize distances to infinity and previous nodes to null
        for (Node node : graph.nodeNames.values()) {
            distance.put(node.getSuburb(), Double.MAX_VALUE);
            previous.put(node.getSuburb(), null);
        }

        // The distance from the source to itself is 0
        distance.put(source.getSuburb(), 0.0);
        queue.add(source);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Edge edge : current.getAdj()) {
                Node neighbor = edge.getDestination();
                double newDistance = distance.get(current.getSuburb()) + edge.getDistance();

                if (newDistance < distance.get(neighbor.getSuburb())) {
                    distance.put(neighbor.getSuburb(), newDistance);
                    previous.put(neighbor.getSuburb(), current.getSuburb());

                    // Reorder the queue
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        Map<String, Object> results = new HashMap<>();
        results.put("distance", distance);
        results.put("previous", previous);

        return results;
    }

    /**
     * GetShortestPath method
     * Using calculateShortestDistances(), it calculates the shortest path
     * between a source and target Node
     * @param source Node representing the start of the shortest path search
     * @param target Node representing the end of the shortest path search
     * @return List of Strings, representing the shortest path between two Nodes.
     */
    public List<String> getShortestPath(Node source, Node target){
        if(!graph.nodeNames.containsKey(source.getSuburb()) || !graph.nodeNames.containsKey(target.getSuburb())){
            throw new IllegalArgumentException("Source is not part of graph");
        }
        Map<String, Object> results = calculateShortestDistances(source);
        Map<String, String> previous = (Map<String, String>) results.get("previous");

        // Build the shortest path from the source to the destination
        List<String> path = new LinkedList<>();
        for (String node = target.getSuburb(); node != null; node = previous.get(node)) {
            path.add(0, node);
        }

        if (path.get(0).equals(source.getSuburb())) {
            return path;
        } else {
            throw new RuntimeException("No path exists from " + source.getSuburb() + " to " + target);
        }
    }

    /**
     * GetShortestPath method
     * Using calculateShortestDistances(), it calculates the shortest path
     * between a source and a target amenity
     * @param source Node representing the start of the shortest path search
     * @param amenity String representing the amenity to find the nearest one
     * @return List of Strings, representing the shortest path between the Node
     * and the amenity along with the distance to reach it.
     */
    public List<String> calculateDistanceToAmenity(Node source, String amenity){
        if(!graph.nodeNames.containsKey(source.getSuburb())){
            throw new IllegalArgumentException("Source is not part of graph");
        }
        Map<String, Object> results = calculateShortestDistances(source);
        Map<String, String> previous = (Map<String, String>) results.get("previous");
        Map<String, Double> distances = (Map<String, Double>) results.get("distance");

        Node closestAmenityNode = null;
        Double shortestDistance = Double.MAX_VALUE;

        // Find the nearest node with the amenity
        for (Node node : graph.nodeNames.values()) {
            if (node.getAmenity().contains(amenity)) {
                Double distanceToNode = distances.get(node.getSuburb());
                if(distanceToNode < shortestDistance){
                    shortestDistance = distanceToNode;
                    closestAmenityNode = node;
                }
            }
        }

        if (closestAmenityNode == null) {
            throw new IllegalArgumentException("No path exists from " + source.getSuburb() + " to any node with the amenity");
        }

        // Build the shortest path from the source to the destination
        List<String> path = new LinkedList<>();
        for (String node = closestAmenityNode.getSuburb(); node != null; node = previous.get(node)) {
            path.add(0, node);
        }
        //Ensures that the final answer is a nice human-readable number
        BigDecimal bd = new BigDecimal(shortestDistance);
        if(shortestDistance < 10){
            bd = bd.round(new MathContext(2));
        }else{
            bd = bd.round(new MathContext(3));
        }
        shortestDistance = bd.doubleValue();
        path.add(String.valueOf(shortestDistance));
        return path;
    }


}
