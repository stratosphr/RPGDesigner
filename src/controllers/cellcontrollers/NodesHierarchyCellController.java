package controllers.cellcontrollers;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import models.nodes.hierarchy.ANodesHierarchyElement;
import models.nodes.hierarchy.NodesHierarchyDirectory;

/**
 * Created by stratosphr on 21/07/2018.
 */
public class NodesHierarchyCellController extends TreeCell<ANodesHierarchyElement> {

    private TextField textField;

    private void createTextField() {
        this.textField = new TextField();
        textField.setText(getItem().nameProperty().get());
        getItem().nameProperty().bindBidirectional(textField.textProperty());
        textField.focusedProperty().addListener((observable, oldValue, focused) -> {
            if (!focused) {
                commitEdit(getItem());
            } else {
                Platform.runLater(textField::selectAll);
            }
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
    }

    @Override
    protected void updateItem(ANodesHierarchyElement item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (item.isDirectory() && getTreeItem().getChildren().isEmpty()) {
                ((NodesHierarchyDirectory) item).getChildren().forEach(nodesHierarchyElement -> {
                    TreeItem<ANodesHierarchyElement> childItem = new TreeItem<>(nodesHierarchyElement);
                    childItem.setExpanded(true);
                    getTreeItem().getChildren().add(childItem);
                });
            }
            if (isEditing()) {
                setText(null);
                setGraphic(textField);
            } else {
                setText(item.nameProperty().get());
                setGraphic(null);
            }
        }
    }

}
