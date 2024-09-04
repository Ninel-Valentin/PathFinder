package src.scripts.utils;

import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;

import src.scripts.utils.ConstComponents.WindowSettings;
import src.scripts.utils.Consts.ConfirmationFlagType;
import src.scripts.utils.Consts.GraphType;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;

public class Utils {

        /**
         * Computes the center of the screen with respect to the 2 parameter sizes
         * 
         * @param windowDimension
         * @return centered Point with offset
         */
        public static Point computeScreenCenter(Dimension windowDimension) {
                int xCoord, yCoord;
                // Get the size of the screen
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                // Compute point X on screen
                xCoord = (int) (screenSize.getWidth() - windowDimension.getWidth()) / 2;
                // Compute point Y on screen
                yCoord = (int) (screenSize.getHeight() - windowDimension.getHeight()) / 2;

                return new Point(xCoord, yCoord);
        }

        public static Dimension getCurrentScreenDimension() {
                // Use this approach instead of ToolKit for multe screen set-ups
                GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                int width = gd.getDisplayMode().getWidth();
                int height = gd.getDisplayMode().getHeight();

                return new Dimension(width, height);
        }

        public static void setCustomCursor(Container target, String cursorType) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = toolkit.getImage(Consts.cursorPath + cursorType + ".png");
                Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), cursorType);
                target.setCursor(c);
        }

        public static String getAwaitingConfirmationMessage(ConfirmationFlagType flag) {
                switch (flag) {
                case CLEAR_GRAPH:
                        return "Delete all graph data? You won't be able to recover it once it's gone.";
                default:
                        System.out.println("ERROR: Confirmation flag \"" + flag + "\" awaiting message not handled!");
                }
                return "";
        }

        public static String getFinishedConfirmationMessage(ConfirmationFlagType flag) {
                switch (flag) {
                case CLEAR_GRAPH:
                        return "Canvas cleared!";
                default:
                        System.out.println("ERROR: Confirmation flag \"" + flag + "\" confirmation message not handled!");
                }
                return "";
        }

        public static GraphType getGraphType(String value) {
                switch (value) {
                case "Graph - undirected":
                        return GraphType.UNDIRECTED;
                case "Digraph - directed":
                        return GraphType.DIRECTED;
                }
                return null;
        }

        public static void formatLabel(JLabel label, int horizontalAlignment, int prefferedWidth) {
                formatLabel(label, JLabel.CENTER, horizontalAlignment, prefferedWidth);
        }

        public static void formatLabel(JLabel label, int verticalAlignment, int horizontalAlignment, int prefferedWidth) {
                label.setForeground(Consts.lightGray_1);
                label.setPreferredSize(new Dimension(prefferedWidth, Consts.settingsCellHeight));
                label.setVerticalAlignment(verticalAlignment);
                label.setHorizontalAlignment(horizontalAlignment);
        }

        public static void alignPairRow(JComponent leftComponent, JComponent rightComponent, SpringLayout targetLayout, JComponent target, String ownAnchor, String targetAnchor) {
                alignSettingsPairRow(leftComponent, rightComponent, targetLayout, target, target, ownAnchor, targetAnchor);
        }

        public static void alignSettingsPairRow(JComponent leftComponent, JComponent rightComponent, SpringLayout targetLayout, JComponent horizontalTarget, JComponent verticalTarget,
                        String ownAnchor, String targetAnchor) {

                int verticalPadding = (ownAnchor == SpringLayout.SOUTH ? -1 : 1) * Consts.settingsBtnsVerticalPadding;

                targetLayout.putConstraint(ownAnchor, leftComponent, verticalPadding, targetAnchor, verticalTarget);
                targetLayout.putConstraint(SpringLayout.WEST, leftComponent, Consts.settingsHorizontalPadding, SpringLayout.WEST, horizontalTarget);

                targetLayout.putConstraint(ownAnchor, rightComponent, verticalPadding, targetAnchor, verticalTarget);
                targetLayout.putConstraint(SpringLayout.EAST, rightComponent, -Consts.settingsHorizontalPadding, SpringLayout.EAST, horizontalTarget);

                targetLayout.putConstraint(SpringLayout.WEST, rightComponent, Consts.settingsColumnPadding, SpringLayout.EAST, leftComponent);
        }

        public static void alignEditorPairRow(JComponent leftComponent, JComponent rightComponent, SpringLayout targetLayout, JComponent horizontalTarget, JComponent verticalTarget, String ownAnchor,
                        String targetAnchor) {

                int verticalPadding = (ownAnchor == SpringLayout.SOUTH ? -1 : 1) * Consts.settingsBtnsVerticalPadding;

                targetLayout.putConstraint(ownAnchor, leftComponent, verticalPadding, targetAnchor, verticalTarget);
                targetLayout.putConstraint(SpringLayout.WEST, leftComponent, Consts.settingsHorizontalPadding, SpringLayout.WEST, horizontalTarget);

                targetLayout.putConstraint(ownAnchor, rightComponent, verticalPadding, targetAnchor, verticalTarget);
                targetLayout.putConstraint(SpringLayout.EAST, rightComponent, -Consts.settingsHorizontalPadding, SpringLayout.EAST, horizontalTarget);

                targetLayout.putConstraint(SpringLayout.WEST, rightComponent, Consts.settingsHorizontalPadding, SpringLayout.EAST, leftComponent);
        }

        public static void alignSoloRow(JComponent component, SpringLayout targetLayout, JComponent horizontalTarget, JComponent verticalTarget, boolean keepHorizontalPadding,
                        boolean keepVerticalPadding, String ownAnchor, String targetAnchor) {
                targetLayout.putConstraint(ownAnchor, component, keepVerticalPadding ? Consts.settingsColumnPadding : 0, targetAnchor, verticalTarget);
                targetLayout.putConstraint(SpringLayout.WEST, component, keepHorizontalPadding ? Consts.settingsHorizontalPadding : 0, SpringLayout.WEST, horizontalTarget);
                targetLayout.putConstraint(SpringLayout.EAST, component, keepHorizontalPadding ? -Consts.settingsHorizontalPadding : 0, SpringLayout.EAST, horizontalTarget);
        }

        public static void toggleFullScreen(JFrame window, WindowSettings windowSettings, boolean moveToCenter) {
                Dimension newDimension;
                if (windowSettings.getFullScreenState())
                        newDimension = windowSettings.getDimension();
                else
                        newDimension = getCurrentScreenDimension();

                window.setPreferredSize(newDimension);
                window.setMinimumSize(newDimension);
                if (moveToCenter)
                        window.setLocationRelativeTo(null);
                window.pack();

                windowSettings.toggleFullScreen();
                window.revalidate();
                window.repaint();
        }

        public static JLabel createHorizontalSeparator(int length) {
                String separatorText = new String(new char[length]).replace('\0', '.');
                JLabel separator = new JLabel(separatorText);

                return separator;
        }

        public static MaskFormatter createDigitOnlyFormatter(String placeholder) {
                MaskFormatter formatter = null;
                try {
                        formatter = new MaskFormatter("####");
                        formatter.setPlaceholder(placeholder);
                        formatter.setValidCharacters("0123456789");
                } catch (Exception e) {
                        System.err.println("Bad format string when creating grid size input!");
                }
                return formatter;
        }

}
