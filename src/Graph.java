import java.util.HashMap;

/**
 * The Graph class handles how the Graph is constructed.
 * This method adds Nodes, Edges and handles the subsequent removal methods
 *
 * @author Jayath Gunawardena
 * created on 19/05/2023
 */
public class Graph {

    //Attributes
    /**
     * HashMap of String and Nodes of all Nodes in the Graph
     */
    protected HashMap<String, Node> nodeNames;

    //Methods

    /**
     * Graph constructor method
     * Initialises the HashMap used to store the nodes
     */
    public Graph() {
        this.nodeNames = new HashMap<>();
    }

    /**
     * AddNode method
     * @param n Node which is to be put into the hashmap of nodes in the graph
     * @throws NullPointerException if n Node is null
     */
    public void addNode(Node n) {
        if(n == null) {
            throw new NullPointerException("Node cannot be null");
        }
        nodeNames.put(n.getSuburb(),n);
    }

    /**
     * AddEdge method
     * Only adds an edge if it does not exist
     * @param from Node which is the start of the Edge
     * @param to Node which is the destination of the Edge
     * @param distance Double which is the distance between the two nodes
     * @throws NullPointerException if either Node is null
     * @throws IllegalArgumentException if Nodes are identical or if distance is neg
     */
    public void addEdge(Node from, Node to, double distance){
        if(from == null || to == null) {
            throw new NullPointerException("Node cannot be null");
        }
        if(from.equals(to)) {
            throw new IllegalArgumentException("The 'from' and 'to' nodes cannot be the same");
        }
        if(distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        Edge e = new Edge(to, distance);
        if(!from.getAdj().contains(e) || !to.getAdj().contains(e)){
            from.addAdj(e);
        }
    }

    /**
     * RemoveEdge method
     * Only removes an edge if it connects between two Nodes
     * @param from Node which is the start of the Edge
     * @param to Node which is the destination of the Edge
     * @param distance Double which is the distance between the two nodes
     */
    public void removeEdge(Node from, Node to, double distance){
        Edge e = new Edge(to, distance);
        if(from.getAdj().contains(e) || to.getAdj().contains(e)){
            from.removeAdj(e);
        }
    }

    /**
     * RemoveNode method
     * @param node Node which is to be removed from the graph
     */
    public void removeNode(Node node){
        for(Edge edge : node.getAdj() ){
            node.removeAdj(edge);
        }
        nodeNames.remove(node.getSuburb());
    }

    /**
     * GetNode method
     * @param name String representation of the Node's suburb name
     * @return Node representation of the found Node
     */
    public Node getNode(String name){
        return nodeNames.get(name);
    }

    /**
     * GetNodeNames method
     * @return HashMap of String and Nodes, which store the Node's
     * suburb as the key and Node as the value
     */
    public HashMap<String, Node> getNodeNames() {
        return nodeNames;
    }


}
