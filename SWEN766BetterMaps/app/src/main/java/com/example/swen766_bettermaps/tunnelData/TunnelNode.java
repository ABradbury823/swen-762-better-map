package com.example.swen766_bettermaps.tunnelData;

import java.util.Map;

public interface TunnelNode {


    public int getNodeID();
    public void addNeighbor(TunnelNode neighbor, Vector vector);
    public Map<TunnelNode, Vector> getNeighbors();
} 