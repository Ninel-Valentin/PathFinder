package src.scripts.Windows.Components.WindowContent.templates;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

public abstract class AppWindowContent extends JPanel {
    public AppWindowContent() {
        super(new SpringLayout());
    }
}
