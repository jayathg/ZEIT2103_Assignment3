import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        this.adj = new ArrayList<>();
        this.amenity = new ArrayList<>();

    }

    public String getSuburb() {
        return suburb;
    }


    public int getPostcode() {
        return postCode;
    }

    public void addAdj(Edge e){
        if(!adj.contains(e)) {
            adj.add(e);

        }
    }
    public ArrayList<Edge> getAdj() {
        return adj;
    }

    public void setPostcode(int Postcode) {
        this.postCode = Postcode;
    }
    public void addAmenity(String Amenity) {
        amenity.add(Amenity);
    }

    public List<String> getAmenity() {
        return amenity;
    }

    public void removeAmenity(String Amenity) {
        amenity.remove(Amenity);
    }

    @Override
    public String toString() {
        return "Node{" +
                "postCode=" + postCode +
                ", suburb='" + suburb + '\'' +
                '}';
    }

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


    public void removeAdj(Edge e) {
        adj.remove(e);
    }
}
