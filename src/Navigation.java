import java.io.*;
import java.time.LocalDate;
import java.util.*;

// TODO: 9/5/2023 TODO: loadData(), getAllAmenities(), getAlLSuburbs(), addToVisitedPlaces(),getVisitedPlaces(), getDate(), getShortestPath(),calculateDistanceToAmenities() 
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
            while(suburbDets != null){
                String[] lineItems = suburbDets.split("\t");
                Node n = new Node(Integer.parseInt(lineItems[0]), lineItems[1]);
                graph.addNode(n);

                //Add Adjacency
                for (int j = 2; j < lineItems.length; j++) {
                    if(!lineItems[j].equals("") && !lineItems[j].equals("0")){
                        Node adjNode = graph.nodeNames.get(j-2);
                        Edge e = new Edge(adjNode,Double.parseDouble(lineItems[j]));
                        if(!n.getAdj().contains(e)){
                            n.addAdj(e);
                            graph.addEdge(n, adjNode, Double.parseDouble(lineItems[j]));
                        }
                    }
                }

                suburbDets = br.readLine();
            }
            br.close();
            //Add Amenities
            File amenitiesFile = new File("Files/amenities.txt");
            br = new BufferedReader(new FileReader(amenitiesFile));
            String amenities = br.readLine();
            while(amenities != null) {
                String[] lineItems = amenities.split(",");
                String nodeName = lineItems[0];
                int colonIndex = nodeName.indexOf(':');

                if(colonIndex != -1 && colonIndex != nodeName.length()){
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
        return amenitiesSet;
    }

    public List<String> getAllSuburbs(String amenity){
        ArrayList<String> locationList = new ArrayList<>();
        for(Node node: graph.getNodeNames().values()){
            if(node.getAmenity().contains(amenity)){
                locationList.add(node.getSuburb());
            }
        }
        return locationList;
    }

    public void addToVisitedPlaces(String location, LocalDate time){
        PlacesVisited placesVisited = new PlacesVisited(location, time);
        visited.add(placesVisited);
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

        return visitedPlaces;

    }

    public List <LocalDate> getDate(String location){
        return null;
    }

    public static List <String> getShortestPath(Node source, Node target){
        return null;
    }

    public List<String> calculateDistanceToAmenity(Node source, String amenity){
        return null;
    }
}
