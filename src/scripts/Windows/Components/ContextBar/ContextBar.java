package src.scripts.Windows.Components.ContextBar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import src.scripts.AppData.AppData;
import src.scripts.AppData.GraphData.GraphData;
import src.scripts.StorageManager.FileManager.FileManager;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.ConstComponents.CtrlButtonInfo;
import src.scripts.utils.ConstComponents.WindowSettings;

public class ContextBar extends JMenuBar implements ActionListener {
    private String title;
    private int window_W;

    private WindowSettings windowSettings;
    private Point mouseDownCompCoords = null;

    private JPanel contentPanel;

    public AppData appData;

    public ContextBar(WindowSettings windowSettings, AppData appData) {
        super();
        this.appData = appData;
        // Set the border of the JMenuBar. Otherwise it shows a white line
        UIManager.put("MenuBar.border", BorderFactory.createLineBorder(Consts.darkGray_2));

        this.windowSettings = windowSettings;
        // this.window_W = windowSettings.getW();
        this.window_W = Utils.getCurrentScreenDimension().width;
        this.title = windowSettings.getTitle();
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(this.window_W, Consts.ctrlBtnSize));
        contentPanel.setBackground(Consts.darkGray_0);
        // setBackground(Consts.darkGray_5);
        add(contentPanel);

        SpringLayout contentLayout = new SpringLayout();
        contentPanel.setLayout(contentLayout);

        JPanel contextMenu = GetContextPanel();
        contentPanel.add(contextMenu);
        contentLayout.putConstraint(SpringLayout.WEST, contextMenu, 0, SpringLayout.WEST, contentPanel);

        JPanel ctrlPanel = GetControlPanel(windowSettings);
        contentPanel.add(ctrlPanel);
        contentLayout.putConstraint(SpringLayout.EAST, ctrlPanel, 0, SpringLayout.EAST, contentPanel);

        JLabel windowTitle = new JLabel(this.title);
        windowTitle.setBorder(new EmptyBorder(5, 0, 0, 0));
        windowTitle.setForeground(Consts.lightGray_1);
        windowTitle.setVerticalAlignment(JLabel.CENTER);
        windowTitle.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(windowTitle);

        contentLayout.putConstraint(SpringLayout.WEST, windowTitle, 0, SpringLayout.EAST, contextMenu);
        contentLayout.putConstraint(SpringLayout.EAST, windowTitle, 0, SpringLayout.WEST, ctrlPanel);

        // Add mouse listener for dragging
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();

                // Provide offset when moving in fullScreen mode
                if (windowSettings.getFullScreenState()) {
                    int x = (int) mouseDownCompCoords.getX() - window_W / 2;
                    int y = (int) mouseDownCompCoords.getY();
                    mouseDownCompCoords = new Point(x, y);
                }
            }

            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((ContextBar) e.getSource());
                frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);

                // Resize from fullscreen when moving
                if (windowSettings.getFullScreenState())
                    Utils.toggleFullScreen(frame, windowSettings, false);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String targetSource = ((ContextBarDropDown) e.getSource()).getName();
        String targetAction = ((ContextBarDropDown) e.getSource()).getSelectedItem().toString();

        switch (targetSource) {
        case "File":
            switch (targetAction) {
            case "New":
                appData.savePath = null;
                appData.graphData = new GraphData(appData.settings);
                appData.screenData.screen.repaint();
                break;
            case "Save":
                try {
                    FileManager.SaveAppData(appData);
                    appData.log.info("File data saved successfully!");
                } catch (IOException err) {
                    appData.log.error("Saving file exception! Could not save file.");
                    System.out.println("ERROR: Saving file exception! Could not save file.");
                    err.printStackTrace();
                } catch (NullPointerException err) {
                    appData.log.info("Save file window closed. Action canceled.");
                    System.out.println("INFO: Save file window closed. Action canceled.");
                }
                break;
            case "Save as":
                try {
                    FileManager.SaveAppDataAs(appData);
                    appData.log.info("File data saved successfully!");
                } catch (IOException err) {
                    appData.log.error("Saving file exception! Could not save file.");
                    System.out.println("ERROR: Saving file exception! Could not save file.");
                    err.printStackTrace();
                } catch (NullPointerException err) {
                    appData.log.info("Save file window closed. Action canceled.");
                    System.out.println("INFO: Save file window closed. Action canceled.");
                }
                break;
            case "Open":
                try {
                    String path = FileManager.getFilePath();
                    AppData loadedData = FileManager.LoadAppData(path);
                    appData.graphData = loadedData.graphData;
                    appData.settings = loadedData.settings;
                    appData.savePath = path;
                    appData.screenData.screen.repaint();
                    appData.screenData.editor.updateUIForSelectedNode();
                } catch (IOException err) {
                    appData.log.error("Loading file exception! Could not read file.");
                    System.out.println("ERROR: Loading file exception! Could not read file.");
                    err.printStackTrace();
                } catch (ClassNotFoundException err) {
                    appData.log.error("Loading file exception! Could not parse file.");
                    System.out.println("ERROR: Loading file exception! Could not parse file.");
                    err.printStackTrace();
                } catch (NullPointerException err) {
                    appData.log.info("Open file window closed. Action canceled.");
                    System.out.println("INFO: Open file window closed. Action canceled.");
                }
                break;
            case "Exit":
                System.exit(0);
                break;
            }
            break;
        case "Run":
            switch (targetAction) {
            case "[F5] Start":
                appData.screenData.editor.startAlgorithm();
                break;
            }
            break;
        case "Templates":
            switch (targetAction) {
            case "Floyd-Warshall":
                break;
            }
            break;
        }

    }

    private JPanel GetContextPanel() {
        JPanel contextPanel = new JPanel();
        contextPanel.setBackground(Consts.darkGray_0);
        contextPanel.setBorder(new EmptyBorder(-5, -5, 0, 0));

        LoadContextButtons(contextPanel, windowSettings.getName());

        return contextPanel;
    }

    private void LoadContextButtons(JPanel contextPanel, String windowName) {
        switch (windowName) {
        case "CANVAS_WINDOW":
            ContextBarDropDown file = new ContextBarDropDown("File");
            file.addActionListener(this);
            contextPanel.add(file);

            ContextBarDropDown run = new ContextBarDropDown("Run");
            run.addActionListener(this);
            contextPanel.add(run);

            ContextBarDropDown templates = new ContextBarDropDown("Templates");
            templates.addActionListener(this);
            contextPanel.add(templates);
            break;
        }
    }

    private JPanel GetControlPanel(WindowSettings windowSettings) {
        JPanel ctrlPanel = new JPanel();
        ctrlPanel.setBackground(Consts.darkGray_0);
        ctrlPanel.setPreferredSize(new Dimension(Consts.ctrlBtnSize * 3, Consts.ctrlBtnSize));

        GridLayout ctrlGridLayout = new GridLayout(1, 3);
        ctrlPanel.setLayout(ctrlGridLayout);

        for (CtrlButtonInfo ctrlBtnInfo : Consts.ctrlBtnsInfo)
            ctrlPanel.add(new ContextBarButton(ctrlBtnInfo, this.windowSettings.getControlSettings().getProperty(ctrlBtnInfo.getName()), windowSettings));

        return ctrlPanel;
    }
}
