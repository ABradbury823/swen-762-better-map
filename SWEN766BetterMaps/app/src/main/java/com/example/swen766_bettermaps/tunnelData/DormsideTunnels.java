package com.example.swen766_bettermaps.tunnelData;

import java.util.List;

public class DormsideTunnels {
    TunnelMap map;
    public DormsideTunnels (){
        map = new TunnelMap();

        TunnelNode graceWatson = new BuildingNode("Grace Watson Hall", 25);
        TunnelNode Baker = new BuildingNode("Frances Baker Hall", 29);
        TunnelNode Gleason = new BuildingNode("Kate Gleason", 35);
        TunnelNode Colby = new BuildingNode("Eugene Colby Hall", 33);
        TunnelNode Sol = new BuildingNode("Sol Heumann", 47);
        TunnelNode Gibson = new BuildingNode("Carleton Gibson Hall", 49);
        TunnelNode Peterson = new BuildingNode("Peter Peterson Hall", 50);
        TunnelNode resHallD = new BuildingNode("Residence Hall D", 52);
        TunnelNode lbj = new BuildingNode("Lyndon Baines Johnson", 60);
        TunnelNode Fish = new BuildingNode("Helen Fish", 41);
        TunnelNode Commons = new BuildingNode("Hettie L. Shumway Dining Commons", 55);
        TunnelNode residenceHallA = new BuildingNode("Residence Hall A", 28);
        TunnelNode residenceHallB = new BuildingNode("Residence Hall B", 30);
        TunnelNode residenceHallC = new BuildingNode("Residence Hall C", 32);
        TunnelNode Ellingson = new BuildingNode("Mark Ellingson", 51);
        TunnelNode DSP = new BuildingNode("Douglas Sprague Perry", 43);

        map.addNode(graceWatson);
        map.addNode(Baker);
        map.addNode(Gleason);
        map.addNode(Colby);
        map.addNode(Sol);
        map.addNode(Gibson);
        map.addNode(Peterson);
        map.addNode(resHallD);
        map.addNode(lbj);
        map.addNode(Fish);
        map.addNode(Commons);
        map.addNode(residenceHallA);
        map.addNode(residenceHallB);
        map.addNode(residenceHallC);
        map.addNode(Ellingson);
        map.addNode(DSP);

        
        
        map.connect(lbj, Ellingson, 1, CardinalDirection.SOUTH);
        TunnelNode EP = new IntersectionNode(1);
        map.addNode(EP);
        map.connect(Ellingson, EP, 2, CardinalDirection.SOUTH);
        map.connect(EP,Peterson,1,CardinalDirection.WEST);
        TunnelNode PCA = new IntersectionNode(2);
        map.addNode(PCA);
        map.connect(Peterson, PCA, 1, CardinalDirection.WEST);
        map.connect(PCA, Commons, 2, CardinalDirection.NORTH);
        map.connect(PCA, resHallD, 3, CardinalDirection.SOUTH);
        TunnelNode GR = new IntersectionNode(3);
        map.addNode(GR);
        map.connect(EP, GR, 2, CardinalDirection.WEST);
        map.connect(GR,EP,2,CardinalDirection.EAST);
        map.connect(GR, Gibson, 1, CardinalDirection.EAST);
        TunnelNode GS = new IntersectionNode(4);
        map.addNode(GS);
        map.connect(Gibson, GS, 1, CardinalDirection.EAST);
        map.connect(GS, Sol, 4, CardinalDirection.SOUTH);
        TunnelNode S6 = new IntersectionNode(5);
        map.addNode(S6);
        map.connect(Sol, S6, 2, CardinalDirection.SOUTH);
        TunnelNode F5 = new IntersectionNode(6);
        map.addNode(F5);
        map.connect(S6, F5, 1, CardinalDirection.SOUTH);
        map.connect(F5,Fish,3,CardinalDirection.WEST);
        map.connect(F5, DSP, 2, CardinalDirection.SOUTH);
        TunnelNode DC = new IntersectionNode(7);
        map.addNode(DC);
        map.connect(DSP, DC, 1, CardinalDirection.SOUTH);
        TunnelNode CG = new IntersectionNode(8);
        map.addNode(CG);
        map.connect(DC, CG, 1, CardinalDirection.WEST);
        map.connect(CG,Colby,2,CardinalDirection.WEST);
        map.connect(CG,Gleason,1,CardinalDirection.SOUTH);
        TunnelNode GB = new IntersectionNode(9);
        map.addNode(GB);
        map.connect(Gleason, GB, 1, CardinalDirection.SOUTH);
        map.connect(GB, Baker, 1, CardinalDirection.WEST);
        TunnelNode BGW = new IntersectionNode(10);
        map.addNode(BGW);
        map.connect(Baker, BGW, 1, CardinalDirection.WEST);
        map.connect(BGW, graceWatson, 1, CardinalDirection.WEST);
        TunnelNode GRA = new IntersectionNode(11);
        map.addNode(GRA);
        map.connect(graceWatson, GRA, 1, CardinalDirection.WEST);
        map.connect(GRA, residenceHallA, 4, CardinalDirection.NORTH);
        map.connect(residenceHallA,residenceHallB,3,CardinalDirection.NORTH);
        map.connect(residenceHallB, residenceHallC, 2, CardinalDirection.NORTH);
    }

    public String buildRoute(String buildingOne, String buildingTwo){
        int b1 = nameConvert(buildingOne);
        int b2 = nameConvert(buildingTwo);

        List<Integer> x = (this.map.breadthFirstPath(b1, b2, this.map));
        return(generateInstructions(x,this.map));
    }

    private int nameConvert(String buildingName){
        if ("Grace Watson Hall".equals(buildingName)) {
            return 25;
        }
        else if ("Frances Baker Hall".equals(buildingName)) {
            return 29;
        }
        else if ("Kate Gleason".equals(buildingName)) {
            return 35;
        }
        else if ("Eugene Colby Hall".equals(buildingName)) {
            return 33;
        }
        else if ("Sol Heumann".equals(buildingName)) {
            return 47;
        }
        else if ("Carleton Gibson Hall".equals(buildingName)) {
            return 49;
        }
        else if ("Peter Peterson Hall".equals(buildingName)) {
            return 50;
        }
        else if ("Residence Hall D".equals(buildingName)) {
            return 52;
        }
        else if ("Lyndon Baines Johnson".equals(buildingName)) {
            return 60;
        }
        else if ("Helen Fish".equals(buildingName)) {
            return 41;
        }
        else if ("Ritchies Game Room (Carleton Gibson)".equals(buildingName)) {
            return 49;
        }
        else if ("Hettie L. Shumway Dining Commons".equals(buildingName)) {
            return 55;
        }
        else if ("Residence Hall A".equals(buildingName)) {
            return 28;
        }
        else if ("Residence Hall B".equals(buildingName)) {
            return 30;
        }
        else if ("Residence Hall C".equals(buildingName)) {
            return 32;
        }
        else if ("Mark Ellingson".equals(buildingName)) {
            return 51;
        }
        else if ("Douglas Sprague Perry".equals(buildingName)) {
            return 43;
        }
        else {
            return -1; // Default case if the name does not match
        }

    }

    public static String generateInstructions(List<Integer> nodeIDs, TunnelMap tunnelMap) {
        if (nodeIDs == null || nodeIDs.size() < 2) {
            throw new IllegalArgumentException("At least two nodes are required to generate instructions.");
        }

        StringBuilder instructions = new StringBuilder();
        CardinalDirection previousDirection = null;

        for (int i = 0; i < nodeIDs.size() - 1; i++) {
            int currentNodeID = nodeIDs.get(i);
            int nextNodeID = nodeIDs.get(i + 1);

            TunnelNode currentNode = tunnelMap.getVertices().get(currentNodeID);
            TunnelNode nextNode = tunnelMap.getVertices().get(nextNodeID);

            if (currentNode == null || nextNode == null) {
                throw new IllegalArgumentException("One or more nodes in the list do not exist in the tunnel map.");
            }

            Vector movement = currentNode.getNeighbors().get(nextNode);

            if (movement == null) {
                throw new IllegalStateException(
                        "No connection found between nodes " + currentNodeID + " and " + nextNodeID);
            }


            CardinalDirection currentDirection = movement.getDirection();
            String relativeDirection = getRelativeDirection(previousDirection, currentDirection);

            String currentLocation = getNodeDescription(currentNode);
            String nextLocation = getNodeDescription(nextNode);


            if (currentNode instanceof BuildingNode) {
                // Passing through a building
                instructions.append("Continue past ")
                        .append(currentLocation)
                        .append(" for another ")
                        .append(movement.getMagnitude())
                        .append("0 meters.\n\n");
            }
            else if (!"forward".equals(relativeDirection) && !"unknown".equals(relativeDirection)) {
                // Directional change at intersections
                instructions.append("Turn ")
                        .append(relativeDirection)
                        .append(" at ")
                        .append(currentLocation)
                        .append(" and continue for ")
                        .append(movement.getMagnitude())
                        .append("0 meters towards ")
                        .append(nextLocation)
                        .append(".\n\n");
            }
            else {
                // Default forward movement
                instructions.append("From ")
                        .append(currentLocation)
                        .append(", head ")
                        .append(relativeDirection)
                        .append(" for ")
                        .append(movement.getMagnitude())
                        .append("0 meters towards ")
                        .append(nextLocation)
                        .append(".\n\n");
            }

            previousDirection = currentDirection;
        }

        TunnelNode destinationNode = tunnelMap.getVertices().get(nodeIDs.get(nodeIDs.size() - 1));
        String destinationDescription = getNodeDescription(destinationNode);

        instructions.append("Congrats! You have arrived at ")
                .append(destinationDescription)
                .append(".");

        return instructions.toString().trim();
    }

    private static String getNodeDescription(TunnelNode node) {
        if (node instanceof BuildingNode) {
            String building = ((BuildingNode) node).getConnectedBuilding();
            return building.equals("none") ? "an unknown building" : building;
        } else if (node instanceof IntersectionNode) {
            String type = ((IntersectionNode) node).getIntersectionType();
            return type.equals("none") ? "the intersection" : "the " + type + " intersection";
        } else {
            return "an unknown location";
        }
    }

    private static String getRelativeDirection(CardinalDirection previous, CardinalDirection current) {
        if (previous == null) {
            return "forward"; // Default to "forward" if there's no previous direction
        }

        if (current == previous) {
            return "forward";
        }
        else if (current == previous.opposite()) {
            return "backward";
        }
        else if ((previous == CardinalDirection.NORTH && current == CardinalDirection.EAST) ||
                (previous == CardinalDirection.EAST && current == CardinalDirection.SOUTH) ||
                (previous == CardinalDirection.SOUTH && current == CardinalDirection.WEST) ||
                (previous == CardinalDirection.WEST && current == CardinalDirection.NORTH)) {
            return "right";
        }
        else if ((previous == CardinalDirection.NORTH && current == CardinalDirection.WEST) ||
                (previous == CardinalDirection.WEST && current == CardinalDirection.SOUTH) ||
                (previous == CardinalDirection.SOUTH && current == CardinalDirection.EAST) ||
                (previous == CardinalDirection.EAST && current == CardinalDirection.NORTH)) {
            return "left";
        }
        else {
            return "unknown"; // Fallback for unexpected directions
        }
    }




}
