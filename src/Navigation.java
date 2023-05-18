import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;

public class Navigation {

    //Attributes

    protected Graph graph;

    protected ArrayList<PlacesVisited> visited;

    //Methods


    public Navigation() {
        this.graph = new Graph();
        this.visited = new ArrayList<>();
        loadData();
    }

    public void loadData(){
        try{
            //Add Nodes
            File suburbsFile = new File("Files/suburbs_dist.txt");
            BufferedReader br = new BufferedReader(new FileReader(suburbsFile));
            String suburbDets = br.readLine();
            List<Node> nodesInOrder = new ArrayList<>();

            // First pass - add nodes
            while(suburbDets != null){
                String[] lineItems = suburbDets.split("\t");
                Node n = new Node(Integer.parseInt(lineItems[0]), lineItems[1]);
                graph.addNode(n);
                nodesInOrder.add(n); // keep track of the order of nodes
                suburbDets = br.readLine();
            }

// Close and reopen the reader
            br.close();
            br = new BufferedReader(new FileReader(suburbsFile));

// Reset suburbDets for the second pass
            suburbDets = br.readLine();

// Second pass - add edges
            while(suburbDets != null){
                String[] lineItems = suburbDets.split("\t");
                Node n = nodesInOrder.get(nodesInOrder.indexOf(new Node(Integer.parseInt(lineItems[0]), lineItems[1])));

                // Add Adjacency
                for (int j = 2; j < lineItems.length; j++) {
                    if(!lineItems[j].isEmpty() && !lineItems[j].equals("0")){
                        Node adjNode = nodesInOrder.get(j-2); // get the node from the list
                        Edge e = new Edge(adjNode,Double.parseDouble(lineItems[j]));
                        if(!n.getAdj().contains(e)){
                            n.addAdj(e);
                        }
                    }
                }
                suburbDets = br.readLine();
            }

            //Add Amenities
            File amenitiesFile = new File("Files/amenities.txt");
            br = new BufferedReader(new FileReader(amenitiesFile));
            String amenities = br.readLine();
            while(amenities != null) {
                String[] lineItems = amenities.split(",");
                String nodeName = lineItems[0];
                int colonIndex = nodeName.indexOf(':');

                if(colonIndex != -1){
                    Node node = graph.getNode(nodeName.substring(0, colonIndex));
                    String firstAmenity = nodeName.substring(colonIndex + 1);
                    if (!firstAmenity.isEmpty()) {
                        node.addAmenity(firstAmenity);
                    }
                    for (int j = 1; j < lineItems.length; j++) {
                        node.addAmenity(lineItems[j]);
                    }
                }
                amenities = br.readLine();
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


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

    public void addToVisitedPlaces(String location, LocalDate time){
        if(graph.nodeNames.containsKey(location)){
            PlacesVisited placesVisited = new PlacesVisited(location, time);
            visited.add(placesVisited);
        }else {
            throw new IllegalArgumentException("Invalid Details");
        }

    }

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
