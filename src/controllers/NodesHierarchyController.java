package controllers;

import controllers.cellcontrollers.NodesHierarchyCellController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.hierarchy.ANodesHierarchyElement;
import models.nodes.hierarchy.NodesHierarchyDirectory;
import models.nodes.hierarchy.NodesHierarchyLeaf;
import models.nodes.properties.FileNodeProperty;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.StringNodeProperty;

import static javafx.scene.input.KeyCode.F2;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodesHierarchyController {

    @FXML
    private TreeView<ANodesHierarchyElement> tree_nodesHierarchy;

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
        NodesHierarchyDirectory root = new NodesHierarchyDirectory("Project");
        NodesHierarchyDirectory subFolder = new NodesHierarchyDirectory("SubFolder");
        root.getChildren().add(new NodesHierarchyLeaf(new ANode("node1", new IntegerNodeProperty("Value", 42, 1, 50), new StringNodeProperty("Text", "some string")) {
        }));
        root.getChildren().add(new NodesHierarchyLeaf(new ANode("node2", new FileNodeProperty("Image", null)) {
        }));
        root.getChildren().add(new NodesHierarchyLeaf(new ANode("node3", new FileNodeProperty("Settings file", null)) {
        }));
        subFolder.getChildren().add(new NodesHierarchyLeaf(new ANode("node4", new FileNodeProperty("Image", null)) {
        }));
        subFolder.getChildren().add(new NodesHierarchyLeaf(new ANode("node5", new FileNodeProperty("Settings file", null)) {
        }));
        root.getChildren().add(subFolder);
        tree_nodesHierarchy.setRoot(new TreeItem<>(root));
        tree_nodesHierarchy.getRoot().setExpanded(true);
        tree_nodesHierarchy.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getClickCount() == 2) {
                TreeItem<ANodesHierarchyElement> selectedItem = tree_nodesHierarchy.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    ANodesHierarchyElement value = selectedItem.getValue();
                    if (!value.isDirectory()) {
                        model.previewedNodeProperty().set(((NodesHierarchyLeaf) value).getNode());
                    }
                }
            }
        });
    }

}
