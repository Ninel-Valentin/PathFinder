package utils;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Consts {
    // #region Entry Dialogue design
    public static Color MENU_BTN_COLOR_HOVER = new Color(75, 75, 75);
    public static Color MENU_COLOR = new Color(45, 45, 45);
    public static Color BG_COLOR = new Color(30, 30, 30);
    public static int MENU_BTN_SIZE = 25;
    public static int MENU_BTN_MARGIN = 10;
    public static float MENU_BTN_PADDING = 2.5f;
    public static int MENU_BTN_IMG_SIZE = 20;

    public static int DIALOGUE_PADDING_H = 50;
    public static int DIALOGUE_PADDING_V = 5;
    public static int DIALOGUE_PADDING_CATEGORY_V = 25;

    public static int RECENT_FILE_Y = 160;
    public static int RECENT_FILE_ROW_HEIGHT = 20;

    public static int DIALOGUE_BUTTON_MARGIN_TOP = 20;
    public static int DIALOGUE_BUTTON_MARGIN_H = 55;

    public static int DIALOGUE_GRIDSIZE_INPUT_WIDTH = 95;
    public static Color DIALOGUE_GRIDSIZE_WRONG_COLOR = new Color(255, 150, 120);
    // #endregion

    // #region Entry Dialogue data
    public static String[] ALGORTIHMS_GRAPH = { "A*" };
    public static String[] ALGORIHMS_GRID = { "TESTING1", "TESTING2" };

    public static String DIALOGUE_TITLE = "Pathfinder - Entry";
    public static int DIALOGUE_CLOSING = JFrame.EXIT_ON_CLOSE;
    public static boolean DIALOGUE_FULLSCREEN = false;
    public static Dimension DIALOGUE_WINDOW_SIZE = new Dimension(300, 500);
    // #endregion

    // #region Main - Toolbox design
    public static int TOOLBOX_HEIGHT = 50;
    public static int TOOLBOX_WIDTH = 550;
    public static int TOOLBOX_CORNER_RADIUS = 25;
    public static Color TOOLBOX_COLOR = Color.WHITE;
    public static Color TOOLBOX_TEXT_COLOR = Color.BLACK;
    public static int TOOLBOX_LABEL_HEIGHT = 15;

    public static int CANVAS_PADDING = 10;
    // #endregion

    // #region Main - Canvas design
    public static String CUSTOM_CURSOR_PATH = "./data/img/cursor/images/Cursor";
    public static int NODE_RADIUS = 50;
    // #endregion

    // #region Main data
    public static String MAIN_TITLE = "Pathfinder - Application";
    public static int MAIN_CLOSING = JFrame.EXIT_ON_CLOSE;
    public static boolean MAIN_FULLSCREEN = true;
    // #endregion

    // #region Local Storage

    // Local
    public static String LOCAL_STORAGE_DIRECTORY_PATH = System.getenv("APPDATA") + "\\PathfinderApp\\saves";

    // Active
    public static String SESSION_STORAGE_PATH = ".\\data\\settings\\sessionStorage.txt";

    // #endregion

}
