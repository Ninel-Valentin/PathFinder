package src.scripts.Windows.Components.ContextBar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.ConstComponents.CtrlButtonInfo;
import src.scripts.utils.ConstComponents.WindowSettings;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

public class ContextBarButton extends JButton {
    CtrlButtonInfo btnInfo;
    boolean isUsable;

    public ContextBarButton(CtrlButtonInfo btnInfo, boolean isUsable, WindowSettings windowSettings) {
        super(btnInfo.getValue());

        this.btnInfo = btnInfo;
        this.isUsable = isUsable;

        setPreferredSize(new Dimension(Consts.ctrlBtnSize, Consts.ctrlBtnSize));

        setBackground(Consts.darkGray_0);
        if (this.isUsable)
            setForeground(Consts.lightGray_1);
        else
            setForeground(Consts.lightGray_2);

        setHorizontalTextPosition(JButton.CENTER);

        // Remove button borders
        setBorder(null);
        setFocusPainted(false);
        // Remove color onClick
        setContentAreaFilled(false);
        setName(this.btnInfo.getName());
        if (isUsable)
            Utils.setCustomCursor(this, Consts.cursorAttention);

        addMouseListener(new CtxBarBtnMouseAdapter(windowSettings));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (this.isUsable && getModel().isPressed()) {
            g.setColor(Consts.darkGray_3);
        } else if (this.isUsable && getModel().isRollover()) {
            g.setColor(Consts.darkGray_2);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    private class CtxBarBtnMouseAdapter extends MouseAdapter {
        WindowSettings windowSettings;

        public CtxBarBtnMouseAdapter(WindowSettings windowSettings) {
            super();
            this.windowSettings = windowSettings;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            String clickedName = ((JButton) e.getSource()).getName();
            JFrame topParentFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());

            switch (clickedName) {
                case Consts.minimizeCrlBtnName:
                    topParentFrame.setState(JFrame.ICONIFIED);
                    break;
                case Consts.maximizeCrlBtnName:
                    if (windowSettings.getControlSettings().getProperty(Consts.maximizeCrlBtnName))
                        Utils.toggleFullScreen(topParentFrame, windowSettings, true);

                    break;
                case Consts.exitCrlBtnName:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unresolved mouse event name:" + clickedName);
            }
        }
    }

}
