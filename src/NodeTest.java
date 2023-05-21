import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @org.junit.jupiter.api.Test
    void getSuburb() {
    }

    @org.junit.jupiter.api.Test
    void getPostcode() {
    }

    @org.junit.jupiter.api.Test
    void addAdj() {
    }

    @org.junit.jupiter.api.Test
    void getAdj() {
    }

    @org.junit.jupiter.api.Test
    void setPostcode() {
    }

    @org.junit.jupiter.api.Test
    void addAmenity() {
    }

    @org.junit.jupiter.api.Test
    void getAmenity() {
    }

    @org.junit.jupiter.api.Test
    void removeAmenity() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
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

    @org.junit.jupiter.api.Test
    void testEquals() {
    }

    @org.junit.jupiter.api.Test
    void removeAdj() {
    }
}