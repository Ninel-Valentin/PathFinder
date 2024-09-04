package src.scripts.Windows.Dialogs.Entry;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import src.scripts.Windows.Components.ContextBar.ContextBar;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.ConstComponents.WindowSettings;

public class DefaultDialog extends JFrame {
    private WindowSettings windowSettings;

    public DefaultDialog(WindowSettings windowSettings) {
        // Call default constructor of JFrame with the default window name
        super();

        // Set value properties
        this.windowSettings = windowSettings;

        // Add aditional styling
        setSize(windowSettings.getDimension());
        setPreferredSize(windowSettings.getDimension());
        setMinimumSize(windowSettings.getMinimumDimension());
        setLocationRelativeTo(null);

        // Don't show default styling
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        setJMenuBar(new ContextBar(this.windowSettings, null));

        Utils.setCustomCursor(this, Consts.cursorDefault);

        // Open window in front of all windows and shift focus on it
        setVisible(true);
        setState(JFrame.MAXIMIZED_BOTH);
        toFront();
        requestFocus();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public WindowSettings GetSettings() {
        return windowSettings;
    }
}
