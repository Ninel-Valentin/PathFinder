package src.scripts.utils.BrowsingFiles;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.scripts.utils.Consts;
import src.scripts.utils.Utils;

public class RecentProjectButton extends JButton {
    public RecentProjectButton(String path, String shortName) {
        super(shortName);

        setBackground(Consts.darkGray_3);
        setForeground(Consts.lightGray_1);

        setHorizontalAlignment(JLabel.LEFT);
        // Remove button borders
        setBorder(null);
        setFocusPainted(false);
        // Remove color onClick
        setContentAreaFilled(false);
        setName(path);
        
        Utils.setCustomCursor(this, Consts.cursorAttention);

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(Consts.darkGray_2);
        } else if (getModel().isRollover()) {
            g.setColor(Consts.darkGray_1);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
