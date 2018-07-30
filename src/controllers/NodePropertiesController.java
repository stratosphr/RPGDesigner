package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import models.MasterModel;
import models.nodes.properties.AProperty;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodePropertiesController extends AController {

    @FXML
    private TreeTableView<AProperty<?>> table_previewedNodeProperties;
    @FXML
    private TreeTableColumn<AProperty<?>, String> col_properties;
    @FXML
    private TreeTableColumn<AProperty<?>, Node> col_values;

    @Override
    public void initModel(MasterModel model) {
    }

}
