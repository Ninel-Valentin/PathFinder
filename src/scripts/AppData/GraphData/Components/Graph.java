package src.scripts.AppData.GraphData.Components;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Arrays;

import src.scripts.AppData.Settings.AppSettings;
import src.scripts.utils.Consts;
import src.scripts.utils.Consts.GraphType;

public class Graph implements Serializable {
    public AppSettings settings;
    private Node[] nodes;
    private int[][] matrix;
    private int selectedNode;

    public Graph(int length) {
        nodes = new Node[length];
        matrix = new int[length][length];
    }

    public Graph(AppSettings settings) {
        this.settings = settings;
        // Initialize them with 0 length so they can be resized later without a check
        nodes = new Node[0];
        matrix = new int[0][0];
        selectedNode = -1;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public int[][] getAdjacencyMatrix() {
        return matrix;
    }

    public int getNodesCount() {
        return nodes.length;
    }

    public void setSelectedValue(int value) {
        selectedNode = value;
    }

    public int getSelectedValue() {
        return selectedNode;
    }

    public Node getSelectedNode() {
        return getNodeWithValue(selectedNode);
    }

    public void addNode(Point2D.Double coords) {
        int value = getNextValue();
        Node newNode = new Node(value);
        newNode.setCoords(coords);

        Node[] resizedNodesArray = new Node[nodes.length + 1];
        for (int i = 0; i < nodes.length; i++)
            resizedNodesArray[i + (i >= value ? 1 : 0)] = nodes[i];
        resizedNodesArray[value] = newNode;
        // Replace old array
        nodes = resizedNodesArray;

        int resizedMatrix[][] = new int[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                resizedMatrix[i + (i >= value ? 1 : 0)][j + (j >= value ? 1 : 0)] = matrix[i][j];
        // Replace old matrix
        matrix = resizedMatrix;
        selectedNode = value;
    }

    public void removeNode(Node removed) {
        int removedIndex = getIndexOfValue(removed.getValue());

        Node[] resizedNodesArray = new Node[nodes.length - 1];
        for (int i = 0; i < resizedNodesArray.length; i++)
            resizedNodesArray[i] = nodes[i + (i >= removedIndex ? 1 : 0)];
        // Replace old array
        nodes = resizedNodesArray;

        int resizedMatrix[][] = new int[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < resizedMatrix.length; i++)
            for (int j = 0; j < resizedMatrix.length; j++)
                resizedMatrix[i][j] = matrix[i + (i >= removedIndex ? 1 : 0)][j + (j >= removedIndex ? 1 : 0)];
        // Replace old matrix
        matrix = resizedMatrix;
    }

    public boolean canPlaceNodeAt(int x, int y) {
        for (Node node : nodes) {
            Point2D.Double coords = node.getCoords();
            if (x >= coords.getX() - Consts.nodeRadius && x <= coords.getX() + Consts.nodeRadius * 1.5 && y >= coords.getY() - Consts.nodeRadius && y <= coords.getY() + Consts.nodeRadius * 1.5)
                return false;
        }
        return true;
    }

    public Node getNodeWithValue(int value) {
        for (Node node : nodes)
            if (node.getValue() == value)
                return node;
        return null;
    }

    public Node getNodeAtCoords(int x, int y) {
        for (Node node : nodes) {
            Point2D.Double coords = node.getCoords();
            if (x >= coords.getX() && x <= coords.getX() + Consts.nodeRadius && y >= coords.getY() && y <= coords.getY() + Consts.nodeRadius)
                return node;
        }
        return null;
    }

    public Node getNodeAtIndex(int index) {
        return nodes[index];
    }

    public int getIndexOfValue(int value) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].getValue() == value)
                return i;
        }
        return -1;
    }

    public boolean hasEdge(int from, int to) {
        return matrix[from][to] != 0;
    }

    public int getEdge(int from, int to) {
        return matrix[from][to];
    }

    public void addEdge(int from, int to) {
        if (from == to)
            if (settings.getGraphType() == GraphType.DIRECTED)
                matrix[from][to] = 1;
            else
                System.out.println("ERROR: Cannot create an edge ending where it starts in a directed graph");

        if (from > to)
            if (settings.getGraphType() == GraphType.DIRECTED)
                matrix[from][to] = 1;
            else
                matrix[to][from] = 1;

        if (from < to)
            matrix[from][to] = 1;
    }

    public void addEdge(int from, int to, int weight) {
        matrix[from][to] = weight;
    }

    public void removeEdge(int from, int to) {
        matrix[from][to] = 0;

    }

    public void setWeight(int from, int to, int weight) {
        // Only update the weight if the edge exists
        if (matrix[from][to] != 0)
            matrix[from][to] = weight;
    }

    public int getNextValue() {
        // If no nodes exist, return 0
        if (nodes.length == 0)
            return 0;

        // Create and sort an array with all existing values
        int[] existingIds = new int[nodes.length];
        for (int i = 0; i < existingIds.length; i++)
            existingIds[i] = nodes[i].getValue();
        Arrays.sort(existingIds);

        // Check all values to match the positions, they should be in ascending order
        for (int i = 0; i < existingIds.length; i++)
            if (existingIds[i] != i)
                return i;
        // If all existing values are consecutive, return next value
        return existingIds.length;
    }
}
