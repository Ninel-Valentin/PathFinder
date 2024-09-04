package src.scripts.Windows.Components.WindowContent.templates;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import src.scripts.AppData.AppData;
import src.scripts.Windows.Components.WindowContent.interfaces.AppWindowComponentInterface;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;

public abstract class AppWindowEditorPanel extends JPanel implements AppWindowComponentInterface {
    public AppData appData;
    public JComponent lastVerticalAnchor;
    public Dimension ownSize;

    public AppWindowEditorPanel() {
        super(new SpringLayout());

        setBackground(Consts.darkGray_0);
        loadContent();
    }

    private void loadContent() {
        JLabel editorLabel = new JLabel("Editor settings");
        Utils.formatLabel(editorLabel, JLabel.CENTER, getWidth());
        Utils.alignSoloRow(editorLabel, (SpringLayout) getLayout(), this, this,
                false, true, SpringLayout.NORTH, SpringLayout.NORTH);
        add(editorLabel);
        lastVerticalAnchor = editorLabel;

        addSeparator();
    }

    public void alignSelf(SpringLayout layout, Component target) {
        layout.putConstraint(
                SpringLayout.NORTH,
                this,
                0,
                SpringLayout.NORTH,
                target);
        layout.putConstraint(
                SpringLayout.WEST,
                this,
                0,
                SpringLayout.WEST,
                target);
        layout.putConstraint(
                SpringLayout.SOUTH,
                this,
                (int) ownSize.getHeight(),
                SpringLayout.NORTH,
                this);
        layout.putConstraint(
                SpringLayout.EAST,
                this,
                (int) ownSize.getWidth(),
                SpringLayout.WEST,
                this);
    }

    public void setSelfSize(Dimension size) {
        setSize(size);
        ownSize = size;
    }

    public void addSeparator() {
        JLabel separator = Utils.createHorizontalSeparator(100);
        Utils.formatLabel(separator, JLabel.CENTER, getWidth());
        Utils.alignSoloRow(separator, (SpringLayout) getLayout(), this, lastVerticalAnchor,
                false, false, SpringLayout.NORTH, SpringLayout.SOUTH);
        add(separator);
        lastVerticalAnchor = separator;
    }
}
