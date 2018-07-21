package controllers;

import controllers.cellcontrollers.NodePropertiesEditorCellController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import models.MasterModel;
import models.nodes.properties.ANodeProperty;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodePropertiesController implements Initializable {

    @FXML
    private TreeTableView<ANodeProperty<?>> table_previewedNodeProperties;
    @FXML
    private TreeTableColumn<ANodeProperty<?>, String> col_properties;
    @FXML
    private TreeTableColumn<ANodeProperty<?>, Node> col_values;

    @SuppressWarnings("unused")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initModel(MasterModel model) {
        NodePropertiesEditorCellController nodePropertiesEditorCellController = new NodePropertiesEditorCellController(model);
        col_properties.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        col_values.setCellValueFactory(nodePropertiesEditorCellController);
        table_previewedNodeProperties.setShowRoot(false);
        table_previewedNodeProperties.setRoot(new TreeItem<>());
        model.previewedNodeProperty().addListener((observable, oldValue, newValue) -> {
            table_previewedNodeProperties.getRoot().getChildren().clear();
            newValue.getProperties().forEach(nodeProperty -> table_previewedNodeProperties.getRoot().getChildren().add(new TreeItem<>(nodeProperty)));
        });
    }

}
