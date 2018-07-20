package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import models.MasterModel;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MainWindowController {

    @FXML
    private MenuBarController menuBarController;
    @FXML
    private ToolBarController toolBarController;
    @FXML
    private SplitPane split_main;
    @FXML
    private PreviewController previewController;
    @FXML
    private NodesHierarchyController nodesHierarchyController;
    @FXML
    private NodePropertiesController nodePropertiesController;

    public void initModel(MasterModel model) {
        previewController.initModel(model);
        nodesHierarchyController.initModel(model);
        nodePropertiesController.initModel(model);
    }

}
