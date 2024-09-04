package src.scripts.Windows.AppContent.CanvasContent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import src.scripts.AppData.AppData;
import src.scripts.AppData.GraphData.Components.Node;
import src.scripts.Windows.Components.WindowContent.templates.AppWindowEditorPanel;
import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.Consts.EdgeWeight;

public class CanvasEditorPanel extends AppWindowEditorPanel implements ActionListener {
        // Changing the components of a dropdown triggers events, we need a way to control this
        private boolean canTriggerDDEvents = true;

        private JComboBox<String> edgeStartDD, edgeEndDD, algorithmStartDD, algorithmEndDD;
        private String[] nodeValues;

        private JTextField xCoordInput, yCoordInput, nodeTextValue, edgeWeightValue;
        private JButton removeEdgeBtn, setEdgeBtn, startAlgorithm;

        public CanvasEditorPanel(Dimension size, AppData appData) {
                this.appData = appData;
                // super() - Already called
                setSelfSize(size);
                loadCanvasEditor();
        }

        public void loadCanvasEditor() {

                Node selectedNode = appData.graphData.graph.getSelectedNode();

                JLabel coordinatesLabel = new JLabel("Coordinates:");
                Utils.formatLabel(coordinatesLabel, JLabel.LEFT, getWidth() / 2);
                add(coordinatesLabel);
                Utils.alignSoloRow(coordinatesLabel, (SpringLayout) getLayout(), this, lastVerticalAnchor, true, true, SpringLayout.NORTH, SpringLayout.SOUTH);

                // * Labels
                JLabel xCoordLabel = new JLabel("X:");
                Utils.formatLabel(xCoordLabel, JLabel.CENTER, Consts.editorDualInputSize);
                JLabel yCoordLabel = new JLabel("Y:");
                Utils.formatLabel(yCoordLabel, JLabel.CENTER, Consts.editorDualInputSize);
                add(xCoordLabel);
                add(yCoordLabel);
                // Set Size
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, xCoordLabel, Consts.editorDualInputSize, SpringLayout.WEST, xCoordLabel);
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, yCoordLabel, Consts.editorDualInputSize, SpringLayout.WEST, yCoordLabel);
                // Set location
                Utils.alignEditorPairRow(xCoordLabel, yCoordLabel, (SpringLayout) getLayout(), this, coordinatesLabel, SpringLayout.NORTH, SpringLayout.SOUTH);

                // * Coords
                String xCoordString = selectedNode == null ? "" : String.valueOf(selectedNode.getCoords().getX()),
                                yCoordString = selectedNode == null ? "" : String.valueOf(selectedNode.getCoords().getY());
                xCoordInput = new JTextField(xCoordString);
                Utils.setCustomCursor(xCoordInput, Consts.cursorDenied);
                xCoordInput.setEditable(false);
                xCoordInput.setBackground(Consts.lightGray_1);
                yCoordInput = new JTextField(yCoordString);
                Utils.setCustomCursor(yCoordInput, Consts.cursorDenied);
                yCoordInput.setEditable(false);
                yCoordInput.setBackground(Consts.lightGray_1);
                add(xCoordInput);
                add(yCoordInput);
                // Set Size
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, xCoordInput, Consts.editorDualInputSize, SpringLayout.WEST, xCoordInput);
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, yCoordInput, Consts.editorDualInputSize, SpringLayout.WEST, yCoordInput);
                // Set location
                Utils.alignEditorPairRow(xCoordInput, yCoordInput, (SpringLayout) getLayout(), this, xCoordLabel, SpringLayout.NORTH, SpringLayout.SOUTH);

                // Node value
                JLabel nodeTextLabel = new JLabel("Node value:");
                Utils.formatLabel(nodeTextLabel, JLabel.LEFT, getWidth() / 2);
                add(nodeTextLabel);
                Utils.alignSoloRow(nodeTextLabel, (SpringLayout) getLayout(), this, xCoordInput, true, false, SpringLayout.NORTH, SpringLayout.SOUTH);

                String nodeValueString = selectedNode == null ? "Select/Draw a node for more details" : String.valueOf(selectedNode.getValue());
                nodeTextValue = new JTextField(nodeValueString);
                Utils.setCustomCursor(nodeTextValue, Consts.cursorDenied);
                nodeTextValue.setEditable(false);
                nodeTextValue.setBackground(Consts.lightGray_1);

                add(nodeTextValue);
                Utils.alignSoloRow(nodeTextValue, (SpringLayout) getLayout(), this, nodeTextLabel, true, false, SpringLayout.NORTH, SpringLayout.SOUTH);
                lastVerticalAnchor = nodeTextValue;

                addSeparator();

                // * Edge settings

                JLabel edgeSettingsLabel = new JLabel("Edge settings");
                Utils.formatLabel(edgeSettingsLabel, JLabel.LEFT, getWidth() / 2);
                add(edgeSettingsLabel);
                Utils.alignSoloRow(edgeSettingsLabel, (SpringLayout) getLayout(), this, lastVerticalAnchor, true, false, SpringLayout.NORTH, SpringLayout.SOUTH);
                lastVerticalAnchor = edgeSettingsLabel;

                // * Edge start/end labels
                JLabel edgeStartLabel = new JLabel("from:");
                Utils.formatLabel(edgeStartLabel, JLabel.CENTER, Consts.editorDualInputSize);
                JLabel edgeEndLabel = new JLabel("to:");
                Utils.formatLabel(edgeEndLabel, JLabel.CENTER, Consts.editorDualInputSize);
                add(edgeStartLabel);
                add(edgeEndLabel);
                // Set Size
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, edgeStartLabel, Consts.editorDualInputSize, SpringLayout.WEST, edgeStartLabel);
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, edgeEndLabel, Consts.editorDualInputSize, SpringLayout.WEST, edgeEndLabel);
                // Set location
                Utils.alignEditorPairRow(edgeStartLabel, edgeEndLabel, (SpringLayout) getLayout(), this, lastVerticalAnchor, SpringLayout.NORTH, SpringLayout.SOUTH);

                instantiateNodeValuesArray();

                edgeStartDD = new JComboBox<String>(nodeValues);
                edgeStartDD.setName(Consts.edgeStartDDName);
                edgeStartDD.addActionListener(this);
                Utils.setCustomCursor(edgeStartDD, Consts.cursorAttention);

                edgeEndDD = new JComboBox<String>(nodeValues);
                edgeEndDD.setName(Consts.edgeEndDDName);
                edgeEndDD.addActionListener(this);
                Utils.setCustomCursor(edgeEndDD, Consts.cursorAttention);

                add(edgeStartDD);
                add(edgeEndDD);
                // Set Size
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, edgeStartDD, Consts.editorDualInputSize, SpringLayout.WEST, edgeStartDD);
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, edgeEndDD, Consts.editorDualInputSize, SpringLayout.WEST, edgeEndDD);

                // Set location
                Utils.alignEditorPairRow(edgeStartDD, edgeEndDD, (SpringLayout) getLayout(), this, edgeEndLabel, SpringLayout.NORTH, SpringLayout.SOUTH);

                lastVerticalAnchor = edgeStartDD;

                if (appData.settings.getEdgeWeightType() == EdgeWeight.WEIGHTED) {
                        // Node weight
                        JLabel edgeWeightLabel = new JLabel("Edge weight:");
                        Utils.formatLabel(edgeWeightLabel, JLabel.LEFT, getWidth() / 2);
                        add(edgeWeightLabel);
                        Utils.alignSoloRow(edgeWeightLabel, (SpringLayout) getLayout(), this, edgeStartDD, true, true, SpringLayout.NORTH, SpringLayout.SOUTH);

                        edgeWeightValue = new JTextField(4);
                        Utils.setCustomCursor(edgeWeightValue, Consts.cursorAttention);
                        add(edgeWeightValue);
                        Utils.alignSoloRow(edgeWeightValue, (SpringLayout) getLayout(), this, edgeWeightLabel, true, false, SpringLayout.NORTH, SpringLayout.SOUTH);
                        lastVerticalAnchor = edgeWeightValue;
                }

                // * Add edge button
                setEdgeBtn = new JButton("Add edge");
                setEdgeBtn.setName(Consts.addEdgeBtnName);
                setEdgeBtn.addActionListener(this);
                Utils.setCustomCursor(setEdgeBtn, Consts.cursorAttention);

                add(setEdgeBtn);
                Utils.alignSoloRow(setEdgeBtn, (SpringLayout) getLayout(), this, lastVerticalAnchor, true, true, SpringLayout.NORTH, SpringLayout.SOUTH);

                removeEdgeBtn = new JButton("Delete edge");
                removeEdgeBtn.setName(Consts.removeEdgeBtnName);
                removeEdgeBtn.addActionListener(this);
                Utils.setCustomCursor(removeEdgeBtn, Consts.cursorAttention);

                removeEdgeBtn.setEnabled(false);

                add(removeEdgeBtn);
                Utils.alignSoloRow(removeEdgeBtn, (SpringLayout) getLayout(), this, setEdgeBtn, true, true, SpringLayout.NORTH, SpringLayout.SOUTH);
                lastVerticalAnchor = removeEdgeBtn;

                addSeparator();

                // * Algorithm settings
                JLabel algorithmSettingsLabel = new JLabel("Algorithm settings");
                Utils.formatLabel(algorithmSettingsLabel, JLabel.LEFT, getWidth() / 2);
                add(algorithmSettingsLabel);
                Utils.alignSoloRow(algorithmSettingsLabel, (SpringLayout) getLayout(), this, lastVerticalAnchor, true, false, SpringLayout.NORTH, SpringLayout.SOUTH);

                JLabel algorithmStartLabel = new JLabel("start:");
                Utils.formatLabel(algorithmStartLabel, JLabel.CENTER, Consts.editorDualInputSize);
                JLabel algorithmEndLabel = new JLabel("end:");
                Utils.formatLabel(algorithmEndLabel, JLabel.CENTER, Consts.editorDualInputSize);
                add(algorithmStartLabel);
                add(algorithmEndLabel);
                // Set Size
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, algorithmStartLabel, Consts.editorDualInputSize, SpringLayout.WEST, algorithmStartLabel);
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, algorithmEndLabel, Consts.editorDualInputSize, SpringLayout.WEST, algorithmEndLabel);
                // Set location
                Utils.alignEditorPairRow(algorithmStartLabel, algorithmEndLabel, (SpringLayout) getLayout(), this, algorithmSettingsLabel, SpringLayout.NORTH, SpringLayout.SOUTH);

                algorithmStartDD = new JComboBox<String>(nodeValues);
                algorithmStartDD.setName(Consts.edgeStartDDName);
                algorithmStartDD.addActionListener(this);
                Utils.setCustomCursor(algorithmStartDD, Consts.cursorAttention);

                algorithmEndDD = new JComboBox<String>(nodeValues);
                algorithmEndDD.setName(Consts.edgeEndDDName);
                algorithmEndDD.addActionListener(this);
                Utils.setCustomCursor(algorithmEndDD, Consts.cursorAttention);

                add(algorithmStartDD);
                add(algorithmEndDD);
                // Set Size
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, algorithmStartDD, Consts.editorDualInputSize, SpringLayout.WEST, algorithmStartDD);
                ((SpringLayout) getLayout()).putConstraint(SpringLayout.EAST, algorithmEndDD, Consts.editorDualInputSize, SpringLayout.WEST, algorithmEndDD);

                // Set location
                Utils.alignEditorPairRow(algorithmStartDD, algorithmEndDD, (SpringLayout) getLayout(), this, algorithmEndLabel, SpringLayout.NORTH, SpringLayout.SOUTH);

                // * Start button
                startAlgorithm = new JButton("Start alogirthm");
                startAlgorithm.setName(Consts.startAlgorithmBtnName);
                startAlgorithm.addActionListener(this);
                Utils.setCustomCursor(startAlgorithm, Consts.cursorAttention);

                add(startAlgorithm);
                Utils.alignSoloRow(startAlgorithm, (SpringLayout) getLayout(), this, algorithmStartDD, true, true, SpringLayout.NORTH, SpringLayout.SOUTH);

                lastVerticalAnchor = startAlgorithm;

        }

        public void updateUIForSelectedNode() {
                xCoordInput.setText(String.valueOf(appData.graphData.graph.getSelectedNode().getCoords().getX()));
                yCoordInput.setText(String.valueOf(appData.graphData.graph.getSelectedNode().getCoords().getY()));
                nodeTextValue.setText(String.valueOf(appData.graphData.graph.getSelectedValue()));
        }

        private void updateDropDowns(ActionEvent e, JComboBox<String> targetDD) {
                instantiateNodeValuesArray();
                // We can draw nodes that ends where it starts for Directed graphs
                if (appData.settings.getGraphType() == Consts.GraphType.UNDIRECTED) {
                        canTriggerDDEvents = false;
                        @SuppressWarnings("unchecked")
                        JComboBox<String> actionedDD = ((JComboBox<String>) e.getSource());

                        String actionedDDValue = actionedDD.getSelectedItem().toString();
                        String targetDDValue = targetDD.getSelectedItem().toString();
                        targetDD.removeAllItems();

                        for (int i = 0; i < nodeValues.length; i++)
                                if (actionedDDValue.compareTo(nodeValues[i]) != 0)
                                        targetDD.addItem(nodeValues[i]);

                        targetDD.setSelectedItem(targetDDValue);

                        canTriggerDDEvents = true;
                }
        }

        public void refreshDropDowns() {
                instantiateNodeValuesArray();
                canTriggerDDEvents = false;
                // Edge settings
                String startDDValue = edgeStartDD.getSelectedItem().toString();
                String endDDValue = edgeEndDD.getSelectedItem().toString();

                edgeStartDD.removeAllItems();
                edgeEndDD.removeAllItems();

                for (int i = 0; i < nodeValues.length; i++) {
                        edgeStartDD.addItem(nodeValues[i]);
                        edgeEndDD.addItem(nodeValues[i]);
                }

                edgeStartDD.setSelectedItem(startDDValue);
                edgeEndDD.setSelectedItem(endDDValue);

                // Algorithm settings
                String algorithmStartDDValue = algorithmStartDD.getSelectedItem().toString();
                String algorithmEndDDValue = algorithmEndDD.getSelectedItem().toString();

                algorithmStartDD.removeAllItems();
                algorithmEndDD.removeAllItems();

                for (int i = 0; i < nodeValues.length; i++) {
                        algorithmStartDD.addItem(nodeValues[i]);
                        algorithmEndDD.addItem(nodeValues[i]);
                }

                algorithmStartDD.setSelectedItem(algorithmStartDDValue);
                algorithmEndDD.setSelectedItem(algorithmEndDDValue);

                updateEdgeSettings();
                canTriggerDDEvents = true;
        }

        private void updateEdgeSettings() {
                if (edgeStartDD.getSelectedIndex() != 0 && edgeEndDD.getSelectedIndex() != 0) {
                        int startNodeValue = Integer.valueOf(edgeStartDD.getSelectedItem().toString());
                        int endNodeValue = Integer.valueOf(edgeEndDD.getSelectedItem().toString());

                        int selectedEdgeWeight = appData.graphData.graph.getEdge(startNodeValue, endNodeValue);

                        // Toggle the remove button and update the weight label value
                        removeEdgeBtn.setEnabled(selectedEdgeWeight != 0);
                        if (appData.settings.getEdgeWeightType() == EdgeWeight.WEIGHTED)
                                edgeWeightValue.setText(String.valueOf(selectedEdgeWeight));

                        if (appData.settings.getEdgeWeightType() == EdgeWeight.WEIGHTED) {
                                setEdgeBtn.setText(selectedEdgeWeight != 0 ? "Update edge's weight" : "Add edge");
                                setEdgeBtn.setName(selectedEdgeWeight != 0 ? Consts.setWeightBtnName : Consts.addEdgeBtnName);
                        }
                }
        }

        private void instantiateNodeValuesArray() {
                Node[] nodes = appData.graphData.graph.getNodes();
                nodeValues = new String[nodes.length + 1];
                nodeValues[0] = "Select...";
                for (int i = 0; i < nodes.length; i++)
                        nodeValues[i + 1] = String.valueOf(nodes[i].getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (!canTriggerDDEvents)
                        return;

                String sourceName = ((JComponent) e.getSource()).getName();
                switch (sourceName) {
                case Consts.edgeStartDDName:
                        updateDropDowns(e, edgeEndDD);
                        updateEdgeSettings();
                        break;
                case Consts.edgeEndDDName:
                        updateDropDowns(e, edgeStartDD);
                        updateEdgeSettings();
                        break;
                case Consts.addEdgeBtnName:
                        // Check if both ends have been selected
                        if (edgeStartDD.getSelectedIndex() != 0 && edgeEndDD.getSelectedIndex() != 0) {
                                try {
                                        int startNodeIndex = Integer.valueOf(edgeStartDD.getSelectedItem().toString());
                                        int targetNodeIndex = Integer.valueOf(edgeEndDD.getSelectedItem().toString());

                                        int weightInputValue = edgeWeightValue == null ? 1 : Integer.valueOf(edgeWeightValue.getText().replace(" ", ""));
                                        // Check if they are the same point
                                        if (edgeStartDD.getSelectedItem().toString().compareTo(edgeEndDD.getSelectedItem().toString()) == 0)
                                                appData.log.error("Cannot create an edge that starts and ends in the same point!");
                                        // Check if there is an existing edge here
                                        else if (appData.graphData.graph.hasEdge(startNodeIndex, targetNodeIndex))
                                                appData.log.error("Edge from " + startNodeIndex + " to " + targetNodeIndex + " already exists.");
                                        else {
                                                weightInputValue = weightInputValue == 0 ? 1 : weightInputValue;
                                                appData.graphData.graph.addEdge(startNodeIndex, targetNodeIndex, weightInputValue);
                                                appData.log.info("Edge from " + startNodeIndex + " to " + targetNodeIndex + " created successfully.");
                                                appData.screenData.screen.repaint();
                                                updateEdgeSettings();
                                        }
                                } catch (NumberFormatException exception) {
                                        appData.log.error("Wrong weight format. The value should be a number!");
                                }

                        }
                        break;
                case Consts.setWeightBtnName:
                        // Check if both ends have been selected
                        if (edgeStartDD.getSelectedIndex() != 0 && edgeEndDD.getSelectedIndex() != 0) {
                                try {
                                        int startNodeIndex = Integer.valueOf(edgeStartDD.getSelectedItem().toString());
                                        int endNodeIndex = Integer.valueOf(edgeEndDD.getSelectedItem().toString());
                                        int weightInputValue = Integer.valueOf(edgeWeightValue.getText().replace(" ", ""));

                                        // Check if they are the same point
                                        if (edgeStartDD.getSelectedItem().toString().compareTo(edgeEndDD.getSelectedItem().toString()) == 0)
                                                appData.log.error("Cannot create an edge that starts and ends in the same point!");
                                        else if (weightInputValue == 0)
                                                appData.log.error("Weight of an edge cannot be 0!");
                                        else
                                                appData.graphData.graph.setWeight(startNodeIndex, endNodeIndex, weightInputValue);
                                } catch (NumberFormatException exception) {
                                        appData.log.error("Wrong weight format. The value should be a number!");
                                }
                        }
                        break;
                case Consts.removeEdgeBtnName:
                        // Check if both ends have been selected
                        if (edgeStartDD.getSelectedIndex() != 0 && edgeEndDD.getSelectedIndex() != 0) {
                                int startNodeIndex = Integer.valueOf(edgeStartDD.getSelectedItem().toString());
                                int targetNodeIndex = Integer.valueOf(edgeEndDD.getSelectedItem().toString());

                                // Check if there is an existing edge here
                                if (appData.graphData.graph.hasEdge(startNodeIndex, targetNodeIndex)) {
                                        appData.graphData.graph.removeEdge(startNodeIndex, targetNodeIndex);
                                        appData.log.info("Edge from " + startNodeIndex + " to " + targetNodeIndex + " removed successfully.");
                                        appData.screenData.screen.repaint();
                                        updateEdgeSettings();
                                } else
                                        appData.log.error("There is no edge from " + startNodeIndex + " to " + targetNodeIndex + ".");
                        }
                case Consts.startAlgorithmBtnName:
                        startAlgorithm();

                        break;
                }
        }

        public void startAlgorithm() {
                // Check if both ends have been selected
                if (algorithmStartDD.getSelectedIndex() != 0 && algorithmEndDD.getSelectedIndex() != 0) {
                        String startNodeIndex = algorithmStartDD.getSelectedItem().toString();
                        String targetNodeIndex = algorithmEndDD.getSelectedItem().toString();

                        appData.graphData.outputBFS(startNodeIndex, targetNodeIndex);
                } else
                        appData.log.error("Please select the start and the end of the algorithm!");
        }

}
