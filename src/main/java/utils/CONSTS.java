package utils;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Consts {
    // #region Entry Dialogue design
    public static final Color MENU_BTN_COLOR_HOVER = new Color(75, 75, 75);
    public static final Color MENU_COLOR = new Color(45, 45, 45);
    public static final Color BG_COLOR = new Color(30, 30, 30);
    public static final int MENU_BTN_SIZE = 25;
    public static final int MENU_BTN_MARGIN = 10;
    public static final float MENU_BTN_PADDING = 2.5f;
    public static final int MENU_BTN_IMG_SIZE = 20;

    public static final int DIALOGUE_PADDING_H = 50;
    public static final int DIALOGUE_PADDING_V = 5;
    public static final int DIALOGUE_PADDING_CATEGORY_V = 25;

    public static final int RECENT_FILE_Y = 160;
    public static final int RECENT_FILE_ROW_HEIGHT = 20;
    public static final int NUMBER_OF_READ_FILES = 8;

    public static final int DIALOGUE_BUTTON_MARGIN_TOP = 20;
    public static final int DIALOGUE_BUTTON_MARGIN_H = 55;

    public static final int DIALOGUE_GRIDSIZE_INPUT_WIDTH = 95;
    public static final Color DIALOGUE_GRIDSIZE_WRONG_COLOR = new Color(255, 150, 120);
    // #endregion

    // #region Entry Dialogue data
    public static final String[] ALGORTIHMS_GRAPH = { "A*" };
    public static final String[] ALGORIHMS_GRID = { "TESTING1", "TESTING2" };

    public static final String DIALOGUE_TITLE = "Pathfinder - Entry";
    public static final int DIALOGUE_CLOSING = JFrame.EXIT_ON_CLOSE;
    public static final boolean DIALOGUE_FULLSCREEN = false;
    public static final Dimension DIALOGUE_WINDOW_SIZE = new Dimension(300, 500);
    // #endregion

    // #region Main - Toolbox design
    public static final int TOOLBOX_HEIGHT = 50;
    public static final int TOOLBOX_WIDTH = 550;
    public static final int TOOLBOX_CORNER_RADIUS = 25;
    public static final Color TOOLBOX_COLOR = Color.WHITE;
    public static final Color TOOLBOX_TEXT_COLOR = Color.BLACK;
    public static final int TOOLBOX_LABEL_HEIGHT = 15;

    public static final int CANVAS_PADDING = 10;
    // #endregion

    // #region Main - Canvas design
    public static final String CUSTOM_CURSOR_PATH = "./data/img/cursor/images/Cursor";

    public static final int NODE_RADIUS = 50;
    public static final int NODE_MARGIN_RADIUS = 5;
    public static final int NODE_ERASE_RADIUS = 4;

    public static final Color NODE_DEFAULT_COLOR = Color.WHITE;
    public static final Color NODE_ACTIVE_BORDER_COLOR = new Color(75, 135, 0);

    // Has to be float to account as Size | if int it accounts as Style
    public static final float NODE_TEXT_FONT_SIZE = 2F;
    public static final Color NODE_NEW_TEXT_COLOR = Color.BLACK;
    public static final Color NODE_ACTIVE_TEXT_COLOR = Color.BLACK;
    public static final Color NODE_START_TEXT_COLOR = Color.BLACK;
    public static final Color NODE_END_TEXT_COLOR = Color.BLACK;
    // #endregion

    // #region Main data
    public static final String MAIN_TITLE = "Pathfinder - Application";
    public static final int MAIN_CLOSING = JFrame.EXIT_ON_CLOSE;
    public static final boolean MAIN_FULLSCREEN = true;
    // #endregion

    // #region Logger
    public static final int LOGGER_HEIGHT = 25;
    // #endregion

    // #region Local Storage

    // Local
    public static final String LOCAL_STORAGE_DIRECTORY_PATH = System.getenv("APPDATA") + "\\PathfinderApp\\saves";

    // Active
    public static final String SESSION_STORAGE_PATH = ".\\data\\settings\\sessionStorage.txt";

    // #endregion

}
