package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.properties.ANodeProperty;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.F2;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodesHierarchyController implements Initializable {

    @FXML
    private TreeView<ANode> tree_nodesHierarchy;

    @SuppressWarnings("unused")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        tree_nodesHierarchy.getRoot().getChildren().add(new TreeItem<>(new ANode("node1", new ANodeProperty<>("property1", 42) {
        }, new ANodeProperty<>("property2", "value") {
        }) {
        }));
        tree_nodesHierarchy.getRoot().getChildren().add(new TreeItem<>(new ANode("node2") {
        }));
        tree_nodesHierarchy.getRoot().setExpanded(true);
    }

    public void initModel(MasterModel model) {
        tree_nodesHierarchy.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getClickCount() == 2) {
                model.previewedNodeProperty().set(tree_nodesHierarchy.getSelectionModel().getSelectedItem().getValue());
            }
        });
    }

}
