package utils;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public final class Consts {

    public static class MenuBar {
        public static final Color BTN_COLOR_HOVER = new Color(75, 75, 75);
        public static final Color WINDOW_BG_COLOR = new Color(45, 45, 45);
        public static final Color MENU_BG_COLOR = new Color(30, 30, 30);
        public static final int BTN_SIZE = 25;
        public static final int BTN_MARGIN = 10;
        public static final int BTN_IMG_SIZE = 20;
        public static final float BTN_PADDING = 2.5f;
    }

    public static class Dialogue {
        public static class Window {
            public static final int PADDING_H = 50;
            public static final int PADDING_V = 5;
            public static final int PADDING_CATEGORY_V = 25;

            public static final int RECENT_FILE_Y = 160;
            public static final int RECENT_FILE_ROW_HEIGHT = 20;
            public static final int NUMBER_OF_READ_FILES = 8;

            public static final int BUTTON_MARGIN_TOP = 20;
            public static final int BUTTON_MARGIN_H = 55;

            public static final int GRIDSIZE_INPUT_WIDTH = 95;
            public static final Color GRIDSIZE_WRONG_COLOR = new Color(255, 150, 120);
        }

        public static class Data {
            public static final String[] ALGORTIHMS_GRAPH = { "A*" };
            public static final String[] ALGORIHMS_GRID = { "TESTING1", "TESTING2" };

            public static final String TITLE = "Pathfinder - Entry";
            public static final int CLOSING = JFrame.EXIT_ON_CLOSE;
            public static final boolean FULLSCREEN = false;
            public static final Dimension WINDOW_SIZE = new Dimension(300, 500);
            public static ButtonState[] BUTTONS_STATE = new ButtonState[] {
                    ButtonState.ENABLED,
                    ButtonState.DISABLED,
                    ButtonState.ENABLED
            };
        }
    }

    public static class Main {

        public static class Window {
            public static class ToolBox {
                public static final int HEIGHT = 50;
                public static final int WIDTH = 550;
                public static final int CORNER_RADIUS = 25;
                public static final Color COLOR = Color.WHITE;
                public static final Color TEXT_COLOR = Color.BLACK;
                public static final int LABEL_HEIGHT = 15;
            }

            public static class Canvas {
                public static final int PADDING = 10;
                public static final String CUSTOM_CURSOR_PATH = "./data/img/cursor/images/Cursor";

                public static class Node {
                    public static final int RADIUS = 50;
                    public static final int MARGIN_RADIUS = 5;
                    public static final int ERASE_RADIUS = 4;

                    public static final Color DEFAULT_COLOR = Color.WHITE;
                    public static final Color ACTIVE_BORDER_COLOR = new Color(75, 135, 0);

                    // Has to be float to account as Size | if int it accounts as Style
                    public static final float TEXT_FONT_SIZE = 2F;
                    public static final Color NEW_TEXT_COLOR = Color.BLACK;
                    public static final Color ACTIVE_TEXT_COLOR = Color.BLACK;
                    public static final Color START_TEXT_COLOR = Color.BLACK;
                    public static final Color END_TEXT_COLOR = Color.BLACK;

                }
            }
        }

        public static class Data {
            public static final String TITLE = "Pathfinder - Application";
            public static final int CLOSING = JFrame.EXIT_ON_CLOSE;
            public static final boolean FULLSCREEN = true;
            public static ButtonState[] BUTTONS_STATE = new ButtonState[] {
                    ButtonState.ENABLED,
                    ButtonState.ENABLED,
                    ButtonState.ENABLED
            };
        }

    }

    public static class Logger {
        public static final int HEIGHT = 25;
    }

    public static class Storage {
        public static final String LOCAL_STORAGE_PATH = System.getenv("APPDATA") + "\\PathfinderApp\\saves";
        public static final String SESSION_STORAGE_PATH = ".\\data\\settings\\sessionStorage.txt";
    }

    public enum ButtonState {
        ENABLED,
        DISABLED
    }
}