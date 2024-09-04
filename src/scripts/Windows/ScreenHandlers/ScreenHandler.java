package src.scripts.Windows.ScreenHandlers;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import src.scripts.AppData.AppData;
import src.scripts.AppData.GraphData.Components.Node;
import src.scripts.Windows.AppContent.CanvasContent.CanvasEditorPanel;
import src.scripts.utils.Consts;

public class ScreenHandler {

    public static void HandleClick(MouseEvent e, AppData appData, CanvasEditorPanel editor) {
        int xPosition = e.getX();
        int yPosition = e.getY();

        Node clickedNode = appData.graphData.graph.getNodeAtCoords(xPosition, yPosition);
        JPanel canvasPanel = ((JPanel) e.getSource());
        String cursorName = canvasPanel.getCursor().getName();

        switch (cursorName) {
        case Consts.cursorAdd:
            if (appData.graphData.graph.getNodesCount() == Consts.nodeCountLimit) {
                appData.log.error("Nodes limit reached. Can not place more nodes than " + Consts.nodeCountLimit);
                break;
            }
            // Check if the node would go out of the canvas
            if (xPosition > Consts.nodeRadius / 2 && xPosition < canvasPanel.getWidth() - Consts.nodeRadius / 2 && yPosition > Consts.nodeRadius / 2
                    && yPosition < canvasPanel.getHeight() - Consts.nodeRadius / 2) {
                // Check if there is another node too close to the clicked section
                if (appData.graphData.graph.canPlaceNodeAt(xPosition - Consts.nodeRadius / 2, yPosition - Consts.nodeRadius / 2)) {
                    Point2D.Double coords = new Point2D.Double(xPosition - Consts.nodeRadius / 2, yPosition - Consts.nodeRadius / 2);
                    appData.graphData.graph.addNode(coords);

                    // Repaint screen
                    canvasPanel.repaint();
                    editor.updateUIForSelectedNode();
                } else
                    appData.log.warning("Can not create nodes over existing nodes!");
            } else
                appData.log.warning("Can not place nodes over the border!");
            break;
        case Consts.cursorRemove:
            if (clickedNode != null) {
                appData.graphData.graph.removeNode(clickedNode);

                // Repaint screen
                canvasPanel.repaint();
            }
            break;
        case Consts.cursorDefault:
            if (clickedNode != null) {
                int value = clickedNode.getValue();
                appData.graphData.graph.setSelectedValue(value);

                // Repaint screen
                canvasPanel.repaint();
                editor.updateUIForSelectedNode();
            }
            break;
        default:

        }
    }
}
