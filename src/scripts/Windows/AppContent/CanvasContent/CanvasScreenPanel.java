package src.scripts.Windows.AppContent.CanvasContent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import src.scripts.AppData.GraphData.Components.Node;
import src.scripts.Windows.Components.WindowContent.templates.AppWindowScreenPanel;
import src.scripts.Windows.ScreenHandlers.ScreenHandler;
import src.scripts.utils.Consts;
import src.scripts.utils.GraphicalMathematics;
import src.scripts.utils.Consts.GraphType;

public class CanvasScreenPanel extends AppWindowScreenPanel {
    public CanvasScreenPanel(Dimension size, CanvasEditorPanel editor) {
        // super() - Already called
        setSelfSize(size);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenHandler.HandleClick(e, appData, editor);
                editor.refreshDropDowns();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Add antialiasing to drawing
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHints(hints);
        ((Graphics2D) g).setStroke(new BasicStroke(Consts.strokeWidth));
        g.setFont(new Font("default", Font.BOLD, Consts.nodeValueFontSize));

        Node[] nodes = appData.graphData.graph.getNodes();

        // ------------------Edges------------------

        g.setColor(Color.decode(Consts.log_warning_yellow));
        int[][] matrix = appData.graphData.graph.getAdjacencyMatrix();
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++) {
                if (i == j)
                    continue; // Skip main diagonal (same point comparisons)

                if (matrix[i][j] != 0) {
                    // If the graph is a digraph, search all matrix
                    // If the graph is undirected, search the first diagonal half
                    if (appData.settings.getGraphType() == GraphType.UNDIRECTED && i >= j)
                        continue;
                    Node startingNode = nodes[i];
                    Node endingNode = nodes[j];

                    Point2D.Double startingPoint = startingNode.getCoords();
                    Point2D.Double endingPoint = endingNode.getCoords();

                    Point2D.Double startingCoords = new Point2D.Double(
                            startingPoint.getX() + Consts.nodeRadius / 2,
                            startingPoint.getY() + Consts.nodeRadius / 2);
                    Point2D.Double endingCoords = new Point2D.Double(
                            endingPoint.getX() + Consts.nodeRadius / 2,
                            endingPoint.getY() + Consts.nodeRadius / 2);

                    // Draw a line or an arc based on graph type
                    if (appData.settings.getGraphType() == GraphType.UNDIRECTED) {
                        g.drawLine(
                                (int) startingCoords.getX(),
                                (int) startingCoords.getY(),
                                (int) endingCoords.getX(),
                                (int) endingCoords.getY());

                    } else if (appData.settings.getGraphType() == GraphType.DIRECTED) {
                        double distance = startingCoords.distance(endingCoords);
                        // The division by 5 is for close points and it was found through testing
                        int minimumArcHeight = Math.min(Consts.arcHeight, (int) distance / 4);
                        Point2D.Double arcTip = GraphicalMathematics.getArcTip(startingPoint, endingPoint,
                                (startingNode.getValue() > endingNode.getValue() ? -1 : 1) * minimumArcHeight);

                        Point2D.Double circleCenter = GraphicalMathematics.getCircleCenter(startingCoords,
                                endingCoords, arcTip);

                        double radius = circleCenter.distance(arcTip);

                        int startAngle = (int) Math.toDegrees(
                                Math.atan2(startingCoords.getY() - circleCenter.getY(),
                                        startingCoords.getX() - circleCenter.getX()));

                        int arcAngle = (int) Math.toDegrees(
                                Math.atan2(endingCoords.getY() - circleCenter.getY(),
                                        endingCoords.getX() - circleCenter.getX()));

                        g.drawArc(
                                (int) (circleCenter.getX() - radius),
                                (int) (circleCenter.getY() - radius),
                                (int) (2 * radius),
                                (int) (2 * radius),
                                // Start from this angle:
                                -startAngle,
                                // Move this angle in 1:trigonometric || -1:clocwise direction
                                -(arcAngle - startAngle));

                        // Arrow tip

                        // Get slope of radius to ending point
                        double radiusSlope = GraphicalMathematics.getSlope(circleCenter, endingCoords);
                        double tangentSlope = -1 / radiusSlope;

                        // Transform slope to degrees
                        int tangentAngle = (int) Math.toDegrees(Math.atan(tangentSlope));

                        /*
                         * The offset table has been found through experimenting
                         * top | tangentSlope | offset
                         * start| <0 | 0
                         * start| >0 | 180
                         * end | <0 | 180
                         * end | >0 | 0
                         */

                        int angleOffset = 0;
                        // Keep the coords negative because the Y goes down
                        if ((-startingCoords.getY() > -endingCoords.getY() && tangentSlope > 0) ||
                                (-endingCoords.getY() > -startingCoords.getY() && tangentSlope < 0))
                            angleOffset = 180;

                        g.fillArc(
                                (int) (endingCoords.getX() - Consts.nodeRadius),
                                (int) (endingCoords.getY() - Consts.nodeRadius),
                                (int) (2 * Consts.nodeRadius),
                                (int) (2 * Consts.nodeRadius),
                                angleOffset - tangentAngle - Consts.tangentAngleOffset,
                                2 * Consts.tangentAngleOffset);
                    }
                }
            }

        int selectedNode = appData.graphData.graph.getSelectedValue();
        for (Node node : nodes) {
            String value = String.valueOf(node.getValue());

            // ----------------Selected----------------
            if (Integer.valueOf(value) == selectedNode) {
                ((Graphics2D) g).setStroke(new BasicStroke(Consts.nodeBorder));
                g.setColor(Color.decode(Consts.log_info_green));
                g.drawOval(
                        (int) node.getCoords().getX() - Consts.nodeBorder / 2,
                        (int) node.getCoords().getY() - Consts.nodeBorder / 2,
                        Consts.nodeRadius + Consts.nodeBorder,
                        Consts.nodeRadius + Consts.nodeBorder);
                ((Graphics2D) g).setStroke(new BasicStroke(Consts.strokeWidth));
            }

            // ------------------Node------------------

            g.setColor(Color.WHITE);
            g.fillOval((int) node.getCoords().getX(), (int) node.getCoords().getY(),
                    Consts.nodeRadius, Consts.nodeRadius);

            ((Graphics2D) g).setStroke(new BasicStroke(Consts.nodeBorder));
            g.setColor(Color.BLACK);
            g.drawOval((int) node.getCoords().getX(), (int) node.getCoords().getY(),
                    Consts.nodeRadius, Consts.nodeRadius);
            ((Graphics2D) g).setStroke(new BasicStroke(Consts.strokeWidth));

            // ------------------Text------------------

            FontMetrics metrics = g.getFontMetrics();

            // Half of circle + 1/3 font-size height
            int vOffset = (Consts.nodeRadius + metrics.getHeight() / 2) / 2;
            // Half of circle + 1/4 font-size width * ammount of letters
            int hOffset = (Consts.nodeRadius - metrics.stringWidth(value)) / 2;

            g.drawString(value, (int) node.getCoords().getX() + hOffset, (int) node.getCoords().getY() + vOffset);
        }
    }
}
