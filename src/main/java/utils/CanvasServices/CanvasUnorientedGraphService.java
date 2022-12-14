package utils.CanvasServices;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Cursor;
import java.awt.Font;

import utils.Consts;
import utils.LoggingService;
import utils.Algorythms.Graphs.Graph.Node;
import utils.Algorythms.Graphs.Graph.UnorientedGraph;

public class CanvasUnorientedGraphService {
    UnorientedGraph graph;
    JPanel canvas;
    int activeNodeIndex = -1;

    public CanvasUnorientedGraphService(JPanel canvas) {
        this.canvas = canvas;
        this.graph = new UnorientedGraph();
    }

    public void HandleClick(MouseEvent e) {
        Cursor cursor = canvas.getCursor();
        switch (cursor.getName()) {
            case "Add":
                if (!graph.IsPositionOccupied(e.getX(), e.getY())) {
                    CreateNode(e);
                } else {
                    String msg = "Clicked position is occupied by another node's protected area.";
                    LoggingService.Log.Info(msg);
                }
                break;
            case "Remove":
                if (graph.IsPositionOccupied(e.getX(), e.getY())) {
                    RemoveNode(e);
                } else {
                    String msg = "No nodes found in the clicked area.";
                    LoggingService.Log.Info(msg);
                }
                break;
            case "Original":
                if (graph.IsPositionOccupied(e.getX(), e.getY())) {
                    SelectNode(e);
                } else {
                    String msg = "No nodes found in the clicked area.";
                    LoggingService.Log.Info(msg);
                }
                break;
            default:
                String err = "ERROR: \"" + cursor.getName() + "\" is not a handled type of cursor.";
                LoggingService.Log.Error(err);
                System.err.println(err);
        }
    }

    private void SelectNode(MouseEvent e) {
        Node node = graph.GetNode(e.getX(), e.getY());
        // If it exists
        if (activeNodeIndex >= 0) {
            Node selectedNode = graph.GetNode(activeNodeIndex);
            // Erase node with border
            PaintNode(Consts.MenuBar.MENU_BG_COLOR,
                    (int) selectedNode.x,
                    (int) selectedNode.y,
                    Consts.Main.Window.Canvas.Node.RADIUS + Consts.Main.Window.Canvas.Node.ERASE_RADIUS);
            // Draw node without border
            PaintNode(Consts.Main.Window.Canvas.Node.DEFAULT_COLOR,
                    (int) selectedNode.x,
                    (int) selectedNode.y,
                    Consts.Main.Window.Canvas.Node.RADIUS,
                    Consts.Main.Window.Canvas.Node.NEW_TEXT_COLOR,
                    selectedNode.displayName);
        }
        activeNodeIndex = node.id;
        PaintNodeWithBorder(Consts.Main.Window.Canvas.Node.DEFAULT_COLOR,
                (int) node.x,
                (int) node.y,
                Consts.Main.Window.Canvas.Node.RADIUS - Consts.Main.Window.Canvas.Node.MARGIN_RADIUS / 2,
                Consts.Main.Window.Canvas.Node.ACTIVE_TEXT_COLOR,
                node.displayName,
                Consts.Main.Window.Canvas.Node.ACTIVE_BORDER_COLOR,
                Consts.Main.Window.Canvas.Node.RADIUS + Consts.Main.Window.Canvas.Node.MARGIN_RADIUS / 2);

    }

    private void CreateNode(MouseEvent e) {
        int x = e.getX(),
                y = e.getY();
        int footerOffset = (canvas.getWidth() - Consts.Main.Window.ToolBox.WIDTH) / 2;
        int enforcedRadius = Consts.Main.Window.Canvas.Node.MARGIN_RADIUS + Consts.Main.Window.Canvas.Node.RADIUS;
        int boundLeftX = footerOffset - enforcedRadius;
        int boundRightX = footerOffset + enforcedRadius + Consts.Main.Window.ToolBox.WIDTH;
        int boundY = canvas.getHeight()
                - (Consts.Main.Window.ToolBox.HEIGHT - Consts.Main.Window.Canvas.PADDING + enforcedRadius);
        // Paint nodes only above toolbox
        if (y < boundY || x < boundLeftX || x > boundRightX) {
            int index = graph.GetNextAvailableIndex();
            Node newNode = new Node(index, x, y);
            graph.AddNode(newNode);
            SelectNode(e);
        }
    }

    private void RemoveNode(MouseEvent e) {
        Node node = graph.GetNode(e.getX(), e.getY());
        graph.RemoveNode(node);
        PaintNode(Consts.MenuBar.MENU_BG_COLOR,
                (int) node.x,
                (int) node.y,
                Consts.Main.Window.Canvas.Node.RADIUS + Consts.Main.Window.Canvas.Node.ERASE_RADIUS);
        // Reset the activeNodeIndex
        if (activeNodeIndex == node.id)
            activeNodeIndex = -1;
    }

    private void PaintNode(Color color, int x, int y, int radius) {
        Graphics g = canvas.getGraphics();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(color);
        graphics.fillOval(
                x - radius / 2,
                y - radius / 2,
                radius,
                radius);
    }

    private void PaintNode(Color color, int x, int y, int radius, Color textColor, String name) {
        Graphics g = canvas.getGraphics();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(color);
        graphics.fillOval(
                x - radius / 2,
                y - radius / 2,
                radius,
                radius);
        graphics.setColor(textColor);
        Font nodeStringFont = g.getFont();
        float fontSize = nodeStringFont.getSize() * Consts.Main.Window.Canvas.Node.TEXT_FONT_SIZE;
        nodeStringFont = nodeStringFont.deriveFont(fontSize);
        graphics.setFont(nodeStringFont);
        graphics.drawString(name,
                x - ((fontSize + Consts.Main.Window.Canvas.Node.MARGIN_RADIUS) * name.length())
                        / (2 * Consts.Main.Window.Canvas.Node.TEXT_FONT_SIZE),
                y + fontSize / (2 * Consts.Main.Window.Canvas.Node.TEXT_FONT_SIZE));
    }

    private void PaintNodeWithBorder(Color color, int x, int y, int radius, Color textColor, String name,
            Color borderColor,
            int borderRadius) {
        Graphics g = canvas.getGraphics();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(borderColor);
        graphics.fillOval(
                x - borderRadius / 2,
                y - borderRadius / 2,
                borderRadius,
                borderRadius);

        graphics.setColor(color);
        graphics.fillOval(
                x - radius / 2,
                y - radius / 2,
                radius,
                radius);

        graphics.setColor(textColor);
        Font nodeStringFont = g.getFont();
        float fontSize = nodeStringFont.getSize() * Consts.Main.Window.Canvas.Node.TEXT_FONT_SIZE;
        nodeStringFont = nodeStringFont.deriveFont(fontSize);
        graphics.setFont(nodeStringFont);
        // Multiply by length of name, to center when having more digits
        graphics.drawString(name,
                x - ((fontSize + Consts.Main.Window.Canvas.Node.MARGIN_RADIUS) * name.length())
                        / (2 * Consts.Main.Window.Canvas.Node.TEXT_FONT_SIZE),
                y + fontSize / (2 * Consts.Main.Window.Canvas.Node.TEXT_FONT_SIZE));
    }
}
