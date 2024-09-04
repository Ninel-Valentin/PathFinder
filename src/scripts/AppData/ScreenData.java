package src.scripts.AppData;

import javax.swing.JPanel;

import src.scripts.Windows.AppContent.CanvasContent.CanvasEditorPanel;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;

public class ScreenData {
    public String cursorState;
    public JPanel screen;
    public CanvasEditorPanel editor;

    public ScreenData() {
        this.cursorState = Consts.cursorDefault;
    }

    public void setTypeCursor(String cursorType) {
        cursorState = cursorType;
        switch (cursorType) {
        case Consts.cursorDefault:
            Utils.setCustomCursor(screen, Consts.cursorDefault);
            break;
        case Consts.cursorAdd:
            Utils.setCustomCursor(screen, Consts.cursorAdd);
            break;
        case Consts.cursorRemove:
            Utils.setCustomCursor(screen, Consts.cursorRemove);
            break;
        default:
            System.out.println("ERROR: Undefined toolbar action performed!");
            break;
        }
    }
}
