package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.properties.*;
import models.nodes.properties.visitors.IPropertyVisitor;

import java.io.File;

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
        col_properties.setCellValueFactory(new PropertyNameCellFactory());
        col_values.setCellValueFactory(new PropertyValueCellFactory());
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

    private class PropertyNameCellFactory implements Callback<TreeTableColumn.CellDataFeatures<AProperty<?>, String>, ObservableValue<String>> {

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
            Label label = new Label(vector2Property.get().toString());
            vector2Property.addListener((observable, oldValue, newValue) -> label.setText(newValue.toString()));
            node = label;
        }

        @Override
        public void visit(TileSetProperty tileSetProperty) {
        }

        @Override
        public void visit(ImageProperty imageProperty) {
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            TextField textField = new TextField();
            textField.setEditable(false);
            textField.setPromptText("Click to select " + imageProperty.getName().toLowerCase() + "...");
            Button button = new Button("...");
            Runnable fileChooserLoader = () -> {
                FileChooser fileChooser = new FileChooser();
                imageProperty.getFilters().stream().map(AFileProperty.EFileFilters::getExtensionFilter).forEach(filters -> fileChooser.getExtensionFilters().add(filters));
                File file = fileChooser.showOpenDialog(pane_nodeProperties.getScene().getWindow());
                if (file != null) {
                    textField.setText(file.getAbsolutePath());
                    imageProperty.set(file);
                }
                button.requestFocus();
            };
            textField.setOnMouseClicked(event -> fileChooserLoader.run());
            button.setOnAction(event -> fileChooserLoader.run());
            hBox.getChildren().addAll(textField, button);
            node = hBox;
        }

        @Override
        public void visit(IntegerProperty integerProperty) {
            Spinner<Integer> spinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(integerProperty.getMin(), integerProperty.getMax(), integerProperty.get(), integerProperty.getAmountToStepBy()));
            integerProperty.bind(spinner.valueProperty());
            node = spinner;
        }

    }

}
