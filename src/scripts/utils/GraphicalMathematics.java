package src.scripts.utils;

import java.awt.geom.Point2D;

public class GraphicalMathematics {
        public static Point2D.Double getArcTip(Point2D.Double startingPoint, Point2D.Double endingPoint,
                        int arcHeight) {
                // Calculate midpoint of the line segment
                Point2D.Double middlePoint = new Point2D.Double(
                                (startingPoint.getX() + endingPoint.getX() + Consts.nodeRadius) / 2,
                                (startingPoint.getY() + endingPoint.getY() + Consts.nodeRadius) / 2);

                double firstSlope = getSlope(startingPoint, endingPoint);
                double perpendicularSlope = -1 / firstSlope;
                return new Point2D.Double(
                                (arcHeight / Math.sqrt(1 + perpendicularSlope * perpendicularSlope))
                                                + middlePoint.getX(),
                                (perpendicularSlope * arcHeight
                                                / Math.sqrt(1 + perpendicularSlope * perpendicularSlope))
                                                + middlePoint.getY());
        }

        public static Point2D.Double getCircleCenter(Point2D.Double startingCoords, Point2D.Double endingCoords,
                        Point2D.Double arcTip) {
                /*
                 * Determinant method
                 * | xC ^ 2 + yC ^ 2 | xC | yC | 1 |
                 * | xS ^ 2 + yS ^ 2 | xS | yS | 1 | = 0
                 * | xE ^ 2 + yE ^ 2 | xE | yE | 1 |
                 * | xA ^ 2 + yA ^ 2 | xA | yA | 1 |
                 */
                double A = startingCoords.getX() * (arcTip.getY() - endingCoords.getY())
                                - startingCoords.getY() * (arcTip.getX() - endingCoords.getX())
                                + arcTip.getX() * endingCoords.getY()
                                - endingCoords.getX() * arcTip.getY();
                double B = (startingCoords.getX() * startingCoords.getX()
                                + startingCoords.getY() * startingCoords.getY())
                                * (endingCoords.getY() - arcTip.getY())
                                + (endingCoords.getX() * endingCoords.getX()
                                                + endingCoords.getY() * endingCoords.getY())
                                                * (arcTip.getY() - startingCoords.getY())
                                + (arcTip.getX() * arcTip.getX() + arcTip.getY() * arcTip.getY())
                                                * (startingCoords.getY() - endingCoords.getY());
                double C = (startingCoords.getX() * startingCoords.getX()
                                + startingCoords.getY() * startingCoords.getY())
                                * (arcTip.getX() - endingCoords.getX())
                                + (endingCoords.getX() * endingCoords.getX()
                                                + endingCoords.getY() * endingCoords.getY())
                                                * (startingCoords.getX() - arcTip.getX())
                                + (arcTip.getX() * arcTip.getX() + arcTip.getY() * arcTip.getY())
                                                * (endingCoords.getX() - startingCoords.getX());

                return new Point2D.Double(
                                -B / (2 * A),
                                -C / (2 * A));
        }

        public static double getSlope(Point2D.Double startingPoint, Point2D.Double endingPoint) {
                return (startingPoint.getY() - endingPoint.getY())
                                / (startingPoint.getX() - endingPoint.getX());
        }
}
