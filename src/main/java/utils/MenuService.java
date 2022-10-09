package utils;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MenuService {
    public static void LoadMenubar(String windowName, JFrame window) {
        JMenuBar menuBar;
        JMenu menu, subMenu;
        JMenuItem menuItem;

        // Instantiate the menu bar
        menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", new Color(45, 45, 45));
        Dimension windowSize = new Dimension(window.getSize().width, 25);
        menuBar.setPreferredSize(windowSize);
        menuBar.setSize(windowSize);
        menuBar.setBorder(null);

        // Set layout for the custom buttons in the right
        SpringLayout layout = new SpringLayout();
        menuBar.setLayout(layout);

        // 75,75,75 bg

        // Build the first menu
        // menu = new JMenu("A menu");

        // Add the common buttons on the MenuBar => exit|restore|minimize
        Dimension buttonSize = new Dimension(25, 25);

        JButton exitButton = CreateMenuButton(buttonSize, "Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(exitButton);

        JButton restoreButton = CreateMenuButton(buttonSize, "RestoreUp");
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restored");
            }
        });
        menuBar.add(restoreButton);

        JButton minimizeButton = CreateMenuButton(buttonSize, "Minimize");
        minimizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("minimized");
            }
        });
        menuBar.add(minimizeButton);

        // After adding the buttons, we need to set constraints for the layout
        int buttonPadding = 10;
        layout.putConstraint(SpringLayout.WEST, exitButton, -buttonSize.width, SpringLayout.EAST, menuBar);
        layout.putConstraint(SpringLayout.EAST, restoreButton, -buttonPadding, SpringLayout.WEST, exitButton);
        layout.putConstraint(SpringLayout.EAST, minimizeButton, -buttonPadding, SpringLayout.WEST, restoreButton);

        // menuBar.add(menu);
        window.setJMenuBar(menuBar);
    }

    private static JButton CreateMenuButton(Dimension buttonSize, String iconName) {
        ImageIcon minimizeIcon = new ImageIcon("./data/img/menuBarButtons/" + iconName + ".png");
        Image scaledMinimizeIcon = minimizeIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        JButton minimizeButton = new JButton(new ImageIcon(scaledMinimizeIcon));
        // Make the background transparent for the button
        minimizeButton.setOpaque(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setBorder(null);
        // Resize button
        minimizeButton.setSize(buttonSize);

        return minimizeButton;
    }
}

class MenuSettings {
    ButtonState minimize;
    ButtonState restore;
    ButtonState exit;

    public MenuSettings(JsonObject jsonData) {
        // Get values
        JsonElement _minimize = jsonData.get("minimize");
        JsonElement _restore = jsonData.get("restore");
        JsonElement _exit = jsonData.get("exit");

        // Security check if they exist, otherwise set default values
        this.minimize = _minimize == null ? ButtonState.ENABLED : ButtonState.valueOf(_minimize.getAsString());
        this.restore = _restore == null ? ButtonState.ENABLED : ButtonState.valueOf(_restore.getAsString());
        this.exit = _exit == null ? ButtonState.ENABLED : ButtonState.valueOf(_exit.getAsString());
    }

    public MenuSettings() {
        this.minimize = ButtonState.ENABLED;
        this.restore = ButtonState.ENABLED;
        this.exit = ButtonState.ENABLED;
    }

}

enum ButtonState {
    ENABLED,
    DISABLED,
    REMOVED
}