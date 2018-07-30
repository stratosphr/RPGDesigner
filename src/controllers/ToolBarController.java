package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.MasterModel;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class ToolBarController extends AController {

    @FXML
    private Button btn_previewGame;

    public void previewGame(ActionEvent actionEvent) {
    }

    @Override
    public void initModel(MasterModel model) {
    }

}
