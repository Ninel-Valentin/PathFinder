package src.scripts.Windows.Components.EntryContent.SettingsFrame;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import src.scripts.AppData.AppData;
import src.scripts.AppData.ScreenData;
import src.scripts.AppData.Settings.AppSettings;
import src.scripts.StorageManager.FileManager.FileManager;
import src.scripts.Windows.Components.EntryContent.OpenDialoguePanel;
import src.scripts.Windows.Dialogs.App.CanvasWindow;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.Consts.GraphType;
import src.scripts.utils.Log.LoggingSystem;

public class SettingsPanel extends OpenDialoguePanel implements ActionListener {
        private JPanel changingOptionPanels = new JPanel(new CardLayout());
        private JLabel gridType;
        private JComboBox<String> gridTypeOptions, graphTypeOptions;

        public SettingsPanel(SpringLayout parentLayout, JComponent parent) {
                super(parentLayout, parent, parent, SpringLayout.NORTH, Consts.settingsPanelHeight);

                gridType = new JLabel("Grid Type");
                Utils.formatLabel(gridType, JLabel.RIGHT, Consts.settingsLabelWidth);
                add(gridType);

                DefaultComboBoxModel<String> gridTypeOptionsModel = new DefaultComboBoxModel<String>(Consts.canvasTypeStrings);
                gridTypeOptions = new JComboBox<String>(gridTypeOptionsModel);
                gridTypeOptions.addActionListener(this);
                gridTypeOptions.setPreferredSize(new Dimension(Consts.settingsSelectWidth, Consts.settingsCellHeight));
                add(gridTypeOptions);

                // Add the graphType selector
                Utils.alignPairRow(gridType, gridTypeOptions, (SpringLayout) getLayout(), this, SpringLayout.NORTH, SpringLayout.NORTH);

                // Add the graph-type dependent changing panel settings
                loadChangingPanel();

                // Add the "create new" / "open" buttons
                loadOpenButtons();
        }

        private void loadChangingPanel() {
                loadCanvasChangingSettings();
                loadGridChangingSettings();

                // Add and align the changing panel
                add(changingOptionPanels);
                Utils.alignSoloRow(changingOptionPanels, (SpringLayout) getLayout(), this, gridType, false, true, SpringLayout.NORTH, SpringLayout.SOUTH);
        }

        private void loadCanvasChangingSettings() {
                ChangingPanel canvasSettings = new ChangingPanel();
                JLabel graphType = new JLabel("Graph type");
                Utils.formatLabel(graphType, JLabel.RIGHT, Consts.settingsLabelWidth);
                canvasSettings.add(graphType);

                DefaultComboBoxModel<String> graphTypeOptionsModel = new DefaultComboBoxModel<String>(Consts.graphTypeStrings);
                graphTypeOptions = new JComboBox<String>(graphTypeOptionsModel);
                graphTypeOptions.setPreferredSize(new Dimension(Consts.settingsSelectWidth, Consts.settingsCellHeight));
                canvasSettings.add(graphTypeOptions);

                Utils.alignPairRow(graphType, graphTypeOptions, (SpringLayout) canvasSettings.getLayout(), canvasSettings, SpringLayout.NORTH, SpringLayout.NORTH);

                changingOptionPanels.add(canvasSettings, Consts.canvasCardName);
        }

        private void loadGridChangingSettings() {
                ChangingPanel gridSettings = new ChangingPanel();

                // Add rows label
                JLabel gridRows = new JLabel("Rows");
                Utils.formatLabel(gridRows, JLabel.RIGHT, Consts.settingsLabelWidth);
                gridRows.setPreferredSize(new Dimension(Consts.settingsLabelWidth, Consts.settingsCellHeight));
                gridSettings.add(gridRows);
                // Add rows input
                JFormattedTextField rowInput = new JFormattedTextField(Utils.createDigitOnlyFormatter("30"));
                rowInput.setPreferredSize(new Dimension(Consts.settingsSelectWidth, Consts.settingsCellHeight));
                gridSettings.add(rowInput);
                // Align rows label and input
                Utils.alignPairRow(gridRows, rowInput, (SpringLayout) gridSettings.getLayout(), gridSettings, SpringLayout.NORTH, SpringLayout.NORTH);

                // Add cols label
                JLabel gridCols = new JLabel("Cols");
                Utils.formatLabel(gridCols, JLabel.RIGHT, Consts.settingsLabelWidth);
                gridCols.setPreferredSize(new Dimension(Consts.settingsLabelWidth, Consts.settingsCellHeight));
                gridSettings.add(gridCols);
                // Add cols input
                JFormattedTextField colInput = new JFormattedTextField(Utils.createDigitOnlyFormatter("30"));
                colInput.setPreferredSize(new Dimension(Consts.settingsSelectWidth, Consts.settingsCellHeight));
                gridSettings.add(colInput);
                // Align cols label and input

                Utils.alignSettingsPairRow(gridCols, colInput, (SpringLayout) gridSettings.getLayout(), gridSettings, rowInput, SpringLayout.NORTH, SpringLayout.SOUTH);

                changingOptionPanels.add(gridSettings, Consts.gridCardName);
        }

        private void loadOpenButtons() {
                JButton createButton = new JButton("Create new");
                createButton.setPreferredSize(new Dimension(Consts.halfInputWidth, Consts.ctrlBtnSize));
                createButton.addActionListener(this);
                createButton.setName("CREATE");

                JButton openButton = new JButton("Open project");
                openButton.setPreferredSize(new Dimension(Consts.halfInputWidth, Consts.ctrlBtnSize));
                openButton.addActionListener(this);
                openButton.setName("OPEN");

                add(createButton);
                add(openButton);

                Utils.alignPairRow(createButton, openButton, (SpringLayout) getLayout(), this, SpringLayout.SOUTH, SpringLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource().getClass().getName().contains("JButton")) {
                        AppData appData;
                        JFrame frame;
                        switch (((JButton) e.getSource()).getName()) {
                        case "CREATE":
                                GraphType graphType = Utils.getGraphType(graphTypeOptions.getSelectedItem().toString());
                                // TODO: add weighted option on edgeType
                                AppSettings appSettings = new AppSettings(graphType, null);
                                appData = new AppData(null, appSettings);
                                new CanvasWindow(appData);
                                frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                                frame.dispose();
                                break;
                        case "OPEN":
                                String path = FileManager.getFilePath();
                                try {
                                        if (path != null) {
                                                appData = FileManager.LoadAppData(path);
                                                appData.screenData = new ScreenData();
                                                appData.log = new LoggingSystem();
                                                new CanvasWindow(appData);
                                                frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                                                frame.dispose();
                                        }
                                } catch (IOException err) {
                                        System.out.println("ERROR: Could not open loaded file");
                                } catch (ClassNotFoundException err) {
                                        System.out.println("ERROR: Could not parse loaded file");
                                }
                                break;
                        }
                } else if (e.getSource().getClass().getName().contains("JComboBox")) {
                        @SuppressWarnings("unchecked")
                        JComboBox<String> src = (JComboBox<String>) e.getSource();
                        String selectedOption = (String) src.getSelectedItem();
                        ((CardLayout) changingOptionPanels.getLayout()).show(changingOptionPanels, Consts.getCardFromCanvasType(selectedOption));
                }
        }

}