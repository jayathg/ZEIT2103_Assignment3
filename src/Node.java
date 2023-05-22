import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Node class handles how each Node is constructed.
 * This method adds Postcodes, Suburbs, Adjacent Edges and Amenities and handles the subsequent removal methods
 *
 * @author Jayath Gunawardena
 * created on 19/05/2023
 */
public class Node {

    //Attributes

    private int postCode;

    private String suburb;

    private ArrayList<Edge> adj;

    private ArrayList<String> amenity;

    //Methods

    /**
     * Node Constructor
     * @param postCode int Representation of the Node's postcode attribute
     * @param suburb String Representation of the Node's suburb name attribute
     */
    public Node(int postCode, String suburb) {
        this.postCode = postCode;
        this.suburb = suburb;
        this.adj = new ArrayList<>();
        this.amenity = new ArrayList<>();

    }

    /**
     * GetSuburb method
     * @return String Representation of the Node's suburb name attribute
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * GetPostcode method
     * @return int Representation of the Node's postcode attribute
     */
    public int getPostcode() {
        return postCode;
    }

    /**
     * AddAdj method
     * @param e Edge to be added to the Node's adjacency ArrayList
     */
    public void addAdj(Edge e){
        if(!adj.contains(e)) {
            adj.add(e);

        }
    }

    /**
     * GetAdj method
     * @return ArrayList of Edge's that are adjacent to the given node.
     */
    public ArrayList<Edge> getAdj() {
        return adj;
    }

    /**
     * RemoveAdj method
     * @param e Edge to be removed to the Node's adjacency ArrayList
     */
    public void removeAdj(Edge e) {
        adj.remove(e);
    }

    /**
     * SetPostcode method
     * @param Postcode int representation of the Node's postcode attribute
     */
    public void setPostcode(int Postcode) {
        this.postCode = Postcode;
    }

    /**
     * AddAmenity method
     * @param Amenity String representation of an amenity to be added to the Node's amenity ArrayList
     */
    public void addAmenity(String Amenity) {
        amenity.add(Amenity);
    }

    /**
     * GetAmenity method
     * @return List of String representations of the amenities that are in the Node's amenity ArrayList
     */
    public List<String> getAmenity() {
        return amenity;
    }

    /**
     * RemoveAmenity method
     * @param Amenity String representation of an amenity to be removed to the Node's amenity ArrayList
     */
    public void removeAmenity(String Amenity) {
        amenity.remove(Amenity);
    }

    /**
     * toString method
     * @return String representation of the Node
     */
    @Override
    public String toString() {
        return "Node{" +
                "postCode=" + postCode +
                ", suburb='" + suburb + '\'' +
                '}';
    }

    /**
     * hashCode method
     * Overrides the default hashCode method
     * @return Integer representation of the Node's hashcode
     */
    @Override
    public int hashCode() {
        String hashString = this.suburb + this.postCode;
        //Pads the hashString to be an even length
        if(hashString.length() % 2 == 1){
            hashString = hashString + 0;
        }
        //Converts hashString to an integer
        int hashInt = 0;
        for (int i = 0; i < hashString.length(); i++) {
            hashInt += hashString.charAt(i);
        }
        //Take hashString value and squares it
        String squareHash = String.valueOf(hashInt * hashInt);
        //Get new number that is 0 + length, 1 + length -1 etc (mod 10 if necessary)
        int[] hashArr = new int[squareHash.length()/2];
        for (int i = 0; i < squareHash.length()/2; i++) {
            hashArr[i] = (Integer.parseInt(String.valueOf(squareHash.charAt(i))) + Integer.parseInt(String.valueOf(squareHash.charAt(squareHash.length()-(i+1))))) % 10;
        }
        int tempInt = 0;
        for (int j : hashArr) {
            tempInt = tempInt * 10 + j;
        }
        //Square result
        squareHash = String.valueOf(tempInt * tempInt);
        //Get new number that is 0 + length, 1 + length -1 etc (mod 10 if necessary)
        hashArr = new int[squareHash.length()/2];
        for (int i = 0; i < squareHash.length()/2; i++) {
            hashArr[i] = (Integer.parseInt(String.valueOf(squareHash.charAt(i))) + Integer.parseInt(String.valueOf(squareHash.charAt(squareHash.length()-(i+1))))) % 10;
        }
        tempInt = 0;
        for (int j : hashArr) {
            tempInt = tempInt * 10 + j;
        }
        return tempInt;
    }

    /**
     * Equals method
     * Overrides the default equals method
     * @param obj Object to compare against this node object
     * @return Boolean dependent on if the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Node node = (Node) obj;
        return postCode == node.postCode &&
                Objects.equals(suburb, node.suburb) &&
                Objects.equals(adj, node.adj) &&
                Objects.equals(amenity, node.amenity);
    }


}
