package utils;

import javax.swing.JPanel;

import utils.Algorythms.Graphs.Graph;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Cursor;

public class CanvasService {

    Graph graph;
    JPanel canvas;

    public CanvasService(JPanel canvas, String graphType) {
        this.canvas = canvas;
        this.graph = new Graph();
    }

    public void HandleCanvasClick(MouseEvent e) {
        Graphics g = canvas.getGraphics();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Cursor cursor = canvas.getCursor();
        switch (cursor.getName()) {
            case "Add":
                // Node node = graph.CreateNode();
                DrawNode(graphics, e);
                break;
            case "Remove":
                break;
            case "Original":
                break;
            default:
                System.err.println("ERROR: \"" + cursor.getName() + "\" is not a handled type of cursor.");
        }
    }

    private static void DrawNode(Graphics2D graphics, MouseEvent e) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(
                e.getX() - Consts.NODE_RADIUS / 2,
                e.getY() - Consts.NODE_RADIUS / 2,
                Consts.NODE_RADIUS,
                Consts.NODE_RADIUS);
    }
}
