import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// TODO: 9/5/2023 TODO: loadData(), getAllAmenities(), getAlLSuburbs(), addToVisitedPlaces(),getVisitedPlaces(), getDate(), getShortestPath(),calculateDistanceToAmenities() 
public class Navigation {

    //Attributes

    protected Graph graph;

    protected ArrayList<PlacesVisited> visited;

    //Methods


    public Navigation() {
        this.graph = new Graph();
        this.visited = new ArrayList<>();
    }

    public void loadData(){}


    public Set<String> getAllAmenities(int postcode){
        return null;
    }

    public List<String> getAllSuburbs(String amenity){
        return null;
    }

    public void addToVisitedPlaces(String location, LocalDate time){
    }

    public List <PlacesVisited> getVisitedPlaces(){
        return null;
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
