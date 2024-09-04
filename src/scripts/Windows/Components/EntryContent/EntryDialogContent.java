package src.scripts.Windows.Components.EntryContent;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import src.scripts.Windows.Components.EntryContent.SettingsFrame.SettingsPanel;
import src.scripts.utils.Consts;
import src.scripts.utils.BrowsingFiles.BrowsingFilesPanel;

public class EntryDialogContent extends JPanel {
        public EntryDialogContent() {
                super();
                SpringLayout contentLayout = new SpringLayout();
                setLayout(contentLayout);
                setBorder(null);
                setBackground(Consts.darkGray_0);

                SettingsPanel settingsPanel = new SettingsPanel(contentLayout, this);
                add(settingsPanel);
                BrowsingFilesPanel browsingPanel = new BrowsingFilesPanel(contentLayout, this, settingsPanel);
                add(browsingPanel);
        }
}
