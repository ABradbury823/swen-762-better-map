package com.example.swen766_bettermaps.tunnelData;

import java.util.Map;
import java.util.HashMap;

public class IntersectionNode implements TunnelNode {

    private Map<TunnelNode, Vector> neighbors;
    private String intersectionType; 
    private int nodeID;

    

    public IntersectionNode(String intersectionType) {
        this.intersectionType = intersectionType;
        this.nodeID = 0;
        this.neighbors = new HashMap<>();
    }

    public IntersectionNode(int nodeID) {
        this.nodeID = nodeID;
        this.intersectionType = "none";
        this.neighbors = new HashMap<>();
    }

    public IntersectionNode(String intersectionType, int nodeID) {
        this.intersectionType = intersectionType;
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

    public String getIntersectionType() {
        return intersectionType;
    }

    public void setIntersectionType(String intersectionType) {
        this.intersectionType = intersectionType;
    }

    @Override
    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }
}
