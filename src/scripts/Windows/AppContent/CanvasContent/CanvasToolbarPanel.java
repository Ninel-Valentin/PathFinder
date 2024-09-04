package src.scripts.Windows.AppContent.CanvasContent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import src.scripts.Windows.Components.ToolbarContent.ToolbarButton;
import src.scripts.Windows.Components.WindowContent.templates.AppWindowToolbarPanel;
import src.scripts.utils.Consts;

public class CanvasToolbarPanel extends AppWindowToolbarPanel implements ActionListener {
    CanvasEditorPanel editor;

    public CanvasToolbarPanel(Dimension size, CanvasEditorPanel editor) {
        // super() - Already called
        setSelfSize(size);
        this.editor = editor;
        loadContent();
    }

    private void loadContent() {
        int btnSize = (int) (ownSize.getHeight() - Consts.ctrlBtnSize);

        ToolbarButton defaultCursorBtn = new ToolbarButton(btnSize, Consts.cursorDefault);
        defaultCursorBtn.addActionListener(this);
        add(defaultCursorBtn);
        ToolbarButton addCursorBtn = new ToolbarButton(btnSize, Consts.cursorAdd);
        addCursorBtn.addActionListener(this);
        add(addCursorBtn);
        ToolbarButton removeCursorBtn = new ToolbarButton(btnSize, Consts.cursorRemove);
        removeCursorBtn.addActionListener(this);
        add(removeCursorBtn);
        ToolbarButton clearCanvasBtn = new ToolbarButton(btnSize, Consts.optionClear);
        clearCanvasBtn.addActionListener(this);
        add(clearCanvasBtn);
    }

    public void actionPerformed(ActionEvent e) {
        switch (((JButton) e.getSource()).getName()) {
        case Consts.cursorDefault:
            this.appData.log.info("Cursor mode: SELECT");
            this.appData.screenData.setTypeCursor(Consts.cursorDefault);
            break;
        case Consts.cursorAdd:
            this.appData.log.info("Cursor mode: ADD NODE");
            this.appData.screenData.setTypeCursor(Consts.cursorAdd);
            break;
        case Consts.cursorRemove:
            this.appData.log.info("Cursor mode: REMOVE NODE");
            this.appData.screenData.setTypeCursor(Consts.cursorRemove);
            break;
        case Consts.optionClear:
            if (this.appData.log.getAwaitingConfirmationFlag() == Consts.ConfirmationFlagType.CLEAR_GRAPH) {
                appData.graphData.clear(appData.settings);
                this.appData.screenData.screen.repaint();
                this.appData.log.finishAwaitingConfirmation(Consts.ConfirmationFlagType.CLEAR_GRAPH);
                editor.refreshDropDowns();
            } else
                this.appData.log.setAwaitingConfirmationFlag(Consts.ConfirmationFlagType.CLEAR_GRAPH);

            break;
        default:
            System.out.println("ERROR: Undefined toolbar action performed!");
            break;
        }
    }
}