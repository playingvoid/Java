package graphProblems.graph;
import java.util.*;

public class graphNode<SatelliteData> {
    public int nodeId;
    public SatelliteData data;
    public List<graphNode<SatelliteData>> adjacencyList;

    public graphNode(int id){
        nodeId = id;
        data = null;
        adjacencyList = new ArrayList<>();
    }

    public graphNode(int id, SatelliteData data){
        nodeId = id;
        this.data = data;
        adjacencyList = new ArrayList<>();
    }

}
