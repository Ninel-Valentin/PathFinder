package src.scripts.AppData.Settings;

import java.io.Serializable;

import src.scripts.utils.Consts.EdgeWeight;
import src.scripts.utils.Consts.GraphType;

public class AppSettings implements Serializable {
    // All private so we don't reset them once they were set at the start
    private GraphType graphType;
    private EdgeWeight edgeType;

    public AppSettings(GraphType graphType, EdgeWeight edgeType) {
        this.graphType = graphType;
        this.edgeType = edgeType;
    }

    public GraphType getGraphType() {
        return graphType;
    }

    public EdgeWeight getEdgeWeightType() {
        return edgeType;
    }
}
