package controllers;

import controllers.cellcontrollers.NodesHierarchyCellController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.properties.FileNodeProperty;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.StringNodeProperty;

import static javafx.scene.input.KeyCode.F2;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodesHierarchyController {

    @FXML
    private TreeView<ANode> tree_nodesHierarchy;

    public void initModel(MasterModel model) {
        tree_nodesHierarchy.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 3) {
                tree_nodesHierarchy.setEditable(true);
                tree_nodesHierarchy.edit(tree_nodesHierarchy.getSelectionModel().getSelectedItem());
            }
        });
        tree_nodesHierarchy.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == F2) {
                tree_nodesHierarchy.setEditable(true);
                tree_nodesHierarchy.edit(tree_nodesHierarchy.getSelectionModel().getSelectedItem());
            }
        });
        tree_nodesHierarchy.setOnEditCancel(event -> tree_nodesHierarchy.setEditable(false));
        tree_nodesHierarchy.setOnEditCommit(event -> tree_nodesHierarchy.setEditable(false));
        tree_nodesHierarchy.setCellFactory(param -> new NodesHierarchyCellController());
        tree_nodesHierarchy.setRoot(new TreeItem<>(new ANode("Project") {
        }));
        tree_nodesHierarchy.getRoot().getChildren().add(new TreeItem<>(new ANode("node1", new IntegerNodeProperty("Value", 42, 1, 50), new StringNodeProperty("Text", "some string")) {
        }));
        tree_nodesHierarchy.getRoot().getChildren().add(new TreeItem<>(new ANode("node2", new FileNodeProperty("Image", null)) {
        }));
        tree_nodesHierarchy.getRoot().getChildren().add(new TreeItem<>(new ANode("node3", new FileNodeProperty("Settings file", null)) {
        }));
        tree_nodesHierarchy.getRoot().setExpanded(true);
        tree_nodesHierarchy.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getClickCount() == 2) {
                model.previewedNodeProperty().set(tree_nodesHierarchy.getSelectionModel().getSelectedItem().getValue());
            }
        });
    }

}
