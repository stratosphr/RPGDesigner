package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import models.MasterModel;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MainWindowController extends AController {

    @FXML
    private SplitPane split_main;

    @FXML
    private MenuBarController menuBarController;
    @FXML
    private ToolBarController toolBarController;
    @FXML
    private PreviewController previewController;
    @FXML
    private NodesHierarchyController nodesHierarchyController;
    @FXML
    private NodePropertiesController nodePropertiesController;

    @Override
    public void initModel(MasterModel model) {
        menuBarController.initModel(model);
        toolBarController.initModel(model);
        previewController.initModel(model);
        nodesHierarchyController.initModel(model);
        nodePropertiesController.initModel(model);
    }

}
