import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
    void setInvalidAdj_AdjacentNode() {
        Node node = new Node(1234, "Test Suburb");
        Edge edge = new Edge(node, 10.5); // node cannot be adjacent to itself
        assertThrows(IllegalArgumentException.class, () -> node.addAdj(edge));

    }

    @Test
    void setInvalidAdj_DuplicateNode() {
        Node node = new Node(1234, "Test Suburb");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge1 = new Edge(node2, 10.5);
        Edge edge2 = new Edge(node2, 10.5); // duplicate adjacency
        node.addAdj(edge1);
        assertThrows(IllegalArgumentException.class, () -> node.addAdj(edge2));
    }
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
    void removeAdj() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge = new Edge(node2, 10.5);
        node1.addAdj(edge);
        node1.removeAdj(edge);
        assertFalse(node1.getAdj().contains(edge));
    }

    @Test
    void removeAdj_EdgeDoesntExist_False(){
        Node node1 = new Node(1234, "Test Suburb 1");
        Edge edge = new Edge(node1, 10.5);
        assertThrows(IllegalArgumentException.class, () -> node1.removeAdj(edge));
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
        node.addAmenity("House");
        node.removeAmenity("School");
        assertFalse(node.getAmenity().contains("School"));
    }

    @Test
    void removeAmenity_NoSuchAmenity_False(){
        Node node = new Node(1234, "Test Suburb");
        assertThrows(IllegalArgumentException.class, () -> node.removeAmenity("School"));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Node node = new Node(1234, "Test Suburb");
        String expected = "Node{" +
                "postCode=" + 1234 +
                ", suburb='Test Suburb" + '\'' +
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
        assertEquals(7876777,n.hashCode());
    }

    @Test
    void hashCode_OddLengthHash_True(){
        Node n = new Node(1234, "NewSuburb");
        //34 (Expected result) is calculated by manually
        // calculating the hash using the algorithm
        assertEquals(1754642874, n.hashCode());
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
        for (int i = 0; i < 10000000; i++) {
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

        assertTrue(collisionList.size() < 5);
    }

    @Test
    void hashCode_CollisionTest_GivenData_True(){
        Navigation nav = new Navigation();

        HashMap<String, Node> nodeNames = nav.graph.nodeNames;
        ArrayList<Integer> collisionList = new ArrayList<>();
        for (HashMap.Entry<String, Node> set :
                nodeNames.entrySet()) {
            collisionList.add(set.getValue().hashCode());
        }
        Set<Integer> collisionSet = new HashSet<>(collisionList);
        assertEquals(collisionSet.size(),collisionList.size());
    }
      
    @Test
    void testEquals() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(1234, "Test Suburb 1");
        assertEquals(node1, node2);
   
    }

    @Test
    void equals_SameObject_True(){
        Node n1 = new Node(5432, "Test Suburb");
        Node n2 = new Node(5432, "Test Suburb");
        assertTrue(n1.equals(n2));
    }

    @Test
    void equals_DifferentPostCode_False(){
        Node n1 = new Node(5432, "Test Suburb");
        Node n2 = new Node(1234, "Test Suburb");
        assertFalse(n1.equals(n2));
    }
    @Test
    void equals_DifferentSuburb_False(){
        Node n1 = new Node(5432, "Test Suburb");
        Node n2 = new Node(5432, "New Suburb");
        assertFalse(n1.equals(n2));
    }

    @Test
    void equals_DifferentObject_False(){
        Node n1 = new Node(5432, "Test Suburb");
        Edge e1 = new Edge(n1,10.0);
        assertFalse(n1.equals(e1));
    }

}

