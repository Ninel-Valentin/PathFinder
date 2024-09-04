package src.scripts.Windows.Components.WindowContent.templates;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import src.scripts.AppData.AppData;
import src.scripts.Windows.Components.WindowContent.interfaces.AppWindowComponentInterface;
import src.scripts.utils.Consts;

public abstract class AppWindowToolbarPanel extends JPanel implements AppWindowComponentInterface {
    public Dimension ownSize;
    public AppData appData;

    public AppWindowToolbarPanel() {
        super();
        setBackground(Consts.darkGray_1);

        setLayout(new FlowLayout(FlowLayout.CENTER, Consts.settingsColumnPadding, Consts.ctrlBtnSize / 2));
    }

    public void setSelfSize(Dimension size) {
        setSize(size);
        ownSize = size;
    }

    public void alignSelf(SpringLayout layout, Component target) {
        int padding = Consts.ctrlBtnSize;
        layout.putConstraint(
                SpringLayout.NORTH,
                this,
                padding,
                SpringLayout.SOUTH,
                target);
        layout.putConstraint(
                SpringLayout.WEST,
                this,
                -padding,
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
                padding,
                SpringLayout.EAST,
                target);
    }
}
