package utils.Algorythms.Graphs;

import java.util.LinkedList;

import utils.Consts;
import utils.LoggingService;

import java.awt.Point;

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

        public static Node GetNodeFromId(int nodeId) {
            return new Node(nodeId, 0, 0);
        }

        public Point[] GetSquareBoundaries() {
            int enforcedRadius = Consts.NODE_RADIUS + Consts.NODE_MARGIN_RADIUS;
            Point upLeft = new Point((int) this.x - enforcedRadius, (int) this.y + enforcedRadius),
                    downRight = new Point((int) this.x + enforcedRadius, (int) this.y - enforcedRadius);
            return new Point[] { upLeft, downRight };
        }

        public float GetDistance(float x, float y) {
            float xProduct = (x - this.x) * (x - this.x);
            float yProduct = (y - this.y) * (y - this.y);
            double distance = Math.sqrt(xProduct + yProduct);
            return (float) distance;
        }

        public float GetDistance(int targetId) {
            Node targetNode = Node.GetNodeFromId(targetId);
            return GetDistance(targetNode.x, targetNode.y);
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
                LoggingService.Log.Error(e.getMessage());
                System.out.println("Exception ERROR: \"" + e + "\"");
                e.printStackTrace();
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
        public LinkedList<LinkedList<Integer>> edges;

        public UnorientedGraph() {
            nodes = new LinkedList<Node>() {
            };
            this.edges = new LinkedList<LinkedList<Integer>>() {
            };
        }

        public UnorientedGraph(LinkedList<LinkedList<Integer>> edges) {
            this.edges = edges;
        }

        public void AddNode(Node node) {
            if (!this.nodes.contains(node)) {
                this.nodes.add(node);
            }
        }

        public void AddNode(Node node, LinkedList<Integer> links) {
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

        public Node GetNode(int index) {
            for (Node node : this.nodes) {
                if (node.id == index)
                    return node;
            }
            return null;
        }

        public Node GetNode(int x, int y) {
            for (Node node : nodes) {
                Point[] boundries = node.GetSquareBoundaries();
                if (x >= boundries[0].x && x <= boundries[1].x &&
                        y <= boundries[0].y && y >= boundries[1].y)
                    return node;
            }
            return null;
        }

        public void RemoveNode(Node node) {
            nodes.remove(node);
            String msg = "Removed node with index: \"" + node.id + "\"";
            LoggingService.Log.Info(msg);
            System.out.println(msg);
        }

        public void RemoveNode(int index) {
            Node node = GetNode(index);
            RemoveNode(node);
        }

        public boolean IsPositionOccupied(int x, int y) {
            for (Node node : nodes) {
                Point[] boundries = node.GetSquareBoundaries();
                if (x >= boundries[0].x && x <= boundries[1].x &&
                        y <= boundries[0].y && y >= boundries[1].y)
                    return true;
            }
            return false;
        }

        public int GetNextAvailableIndex() {
            // It's initialized as all false
            boolean[] positionArray = new boolean[nodes.size()];
            // for (int i = 0; i < positionArray.length; i++)
            // positionArray[i] = false;
            for (Node node : nodes) {
                // Check first if the id can be indicated I.E. for deleted nodes | 1 2 3 7 9
                // with length 5
                if (node.id < positionArray.length) {
                    positionArray[node.id] = true;
                }
            }
            for (int i = 0; i < positionArray.length; i++) {
                if (!positionArray[i])
                    return i;
            }
            // The list contains all it's nodes, so return the next index | 1 2 3 4 with
            // length 4, return 5
            return positionArray.length;
        }

        class Edge {
            Node[] ends = new Node[2];

            public Edge(Node[] ends) {
                switch (ends.length) {
                    case 0:
                        String err = "Tried to initialize edge with no ends!";
                        LoggingService.Log.Error(err);
                        System.out.println(err);
                        return;
                    case 1:
                        ends[1] = ends[0];
                        // ends = new Node[] {
                        // ends[0],
                        // ends[0]
                        // };
                        break;
                    default:
                        String msg = "Tried to initialize edge with 2 or more ends!\nWill only be using the first 2 entries.";
                        LoggingService.Log.Info(msg);
                        System.out.println(msg);
                        ends = new Node[] {
                                ends[0],
                                ends[1]
                        };
                }
            }

            public Edge(int[] endsIds) {
                switch (endsIds.length) {
                    case 0:
                        String err = "Tried to initialize edge with no ends!";
                        LoggingService.Log.Error(err);
                        System.out.println(err);
                        return;
                    default:
                        ends = new Node[] {
                                Node.GetNodeFromId(endsIds[0]),
                                Node.GetNodeFromId(endsIds[1])
                        };
                }
            }
        }

    }
}