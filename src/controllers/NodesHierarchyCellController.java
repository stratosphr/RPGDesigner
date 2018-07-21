package controllers;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import models.nodes.ANode;

/**
 * Created by stratosphr on 21/07/2018.
 */
public class NodesHierarchyCellController extends javafx.scene.control.TreeCell<ANode> {

    private TextField textField;

    private void createTextField() {
        this.textField = new TextField();
        textField.setText(getItem().nameProperty().get());
        getItem().nameProperty().bind(textField.textProperty());
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
    protected void updateItem(ANode item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
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
