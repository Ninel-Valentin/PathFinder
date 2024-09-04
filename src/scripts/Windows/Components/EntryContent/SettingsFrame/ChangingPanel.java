package src.scripts.Windows.Components.EntryContent.SettingsFrame;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import src.scripts.utils.Consts;

public class ChangingPanel extends JPanel {
    public ChangingPanel() {
        super(new SpringLayout());
        setBackground(Consts.darkGray_2);

        // SpringLayout into CardLayout -> the canvas dissapear
        // Needs setting a prefered size. Width is preset, it only needs > 0
        setPreferredSize(new Dimension(1, Consts.changingPanelHeight));
    }
}
