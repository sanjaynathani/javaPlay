package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GraphOps {

    public static void dfs(Node node, Map<Node, Long> resultMap){
        node.setVisited(true);
        List<Node> neighbours = node.getNeighbours();
        long count = 0;
        for(Node neighbour : neighbours){
            if(node.getNodeColor().equals(neighbour.getNodeColor())){
                resultMap.compute(neighbour, (k,v) ->  v == null ? 1L : v+1);
                count = count + resultMap.get(neighbour);
            }
            if(neighbour != null && !neighbour.isVisited()) {
                dfs(neighbour, resultMap);
            }
        }
        resultMap.put(node, count);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, NodeColor.RED);
        Node node2 = new Node(2, NodeColor.RED);
        Node node3 = new Node(3, NodeColor.YELLOW);
        Node node4 = new Node(4, NodeColor.BLUE);
        Node node5 = new Node(5, NodeColor.YELLOW);
        Node node6 = new Node(6, NodeColor.BLUE);
        Node node7 = new Node(7, NodeColor.YELLOW);
        Node node8 = new Node(8, NodeColor.YELLOW);
        Node node9 = new Node(9, NodeColor.BLUE);
        Node node10 = new Node(10, NodeColor.BLUE);
        Node node11 = new Node(11, NodeColor.YELLOW);
        Node node12 = new Node(12, NodeColor.RED);
        Node node13 = new Node(13, NodeColor.BLUE);
        Node node14 = new Node(14, NodeColor.RED);
        Node node15 = new Node(15, NodeColor.BLUE);

        node1.addNeighbour(node2, node6);
        node2.addNeighbour(node1, node3, node7);
        node3.addNeighbour(node2, node4, node8);
        node4.addNeighbour(node3, node5, node9);
        node5.addNeighbour(node4, node10);
        node6.addNeighbour(node7, node11, node1);
        node7.addNeighbour(node6, node8, node12, node2);
        node8.addNeighbour(node7, node9, node13, node3);
        node9.addNeighbour(node8, node10, node14, node4);
        node10.addNeighbour(node9, node15, node5);
        node11.addNeighbour(node12, node5);
        node12.addNeighbour(node11, node13, node6);
        node13.addNeighbour(node12, node14, node7);
        node14.addNeighbour(node13, node15, node9);
        node15.addNeighbour(node14, node10);

        Map<Node, Long> resultMap = new HashMap<>();
        dfs(node1, resultMap);
        long max = 0;
        Node maxNode = null;
        for(Node key : resultMap.keySet()){
            if(max < resultMap.get(key)){
                max = resultMap.get(key);
                maxNode = key;
            }
        }
        System.out.println(max);
    }
}

class Node {
    long nodeId;
    NodeColor nodeColor;
    boolean visited;
    List<Node> neighbours;

    public Node(long nodeId, NodeColor nodeColor){
        this.nodeId = nodeId;
        this.nodeColor = nodeColor;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public NodeColor getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(NodeColor nodeColor) {
        this.nodeColor = nodeColor;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNeighbour(Node... nodes){
        if(neighbours == null){
            neighbours = new ArrayList<>();
        }
        neighbours.addAll(Arrays.asList(nodes));
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", nodeColor=" + nodeColor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return nodeId == node.nodeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }
}

enum NodeColor {
    RED("RED"),
    BLUE("BLUE"),
    YELLOW("YELLOW");

    String colorName;

    NodeColor(String colorName1){
        colorName = colorName1;
    }
}
