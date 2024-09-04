package src.scripts.utils;

import java.awt.Color;
import java.util.Arrays;

import src.scripts.utils.ConstComponents.CtrlButtonInfo;
import src.scripts.utils.ConstComponents.CtrlButtonSettings;
import src.scripts.utils.ConstComponents.WindowSettings;

public class Consts {

        // #region Default Colors
        public static final Color darkPurple_1 = Color.decode("#20202C");
        public static final Color darkPurple_2 = Color.decode("#262633");
        public static final Color darkPurple_3 = Color.decode("#2C2C3A");

        public static final Color lightPurple_1 = Color.decode("#505061");

        public static final Color darkGray_0 = Color.decode("#1e1e1e");
        public static final Color darkGray_1 = Color.decode("#252525");
        public static final Color darkGray_2 = Color.decode("#2d2d2d");
        public static final Color darkGray_3 = Color.decode("#3b3b3b");
        public static final Color darkGray_4 = Color.decode("#4b4b4b");
        public static final Color darkGray_5 = Color.decode("#5b5b5b");

        public static final Color lightGray_1 = Color.decode("#DEE0FD");
        public static final Color lightGray_2 = Color.decode("#7F8095");

        public static final String log_debug_blue = "#377ded";
        public static final String log_info_green = "#45c728";
        public static final String log_warning_yellow = "#d6a906";
        public static final String log_error_red = "#de142c";
        public static final String log_confirm_pink = "#ff99ff";
        // #endregion

        // #region CursorType
        public static final String cursorDefault = "Cursor_Original";
        public static final String cursorDenied = "Cursor_Denied";
        public static final String cursorAdd = "Cursor_Add";
        public static final String cursorRemove = "Cursor_Remove";
        public static final String cursorAttention = "Cursor_Attention";
        public static final String optionClear = "Option_Clear";

        public static final String cursorPath = "./src/storage/images/cursor/";
        public static final String optionPath = "./src/storage/images/toolbar/";
        // #endregion

        // #region Settings enums
        public enum GraphType {
                UNDIRECTED, DIRECTED
        };

        public enum EdgeWeight {
                NONE, WEIGHTED
        }

        // #endregion

        // #region Context Bar
        public final static int dropdownWidth = 75;
        public final static int dropdownFontSize = 15;
        // #endregion

        // #region Default Dialog values
        public static final String minimizeCrlBtnName = "CtrlBtn_Minimize";
        public static final String maximizeCrlBtnName = "CtrlBtn_Maximize";
        public static final String exitCrlBtnName = "CtrlBtn_Exit";

        public static final CtrlButtonInfo[] ctrlBtnsInfo = { new CtrlButtonInfo("_", minimizeCrlBtnName), new CtrlButtonInfo("□", maximizeCrlBtnName), new CtrlButtonInfo("⨉", exitCrlBtnName) };

        public static final CtrlButtonSettings appDialogueCtrlSettings = new CtrlButtonSettings(true, true, true);

        public static final int ctrlBtnSize = 30;

        public static final String entryName = "ENTRY_WINDOW";
        // #endregion

        // #region Entry Dialog
        public static final WindowSettings entryWindowSettings = new WindowSettings(entryName, "Choose your settings", 350, 600, new CtrlButtonSettings(true, false, true), false);

        public static final int entryContentPadding = 10;

        public static final int settingsHorizontalPadding = 50;
        public static final int settingsVerticalPadding = 10;
        public static final int settingsBtnsVerticalPadding = 5;

        public static final int settingsColumnPadding = 8;

        public static final int settingsCellHeight = 25;
        public static final int settingsLabelWidth = 75;

        public static final int settingsSelectWidth = 150;
        public static final int halfInputWidth = (entryWindowSettings.getW()) / 2 - settingsHorizontalPadding - 2 * settingsColumnPadding;

        public static final String[] canvasTypeStrings = { "Free canvas", "Grid canvas" };
        public static final String[] graphTypeStrings = { "Graph - undirected", "Digraph - directed" };

        public static final String canvasCardName = "canvasTemporarySettings";
        public static final String gridCardName = "gridTemporarySettings";

        static final String[] canvasCards = { canvasCardName, gridCardName };

        public static final String getCardFromCanvasType(String option) {
                int index = Arrays.asList(canvasTypeStrings).indexOf(option);
                return canvasCards[index];
        }

        public static final int recentProjectOptionHeight = 25;
        public static final int recentProjectOptionMargin = 2;
        public static final int recentProjectOptionPadding = 10;

        public static final int browsingPanelHeight = (11) * (recentProjectOptionHeight + recentProjectOptionMargin);
        public static final int settingsPanelHeight = (entryWindowSettings.getH() - ctrlBtnSize) - 3 * settingsVerticalPadding - browsingPanelHeight;

        public static final int changingPanelHeight = Consts.settingsPanelHeight - (5 * Consts.settingsColumnPadding + 2 * Consts.settingsCellHeight);
        // #endregion

        // #region Logging System
        public enum ConfirmationFlagType {
                NONE, CLEAR_GRAPH
        };

        // #endregion

        // #region Canvas:App
        public static final String canvasWindowName = "CANVAS_WINDOW";
        public static final WindowSettings canvasWindowSettings = new WindowSettings(canvasWindowName, "Canvas - Path finding application", 1000, 700, new CtrlButtonSettings(true, true, true), true);

        public static final int editorWindowWidth = (int) Utils.getCurrentScreenDimension().getWidth() / 6;
        public static final int editorSoloInput = editorWindowWidth - 2 * settingsHorizontalPadding;
        public static final int editorDualInputSize = (editorWindowWidth - 3 * settingsHorizontalPadding) / 2;

        public static final int canvasWindowWidth = (int) Utils.getCurrentScreenDimension().getWidth() * 5 / 6;

        public static final String editorName = "_EDITOR";
        public static final String screenName = "_SCREEN";
        public static final String toolbarName = "_TOOLBAR";

        public static final String edgeStartDDName = "DROPDOWN_START";
        public static final String edgeEndDDName = "DROPDOWN_END";
        public static final String addEdgeBtnName = "ADD_EDGE_BTN";
        public static final String startAlgorithmBtnName = "ALGORITHM_START_BTN";
        public static final String setWeightBtnName = "SET_WEIGHT_BTN";
        public static final String removeEdgeBtnName = "REMOVE_EDGE_BTN";

        public static final int nodeCountLimit = 25;

        // #endregion

        // #region Canvas graphics
        public static final int arcHeight = 50;
        public static final int tangentAngleOffset = 10;
        public static final int nodeRadius = 50;
        public static final int nodeBorder = 4;
        public static final int nodeValueFontSize = 26;
        public static final int strokeWidth = 2;
        // #endregion

        // #region Grid:App
        public static final String gridWindowName = "GRID_WINDOW";
        public static final WindowSettings gridWindowSettings = new WindowSettings(canvasWindowName, "Grid - Path finding application", 1000, 700, new CtrlButtonSettings(true, true, true), true);
        // #endregion

        // #region Local Storage
        public static final String localStorageDir = System.getProperty("user.home") + "\\AppData\\Local\\PathfinderApp";
        public static final String localSavesDir = localStorageDir + "\\saves";
        public static final String localSettingsDir = localStorageDir + "\\settings";
        public static final String recentProjectsDir = localStorageDir + "\\recent.ser";
        // #endregion
}
