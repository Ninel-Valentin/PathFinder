package src.scripts.Windows.Components.WindowContent;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import src.scripts.Windows.Components.WindowContent.interfaces.AppWindowComponentInterface;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;

public class AppWindowLogPanel extends JPanel implements AppWindowComponentInterface {
        private Dimension ownSize;
        public JLabel textContent;

        public AppWindowLogPanel(Dimension size) {
                super();
                setSelfSize(size);
                setBackground(Consts.darkGray_0);
                loadContent();
        }

        public void setSelfSize(Dimension size) {
                setSize(size);
                ownSize = size;
        }

        public void alignSelf(SpringLayout layout, Component target) {
                layout.putConstraint(
                                SpringLayout.NORTH,
                                this,
                                (int) -ownSize.getHeight(),
                                SpringLayout.SOUTH,
                                this);
                layout.putConstraint(
                                SpringLayout.WEST,
                                this,
                                0,
                                SpringLayout.WEST,
                                target);
                layout.putConstraint(
                                SpringLayout.SOUTH,
                                this,
                                0,
                                SpringLayout.SOUTH,
                                target);
                layout.putConstraint(
                                SpringLayout.EAST,
                                this,
                                0,
                                SpringLayout.EAST,
                                target);
        }

        private void loadContent() {
                textContent = new JLabel();
                Utils.formatLabel(textContent, JLabel.TOP, JLabel.LEFT,
                                (int) (ownSize.getWidth() - 2 * Consts.ctrlBtnSize));
                add(textContent);
        }
}
