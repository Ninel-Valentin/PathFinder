package src.scripts.Windows.Components.ContextBar;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;

import src.scripts.utils.Consts;

public class ContextBarDropDown extends JComboBox<String> {
    private String label;

    public ContextBarDropDown(String label) {
        super(getChildrenFor(label));
        this.label = label;
        setName(label);

        setPreferredSize(new Dimension(Consts.dropdownWidth, Consts.ctrlBtnSize));

        // Update arrow width
        this.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        // It will be first component because it just got updated, therefore remove it
        this.remove(this.getComponent(0));

    }

    @Override
    protected void paintComponent(Graphics g) {
        // Add antialiasing to drawing
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Consts.darkGray_2);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setFont(new Font("default", Font.BOLD, Consts.dropdownFontSize));
        g.setColor(Consts.lightGray_1);

        FontMetrics metrics = g.getFontMetrics();

        // Half of (button + font) height
        int vOffset = (Consts.ctrlBtnSize + metrics.getHeight() / 2) / 2;
        // Half of (button + text) width
        int hOffset = (int) (Consts.dropdownWidth - metrics.stringWidth(label)) / 2;

        g.drawString(label, hOffset, vOffset);
    }

    private static String[] getChildrenFor(String parent) {
        switch (parent) {
        case "File":
            // return new String[] { "[Ctrl + N] New", "[Ctrl+S] Save", "[Ctrl + Shift + N] Save as", "Open" , "Exit" };
            return new String[] { "New", "Save", "Save as", "Open" , "Exit" };
        case "Run":
            return new String[] { "[F5] Start" };
        case "Templates":
            return new String[] { "Floyd-Warshall" };
        }
        return new String[] {};
    }
}
