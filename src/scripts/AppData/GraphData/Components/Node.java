package src.scripts.AppData.GraphData.Components;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Node implements Serializable {
    private Point2D.Double coords;
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setCoords(Point2D.Double coords) {
        this.coords = coords;
    }

    public Point2D.Double getCoords() {
        return coords;
    }
}
