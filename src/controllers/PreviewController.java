package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import models.MasterModel;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class PreviewController {

    @FXML
    private ScrollPane scroll_preview;

    public void initModel(MasterModel model) {
        Label label = new Label();
        scroll_preview.setContent(label);
        model.previewedNodeProperty().addListener((observable, oldValue, newValue) -> label.textProperty().bind(newValue.nameProperty()));
    }

}
