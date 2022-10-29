package utils.Algorythms.Graphs;

import java.util.LinkedList;

public class Graph {
    public static class Node {
        // position on screen
        public float x, y;
        public int id;
        public String displayName;

        // Can't use java.awt.Point because the coordinates come in integer precision
        // TODO: id should be calculated instead of provided as parameter for both
        // constructors
        public Node(int id, float x, float y, String displayName) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.displayName = displayName;
        }

        public Node(int id, float x, float y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.displayName = String.valueOf(id);
        }

        public static Node getNodeFromId(int nodeId) {
            return new Node(nodeId, 0, 0);
        }

        float getDistance(float x, float y) {
            float xProduct = (x - this.x) * (x - this.x);
            float yProduct = (y - this.y) * (y - this.y);
            double distance = Math.sqrt(xProduct + yProduct);
            return (float) distance;
        }

        float getDistance(int targetId) {
            Node targetNode = Node.getNodeFromId(targetId);
            return getDistance(targetNode.x, targetNode.y);
        }

    }

    public static LinkedList<Node> readNodesFromLinks(LinkedList<LinkedList<Node>> links) {
        LinkedList<Node> nodes = new LinkedList<Node>();
        for (Node node : nodes) {
            try {
                Node current = nodes.get(node.id);
                if (!nodes.contains(current)) {
                    nodes.add(current);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return nodes;
    }

    public static class OrientedGraph {
        public LinkedList<Node> nodes;
        public LinkedList<LinkedList<Node>> links;

        public OrientedGraph() {
            nodes = new LinkedList<Node>() {
            };
            links = new LinkedList<LinkedList<Node>>() {
            };
        }

        public OrientedGraph(LinkedList<LinkedList<Node>> links) {
            this.nodes = Graph.readNodesFromLinks(links);
            this.links = links;
        }

        class Edge {
            Node from;
            Node to;
        }
    }

    public static class UnorientedGraph {
        public LinkedList<Node> nodes;
        public LinkedList<LinkedList<Node>> edges;

        public UnorientedGraph() {
            nodes = new LinkedList<Node>() {
            };
            this.edges = new LinkedList<LinkedList<Node>>() {
            };
        }

        public UnorientedGraph(LinkedList<LinkedList<Node>> edges) {
            this.edges = edges;
        }

        public void AddNode(Node node) {
            if (!this.nodes.contains(node)) {
                this.nodes.add(node);
            }
        }

        public void AddNode(Node node, LinkedList<Node> links) {
            if (!this.nodes.contains(node) && !this.edges.contains(links)) {
                this.nodes.add(node);
                this.edges.get(node.id).addAll(links);
            }
        }

        public void AddNodes(LinkedList<Node> nodes) {
            for (Node node : nodes) {
                if (!this.nodes.contains(node)) {
                    this.nodes.add(node);
                }
            }
        }

        class Edge {
            Node[] ends = new Node[2];

            public Edge(Node[] ends) {
                switch (ends.length) {
                    case 0:
                        System.out.println("ERROR: Tried to initialize edge with no ends!");
                        return;
                    case 1:
                        ends[1] = ends[0];
                        // ends = new Node[] {
                        // ends[0],
                        // ends[0]
                        // };
                        break;
                    default:
                        System.out.println(
                                "SYSTEM: Tried to initialize edge with 2 or more ends!\nWill only be using the first 2 entries.");
                        ends = new Node[] {
                                ends[0],
                                ends[1]
                        };
                }
            }

            public Edge(int[] endsIds) {
                switch (endsIds.length) {
                    case 0:
                        System.out.println("ERROR: Tried to initialize edge with no ends!");
                        return;
                    default:
                        ends = new Node[] {
                                Node.getNodeFromId(endsIds[0]),
                                Node.getNodeFromId(endsIds[1])
                        };
                }
            }
        }

    }
}