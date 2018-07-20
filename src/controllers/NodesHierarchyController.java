package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.properties.ANodeProperty;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodesHierarchyController implements Initializable {

    @FXML
    private TreeView<ANode> tree_nodesHierarchy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tree_nodesHierarchy.setCellFactory(param -> new TreeCell<>() {
            @Override
            public void startEdit() {
                super.startEdit();
                setText(null);
                setGraphic(new TextField("test"));
            }

            @Override
            protected void updateItem(ANode item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.nameProperty().get());
                    setGraphic(null);
                }
            }
        });
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
        tree_nodesHierarchy.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> model.previewedNodeProperty().set(newValue.getValue()));
    }

}
