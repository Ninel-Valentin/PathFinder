package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.MaskFormatter;

import utils.CanvasServices.CanvasUnorientedGraphService;
import utils.DataStorageServices.LocalDataService;
import utils.DataStorageServices.SessionStorageService;

import javax.swing.ImageIcon;

public class ContentService {
        public static JLabel logRef;

        public static JPanel LoadContent(String windowName, final JFrame window) {
                SpringLayout layout = new SpringLayout();
                final JPanel contentPanel = new JPanel(layout);
                switch (windowName) {
                        case Consts.Dialogue.NAME:
                                contentPanel.setBackground(Consts.MenuBar.MENU_BG_COLOR);
                                // #region App Title
                                JLabel title = new JLabel("Application start-up settings", JLabel.CENTER);
                                title.setForeground(Color.WHITE);
                                contentPanel.add(title);

                                layout.putConstraint(SpringLayout.EAST,
                                                title,
                                                0,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                title,
                                                0,
                                                SpringLayout.WEST,
                                                contentPanel);
                                // #endregion

                                // #region Title - Canvas Type DropDown
                                JLabel canvasTitle = new JLabel("Canvas type");
                                canvasTitle.setForeground(Color.WHITE);
                                contentPanel.add(canvasTitle);

                                layout.putConstraint(SpringLayout.NORTH,
                                                canvasTitle,
                                                Consts.Dialogue.Window.PADDING_CATEGORY_V,
                                                SpringLayout.SOUTH,
                                                title);
                                layout.putConstraint(SpringLayout.EAST,
                                                canvasTitle,
                                                -Consts.Dialogue.Window.PADDING_HORIZONTAL,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                canvasTitle,
                                                Consts.Dialogue.Window.PADDING_HORIZONTAL,
                                                SpringLayout.WEST,
                                                contentPanel);
                                // #endregion

                                // #region DropDown - Canvas Type

                                String canvasCbItems[] = { "Graph Canvas", "Grid Canvas" };
                                final JComboBox<String> canvasCb = new JComboBox<>(canvasCbItems);
                                canvasCb.setEditable(false);
                                contentPanel.add(canvasCb);

                                layout.putConstraint(SpringLayout.NORTH,
                                                canvasCb,
                                                Consts.Dialogue.Window.PADDING_VERTICAL,
                                                SpringLayout.SOUTH,
                                                canvasTitle);
                                layout.putConstraint(SpringLayout.EAST,
                                                canvasCb,
                                                0,
                                                SpringLayout.EAST,
                                                canvasTitle);
                                layout.putConstraint(SpringLayout.WEST,
                                                canvasCb,
                                                0,
                                                SpringLayout.WEST,
                                                canvasTitle);
                                // #endregion

                                // #region Panel - Card panel
                                final SpringLayout cardLayout = new SpringLayout();
                                final JPanel cardPanel = new JPanel(cardLayout);
                                cardPanel.setBackground(null);

                                layout.putConstraint(SpringLayout.WEST,
                                                cardPanel,
                                                0,
                                                SpringLayout.WEST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                cardPanel,
                                                0,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.NORTH,
                                                cardPanel,
                                                15,
                                                SpringLayout.SOUTH,
                                                canvasCb);
                                layout.putConstraint(SpringLayout.SOUTH,
                                                cardPanel,
                                                110,
                                                SpringLayout.NORTH,
                                                cardPanel);
                                // #endregion

                                /*
                                 * // #region Title - Algorithm
                                 * 
                                 * JLabel algorithmTitle = new JLabel("Algorithm type", JLabel.LEFT);
                                 * algorithmTitle.setForeground(Color.WHITE);
                                 * cardPanel.add(algorithmTitle);
                                 * 
                                 * cardLayout.putConstraint(SpringLayout.NORTH,
                                 * algorithmTitle,
                                 * 0,
                                 * SpringLayout.NORTH,
                                 * cardPanel);
                                 * cardLayout.putConstraint(SpringLayout.EAST,
                                 * algorithmTitle,
                                 * -Consts.Dialogue.Window.PADDING_H,
                                 * SpringLayout.EAST,
                                 * cardPanel);
                                 * cardLayout.putConstraint(SpringLayout.WEST,
                                 * algorithmTitle,
                                 * Consts.Dialogue.Window.PADDING_H,
                                 * SpringLayout.WEST,
                                 * cardPanel);
                                 * // #endregion
                                 * 
                                 * // #region DropDown - Algorithm Type
                                 * String algorithmCbItems[] = Consts.MenuBar.ALGORTIHMS_GRAPH;
                                 * final JComboBox<String> algorithmCb = new JComboBox<>(algorithmCbItems);
                                 * algorithmCb.setEditable(false);
                                 * cardPanel.add(algorithmCb);
                                 * 
                                 * cardLayout.putConstraint(SpringLayout.NORTH,
                                 * algorithmCb,
                                 * Consts.Dialogue.Window.PADDING_V,
                                 * SpringLayout.SOUTH, algorithmTitle);
                                 * cardLayout.putConstraint(SpringLayout.EAST,
                                 * algorithmCb,
                                 * 0,
                                 * SpringLayout.EAST,
                                 * algorithmTitle);
                                 * cardLayout.putConstraint(SpringLayout.WEST,
                                 * algorithmCb,
                                 * 0,
                                 * SpringLayout.WEST,
                                 * algorithmTitle);
                                 * // #endregion
                                 */

                                // #region Title - Grid Size & Graph type

                                final JLabel graphGridTitle = new JLabel("Graph type", JLabel.LEFT);
                                graphGridTitle.setForeground(Color.WHITE);
                                cardPanel.add(graphGridTitle);

                                cardLayout.putConstraint(SpringLayout.NORTH,
                                                graphGridTitle,
                                                Consts.Dialogue.Window.PADDING_VERTICAL,
                                                SpringLayout.NORTH,
                                                cardPanel);
                                cardLayout.putConstraint(SpringLayout.EAST,
                                                graphGridTitle,
                                                -Consts.Dialogue.Window.PADDING_HORIZONTAL,
                                                SpringLayout.EAST,
                                                cardPanel);
                                cardLayout.putConstraint(SpringLayout.WEST,
                                                graphGridTitle,
                                                Consts.Dialogue.Window.PADDING_HORIZONTAL,
                                                SpringLayout.WEST,
                                                cardPanel);
                                // #endregion

                                // #region DropDown - Graph type
                                String graphCbItems[] = { "Unoriented Graph", "Oriented Graph - Digraph" };

                                final JComboBox<String> graphCb = new JComboBox<>(graphCbItems);
                                graphCb.setEditable(false);
                                graphCb.setName("graphCb_REMOVABLE");
                                cardPanel.add(graphCb);

                                cardLayout.putConstraint(SpringLayout.NORTH,
                                                graphCb,
                                                5,
                                                SpringLayout.SOUTH,
                                                graphGridTitle);
                                cardLayout.putConstraint(SpringLayout.EAST,
                                                graphCb,
                                                0,
                                                SpringLayout.EAST,
                                                graphGridTitle);
                                cardLayout.putConstraint(SpringLayout.WEST,
                                                graphCb,
                                                0,
                                                SpringLayout.WEST,
                                                graphGridTitle);
                                // #endregion

                                contentPanel.add(cardPanel);

                                // #region Title - Recent files
                                JLabel recentTitle = new JLabel("Recent files", JLabel.LEFT);
                                recentTitle.setForeground(Color.WHITE);
                                contentPanel.add(recentTitle);

                                layout.putConstraint(SpringLayout.NORTH,
                                                recentTitle,
                                                Consts.Dialogue.Window.PADDING_VERTICAL,
                                                SpringLayout.SOUTH,
                                                cardPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                recentTitle,
                                                -Consts.Dialogue.Window.PADDING_HORIZONTAL,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                recentTitle,
                                                Consts.Dialogue.Window.PADDING_HORIZONTAL,
                                                SpringLayout.WEST,
                                                contentPanel);
                                // #endregion

                                // #region Panel - Recent files
                                SpringLayout recentLayout = new SpringLayout();
                                JPanel recentFilesPanel = new JPanel(recentLayout);
                                recentFilesPanel.setBackground(Consts.MenuBar.MENU_BG_COLOR);
                                contentPanel.add(recentFilesPanel);

                                File[] recentFiles = LocalDataService.GetAllSaveFilePaths();
                                int rowPadding = 0;
                                if (recentFiles.length == 0) {
                                        // Display message if no save files are available
                                        JLabel noRecentFilesLbl = new JLabel("Hmmm... No recent saves found!",
                                                        SwingConstants.CENTER);
                                        noRecentFilesLbl.setForeground(Color.WHITE);
                                        recentFilesPanel.setBackground(Consts.MenuBar.WINDOW_BG_COLOR);

                                        recentLayout.putConstraint(SpringLayout.NORTH,
                                                        noRecentFilesLbl,
                                                        rowPadding,
                                                        SpringLayout.NORTH,
                                                        recentFilesPanel);
                                        recentLayout.putConstraint(SpringLayout.SOUTH,
                                                        noRecentFilesLbl,
                                                        Consts.Dialogue.Window.RECENT_FILE_ROW_HEIGHT,
                                                        SpringLayout.NORTH,
                                                        noRecentFilesLbl);
                                        recentLayout.putConstraint(SpringLayout.WEST,
                                                        noRecentFilesLbl,
                                                        0,
                                                        SpringLayout.WEST,
                                                        recentFilesPanel);
                                        recentLayout.putConstraint(SpringLayout.EAST,
                                                        noRecentFilesLbl,
                                                        0,
                                                        SpringLayout.EAST,
                                                        recentFilesPanel);
                                        recentFilesPanel.add(noRecentFilesLbl);
                                } else {
                                        // Display buttons to load the files if any are available in the save directory
                                        for (int i = 0; i < Consts.Dialogue.Window.NUMBER_OF_READ_FILES; i++) {
                                                File recentFile = recentFiles[i];
                                                JButton recentFileButton = new JButton(recentFile.getName());
                                                recentFileButton.setToolTipText(
                                                                "<html>Absolute path:<br>" + recentFile.getPath()
                                                                                + "</html>");
                                                recentFileButton.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                                // TODO: Existing project opening logic.
                                                                System.out.println("Opening "
                                                                                + ((JButton) e.getSource()).getText());
                                                        }
                                                });
                                                recentFileButton.setHorizontalAlignment(SwingConstants.LEFT);
                                                recentFileButton.setForeground(Color.WHITE);
                                                recentFileButton.setBackground(Consts.MenuBar.WINDOW_BG_COLOR);
                                                // Make the background transparent for the button
                                                recentFileButton.setBorderPainted(false);
                                                recentFileButton.setBorder(null);

                                                // Add hover effect on the buttons
                                                recentFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseEntered(MouseEvent evt) {
                                                                ((JButton) evt.getSource())
                                                                                .setBackground(Consts.MenuBar.BTN_COLOR_HOVER);
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                                ((JButton) evt.getSource())
                                                                                .setBackground(Consts.MenuBar.WINDOW_BG_COLOR);
                                                        }
                                                });

                                                recentLayout.putConstraint(SpringLayout.NORTH,
                                                                recentFileButton,
                                                                rowPadding,
                                                                SpringLayout.NORTH,
                                                                recentFilesPanel);
                                                recentLayout.putConstraint(SpringLayout.SOUTH,
                                                                recentFileButton,
                                                                Consts.Dialogue.Window.RECENT_FILE_ROW_HEIGHT,
                                                                SpringLayout.NORTH,
                                                                recentFileButton);
                                                recentLayout.putConstraint(SpringLayout.WEST,
                                                                recentFileButton,
                                                                0,
                                                                SpringLayout.WEST,
                                                                recentFilesPanel);
                                                recentLayout.putConstraint(SpringLayout.EAST,
                                                                recentFileButton,
                                                                0,
                                                                SpringLayout.EAST,
                                                                recentFilesPanel);
                                                recentFilesPanel.add(recentFileButton);

                                                rowPadding += Consts.Dialogue.Window.RECENT_FILE_ROW_HEIGHT;
                                        }
                                }

                                layout.putConstraint(SpringLayout.WEST,
                                                recentFilesPanel,
                                                0,
                                                SpringLayout.WEST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                recentFilesPanel,
                                                0,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.NORTH,
                                                recentFilesPanel,
                                                5,
                                                SpringLayout.SOUTH,
                                                recentTitle);
                                layout.putConstraint(SpringLayout.SOUTH,
                                                recentFilesPanel,
                                                Consts.Dialogue.Window.RECENT_FILE_Y,
                                                SpringLayout.NORTH,
                                                recentFilesPanel);
                                // #endregion

                                // #region Button - New
                                JButton newButton = new JButton("NEW");
                                contentPanel.add(newButton);
                                newButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                                String dataInfo = null;
                                                switch (canvasCb.getSelectedItem().toString()) {
                                                        case "Graph Canvas":
                                                                for (Component obj : cardPanel.getComponents()) {
                                                                        if (obj.getName() != null)
                                                                                if (obj.getName() == "graphCb_REMOVABLE") {
                                                                                        @SuppressWarnings("unchecked")
                                                                                        String type = ((JComboBox<String>) (obj))
                                                                                                        .getSelectedItem()
                                                                                                        .toString();
                                                                                        switch (type) {
                                                                                                case "Unoriented Graph":
                                                                                                        dataInfo = "UNORIENTED";
                                                                                                        break;
                                                                                                case "Oriented Graph - Digraph":
                                                                                                        dataInfo = "ORIENTED";
                                                                                                        break;
                                                                                                default:
                                                                                                        System.err.println(
                                                                                                                        "\"" + type
                                                                                                                                        + "\" is not a valid secondary dataset!");
                                                                                        }
                                                                                        break;
                                                                                }
                                                                }
                                                                break;
                                                        case "Grid Canvas":
                                                                Dimension size = new Dimension(0, 0);
                                                                for (Component obj : cardPanel.getComponents()) {
                                                                        if (obj.getName() != null) {
                                                                                if (obj.getName() == "rowInput_REMOVABLE") {
                                                                                        Object rows = ((JFormattedTextField) (obj))
                                                                                                        .getValue();
                                                                                        // Initialize with negative
                                                                                        // value to check at the end;
                                                                                        int height = -1;
                                                                                        if (rows == null || Integer
                                                                                                        .valueOf(((JFormattedTextField) (obj))
                                                                                                                        .getValue()
                                                                                                                        .toString()) < 2) {
                                                                                                ((JFormattedTextField) (obj))
                                                                                                                .setBackground(Consts.Dialogue.Window.GRIDSIZE_WRONG_COLOR);
                                                                                        } else {
                                                                                                ((JFormattedTextField) (obj))
                                                                                                                .setBackground(Color.WHITE);
                                                                                                height = Integer.valueOf(
                                                                                                                ((JFormattedTextField) (obj))
                                                                                                                                .getValue()
                                                                                                                                .toString());

                                                                                        }
                                                                                        size.setSize(size.width,
                                                                                                        height);
                                                                                }
                                                                                if (obj.getName() == "colInput_REMOVABLE") {
                                                                                        Object cols = ((JFormattedTextField) (obj))
                                                                                                        .getValue();
                                                                                        // Initialize with negative
                                                                                        // value to check at the end;
                                                                                        int width = -1;
                                                                                        if (cols == null || Integer
                                                                                                        .valueOf(((JFormattedTextField) (obj))
                                                                                                                        .getValue()
                                                                                                                        .toString()) < 2) {
                                                                                                ((JFormattedTextField) (obj))
                                                                                                                .setBackground(Consts.Dialogue.Window.GRIDSIZE_WRONG_COLOR);
                                                                                        } else {
                                                                                                ((JFormattedTextField) (obj))
                                                                                                                .setBackground(Color.WHITE);
                                                                                                width = Integer.valueOf(
                                                                                                                ((JFormattedTextField) (obj))
                                                                                                                                .getValue()
                                                                                                                                .toString());
                                                                                        }
                                                                                        size.setSize(width,
                                                                                                        size.height);
                                                                                }
                                                                        }
                                                                }
                                                                dataInfo = size.width < 2 || size.height < 2
                                                                                ? "ERROR"
                                                                                : "GRID:" + size.width + ":"
                                                                                                + size.height;
                                                                break;
                                                        default:
                                                                System.err.println(
                                                                                "\"" + canvasCb.getSelectedItem()
                                                                                                + "\" is not a valid setting!");
                                                }
                                                if (dataInfo != "ERROR") {
                                                        SessionStorageService.WriteSessionStorage(dataInfo);
                                                        WindowService.OpenMainWindow();
                                                        window.dispose();
                                                }
                                        }
                                });

                                layout.putConstraint(SpringLayout.NORTH,
                                                newButton,
                                                Consts.Dialogue.Window.BUTTON_MARGIN_TOP,
                                                SpringLayout.SOUTH,
                                                recentFilesPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                newButton,
                                                Consts.Dialogue.Window.BUTTON_MARGIN_H,
                                                SpringLayout.WEST,
                                                contentPanel);
                                // #endregion

                                // #region Button - Open
                                JButton openButton = new JButton("OPEN");
                                contentPanel.add(openButton);
                                layout.putConstraint(SpringLayout.NORTH,
                                                openButton,
                                                Consts.Dialogue.Window.BUTTON_MARGIN_TOP,
                                                SpringLayout.SOUTH,
                                                recentFilesPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                openButton,
                                                -Consts.Dialogue.Window.BUTTON_MARGIN_H,
                                                SpringLayout.EAST,
                                                contentPanel);
                                // #endregion

                                // EventListeners - ComboBoxes
                                canvasCb.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                                // 2 for current item | 1 for selected item
                                                if (e.getStateChange() == 1) {
                                                        switch (e.getItem().toString()) {
                                                                case "Graph Canvas":
                                                                        for (Component obj : cardPanel
                                                                                        .getComponents()) {
                                                                                if (obj.getName() == "rowInput_REMOVABLE"
                                                                                                ||
                                                                                                obj.getName() == "colInput_REMOVABLE") {
                                                                                        cardPanel.remove(obj);
                                                                                }
                                                                        }
                                                                        graphGridTitle.setText(
                                                                                        "Grid size (rows & columns)");

                                                                        // #region DropDown - Graph type
                                                                        String graphCbItems[] = { "Unoriented Graph",
                                                                                        "Oriented Graph - Digraph" };

                                                                        final JComboBox<String> graphCb = new JComboBox<>(
                                                                                        graphCbItems);
                                                                        graphCb.setName("graphCb_REMOVABLE");
                                                                        graphCb.setEditable(false);
                                                                        cardPanel.add(graphCb);

                                                                        cardLayout.putConstraint(SpringLayout.NORTH,
                                                                                        graphCb,
                                                                                        5,
                                                                                        SpringLayout.SOUTH,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.EAST,
                                                                                        graphCb,
                                                                                        0,
                                                                                        SpringLayout.EAST,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.WEST,
                                                                                        graphCb,
                                                                                        0,
                                                                                        SpringLayout.WEST,
                                                                                        graphGridTitle);
                                                                        // #endregion

                                                                        break;
                                                                case "Grid Canvas":
                                                                        for (Component obj : cardPanel
                                                                                        .getComponents()) {
                                                                                if (obj.getName() == "graphCb_REMOVABLE") {
                                                                                        cardPanel.remove(obj);
                                                                                }
                                                                        }
                                                                        graphGridTitle.setText(
                                                                                        "Grid size (rows & columns)");

                                                                        // #region DropDown - Grid Size Rows
                                                                        // TODO: add check for inputs to have values
                                                                        // over 2
                                                                        JFormattedTextField rowInput = new JFormattedTextField(
                                                                                        createDigitOnlyFormatter());
                                                                        rowInput.setName("rowInput_REMOVABLE");
                                                                        cardPanel.add(rowInput);

                                                                        cardLayout.putConstraint(SpringLayout.NORTH,
                                                                                        rowInput,
                                                                                        Consts.Dialogue.Window.PADDING_VERTICAL,
                                                                                        SpringLayout.SOUTH,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.WEST,
                                                                                        rowInput,
                                                                                        0,
                                                                                        SpringLayout.WEST,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.EAST,
                                                                                        rowInput,
                                                                                        Consts.Dialogue.Window.GRIDSIZE_INPUT_WIDTH,
                                                                                        SpringLayout.WEST,
                                                                                        rowInput);
                                                                        // #endregion

                                                                        // #region DropDown - Grid Size Columns
                                                                        // TODO: add check for inputs to have values
                                                                        // over 2
                                                                        JFormattedTextField colInput = new JFormattedTextField(
                                                                                        createDigitOnlyFormatter());
                                                                        colInput.setName("colInput_REMOVABLE");
                                                                        cardPanel.add(colInput);

                                                                        cardLayout.putConstraint(SpringLayout.NORTH,
                                                                                        colInput,
                                                                                        Consts.Dialogue.Window.PADDING_VERTICAL,
                                                                                        SpringLayout.SOUTH,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.EAST,
                                                                                        colInput,
                                                                                        0,
                                                                                        SpringLayout.EAST,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.WEST,
                                                                                        colInput,
                                                                                        -Consts.Dialogue.Window.GRIDSIZE_INPUT_WIDTH,
                                                                                        SpringLayout.EAST,
                                                                                        colInput);
                                                                        // #endregion

                                                                        break;
                                                                default:
                                                                        System.err.println("\"" + e.getItem()
                                                                                        + "\" is not a handled canvas type");
                                                        }
                                                        cardPanel.revalidate();
                                                        cardPanel.repaint();
                                                }
                                        }
                                });
                                return contentPanel;
                        case Consts.Main.NAME:
                                SpringLayout toolboxLayout = new SpringLayout();
                                JPanel toolboxPanel = new JPanel(toolboxLayout) {
                                        @Override
                                        protected void paintComponent(Graphics g) {
                                                super.paintComponent(g);
                                                int width = getWidth();
                                                int height = getHeight();
                                                Graphics2D graphics = (Graphics2D) g;
                                                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                                                RenderingHints.VALUE_ANTIALIAS_ON);

                                                graphics.setColor(Color.WHITE);
                                                graphics.fillRoundRect(
                                                                0,
                                                                0,
                                                                width - 1,
                                                                height - 1,
                                                                Consts.Main.Window.ToolBox.CORNER_RADIUS,
                                                                Consts.Main.Window.ToolBox.CORNER_RADIUS);
                                        }
                                };
                                toolboxPanel.setOpaque(false);
                                toolboxPanel.setBackground(Consts.Main.Window.ToolBox.COLOR);
                                contentPanel.add(toolboxPanel);

                                layout.putConstraint(SpringLayout.NORTH,
                                                toolboxPanel,
                                                -(Consts.Main.Window.ToolBox.HEIGHT
                                                                + Consts.Main.Window.ToolBox.LABEL_HEIGHT),
                                                SpringLayout.SOUTH,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.SOUTH,
                                                toolboxPanel,
                                                0,
                                                SpringLayout.SOUTH,
                                                contentPanel);
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                int toolboxMargin = ((int) screenSize.getWidth() - Consts.Main.Window.ToolBox.WIDTH)
                                                / 2;
                                layout.putConstraint(SpringLayout.EAST,
                                                toolboxPanel,
                                                -toolboxMargin,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                toolboxPanel,
                                                toolboxMargin,
                                                SpringLayout.WEST,
                                                contentPanel);

                                // #region Canvas

                                JPanel canvas = InitializeEmptyCanvas();

                                layout.putConstraint(SpringLayout.WEST,
                                                canvas,
                                                Consts.Main.Window.Canvas.PADDING,
                                                SpringLayout.WEST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                canvas,
                                                -Consts.Main.Window.Canvas.PADDING,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.NORTH,
                                                canvas,
                                                Consts.Main.Window.Canvas.PADDING,
                                                SpringLayout.NORTH,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.SOUTH,
                                                canvas,
                                                -Consts.Main.Window.Canvas.PADDING,
                                                SpringLayout.SOUTH,
                                                contentPanel);

                                canvas.setBackground(Consts.MenuBar.MENU_BG_COLOR);
                                contentPanel.setBackground(Consts.MenuBar.WINDOW_BG_COLOR);
                                contentPanel.add(canvas);
                                // #endregion

                                // #region Toolbox - Buttons

                                int buttonCount = 3;
                                int remainingSpace = Consts.Main.Window.ToolBox.WIDTH
                                                - buttonCount * Consts.Main.Window.ToolBox.HEIGHT;
                                int buttonSpacingAround = remainingSpace / (buttonCount * 2);
                                int labelSize = Consts.Main.Window.ToolBox.WIDTH / buttonCount;

                                // #region Buttons Add
                                JButton addBtn = CreateToolboxButton("Original", canvas);
                                toolboxLayout.putConstraint(SpringLayout.WEST,
                                                addBtn,
                                                buttonSpacingAround,
                                                SpringLayout.WEST,
                                                toolboxPanel);
                                toolboxLayout.putConstraint(SpringLayout.NORTH,
                                                addBtn,
                                                Consts.Main.Window.ToolBox.LABEL_HEIGHT - 1,
                                                SpringLayout.NORTH,
                                                toolboxPanel);
                                toolboxPanel.add(addBtn);

                                JLabel addLbl = new JLabel("Select nodes", SwingConstants.CENTER);
                                addLbl.setForeground(Consts.Main.Window.ToolBox.TEXT_COLOR);
                                toolboxLayout.putConstraint(SpringLayout.WEST,
                                                addLbl,
                                                0,
                                                SpringLayout.WEST,
                                                toolboxPanel);
                                toolboxLayout.putConstraint(SpringLayout.EAST,
                                                addLbl,
                                                labelSize,
                                                SpringLayout.WEST,
                                                toolboxPanel);
                                toolboxLayout.putConstraint(SpringLayout.SOUTH,
                                                addLbl,
                                                -1,
                                                SpringLayout.NORTH,
                                                addBtn);
                                toolboxLayout.putConstraint(SpringLayout.NORTH,
                                                addLbl,
                                                1,
                                                SpringLayout.NORTH,
                                                toolboxPanel);
                                toolboxPanel.add(addLbl);
                                // #endregion

                                // #region Buttons Remove
                                JButton removeButton = CreateToolboxButton("Add", canvas);
                                toolboxLayout.putConstraint(SpringLayout.WEST,
                                                removeButton,
                                                2 * buttonSpacingAround,
                                                SpringLayout.EAST,
                                                addBtn);
                                toolboxLayout.putConstraint(SpringLayout.NORTH,
                                                removeButton,
                                                Consts.Main.Window.ToolBox.LABEL_HEIGHT - 1,
                                                SpringLayout.NORTH,
                                                toolboxPanel);
                                toolboxPanel.add(removeButton);

                                JLabel removeLbl = new JLabel("Create nodes", SwingConstants.CENTER);
                                toolboxLayout.putConstraint(SpringLayout.WEST,
                                                removeLbl,
                                                0,
                                                SpringLayout.EAST,
                                                addLbl);
                                toolboxLayout.putConstraint(SpringLayout.EAST,
                                                removeLbl,
                                                labelSize,
                                                SpringLayout.EAST,
                                                addLbl);
                                toolboxLayout.putConstraint(SpringLayout.SOUTH,
                                                removeLbl,
                                                -1,
                                                SpringLayout.NORTH,
                                                removeButton);
                                toolboxLayout.putConstraint(SpringLayout.NORTH,
                                                removeLbl,
                                                1,
                                                SpringLayout.NORTH,
                                                toolboxPanel);
                                toolboxPanel.add(removeLbl);
                                // #endregion

                                // #region Buttons Select
                                JButton selectButton = CreateToolboxButton("Remove", canvas);
                                toolboxLayout.putConstraint(SpringLayout.WEST,
                                                selectButton,
                                                2 * buttonSpacingAround,
                                                SpringLayout.EAST,
                                                removeButton);
                                toolboxLayout.putConstraint(SpringLayout.NORTH,
                                                selectButton,
                                                Consts.Main.Window.ToolBox.LABEL_HEIGHT - 1,
                                                SpringLayout.NORTH,
                                                toolboxPanel);
                                toolboxPanel.add(selectButton);

                                JLabel selectLbl = new JLabel("Remove nodes", SwingConstants.CENTER);
                                toolboxLayout.putConstraint(SpringLayout.WEST,
                                                selectLbl,
                                                0,
                                                SpringLayout.EAST,
                                                removeLbl);
                                toolboxLayout.putConstraint(SpringLayout.EAST,
                                                selectLbl,
                                                labelSize,
                                                SpringLayout.EAST,
                                                removeLbl);
                                toolboxLayout.putConstraint(SpringLayout.SOUTH,
                                                addLbl,
                                                -1,
                                                SpringLayout.NORTH,
                                                selectButton);
                                toolboxLayout.putConstraint(SpringLayout.NORTH,
                                                selectLbl,
                                                1,
                                                SpringLayout.NORTH,
                                                toolboxPanel);
                                toolboxPanel.add(selectLbl);
                                // #endregion

                                // #endregion Toolbox

                                SpringLayout windowLayout = new SpringLayout();
                                JPanel fullContent = new JPanel(windowLayout);
                                SpringLayout logLayout = new SpringLayout();
                                JPanel logPanel = new JPanel(logLayout);
                                logPanel.setBackground(Consts.MenuBar.MENU_BG_COLOR);

                                JLabel logLbl = new JLabel();

                                logLayout.putConstraint(SpringLayout.NORTH,
                                                logLbl,
                                                0,
                                                SpringLayout.NORTH,
                                                logPanel);
                                logLayout.putConstraint(SpringLayout.SOUTH,
                                                logLbl,
                                                0,
                                                SpringLayout.SOUTH,
                                                logPanel);
                                logLayout.putConstraint(SpringLayout.EAST,
                                                logLbl,
                                                0,
                                                SpringLayout.EAST,
                                                logPanel);
                                logLayout.putConstraint(SpringLayout.WEST,
                                                logLbl,
                                                0,
                                                SpringLayout.WEST,
                                                logPanel);

                                logLbl.setForeground(Color.WHITE);

                                // Save reference to the class
                                logRef = logLbl;
                                logPanel.add(logLbl);

                                fullContent.add(contentPanel);
                                fullContent.add(logPanel);

                                // Set constraints for the two panels
                                windowLayout.putConstraint(SpringLayout.WEST,
                                                logPanel,
                                                0,
                                                SpringLayout.WEST,
                                                fullContent);
                                windowLayout.putConstraint(SpringLayout.EAST,
                                                logPanel,
                                                0,
                                                SpringLayout.EAST,
                                                fullContent);
                                windowLayout.putConstraint(SpringLayout.SOUTH,
                                                logPanel,
                                                0,
                                                SpringLayout.SOUTH,
                                                fullContent);
                                windowLayout.putConstraint(SpringLayout.NORTH,
                                                logPanel,
                                                -Consts.Logger.HEIGHT,
                                                SpringLayout.SOUTH,
                                                fullContent);

                                windowLayout.putConstraint(SpringLayout.WEST,
                                                contentPanel,
                                                0,
                                                SpringLayout.WEST,
                                                fullContent);
                                windowLayout.putConstraint(SpringLayout.EAST,
                                                contentPanel,
                                                0,
                                                SpringLayout.EAST,
                                                fullContent);
                                windowLayout.putConstraint(SpringLayout.NORTH,
                                                contentPanel,
                                                0,
                                                SpringLayout.NORTH,
                                                fullContent);
                                windowLayout.putConstraint(SpringLayout.SOUTH,
                                                contentPanel,
                                                0,
                                                SpringLayout.NORTH,
                                                logPanel);
                                return fullContent;

                        case Consts.Details.NAME:
                                // create custom label 
                                contentPanel.setBackground(Consts.Details.Window.WINDOW_BG_COLOR);
                                return contentPanel;
                        default:
                                System.out.println("\"" + windowName + "\" is not a specified value in ContentService");
                                return null;
                }
        }

        private static JButton CreateToolboxButton(final String iconName, final JPanel canvas) {
                ImageIcon icon = new ImageIcon(Consts.Main.Window.Canvas.CUSTOM_CURSOR_PATH + "_" + iconName + ".png");
                Image scaledIcon = icon.getImage()
                                .getScaledInstance(Consts.Main.Window.ToolBox.HEIGHT, Consts.Main.Window.ToolBox.HEIGHT,
                                                Image.SCALE_SMOOTH);
                JButton button = new JButton(new ImageIcon(scaledIcon));
                button.setBackground(null);
                // Make the background transparent for the button
                button.setBorderPainted(false);
                button.setBorder(null);
                // Resize button
                button.setSize(Consts.Main.Window.ToolBox.HEIGHT, Consts.Main.Window.ToolBox.HEIGHT);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));

                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Toolkit toolkit = Toolkit.getDefaultToolkit();
                                Image cursorIcon = toolkit
                                                .getImage(Consts.Main.Window.Canvas.CUSTOM_CURSOR_PATH + "_" + iconName
                                                                + ".png");
                                Cursor customCursor = toolkit.createCustomCursor(cursorIcon, new Point(0, 0), iconName);
                                canvas.setCursor(customCursor);
                        }
                });

                return button;
        }

        private static JPanel InitializeEmptyCanvas() {

                final String dataSet = SessionStorageService.ReadSessionStorage();

                JPanel canvas = new JPanel();
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image cursorIcon = toolkit.getImage(Consts.Main.Window.Canvas.CUSTOM_CURSOR_PATH + "_Original.png");
                Cursor customCursor = toolkit.createCustomCursor(cursorIcon, new Point(0, 0), "Original");
                canvas.setCursor(customCursor);
                Object canvasService = null;
                switch (dataSet) {
                        case "ORIENTED":
                                // TODO: add handleClick for other types of graph
                                break;
                        case "UNORIENTED":
                                canvasService = new CanvasUnorientedGraphService(canvas);
                                break;
                        default:
                }
                final Object finalCanvasService = canvasService;
                // TODO: add JPanel for GRID graph
                canvas.addMouseListener(new MouseInputAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                                switch (dataSet) {
                                        case "ORIENTED":
                                                // TODO: add handleClick for other types of graph
                                                break;
                                        case "UNORIENTED":
                                                ((CanvasUnorientedGraphService) finalCanvasService).HandleClick(e);
                                                break;
                                        default:
                                }
                        }
                });
                return canvas;
        }

        protected static MaskFormatter createDigitOnlyFormatter() {
                MaskFormatter formatter = null;
                try {
                        formatter = new MaskFormatter("##");
                        formatter.setPlaceholder("00");
                        formatter.setValidCharacters("0123456789");
                } catch (Exception e) {
                        System.err.println("Bad format string when creating grid size input!");
                }
                return formatter;
        }
}