package src.scripts.Windows.Components.EntryContent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import src.scripts.utils.Consts;

public class OpenDialoguePanel extends JPanel {
        public OpenDialoguePanel(SpringLayout parentLayout, JComponent verticalAnchor, JComponent horizontalAnchor,
                        String anchorLocation, int windowHeight) {
                super(new SpringLayout());
                setBackground(Consts.darkGray_2);
                parentLayout.putConstraint(
                                SpringLayout.NORTH,
                                this,
                                Consts.entryContentPadding,
                                anchorLocation,
                                verticalAnchor);
                parentLayout.putConstraint(
                                SpringLayout.EAST,
                                this,
                                -Consts.entryContentPadding,
                                SpringLayout.EAST,
                                horizontalAnchor);
                parentLayout.putConstraint(
                                SpringLayout.SOUTH,
                                this,
                                windowHeight,
                                SpringLayout.NORTH,
                                this);
                parentLayout.putConstraint(
                                SpringLayout.WEST,
                                this,
                                Consts.entryContentPadding,
                                SpringLayout.WEST,
                                horizontalAnchor);

        }
}
