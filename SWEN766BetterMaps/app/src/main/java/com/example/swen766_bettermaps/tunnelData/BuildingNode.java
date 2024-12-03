package com.example.swen766_bettermaps.tunnelData;

import java.util.Map;
import java.util.HashMap;

public class BuildingNode implements TunnelNode {
    private Map<TunnelNode, Vector> neighbors;
    String connectedBuilding;
    int nodeID;

    public BuildingNode(int nodeID) {
        this.nodeID = nodeID;
        this.connectedBuilding = "none";
        this.neighbors = new HashMap<>();
    }

    public BuildingNode(String connectedBuilding) {
        this.connectedBuilding = connectedBuilding;
        this.nodeID = 0;
        this.neighbors = new HashMap<>();
    }

    public BuildingNode(String connectedBuilding, int nodeID) {
        this.connectedBuilding = connectedBuilding;
        this.nodeID = nodeID;
        this.neighbors = new HashMap<>();
    }

    
    public Map<TunnelNode, Vector> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Map<TunnelNode, Vector> neighbors) {
        this.neighbors = neighbors;
    }


    @Override
    public void addNeighbor(TunnelNode neighbor, Vector vector) {
        neighbors.put(neighbor, vector);
        
    }

    public String getConnectedBuilding() {
        return connectedBuilding;
    }

    public void setConnectedBuilding(String connectedBuilding) {
        this.connectedBuilding = connectedBuilding;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }
}
