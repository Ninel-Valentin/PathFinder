package src.scripts.AppData.GraphData;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import src.scripts.AppData.GraphData.Components.Graph;
import src.scripts.AppData.GraphData.Components.Node;
import src.scripts.AppData.Settings.AppSettings;
import src.scripts.utils.Consts.GraphType;

public class GraphData implements Serializable {
    public Graph graph;

    public GraphData(Graph presetGraph) {
        this.graph = presetGraph;
    }

    public GraphData(AppSettings settings) {
        this.graph = new Graph(settings);
    }

    public void clear(AppSettings settings) {
        this.graph = new Graph(settings);
    }

    private ArrayList<String> getNodesList() {
        ArrayList<String> list = new ArrayList<>();
        Node[] nodes = graph.getNodes();
        for (int i = 0; i < nodes.length; i++)
            list.add(String.valueOf(nodes[i].getValue()));
        return list;
    }

    private ArrayList<Point> getUndirectedEdgesList() {
        int[][] matrix = graph.getAdjacencyMatrix();
        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < matrix.length - 1; i++)
            for (int j = i + 1; j < matrix.length; j++)
                if (matrix[i][j] != 0)
                    list.add(new Point(i, j));

        return list;
    }

    private ArrayList<Point> getDirectedEdgesList() {
        int[][] matrix = graph.getAdjacencyMatrix();
        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                if (matrix[i][j] != 0)
                    list.add(new Point(i, j));

        return list;
    }

    // Here goes the algorithms
    public ArrayList<String> solveBFS(String from) {
        ArrayList<Point> edges = null;
        switch (graph.settings.getGraphType()) {
        case DIRECTED:
            edges = getDirectedEdgesList();
            break;

        case UNDIRECTED:
            edges = getUndirectedEdgesList();
            break;
        }
        ArrayList<String> predecessors = new ArrayList<>();
        if (edges != null) {
            ArrayList<String> nodes = getNodesList(), visited = new ArrayList<>(), notVisited = new ArrayList<>(), analyzed = new ArrayList<>(), lengths = new ArrayList<>();
            visited.add(String.valueOf(from));

            // Instantiate the not visited array with all nodes except the start and the
            // predecessors array with 0
            for (int i = 0; i < nodes.size(); i++) {
                predecessors.add(null);
                lengths.add("0");
                if (nodes.get(i).compareTo(from) != 0)
                    notVisited.add(String.valueOf(nodes.get(i)));
            }

            nodes.sort(null);
            analyzed.sort(null);

            while (!nodes.equals(analyzed)) {
                while (visited.size() != 0) {
                    String targetNode = visited.get(visited.size() - 1);
                    for (int i = 0; i < edges.size(); i++) {
                        String startingPoint = String.valueOf((int) edges.get(i).getX()), endingPoint = String.valueOf((int) edges.get(i).getY());

                        // Check if the edge starts from the selected node
                        if (startingPoint.compareTo(targetNode) == 0 || endingPoint.compareTo(targetNode) == 0) {

                            if (graph.settings.getGraphType() == GraphType.UNDIRECTED)
                                // Swap edge orientation if needed
                                if (endingPoint.compareTo(targetNode) == 0) {
                                    String aux = endingPoint;
                                    endingPoint = startingPoint;
                                    startingPoint = aux;
                                }

                            // Check if the edge end is part of the notVisited array
                            if (notVisited.contains(endingPoint)) {
                                visited.add(endingPoint);
                                // Need to use indexOf because the default int param is interpreted as removeAt
                                notVisited.remove(notVisited.indexOf(endingPoint));
                                predecessors.set(Integer.valueOf(endingPoint), startingPoint);
                                lengths.set(Integer.valueOf(endingPoint), String.valueOf(Integer.valueOf(lengths.get(Integer.valueOf(startingPoint))) + 1));
                            }
                        }
                    }
                    // Need to use indexOf because the default int param is interpreted as removeAt
                    visited.remove(visited.indexOf(targetNode));
                    analyzed.add(targetNode);
                }

                if (notVisited.size() > 0) {
                    from = notVisited.get(0);
                    visited.add(from);
                    // Need to use indexOf because the default int param is interpreted as removeAt
                    notVisited.remove(notVisited.indexOf(from));
                    // lengths.set(start, 0); // Done by default
                }

                nodes.sort(null);
                analyzed.sort(null);
            }

        }
        return predecessors;
    }

    public void outputBFS(String from, String to) {
        @SuppressWarnings("rawtypes")
        ArrayList predecessors = solveBFS(from);
        boolean continuousPath = true;

        String reversedPath = to + " ", output = "";
        int currentNode = Integer.valueOf(to);
        do {
            if (predecessors.get(currentNode) == null) {
                output = "No direct roads from [" + from + "] to [" + to + "]";
                continuousPath = false;
                break;
            } else {
                currentNode = Integer.valueOf(predecessors.get(currentNode).toString());
                reversedPath += currentNode + " ";
            }
        } while (currentNode != Integer.valueOf(from));
        reversedPath = reversedPath.trim();

        if (continuousPath) {
            output = "The path from [" + from + "] to [" + to + "] is: \n";
            for (int i = reversedPath.length() - 1; i >= 0; i--)
                output += reversedPath.charAt(i);
        }

        JOptionPane.showMessageDialog(null, output);

    }
}
