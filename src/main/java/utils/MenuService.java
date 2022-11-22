package utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import utils.Consts.ButtonState;

import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class MenuService {
    public static void LoadMenubar(String windowName, JFrame window, ButtonState[] stateOfButtons) {
        JMenuBar menuBar;

        // Instantiate the menu bar
        menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", Consts.MenuBar.MENU_BG_COLOR);
        Dimension windowSize = new Dimension(window.getSize().width, Consts.MenuBar.BTN_SIZE);
        menuBar.setPreferredSize(windowSize);
        menuBar.setSize(windowSize);
        menuBar.setBorder(null);
        menuBar.setBorderPainted(false);

        // Set layout for the custom buttons in the right
        SpringLayout layout = new SpringLayout();
        menuBar.setLayout(layout);

        // Instantiate a final version of the buttons state for use in the action
        final ButtonState[] finalStateOfButtons = stateOfButtons;

        // Create the buttons and their listeners
        JButton exitButton = CreateMenuButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: ENABLE THIS WHEN FINISHING DEV
                // SessionStorageService.DeleteSessionStorage();
                System.exit(0);
            }
        });

        String restoreIconName = stateOfButtons[1] == ButtonState.DISABLED ? "RestoreDown-disabled" : "RestoreDown";
        JButton restoreButton = CreateMenuButton(restoreIconName);
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton src = (JButton) e.getSource();
                String name = src.getName();

                if (!name.contains("-disabled")) {
                    if (name.contains("Up")) {
                        WindowService.activeMainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    } else {
                        WindowService.activeMainWindow.setSize(800, 800);
                    }

                    String newName = name.contains("Up") ? name.replace("Up", "Down") : name.replace("Down", "Up");
                    src.setName(newName);
                    ImageIcon icon = new ImageIcon("./data/img/menuBarButtons/" + newName + ".png");
                    Image scaledIcon = icon.getImage()
                            .getScaledInstance(Consts.MenuBar.BTN_SIZE, Consts.MenuBar.BTN_SIZE, Image.SCALE_SMOOTH);
                    src.setIcon(new ImageIcon(scaledIcon));
                }
            }
        });

        String minimizedIconName = stateOfButtons[0] == ButtonState.DISABLED ? "Minimize-disabled" : "Minimize";
        JButton minimizeButton = CreateMenuButton(minimizedIconName);
        minimizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowService.activeMainWindow.setState(JFrame.ICONIFIED);
            }
        });

        // Add the buttons the the menuBar
        menuBar.add(exitButton);
        menuBar.add(restoreButton);
        menuBar.add(minimizeButton);

        // Add the buttons to the layout
        layout.putConstraint(SpringLayout.WEST, exitButton, -Consts.MenuBar.BTN_SIZE, SpringLayout.EAST, menuBar);
        layout.putConstraint(SpringLayout.EAST, restoreButton, 0, SpringLayout.WEST, exitButton);
        layout.putConstraint(SpringLayout.EAST, minimizeButton, 0, SpringLayout.WEST, restoreButton);

        window.addMouseMotionListener(new CustomMouseMotionListener());
        window.setJMenuBar(menuBar);
    }

    private static JButton CreateMenuButton(String iconName) {
        ImageIcon icon = new ImageIcon("./data/img/menuBarButtons/" + iconName + ".png");
        Image scaledIcon = icon.getImage()
                .getScaledInstance(Consts.MenuBar.BTN_SIZE, Consts.MenuBar.BTN_SIZE, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(scaledIcon));
        button.setName(iconName);
        button.setBackground(Consts.MenuBar.MENU_BG_COLOR);
        // Make the background transparent for the button
        button.setBorderPainted(false);
        button.setBorder(null);
        // Resize button
        button.setSize(Consts.MenuBar.BTN_SIZE, Consts.MenuBar.BTN_SIZE);

        // Add hover effect on the buttons
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                ((JButton) evt.getSource()).setBackground(Consts.MenuBar.BTN_COLOR_HOVER);
            }

            public void mouseExited(MouseEvent evt) {
                ((JButton) evt.getSource()).setBackground(Consts.MenuBar.MENU_BG_COLOR);
            }
        });
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public static class CustomMouseMotionListener implements MouseMotionListener {
        public static int xOffset, yOffset;

        public void mouseDragged(MouseEvent e) {
            if (xOffset == 0 || yOffset == 0) {
                xOffset = (int) (e.getX());
                yOffset = (int) (e.getY());
            }
            JFrame window = WindowService.activeMainWindow;
            int newX = (int) (window.getLocation().getX() + e.getPoint().getX() - xOffset);
            int newY = (int) (window.getLocation().getY() + e.getPoint().getY() - yOffset);

            WindowService.activeMainWindow.setLocation(newX, newY);
        }

        public void mouseMoved(MouseEvent e) {
            if (xOffset != 0 || yOffset != 0) {
                xOffset = yOffset = 0;
            }
        }
    }

}