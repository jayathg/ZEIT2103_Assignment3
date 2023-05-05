import java.util.ArrayList;
import java.util.List;

public class Node {

    //Attributes

    private int postCode;

    private String suburb;

    private ArrayList<Edge> adj;

    private ArrayList<String> amenity;
    //Methods

    public Node(int postCode, String suburb) {
        this.postCode = postCode;
        this.suburb = suburb;
    }

    public String getSuburb() {
        return suburb;
    }


    public int getPostcode() {
        return postCode;
    }


    public ArrayList<Edge> getAdj() {
        return adj;
    }

    public void setPostcode(int Postcode) {
    }
    public void addAmenity(String Amenity) {
        amenity.add(Amenity);
    }

    public List<String> getAmenity() {
        return null;
    }

    public void removeAmenity(String Amenity) {
        amenity.remove(Amenity);
    }

    @Override
    public String toString() {
        return "Suburb: " + suburb +"\n Postcode: " + postCode;
    }

    //Need to implement
    @Override
    public int hashCode() {
        return 0;
    }

    //Need to implement
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
