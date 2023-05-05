import java.nio.file.Files;
import java.util.HashMap;

public class Graph {

    //Attributes

    protected HashMap<String, Node> nodeNames;

    //Methods
    public void addNode(Node n) {

    }

    public void addEdge(Node from, Node to, double distance){
    }

    public void removeEdge(Node from, Node to, double distance){}
    public void removeNode(Node node){
    }

    public Node getNode(String name){
        return nodeNames.get(name);
    }
    public HashMap<String, Node> getNodeNames() {
        return nodeNames;
    }


}
