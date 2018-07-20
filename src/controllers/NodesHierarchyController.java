package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.properties.ANodeProperty;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.ESCAPE;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodesHierarchyController implements Initializable {

    @FXML
    private TreeView<ANode> tree_nodesHierarchy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tree_nodesHierarchy.setCellFactory(param -> new NodeCell());
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

    private class NodeCell extends TreeCell<ANode> {

        private String text;
        private Node graphic;

        @Override
        public void startEdit() {
            super.startEdit();
            if (graphic == null) {
                createGraphic();
            }
            setText(text);
            setGraphic(graphic);
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(text);
            setGraphic(getTreeItem().getGraphic());
        }

        private void createGraphic() {
            if (getItem().nameProperty().get().contains("1")) {
                TextField textField = new TextField(getItem().nameProperty().get());
                textField.textProperty().addListener((observable, oldValue, newValue) -> getItem().nameProperty().set(newValue));
                textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        commitEdit(getItem());
                    }
                });
                textField.setOnKeyPressed(event -> {
                    if (event.getCode() == ESCAPE) {
                        cancelEdit();
                    } else if (event.getCode() == ENTER) {
                        commitEdit(getItem());
                    }
                });
                graphic = textField;
            } else {
                text = getItem().nameProperty().get();
                graphic = null;
            }
        }

        @Override
        protected void updateItem(ANode item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.nameProperty().get());
                setGraphic(getTreeItem().getGraphic());
            }
        }

    }

}
