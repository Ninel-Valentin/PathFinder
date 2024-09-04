package src.scripts.Windows.Components.ToolbarContent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import src.scripts.utils.Consts;
import src.scripts.utils.Utils;

public class ToolbarButton extends JButton {

    public ToolbarButton(int btnSize, String imageName) {
        super();
        Utils.setCustomCursor(this, Consts.cursorAttention);

        String imagePath = (imageName.contains("Cursor") ? Consts.cursorPath : Consts.optionPath)
                + imageName + ".png";
        ImageIcon defaultIcon = new ImageIcon(imagePath);
        defaultIcon = new ImageIcon(
                defaultIcon.getImage().getScaledInstance(btnSize - Consts.ctrlBtnSize, btnSize - Consts.ctrlBtnSize,
                        Image.SCALE_SMOOTH));
        setIcon(defaultIcon);
        setPreferredSize(new Dimension(btnSize, btnSize));
        setMinimumSize(new Dimension(btnSize, btnSize));
        setMaximumSize(new Dimension(btnSize, btnSize));

        setBackground(Consts.darkGray_0);

        // Remove button borders
        setBorder(null);
        setFocusPainted(false);
        // Remove color onClick
        setContentAreaFilled(false);
        setName(imageName);

        // addMouseListener(new ToolbarMouseAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(Consts.darkGray_3);
        } else if (getModel().isRollover()) {
            g.setColor(Consts.darkGray_2);
        } else {
            g.setColor(getBackground());
        }

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
