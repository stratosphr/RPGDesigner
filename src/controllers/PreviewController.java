package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import models.MasterModel;
import models.nodes.ANode;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class PreviewController {

    @FXML
    private ScrollPane scroll_preview;

    public void initModel(MasterModel model) {
        model.previewedNodeProperty().addListener((observable, oldValue, newValue) -> {
            previewNode(newValue);
            newValue.nameProperty().addListener((observable1, oldValue1, newValue1) -> previewNode(newValue));
            newValue.getProperties().forEach(nodeProperty -> nodeProperty.valueProperty().addListener((observable1, oldValue1, newValue1) -> previewNode(newValue)));
        });
    }

    private void previewNode(ANode node) {
        Label label = new Label();
        label.setText(node.toString());
        scroll_preview.setContent(label);
    }

}
