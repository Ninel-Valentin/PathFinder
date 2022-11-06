package utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MenuService {
    public static void LoadMenubar( String windowName, JFrame window) {
        JMenuBar menuBar;

        // Instantiate the menu bar
        menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", Consts.MENU_COLOR);
        Dimension windowSize = new Dimension(window.getSize().width, Consts.MENU_BTN_SIZE);
        menuBar.setPreferredSize(windowSize);
        menuBar.setSize(windowSize);
        menuBar.setBorder(null);
        menuBar.setBorderPainted(false);

        // Set layout for the custom buttons in the right
        SpringLayout layout = new SpringLayout();
        menuBar.setLayout(layout);

        /*
         * TODO: ADD ICONS FOR DISABLED AND READ FROM CUSTOM SETTINGS ENABLED DISABLED
         * STATUS
         */

        // Add the common buttons on the MenuBar => exit|restore|minimize
        JButton exitButton = CreateMenuButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(exitButton);

        JButton restoreButton = CreateMenuButton("RestoreUp");
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restored");
            }
        });
        menuBar.add(restoreButton);

        JButton minimizeButton = CreateMenuButton("Minimize");
        minimizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("minimized");
            }
        });
        menuBar.add(minimizeButton);

        // After adding the buttons, we need to set constraints for the layout
        layout.putConstraint(SpringLayout.WEST, exitButton, -Consts.MENU_BTN_SIZE, SpringLayout.EAST, menuBar);
        layout.putConstraint(SpringLayout.EAST, restoreButton, 0, SpringLayout.WEST, exitButton);
        layout.putConstraint(SpringLayout.EAST, minimizeButton, 0, SpringLayout.WEST, restoreButton);

        // menuBar.add(menu);
        window.setJMenuBar(menuBar);
    }

    private static JButton CreateMenuButton(String iconName) {
        ImageIcon icon = new ImageIcon("./data/img/menuBarButtons/" + iconName + ".png");
        Image scaledIcon = icon.getImage()
                .getScaledInstance(Consts.MENU_BTN_SIZE, Consts.MENU_BTN_SIZE, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(scaledIcon));
        button.setBackground(Consts.MENU_COLOR);
        // Make the background transparent for the button
        button.setBorderPainted(false);
        button.setBorder(null);
        // Resize button
        button.setSize(Consts.MENU_BTN_SIZE, Consts.MENU_BTN_SIZE);

        // Add hover effect on the buttons
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                ((JButton) evt.getSource()).setBackground(Consts.MENU_BTN_COLOR_HOVER);
            }

            public void mouseExited(MouseEvent evt) {
                ((JButton) evt.getSource()).setBackground(Consts.MENU_COLOR);
            }
        });
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
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