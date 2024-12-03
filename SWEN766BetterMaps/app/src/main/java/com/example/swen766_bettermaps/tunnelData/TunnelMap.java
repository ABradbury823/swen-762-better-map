package com.example.swen766_bettermaps.tunnelData;

import java.util.*;


public class TunnelMap{
    private Map<Integer, TunnelNode> vertices;

    public TunnelMap() {
        vertices = new HashMap<>();
    }

    public void addNode(TunnelNode newNode) {
        vertices.put(newNode.getNodeID(), newNode);
    }

    public boolean contains(int nodeID) {
        return vertices.containsKey(nodeID);
    }

    public void connect(TunnelNode nodeOne, TunnelNode nodeTwo, int weight, CardinalDirection dir) {
        Vector n1Vector = new Vector(dir, weight);
        Vector n2Vector = new Vector(dir.opposite(), weight);

        nodeOne.addNeighbor(nodeTwo, n1Vector);
        nodeTwo.addNeighbor(nodeOne, n2Vector);
    }

    public void connect(TunnelNode nodeOne, TunnelNode nodeTwo, int weight, CardinalDirection dirOne, CardinalDirection dirTwo){
        Vector n1Vector = new Vector(dirOne, weight);
        Vector n2Vector = new Vector(dirTwo, weight);

        nodeOne.addNeighbor(nodeTwo, n1Vector);
        nodeTwo.addNeighbor(nodeOne, n2Vector);
    }    

    public int size() {
        return vertices.size();
    }

    public List<Integer> breadthFirstPath(int startNodeID, int endNodeID, TunnelMap tunnelMap) {
        TunnelNode startNode = tunnelMap.contains(startNodeID) ? tunnelMap.vertices.get(startNodeID) : null;
        TunnelNode endNode = tunnelMap.contains(endNodeID) ? tunnelMap.vertices.get(endNodeID) : null;

        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("Start or end node not found in the tunnel map.");
        }

        Queue<TunnelNode> queue = new LinkedList<>();
        Map<TunnelNode, TunnelNode> visited = new HashMap<>();

        queue.add(startNode);
        visited.put(startNode, null);

        while (!queue.isEmpty()) {
            TunnelNode currentNode = queue.poll();

            if (currentNode == endNode) {
                // Reconstruct the path
                List<Integer> path = new LinkedList<>();
                TunnelNode pathNode = endNode;

                while (pathNode != null) {
                    path.add(0, pathNode.getNodeID());
                    pathNode = visited.get(pathNode);
                }

                return path;
            }

            // Explore neighbors
            for (TunnelNode neighbor : currentNode.getNeighbors().keySet()) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, currentNode);
                    queue.add(neighbor);
                }
            }
        }

        // Return null if no path found
        return null;
    }

    public Map<Integer, TunnelNode> getVertices() {
        return vertices;
    }

    public void setVertices(Map<Integer, TunnelNode> vertices) {
        this.vertices = vertices;
    }

    


}
