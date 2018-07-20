package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.MasterModel;
import models.nodes.properties.ANodeProperty;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodePropertiesController implements Initializable {

    @FXML
    private TreeTableView<ANodeProperty> table_previewedNodeProperties;
    @FXML
    private TreeTableColumn<ANodeProperty, String> col_properties;
    @FXML
    private TreeTableColumn<ANodeProperty, Node> col_values;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_properties.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        col_values.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(new Rectangle(10, 10, Color.BLACK)));
        table_previewedNodeProperties.setShowRoot(false);
        table_previewedNodeProperties.setRoot(new TreeItem<>());
    }

    public void initModel(MasterModel model) {
        model.previewedNodeProperty().addListener((observable, oldValue, newValue) -> {
            table_previewedNodeProperties.getRoot().getChildren().clear();
            newValue.getProperties().forEach(nodeProperty -> table_previewedNodeProperties.getRoot().getChildren().add(new TreeItem<>(nodeProperty)));
        });
    }

}
