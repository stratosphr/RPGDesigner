package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.properties.*;
import models.nodes.properties.visitors.IPropertyVisitor;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodePropertiesController extends AController implements IPropertyVisitor {

    @FXML
    private TitledPane pane_nodeProperties;
    @FXML
    private TreeTableView<AProperty<?>> table_previewedNodeProperties;
    @FXML
    private TreeTableColumn<AProperty<?>, String> col_properties;
    @FXML
    private TreeTableColumn<AProperty<?>, Node> col_values;

    private TreeItem<AProperty<?>> currentItem;

    @Override
    public void initModel(MasterModel model) {
        col_properties.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        if (model.getPreviewedNode() != null) {
            displayProperties(model.getPreviewedNode());
        } else {
            pane_nodeProperties.setDisable(true);
        }
        model.previewedNodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayProperties(newValue);
            } else {
                pane_nodeProperties.setDisable(true);
            }
        });
    }

    private void displayProperties(ANode node) {
        pane_nodeProperties.setText("Node Properties for " + node.getName());
        pane_nodeProperties.setDisable(false);
        table_previewedNodeProperties.setRoot(new TreeItem<>());
        table_previewedNodeProperties.setShowRoot(false);
        col_properties.setCellValueFactory(new PropertyNameCellFactory());
        col_values.setCellValueFactory(new PropertyValueCellFactory());
        currentItem = table_previewedNodeProperties.getRoot();
        for (AProperty<?> property : node.getProperties()) {
            property.accept(this);
        }
    }

    @Override
    public void visit(Vector2Property vector2Property) {
        TreeItem<AProperty<?>> item = new TreeItem<>(vector2Property);
        item.setExpanded(true);
        currentItem.getChildren().add(item);
        currentItem = item;
        vector2Property.getFirstProperty().accept(this);
        vector2Property.getSecondProperty().accept(this);
        currentItem = item.getParent();
    }

    @Override
    public void visit(TileSetProperty tileSetProperty) {
    }

    @Override
    public void visit(ImageProperty imageProperty) {
        currentItem.getChildren().add(new TreeItem<>(imageProperty));
    }

    @Override
    public void visit(IntegerProperty integerProperty) {
        currentItem.getChildren().add(new TreeItem<>(integerProperty));
    }

    private final class PropertyNameCellFactory implements Callback<TreeTableColumn.CellDataFeatures<AProperty<?>, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AProperty<?>, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getValue().getName());
        }

    }

    private final class PropertyValueCellFactory implements Callback<TreeTableColumn.CellDataFeatures<AProperty<?>, Node>, ObservableValue<Node>>, IPropertyVisitor {

        private Node node;

        @Override
        public ObservableValue<Node> call(TreeTableColumn.CellDataFeatures<AProperty<?>, Node> param) {
            param.getValue().getValue().accept(this);
            return new ReadOnlyObjectWrapper<>(node);
        }

        @Override
        public void visit(Vector2Property vector2Property) {
        }

        @Override
        public void visit(TileSetProperty tileSetProperty) {
        }

        @Override
        public void visit(ImageProperty imageProperty) {
        }

        @Override
        public void visit(IntegerProperty integerProperty) {
            Spinner<Integer> spinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(integerProperty.getMin(), integerProperty.getMax(), integerProperty.get(), integerProperty.getAmountToStepBy()));
            integerProperty.bind(spinner.valueProperty());
            node = spinner;
        }

    }

}
