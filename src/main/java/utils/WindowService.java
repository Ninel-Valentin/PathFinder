package utils;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class WindowService {
    public static void OpenEntryDialogue() {
        CreateWindow("dialogueSettings");
    }

    public static void CreateWindow(String windowName) {
        Settings settings = SettingsService.GetSettings(windowName);
        JFrame window = new JFrame(settings.windowTitle);

        String closingOperation = settings.closingOperation;
        int closingOperationValue = JFrame.EXIT_ON_CLOSE;
        switch (closingOperation) {
            case "DISPOSE_ON_CLOSE":
                closingOperationValue = JFrame.DISPOSE_ON_CLOSE;
                break;
            case "EXIT_ON_CLOSE":
                break;
            default:
                System.out.println(closingOperation + " is not a specified value");
        }
        // What happens when the window closes?
        window.setDefaultCloseOperation(closingOperationValue);
        window.setResizable(false);
        window.setUndecorated(true);

        if (settings.isFullscreen) {
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            // Get screen size to calculate the window position on it.
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Point location = new Point(
                    ((int) screenSize.getWidth() - settings.width) / 2,
                    ((int) screenSize.getHeight() - settings.height) / 2);

            window.setSize(settings.GetSize());
            window.setLocation(location);
        }

        // Add the menubar to the window
        MenuService.LoadMenubar(windowName,window);

        // Add the content to the window
        ContentService.LoadContent(windowName,window);

        window.setVisible(true);
    }
}