package src.scripts.AppData;

import java.io.Serializable;

import src.scripts.AppData.GraphData.GraphData;
import src.scripts.AppData.GraphData.Components.Graph;
import src.scripts.AppData.Settings.AppSettings;
import src.scripts.utils.Log.LoggingSystem;

public class AppData implements Serializable {
    // transient makes the field ignored in serialization
    transient public LoggingSystem log;
    transient public ScreenData screenData;
    public GraphData graphData;
    public AppSettings settings;
    transient public String savePath = null;

    public AppData(Graph presetGraph, AppSettings settings) {
        log = new LoggingSystem();
        screenData = new ScreenData();
        if (presetGraph == null)
            graphData = new GraphData(settings);
        else
            graphData = new GraphData(presetGraph);
        this.settings = settings;
    }
}
