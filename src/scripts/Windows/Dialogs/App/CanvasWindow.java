package src.scripts.Windows.Dialogs.App;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import src.scripts.AppData.AppData;
import src.scripts.Windows.AppContent.CanvasContent.CanvasWindowContent;
import src.scripts.utils.Consts;

public class CanvasWindow extends AppWindow {
    public CanvasWindow(AppData appData) {
        super(Consts.canvasWindowSettings, appData);
        this.appData = appData;

        loadContent();
        pack();

        // Get the input map for the JFrame's root pane
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Get the action map for the JFrame's root pane
        ActionMap actionMap = getRootPane().getActionMap();

        // Define the key stroke for the F5 key
        KeyStroke f5KeyStroke = KeyStroke.getKeyStroke("F5");

        // Define the action to be performed when F5 is pressed
        Action f5Action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appData.screenData.editor.startAlgorithm();
            }
        };

        // Bind the F5 key stroke to an action named "doF5Action"
        inputMap.put(f5KeyStroke, "doF5Action");
        // Map the action name "doF5Action" to the f5Action defined above
        actionMap.put("doF5Action", f5Action);
    }

    private void loadContent() {
        add(new CanvasWindowContent(windowSettings, this.appData));
    }
}
