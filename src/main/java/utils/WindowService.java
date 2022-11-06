package utils;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class WindowService {
    public static void OpenEntryDialogue() {
        CreateWindow("dialogueSettings");
    }

    public static void OpenDefaultWindow() {
        CreateWindow("mainWindow");
    }

    public static void CreateWindow(String windowName) {
        String windowTitle = "Title not found...";
        int closingOperation = JFrame.EXIT_ON_CLOSE;
        boolean isFullscreen = false;
        Dimension windowSize = new Dimension(0, 0);

        switch (windowName) {
            case "dialogueSettings":
                windowTitle = Consts.DIALOGUE_TITLE;
                closingOperation = Consts.DIALOGUE_CLOSING;
                isFullscreen = Consts.DIALOGUE_FULLSCREEN;
                windowSize = Consts.DIALOGUE_WINDOW_SIZE;
                break;
            case "mainWindow":
                windowTitle = Consts.MAIN_TITLE;
                closingOperation = Consts.MAIN_CLOSING;
                isFullscreen = Consts.MAIN_FULLSCREEN;
                break;
            default:
                System.err.println("ERROR: \"" + windowName + "\" is not a handled windowName in CreateWindow method.");
                return;
        }
        JFrame window = new JFrame(windowTitle);

        // What happens when the window closes?
        window.setDefaultCloseOperation(closingOperation);
        window.setResizable(false);
        window.setUndecorated(true);

        if (isFullscreen) {
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            // Get screen size to calculate the window position on it.
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Point location = new Point(
                    ((int) screenSize.getWidth() - windowSize.width) / 2,
                    ((int) screenSize.getHeight() - windowSize.height) / 2);

            window.setSize(windowSize);
            window.setLocation(location);
        }

        // Add the menubar to the window
        MenuService.LoadMenubar(windowName, window);

        // Add the content to the window
        JPanel windowContent = ContentService.LoadContent(windowName, window);
        if (windowContent == null) {
            System.out.println("Window content creation returned null!");
            System.exit(0);
        }
        window.add(windowContent);
        window.setVisible(true);
    }
}