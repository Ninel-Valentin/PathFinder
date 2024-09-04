package src.scripts.Windows.AppContent.CanvasContent;

import java.awt.Dimension;

import javax.swing.SpringLayout;

import src.scripts.AppData.AppData;
import src.scripts.Windows.Components.WindowContent.AppWindowLogPanel;
import src.scripts.Windows.Components.WindowContent.templates.AppWindowContent;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.ConstComponents.WindowSettings;

public class CanvasWindowContent extends AppWindowContent {
        public AppData appData;

        public CanvasWindowContent(WindowSettings windowSettings, AppData appData) {
                this.appData = appData;
                Dimension windowSize = windowSettings.getFullScreenState() ? Utils.getCurrentScreenDimension() : windowSettings.getDimension();

                // Remove the contextBar height
                windowSize.height = (int) (windowSize.getHeight() - Consts.ctrlBtnSize);

                setBackground(Consts.darkGray_1);
                setPreferredSize(windowSize);
                setSize(windowSize);
                loadContent();
        }

        private void loadContent() {
                SpringLayout windowLayout = (SpringLayout) super.getLayout();

                CanvasEditorPanel editor = new CanvasEditorPanel(new Dimension((int) (getWidth() * 1 / 6), (int) (getHeight() * 39 / 40)), appData);
                add(editor);
                editor.alignSelf(windowLayout, this);

                CanvasScreenPanel screen = new CanvasScreenPanel(new Dimension((int) (getWidth() * 5 / 6) - 2 * Consts.ctrlBtnSize, (int) (getHeight() * 35 / 40) - 2 * Consts.ctrlBtnSize), editor);
                add(screen);
                screen.alignSelf(windowLayout, this);

                // set log as variable
                CanvasToolbarPanel toolbar = new CanvasToolbarPanel(new Dimension((int) (getWidth() * 5 / 6), (int) (getHeight() * 4 / 40)), editor);
                add(toolbar);
                toolbar.alignSelf(windowLayout, screen);

                AppWindowLogPanel log = new AppWindowLogPanel(new Dimension((int) (getWidth()), (int) (getHeight() * 1 / 40)));
                add(log);
                log.alignSelf(windowLayout, this);

                // Link logging
                this.appData.log.setLoggingRef(log.textContent);
                this.appData.screenData.screen = screen;
                this.appData.screenData.editor = editor;
                screen.appData = this.appData;
                toolbar.appData = this.appData;
        }
}
