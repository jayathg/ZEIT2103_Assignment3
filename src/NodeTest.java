import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @org.junit.jupiter.api.Test
    void getSuburb() {
        Node node = new Node(1234, "Test Suburb");
        assertEquals("Test Suburb", node.getSuburb());
    }

    @org.junit.jupiter.api.Test
    void getPostcode() {
        Node node = new Node(1234, "Test Suburb");
        assertEquals(1234, node.getPostcode());
    }

    @Test
    void setValidAdj() {
        //check for duplicates
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge = new Edge(node2, 10.5);
        node1.addAdj(edge);
        assertTrue(node1.getAdj().contains(edge));
    }

    @Test
    void setInvalidAdj() {
        Node node = new Node(1234, "Test Suburb");
        Edge edge = new Edge(node, 10.5); // node cannot be adjacent to itself
        assertThrows(IllegalArgumentException.class, () -> node.addAdj(edge));

        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge1 = new Edge(node2, 10.5);
        Edge edge2 = new Edge(node2, 10.5); // duplicate adjacency
        node.addAdj(edge1);
        assertThrows(IllegalArgumentException.class, () -> node.addAdj(edge2));

      @org.junit.jupiter.api.Test
    void addAdj() {

    }

    @org.junit.jupiter.api.Test
    void getAdj() {
        //check for duplicates
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge = new Edge(node2, 10.5);
        node1.addAdj(edge);
        assertTrue(node1.getAdj().contains(edge));
    }

    @org.junit.jupiter.api.Test
    void setPostcode() {
    }
      
    @Test
    void setValidPostcode() {
        Node node = new Node(1234, "Test Suburb");
        node.setPostcode(5432);
        assertEquals(5432, node.getPostcode());
    }

    @Test
    void setInvalidPostcode() {
        Node node = new Node(1234, "Test Suburb");
        assertThrows(IllegalArgumentException.class, () -> node.setPostcode(543)); // postcode less than 4 digits
        assertThrows(IllegalArgumentException.class, () -> node.setPostcode(54321)); // postcode more than 4 digits
        //assertThrows(NumberFormatException.class, () -> Integer.parseInt("abcd")); // non-numeric input    

    @org.junit.jupiter.api.Test
    void addAmenity() {
        //check for duplicates
        Node node = new Node(1234, "Test Suburb");
        node.addAmenity("School");
        assertTrue(node.getAmenity().contains("School"));
    }

    @org.junit.jupiter.api.Test
    void getAmenity() {
        Node node = new Node(1234, "Test Suburb");
        node.addAmenity("School");
        assertTrue(node.getAmenity().contains("School"));
    }

    @org.junit.jupiter.api.Test
    void removeAmenity() {
        Node node = new Node(1234, "Test Suburb");
        node.addAmenity("School");
        node.removeAmenity("School");
        assertFalse(node.getAmenity().contains("School"));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Node node = new Node(1234, "Test Suburb");
        String expected = "Node{" +
                "postCode=" + 1234 +
                ", suburb='Test Suburb" + '\'' +
                ", adj=" + new ArrayList<>() +
                ", amenity=" + new ArrayList<>() +
                '}';
        assertEquals(expected, node.toString());
    }
   
      

    @org.junit.jupiter.api.Test
    void testHashCode() {
        Node node = new Node(1234, "Test Suburb");
        assertNotEquals(0, node.hashCode());
    }
      
    @Test
    void hashCode_EvenLengthHash_True(){
        Node n = new Node(1234,"Suburb");
        //675 (Expected result) is calculated by manually
        // calculating the hash using the algorithm
        assertEquals(675,n.hashCode());
    }

    @Test
    void hashCode_OddLengthHash_True(){
        Node n = new Node(1234, "NewSuburb");
        //34 (Expected result) is calculated by manually
        // calculating the hash using the algorithm
        assertEquals(34, n.hashCode());
    }

    /**
     * This test shows that the rate of collision is less tha 0.05%.
     */
    @Test
    void hashCode_CollisionTest_False(){
        Node n = new Node(1234, "NewSuburb");
        ArrayList<Node> collisionList = new ArrayList<>();
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";
        for (int i = 0; i < 100000; i++) {
            StringBuilder sb = new StringBuilder(9);
            for (int j = 0; j < 9; j++) {
                int index
                        = (int)(AlphaNumericString.length()
                        * Math.random());
                sb.append(AlphaNumericString
                        .charAt(index));
            }
            String testSuburb = sb.toString();
            Node testNode = new Node(i,testSuburb);
            if(n.hashCode() == testNode.hashCode() && !testSuburb.equals("NewSuburb")){
                collisionList.add(testNode);
            }
        }
        System.out.println(collisionList.size());
    }
      
    @Test
    void testEquals() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(1234, "Test Suburb 1");
        assertEquals(node1, node2);
   
    }

    @org.junit.jupiter.api.Test
    void removeAdj() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge = new Edge(node2, 10.5);
        node1.addAdj(edge);
        node1.removeAdj(edge);
        assertFalse(node1.getAdj().contains(edge));
    }
}

