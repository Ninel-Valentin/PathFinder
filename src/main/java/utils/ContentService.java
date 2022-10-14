package utils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class ContentService {
    public static void LoadContent(String windowName, JFrame window) {

        SpringLayout layout = new SpringLayout();
        JPanel contentPanel = new JPanel(layout);
        contentPanel.setBackground(CONSTS.backgroundColor);

        // #region App Title
        JLabel title = new JLabel("Application start-up settings", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        contentPanel.add(title);

        layout.putConstraint(SpringLayout.EAST, title, 0, SpringLayout.EAST, contentPanel);
        layout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, contentPanel);
        // #endregion

        // #region Title - Canvas Type DropDown
        JLabel canvasTitle = new JLabel("Canvas type");
        canvasTitle.setForeground(Color.WHITE);
        contentPanel.add(canvasTitle);

        layout.putConstraint(SpringLayout.NORTH, canvasTitle, 25, SpringLayout.SOUTH, title);
        layout.putConstraint(SpringLayout.EAST, canvasTitle, -50, SpringLayout.EAST, contentPanel);
        layout.putConstraint(SpringLayout.WEST, canvasTitle, 50, SpringLayout.WEST, contentPanel);
        // #endregion

        // #region DropDown - Canvas Type
        String canvasCbItems[] = { "Graph Canvas", "Grid Canvas" };
        JComboBox<String> canvasCb = new JComboBox<>(canvasCbItems);
        canvasCb.setEditable(false);
        contentPanel.add(canvasCb);

        layout.putConstraint(SpringLayout.NORTH, canvasCb, 5, SpringLayout.SOUTH, canvasTitle);
        layout.putConstraint(SpringLayout.EAST, canvasCb, 0, SpringLayout.EAST, canvasTitle);
        layout.putConstraint(SpringLayout.WEST, canvasCb, 0, SpringLayout.WEST, canvasTitle);
        // #endregion

        // #region Panel - Card panel
        SpringLayout cardLayout = new SpringLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(CONSTS.backgroundColor);

        layout.putConstraint(SpringLayout.WEST, cardPanel, 0, SpringLayout.WEST, contentPanel);
        layout.putConstraint(SpringLayout.EAST, cardPanel, 0, SpringLayout.EAST, contentPanel);
        layout.putConstraint(SpringLayout.NORTH, cardPanel, 15, SpringLayout.SOUTH, canvasCb);
        layout.putConstraint(SpringLayout.SOUTH, cardPanel, 110, SpringLayout.NORTH, cardPanel);
        // #endregion

        // #region Title - Graph type
        JLabel graphTitle = new JLabel("Graph type", JLabel.LEFT);
        graphTitle.setForeground(Color.WHITE);
        cardPanel.add(graphTitle);

        cardLayout.putConstraint(SpringLayout.NORTH, graphTitle, 0, SpringLayout.NORTH, cardPanel);
        cardLayout.putConstraint(SpringLayout.EAST, graphTitle, -50, SpringLayout.EAST, cardPanel);
        cardLayout.putConstraint(SpringLayout.WEST, graphTitle, 50, SpringLayout.WEST, cardPanel);
        // #endregion

        // #region DropDown - Graph type
        String graphCbItems[] = { "Unoriented Graph", "Oriented Graph - Digraph" }; // TODO: modify based on canvas type
        JComboBox<String> graphCb = new JComboBox<>(graphCbItems);
        graphCb.setEditable(false);
        cardPanel.add(graphCb);

        cardLayout.putConstraint(SpringLayout.NORTH, graphCb, 5, SpringLayout.SOUTH, graphTitle);
        cardLayout.putConstraint(SpringLayout.EAST, graphCb, 0, SpringLayout.EAST, graphTitle);
        cardLayout.putConstraint(SpringLayout.WEST, graphCb, 0, SpringLayout.WEST, graphTitle);
        // #endregion

        // #region Title - Algorithm
        JLabel algorithmTitle = new JLabel("Algorithm type", JLabel.LEFT);
        algorithmTitle.setForeground(Color.WHITE);
        cardPanel.add(algorithmTitle);

        cardLayout.putConstraint(SpringLayout.NORTH, algorithmTitle, 10, SpringLayout.SOUTH, graphCb);
        cardLayout.putConstraint(SpringLayout.EAST, algorithmTitle, -50, SpringLayout.EAST, cardPanel);
        cardLayout.putConstraint(SpringLayout.WEST, algorithmTitle, 50, SpringLayout.WEST, cardPanel);
        // #endregion

        // #region DropDown - Algorithm Type
        String algorithmCbItems[] = { "A*" }; // TODO: modify based on canvas type
        JComboBox<String> algorithmCb = new JComboBox<>(algorithmCbItems);
        algorithmCb.setEditable(false);
        cardPanel.add(algorithmCb);

        cardLayout.putConstraint(SpringLayout.NORTH, algorithmCb, 5, SpringLayout.SOUTH, algorithmTitle);
        cardLayout.putConstraint(SpringLayout.EAST, algorithmCb, 0, SpringLayout.EAST, algorithmTitle);
        cardLayout.putConstraint(SpringLayout.WEST, algorithmCb, 0, SpringLayout.WEST, algorithmTitle);
        // #endregion

        contentPanel.add(cardPanel);

        // #region Title - Recent files
        JLabel recentTitle = new JLabel("Recent files", JLabel.LEFT);
        recentTitle.setForeground(Color.WHITE);
        contentPanel.add(recentTitle);

        layout.putConstraint(SpringLayout.NORTH, recentTitle, 10, SpringLayout.SOUTH, cardPanel);
        layout.putConstraint(SpringLayout.EAST, recentTitle, -50, SpringLayout.EAST, contentPanel);
        layout.putConstraint(SpringLayout.WEST, recentTitle, 50, SpringLayout.WEST, contentPanel);
        // #endregion

        // #region Panel - Recent files
        SpringLayout recentLayout = new SpringLayout();
        JPanel recentFilesPanel = new JPanel(recentLayout);
        recentFilesPanel.setBackground(CONSTS.backgroundColor);
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
                    System.out.println("Opening " + ((JButton) e.getSource()).getText());
                }
            });
            recentFileButton.setHorizontalAlignment(SwingConstants.LEFT);
            recentFileButton.setForeground(Color.WHITE);
            recentFileButton.setBackground(CONSTS.menuColor);
            // Make the background transparent for the button
            recentFileButton.setBorderPainted(false);
            recentFileButton.setBorder(null);

            // Add hover effect on the buttons
            recentFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    ((JButton) evt.getSource()).setBackground(CONSTS.menuButtonHoverColor);
                }

                public void mouseExited(MouseEvent evt) {
                    ((JButton) evt.getSource()).setBackground(CONSTS.menuColor);
                }
            });

            recentLayout.putConstraint(SpringLayout.NORTH, recentFileButton, rowPadding, SpringLayout.NORTH,
                    recentFilesPanel);
            recentLayout.putConstraint(SpringLayout.SOUTH, recentFileButton, CONSTS.recentFileRowHeight,
                    SpringLayout.NORTH, recentFileButton);
            recentLayout.putConstraint(SpringLayout.WEST, recentFileButton, 0, SpringLayout.WEST, recentFilesPanel);
            recentLayout.putConstraint(SpringLayout.EAST, recentFileButton, 0, SpringLayout.EAST, recentFilesPanel);
            recentFilesPanel.add(recentFileButton);

            rowPadding += CONSTS.recentFileRowHeight;
        }

        layout.putConstraint(SpringLayout.WEST, recentFilesPanel, 0, SpringLayout.WEST, contentPanel);
        layout.putConstraint(SpringLayout.EAST, recentFilesPanel, 0, SpringLayout.EAST, contentPanel);
        layout.putConstraint(SpringLayout.NORTH, recentFilesPanel, 5, SpringLayout.SOUTH, recentTitle);
        layout.putConstraint(SpringLayout.SOUTH, recentFilesPanel, 160, SpringLayout.NORTH, recentFilesPanel);
        // #endregion

        // #region Button - New
        JButton newButton = new JButton("NEW");
        contentPanel.add(newButton);
        layout.putConstraint(SpringLayout.NORTH, newButton, 15, SpringLayout.SOUTH, recentFilesPanel);
        layout.putConstraint(SpringLayout.WEST, newButton, 55, SpringLayout.WEST, contentPanel);
        // #endregion

        // #region Button - Open
        JButton openButton = new JButton("OPEN");
        contentPanel.add(openButton);
        layout.putConstraint(SpringLayout.NORTH, openButton, 15, SpringLayout.SOUTH, recentFilesPanel);
        layout.putConstraint(SpringLayout.EAST, openButton, -55, SpringLayout.EAST, contentPanel);
        // #endregion

        window.add(contentPanel);

        switch (windowName) {
            case "dialogueSettings":
                break;
            default:
                System.out.println(windowName + " is not a specified value in ContentService");
        }
    }
}