package src;

import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.SwingUtilities;

import src.scripts.AppData.AppData;
import src.scripts.AppData.GraphData.Components.Graph;
import src.scripts.AppData.Settings.AppSettings;
import src.scripts.StorageManager.LocalStorage;
import src.scripts.Windows.Dialogs.App.CanvasWindow;
import src.scripts.Windows.Dialogs.Entry.DefaultDialog;
import src.scripts.Windows.Dialogs.Entry.EntryDialog;
import src.scripts.utils.Consts.EdgeWeight;
import src.scripts.utils.Consts.GraphType;

public class App {
    public static void main(String[] args) {

        // test();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Set anti-alising on true for text and
                System.setProperty("swing.aatext", "true");
                // Initialize local storage if not existing already
                LocalStorage.InitLocalStorage();

                EntryDialog dialog = new EntryDialog();

                // // Should be passed by entrypoint back to appData
                // AppSettings settings = new AppSettings(GraphType.DIRECTED, EdgeWeight.WEIGHTED);

                // Graph gr = new Graph(settings);
                // // Should load all at once instead of one by one
                // gr.addNode(new Point2D.Double(200, 300));
                // gr.addNode(new Point2D.Double(330, 553));
                // gr.addNode(new Point2D.Double(650, 111));
                // gr.addNode(new Point2D.Double(750, 223));
                // gr.addNode(new Point2D.Double(980, 123));
                // gr.addNode(new Point2D.Double(1100, 623));

                // gr.addEdge(0, 5);
                // // gr.addEdge(5, 3);
                // gr.addEdge(1, 5);
                // gr.addEdge(1, 3);
                // gr.addEdge(3, 1);
                // gr.addEdge(3, 0);
                // gr.addEdge(4, 5);

                // AppData appData = new AppData(gr, settings);

                // CanvasWindow entryProject = new CanvasWindow(appData);

            }
        });
    }
}
