package src.scripts.Windows.Dialogs.Entry;

import src.scripts.Windows.Components.EntryContent.EntryDialogContent;
import src.scripts.utils.Consts;
import src.scripts.utils.ConstComponents.CtrlButtonSettings;

public class EntryDialog extends DefaultDialog {
    public CtrlButtonSettings ctrlSettings;

    public EntryDialog() {
        // Call default constructor of JFrame with the default EntryDialog name
        super(Consts.entryWindowSettings);

        loadContent();
        pack();
    }

    private void loadContent() {
        add(new EntryDialogContent());
    }
}
