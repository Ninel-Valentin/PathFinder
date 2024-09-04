package src.scripts.utils.ConstComponents;

import src.scripts.utils.Consts;

public class CtrlButtonSettings {
    private boolean canMaximize, canMinimize, canExit;

    public CtrlButtonSettings(boolean canMinimize, boolean canMaximize, boolean canExit) {
        this.canMinimize = canMinimize;
        this.canMaximize = canMaximize;
        this.canExit = canExit;
    }

    public boolean getProperty(String propertyName) {
        switch (propertyName) {
            case Consts.minimizeCrlBtnName:
                return this.canMinimize;
            case Consts.maximizeCrlBtnName:
                return this.canMaximize;
            case Consts.exitCrlBtnName:
                return this.canExit;
        }
        return false;
    }
}
