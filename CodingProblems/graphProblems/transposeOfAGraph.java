package graphProblems;

import java.util.*;

class GraphN
{
    public Integer nodeId;
    public List<GraphN> adjList;

    public GraphN(Integer nodeId){
        this.nodeId = nodeId;
        adjList = new ArrayList<>();
    }

    public void addNode(GraphN node) {
        adjList.add(node);
    }
}

public class transposeOfAGraph {
    HashMap<Integer, List<Integer>> originalGraph;

    public static GraphN bfsTranspose(GraphN root) {

        Queue<GraphN> bfsQ = new LinkedList<>();
        bfsQ.add(root);
        HashMap<Integer, GraphN> discoveredNode = new HashMap<>();
        GraphN transNodeRoot = new GraphN(root.nodeId);
        discoveredNode.put(root.nodeId, transNodeRoot);

        while (!bfsQ.isEmpty()){
            GraphN curr = bfsQ.poll();
            GraphN currTrans = discoveredNode.get(curr.nodeId);
            for(GraphN currAdj : curr.adjList){
                GraphN currAdjTrasn = null;
                if(discoveredNode.containsKey(currAdj.nodeId)){
                    currAdjTrasn = discoveredNode.get(currAdj.nodeId);
                } else {
                    currAdjTrasn = new GraphN(currAdj.nodeId);
                    discoveredNode.put(currAdj.nodeId, currAdjTrasn);
                    bfsQ.add(currAdj);
                }
                currAdjTrasn.addNode(currTrans);
            }
        }
        return transNodeRoot;
    }



    public static  void main(String[] args){
        GraphN node1 = new GraphN(1);
        GraphN node2 = new GraphN(2);
        GraphN node3 = new GraphN(3);
        GraphN node4 = new GraphN(4);

        node1.addNode(node2);
        node2.addNode(node4);
        node2.addNode(node3);
        node3.addNode(node2);
        node3.addNode(node4);
        node4.addNode(node4);

        GraphN transPosed = bfsTranspose(node1);
    }


}
