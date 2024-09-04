package src.scripts.Windows.Dialogs.App;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import src.scripts.AppData.AppData;
import src.scripts.Windows.Components.ContextBar.ContextBar;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.ConstComponents.WindowSettings;

public class AppWindow extends JFrame {
    WindowSettings windowSettings;
    AppData appData;

    public AppWindow(WindowSettings windowSettings, AppData appData) {
        // Call default constructor of JFrame with the default window name
        super();
        this.appData = appData;
        Utils.setCustomCursor(this, Consts.cursorDefault);

        // Set value properties
        this.windowSettings = windowSettings;

        Dimension fullScreenDimension = Utils.getCurrentScreenDimension();
        // Add aditional styling
        setSize(fullScreenDimension);
        setPreferredSize(fullScreenDimension);
        setMinimumSize(windowSettings.getMinimumDimension());
        setLocationRelativeTo(null);

        // Don't show default styling
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        setJMenuBar(new ContextBar(this.windowSettings, appData));

        // Open window in front of all windows and shift focus on it
        setVisible(true);
        toFront();
        requestFocus();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
