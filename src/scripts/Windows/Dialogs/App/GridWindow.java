package src.scripts.Windows.Dialogs.App;

import src.scripts.AppData.AppData;
import src.scripts.Windows.AppContent.CanvasContent.CanvasWindowContent;
import src.scripts.utils.Consts;

public class GridWindow extends AppWindow {
    public GridWindow(AppData data) {
        super(Consts.gridWindowSettings, data);
        this.appData = data;

        loadContent();
        pack();
    }

    private void loadContent() {
        add(new CanvasWindowContent(windowSettings, this.appData));
    }
}
