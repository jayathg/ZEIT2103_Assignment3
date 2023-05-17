import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    //Attributes
    protected HashMap<String, Node> nodeNames;

    //Methods
    public Graph() {
        this.nodeNames = new HashMap<>();
    }

    public void addNode(Node n) {
        nodeNames.put(n.getSuburb(),n);
    }

    public void addEdge(Node from, Node to, double distance){
        Edge e = new Edge(to, distance);
        if(!from.getAdj().contains(e) || !to.getAdj().contains(e)){
            from.addAdj(e);
        }
    }

    public void removeEdge(Node from, Node to, double distance){
        Edge e = new Edge(to, distance);
        if(from.getAdj().contains(e) || to.getAdj().contains(e)){
            from.removeAdj(e);
        }
    }
    public void removeNode(Node node){
        for(Edge edge :node.getAdj() ){
             node.removeAdj(edge);
        }
    }

    public Node getNode(String name){
        return nodeNames.get(name);
    }
    public HashMap<String, Node> getNodeNames() {
        return nodeNames;
    }


}
