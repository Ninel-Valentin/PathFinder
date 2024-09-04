package src.scripts.utils.BrowsingFiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InvalidClassException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import src.scripts.AppData.AppData;
import src.scripts.AppData.ScreenData;
import src.scripts.StorageManager.LocalStorage;
import src.scripts.StorageManager.FileManager.FileManager;
import src.scripts.Windows.Components.EntryContent.OpenDialoguePanel;
import src.scripts.Windows.Dialogs.App.CanvasWindow;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.Log.LoggingSystem;

public class BrowsingFilesPanel extends OpenDialoguePanel implements ActionListener {
    JComponent lastAnchor;

    public BrowsingFilesPanel(SpringLayout parentLayout, JComponent parent, JComponent anchor) {
        super(parentLayout, anchor, parent, SpringLayout.SOUTH, Consts.browsingPanelHeight);
        lastAnchor = this;

        LoadRecentOptions();
    }

    private void LoadRecentOptions() {
        // String[] recentProjects = LocalStorage.GetRecentProjects();
        try {
            String[] recentProjects = LocalStorage.loadRecentProjects();

            if (recentProjects.length > 0) {
                JLabel recentProjectsLabel = new JLabel("Or choose one of your recent projects...");
                Utils.formatLabel(recentProjectsLabel, JLabel.CENTER, 0);
                add(recentProjectsLabel);
                ResizeAndAlign(recentProjectsLabel);

                for (String projectPath : recentProjects)
                    CreateRecentOption(projectPath);
            } else {
                JLabel recentProjectsLabel = new JLabel("Oops! No recent projects found... Start a new one!");
                Utils.formatLabel(recentProjectsLabel, JLabel.CENTER, 0);
                add(recentProjectsLabel);
                ResizeAndAlign(recentProjectsLabel);
            }
        } catch (IOException e) {
            System.out.println("ERROR: Could not open recent projects file");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Could not parse recent projects file");
        }
    }

    private void CreateRecentOption(String path) {
        // Needs to be escaped twice because it gets unescaped into to regex.
        String[] splitName = path.split("\\\\");
        String shortName = splitName[splitName.length - 1];

        RecentProjectButton recentOption = new RecentProjectButton(path, shortName);
        recentOption.addActionListener(this);
        add(recentOption);
        ResizeAndAlign(recentOption);
    }

    private void ResizeAndAlign(JComponent recentOption) {
        SpringLayout layout = (SpringLayout) getLayout();

        layout.putConstraint(SpringLayout.NORTH, recentOption, Consts.recentProjectOptionMargin, lastAnchor == this ? SpringLayout.NORTH : SpringLayout.SOUTH, lastAnchor);

        layout.putConstraint(SpringLayout.SOUTH, recentOption, Consts.recentProjectOptionHeight, SpringLayout.NORTH, recentOption);

        layout.putConstraint(SpringLayout.EAST, recentOption, -Consts.recentProjectOptionMargin, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, recentOption, Consts.recentProjectOptionMargin, SpringLayout.WEST, this);

        lastAnchor = recentOption;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RecentProjectButton recentProjectBtn = (RecentProjectButton) e.getSource();

        try {
            String path = recentProjectBtn.getName();
            AppData appData = FileManager.LoadAppData(path);
            appData.screenData = new ScreenData();
            appData.log = new LoggingSystem();
            new CanvasWindow(appData);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        } catch (InvalidClassException err) {
            System.out.println("ERROR: Could not parse loaded file");
        } catch (IOException err) {
            System.out.println("ERROR: Could not open loaded file");
        } catch (ClassNotFoundException err) {
            System.out.println("ERROR: Could not parse loaded file");
        }
    }
}
