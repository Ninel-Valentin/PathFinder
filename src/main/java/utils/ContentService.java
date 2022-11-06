package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
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

import utils.Algorythms.Graphs.Graph;
import utils.Algorythms.Grids.Grid;

import javax.swing.ImageIcon;

public class ContentService {
        public static JPanel LoadContent(String windowName, final JFrame window) {
                SpringLayout layout = new SpringLayout();
                final JPanel contentPanel = new JPanel(layout);
                switch (windowName) {
                        case "dialogueSettings":
                                contentPanel.setBackground(Consts.BG_COLOR);
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
                                                Consts.DIALOGUE_PADDING_CATEGORY_V,
                                                SpringLayout.SOUTH,
                                                title);
                                layout.putConstraint(SpringLayout.EAST,
                                                canvasTitle,
                                                -Consts.DIALOGUE_PADDING_H,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                canvasTitle,
                                                Consts.DIALOGUE_PADDING_H,
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
                                                Consts.DIALOGUE_PADDING_V,
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

                                // #region Title - Algorithm

                                JLabel algorithmTitle = new JLabel("Algorithm type", JLabel.LEFT);
                                algorithmTitle.setForeground(Color.WHITE);
                                cardPanel.add(algorithmTitle);

                                cardLayout.putConstraint(SpringLayout.NORTH,
                                                algorithmTitle,
                                                0,
                                                SpringLayout.NORTH,
                                                cardPanel);
                                cardLayout.putConstraint(SpringLayout.EAST,
                                                algorithmTitle,
                                                -Consts.DIALOGUE_PADDING_H,
                                                SpringLayout.EAST,
                                                cardPanel);
                                cardLayout.putConstraint(SpringLayout.WEST,
                                                algorithmTitle,
                                                Consts.DIALOGUE_PADDING_H,
                                                SpringLayout.WEST,
                                                cardPanel);
                                // #endregion

                                // #region DropDown - Algorithm Type
                                String algorithmCbItems[] = Consts.ALGORTIHMS_GRAPH;
                                final JComboBox<String> algorithmCb = new JComboBox<>(algorithmCbItems);
                                algorithmCb.setEditable(false);
                                cardPanel.add(algorithmCb);

                                cardLayout.putConstraint(SpringLayout.NORTH,
                                                algorithmCb,
                                                Consts.DIALOGUE_PADDING_V,
                                                SpringLayout.SOUTH, algorithmTitle);
                                cardLayout.putConstraint(SpringLayout.EAST,
                                                algorithmCb,
                                                0,
                                                SpringLayout.EAST,
                                                algorithmTitle);
                                cardLayout.putConstraint(SpringLayout.WEST,
                                                algorithmCb,
                                                0,
                                                SpringLayout.WEST,
                                                algorithmTitle);
                                // #endregion

                                // #region Title - Grid Size & Graph type

                                final JLabel graphGridTitle = new JLabel("Graph type", JLabel.LEFT);
                                graphGridTitle.setForeground(Color.WHITE);
                                cardPanel.add(graphGridTitle);

                                cardLayout.putConstraint(SpringLayout.NORTH,
                                                graphGridTitle,
                                                Consts.DIALOGUE_PADDING_V,
                                                SpringLayout.SOUTH,
                                                algorithmCb);
                                cardLayout.putConstraint(SpringLayout.EAST,
                                                graphGridTitle,
                                                -Consts.DIALOGUE_PADDING_H,
                                                SpringLayout.EAST,
                                                cardPanel);
                                cardLayout.putConstraint(SpringLayout.WEST,
                                                graphGridTitle,
                                                Consts.DIALOGUE_PADDING_H,
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
                                                Consts.DIALOGUE_PADDING_V,
                                                SpringLayout.SOUTH,
                                                cardPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                recentTitle,
                                                -Consts.DIALOGUE_PADDING_H,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                recentTitle,
                                                Consts.DIALOGUE_PADDING_H,
                                                SpringLayout.WEST,
                                                contentPanel);
                                // #endregion

                                // #region Panel - Recent files
                                SpringLayout recentLayout = new SpringLayout();
                                JPanel recentFilesPanel = new JPanel(recentLayout);
                                recentFilesPanel.setBackground(Consts.BG_COLOR);
                                contentPanel.add(recentFilesPanel);

                                // TODO: MODIFY TO READ FROM FILE | MAX 8 ENTRIES
                                String[] recentFiles = {
                                                "C:\\Users\\valen\\Desktop\\Projects\\Github\\PathFinder\\",
                                                "C:\\Users\\Georg\\Github\\FinderPath\\",
                                                "C:\\Users\\valentin\\Desktop\\DoNotEnter\\TestDir\\",
                                                "C:\\Users\\someoneElse\\Projects\\Testing\\",
                                                "C:\\Users\\valen\\Desktop\\Projects\\Github\\PathFinder\\",
                                                "C:\\Users\\Georg\\Github\\FinderPath\\",
                                                "C:\\Users\\valentin\\Desktop\\DoNotEnter\\TestDir\\",
                                                "C:\\Users\\someoneElse\\Projects\\Testing\\"
                                };

                                int rowPadding = 0;
                                for (String recentFile : recentFiles) {

                                        JButton recentFileButton = new JButton(recentFile);
                                        recentFileButton.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                        System.out.println("Opening "
                                                                        + ((JButton) e.getSource()).getText());
                                                }
                                        });
                                        recentFileButton.setHorizontalAlignment(SwingConstants.LEFT);
                                        recentFileButton.setForeground(Color.WHITE);
                                        recentFileButton.setBackground(Consts.MENU_COLOR);
                                        // Make the background transparent for the button
                                        recentFileButton.setBorderPainted(false);
                                        recentFileButton.setBorder(null);

                                        // Add hover effect on the buttons
                                        recentFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
                                                public void mouseEntered(MouseEvent evt) {
                                                        ((JButton) evt.getSource())
                                                                        .setBackground(Consts.MENU_BTN_COLOR_HOVER);
                                                }

                                                public void mouseExited(MouseEvent evt) {
                                                        ((JButton) evt.getSource()).setBackground(Consts.MENU_COLOR);
                                                }
                                        });

                                        recentLayout.putConstraint(SpringLayout.NORTH,
                                                        recentFileButton,
                                                        rowPadding,
                                                        SpringLayout.NORTH,
                                                        recentFilesPanel);
                                        recentLayout.putConstraint(SpringLayout.SOUTH,
                                                        recentFileButton,
                                                        Consts.RECENT_FILE_ROW_HEIGHT,
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

                                        rowPadding += Consts.RECENT_FILE_ROW_HEIGHT;
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
                                                Consts.RECENT_FILE_Y,
                                                SpringLayout.NORTH,
                                                recentFilesPanel);
                                // #endregion

                                // #region Button - New
                                JButton newButton = new JButton("NEW");
                                contentPanel.add(newButton);
                                newButton.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e) {
                                                // INFO: Save logic

                                                // String path = System.getProperty("user.home") + "\\Desktop";
                                                // JFileChooser chooser = new JFileChooser(path);
                                                // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                                // int returned = chooser.showOpenDialog(window);
                                                // if (returned == JFileChooser.APPROVE_OPTION) {
                                                // String location = chooser.getCurrentDirectory().getAbsolutePath();
                                                // }
                                                String dataInfo = null;
                                                Object secondaryDataset = null;
                                                switch (canvasCb.getSelectedItem().toString()) {
                                                        case "Graph Canvas":
                                                                for (Component obj : cardPanel.getComponents()) {
                                                                        if (obj.getName() != null)
                                                                                if (obj.getName() == "graphCb_REMOVABLE") {
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
                                                                                                                        "ERROR: \"" + type
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
                                                                                        size.setSize(Integer
                                                                                                        .valueOf(((JFormattedTextField) (obj))
                                                                                                                        .getValue()
                                                                                                                        .toString()),
                                                                                                        size.height);
                                                                                }
                                                                                if (obj.getName() == "colInput_REMOVABLE") {

                                                                                        size.setSize(size.width, Integer
                                                                                                        .valueOf(((JFormattedTextField) (obj))
                                                                                                                        .getValue()
                                                                                                                        .toString()));
                                                                                }
                                                                                break;
                                                                        }
                                                                }
                                                                dataInfo = "GRID:" + size.width + ":" + size.height;
                                                                break;
                                                        default:
                                                                System.err.println(
                                                                                "ERROR: \"" + canvasCb.getSelectedItem()
                                                                                                + "\" is not a valid setting!");
                                                }
                                                // push info to %appData%
                                                WindowService.OpenDefaultWindow();
                                                window.dispose();
                                        }
                                });

                                layout.putConstraint(SpringLayout.NORTH,
                                                newButton,
                                                Consts.DIALOGUE_BUTTON_MARGIN_TOP,
                                                SpringLayout.SOUTH,
                                                recentFilesPanel);
                                layout.putConstraint(SpringLayout.WEST,
                                                newButton,
                                                Consts.DIALOGUE_BUTTON_MARGIN_H,
                                                SpringLayout.WEST,
                                                contentPanel);
                                // #endregion

                                // #region Button - Open
                                JButton openButton = new JButton("OPEN");
                                contentPanel.add(openButton);
                                layout.putConstraint(SpringLayout.NORTH,
                                                openButton,
                                                Consts.DIALOGUE_BUTTON_MARGIN_TOP,
                                                SpringLayout.SOUTH,
                                                recentFilesPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                openButton,
                                                -Consts.DIALOGUE_BUTTON_MARGIN_H,
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

                                                                        algorithmCb.removeAllItems();
                                                                        for (String algorithmEntry : Consts.ALGORTIHMS_GRAPH) {
                                                                                algorithmCb.addItem(algorithmEntry);
                                                                        }
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
                                                                                        createFormatter("##"));
                                                                        rowInput.setName("rowInput_REMOVABLE");
                                                                        cardPanel.add(rowInput);

                                                                        cardLayout.putConstraint(SpringLayout.NORTH,
                                                                                        rowInput,
                                                                                        Consts.DIALOGUE_PADDING_V,
                                                                                        SpringLayout.SOUTH,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.WEST,
                                                                                        rowInput,
                                                                                        0,
                                                                                        SpringLayout.WEST,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.EAST,
                                                                                        rowInput,
                                                                                        Consts.DIALOGUE_GRIDSIZE_INPUT_WIDTH,
                                                                                        SpringLayout.WEST,
                                                                                        rowInput);
                                                                        // #endregion

                                                                        // #region DropDown - Grid Size Columns
                                                                        // TODO: add check for inputs to have values
                                                                        // over 2
                                                                        JFormattedTextField colInput = new JFormattedTextField(
                                                                                        createFormatter("##"));
                                                                        colInput.setName("colInput_REMOVABLE");
                                                                        cardPanel.add(colInput);

                                                                        cardLayout.putConstraint(SpringLayout.NORTH,
                                                                                        colInput,
                                                                                        Consts.DIALOGUE_PADDING_V,
                                                                                        SpringLayout.SOUTH,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.EAST,
                                                                                        colInput,
                                                                                        0,
                                                                                        SpringLayout.EAST,
                                                                                        graphGridTitle);
                                                                        cardLayout.putConstraint(SpringLayout.WEST,
                                                                                        colInput,
                                                                                        -Consts.DIALOGUE_GRIDSIZE_INPUT_WIDTH,
                                                                                        SpringLayout.EAST,
                                                                                        colInput);
                                                                        // #endregion

                                                                        algorithmCb.removeAllItems();
                                                                        for (String algorithmEntry : Consts.ALGORIHMS_GRID) {
                                                                                algorithmCb.addItem(algorithmEntry);
                                                                        }
                                                                        break;
                                                                default:
                                                                        System.err.println("ERROR: \"" + e.getItem()
                                                                                        + "\" is not a handled canvas type");
                                                        }
                                                        cardPanel.revalidate();
                                                        cardPanel.repaint();
                                                }
                                        }
                                });

                                break;
                        case "mainWindow":
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
                                                                Consts.TOOLBOX_CORNER_RADIUS,
                                                                Consts.TOOLBOX_CORNER_RADIUS);
                                        }
                                };
                                toolboxPanel.setOpaque(false);
                                toolboxPanel.setBackground(Consts.TOOLBOX_COLOR);
                                contentPanel.add(toolboxPanel);

                                layout.putConstraint(SpringLayout.NORTH,
                                                toolboxPanel,
                                                -(Consts.TOOLBOX_HEIGHT + Consts.TOOLBOX_LABEL_HEIGHT),
                                                SpringLayout.SOUTH,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.SOUTH,
                                                toolboxPanel,
                                                0,
                                                SpringLayout.SOUTH,
                                                contentPanel);
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                int toolboxMargin = ((int) screenSize.getWidth() - Consts.TOOLBOX_WIDTH) / 2;
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
                                final CanvasService canvasServiceInstance = new CanvasService(canvas, "");
                                canvas.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                                canvasServiceInstance.HandleCanvasClick(e);
                                        }
                                });

                                layout.putConstraint(SpringLayout.WEST,
                                                canvas,
                                                Consts.CANVAS_PADDING,
                                                SpringLayout.WEST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.EAST,
                                                canvas,
                                                -Consts.CANVAS_PADDING,
                                                SpringLayout.EAST,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.NORTH,
                                                canvas,
                                                Consts.CANVAS_PADDING,
                                                SpringLayout.NORTH,
                                                contentPanel);
                                layout.putConstraint(SpringLayout.SOUTH,
                                                canvas,
                                                -Consts.CANVAS_PADDING,
                                                SpringLayout.SOUTH,
                                                contentPanel);

                                canvas.setBackground(Consts.BG_COLOR);
                                contentPanel.setBackground(Consts.MENU_COLOR);
                                contentPanel.add(canvas);
                                // #endregion

                                // #region Toolbox - Buttons

                                int buttonCount = 3;
                                int remainingSpace = Consts.TOOLBOX_WIDTH - buttonCount * Consts.TOOLBOX_HEIGHT;
                                int buttonSpacingBetween = remainingSpace / (buttonCount + 1);
                                int buttonSpacingAround = remainingSpace / (buttonCount * 2);
                                int labelSize = Consts.TOOLBOX_WIDTH / buttonCount;

                                // #region Buttons Add
                                JButton addBtn = CreateToolboxButton("Original", canvas);
                                toolboxLayout.putConstraint(SpringLayout.WEST,
                                                addBtn,
                                                buttonSpacingAround,
                                                SpringLayout.WEST,
                                                toolboxPanel);
                                toolboxLayout.putConstraint(SpringLayout.NORTH,
                                                addBtn,
                                                Consts.TOOLBOX_LABEL_HEIGHT - 1,
                                                SpringLayout.NORTH,
                                                toolboxPanel);
                                toolboxPanel.add(addBtn);

                                JLabel addLbl = new JLabel("Select nodes", SwingConstants.CENTER);
                                addLbl.setForeground(Consts.TOOLBOX_TEXT_COLOR);
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
                                                Consts.TOOLBOX_LABEL_HEIGHT - 1,
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
                                                Consts.TOOLBOX_LABEL_HEIGHT - 1,
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

                                break;
                        default:
                                System.out.println("\"" + windowName + "\" is not a specified value in ContentService");
                                return null;
                }
                return contentPanel;
        }

        private static JButton CreateToolboxButton(final String iconName, final JPanel canvas) {
                ImageIcon icon = new ImageIcon("./data/img/cursor/images/Cursor_" + iconName + ".png");
                Image scaledIcon = icon.getImage()
                                .getScaledInstance(Consts.TOOLBOX_HEIGHT, Consts.TOOLBOX_HEIGHT, Image.SCALE_SMOOTH);
                JButton button = new JButton(new ImageIcon(scaledIcon));
                button.setBackground(null);
                // Make the background transparent for the button
                button.setBorderPainted(false);
                button.setBorder(null);
                // Resize button
                button.setSize(Consts.TOOLBOX_HEIGHT, Consts.TOOLBOX_HEIGHT);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));

                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Toolkit toolkit = Toolkit.getDefaultToolkit();
                                Image cursorIcon = toolkit
                                                .getImage("./data/img/cursor/images/Cursor_" + iconName + ".png");
                                Cursor customCursor = toolkit.createCustomCursor(cursorIcon, new Point(0, 0), iconName);
                                canvas.setCursor(customCursor);
                        }
                });

                return button;
        }

        private static JPanel InitializeEmptyCanvas() {
                JPanel canvas = new JPanel();
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image cursorIcon = toolkit.getImage("./data/img/cursor/images/Cursor_Original.png");
                Cursor customCursor = toolkit.createCustomCursor(cursorIcon, new Point(0, 0), "Original");
                canvas.setCursor(customCursor);

                return canvas;
        }

        protected static MaskFormatter createFormatter(String s) {
                MaskFormatter formatter = null;
                try {
                        formatter = new MaskFormatter(s);
                } catch (Exception e) {
                        System.err.println("ERROR: Bad format string when creating grid size input!");
                }
                return formatter;
        }
}