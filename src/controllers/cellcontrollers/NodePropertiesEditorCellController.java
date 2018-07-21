package controllers.cellcontrollers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import models.MasterModel;
import models.nodes.properties.ANodeProperty;
import models.nodes.properties.FileNodeProperty;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.StringNodeProperty;
import visitors.IRegionEditableVisitor;

import java.io.File;

/**
 * Created by stratosphr on 21/07/2018.
 */
public final class NodePropertiesEditorCellController implements Callback<TreeTableColumn.CellDataFeatures<ANodeProperty<?>, Node>, ObservableValue<Node>>, IRegionEditableVisitor {

    private final MasterModel model;
    private TreeTableColumn.CellDataFeatures<ANodeProperty<?>, Node> param;

    public NodePropertiesEditorCellController(MasterModel model) {
        this.model = model;
    }

    @Override
    public ObservableValue<Node> call(TreeTableColumn.CellDataFeatures<ANodeProperty<?>, Node> param) {
        this.param = param;
        return new ReadOnlyObjectWrapper<>(param.getValue().getValue().accept(this));
    }

    @Override
    public Region visit(IntegerNodeProperty integerNodeProperty) {
        Spinner<Integer> spinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(integerNodeProperty.getMin(), integerNodeProperty.getMax(), integerNodeProperty.valueProperty().get()));
        spinner.getEditor().textProperty().bindBidirectional(integerNodeProperty.valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        return spinner;
    }

    @Override
    public Region visit(StringNodeProperty stringNodeProperty) {
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(stringNodeProperty.valueProperty());
        return textField;
    }

    @Override
    public Region visit(FileNodeProperty fileNodeProperty) {
        HBox hBox = new HBox();
        TextField txt_filePath = new TextField();
        Tooltip tooltip = new Tooltip();
        tooltip.setShowDelay(new Duration(100));
        tooltip.textProperty().bind(txt_filePath.textProperty());
        txt_filePath.setEditable(false);
        txt_filePath.prefWidthProperty().bind(param.getTreeTableColumn().widthProperty());
        txt_filePath.textProperty().bindBidirectional(fileNodeProperty.valueProperty(), new StringConverter<>() {
            @Override
            public String toString(File file) {
                return file == null ? "" : file.getAbsolutePath();
            }

            @Override
            public File fromString(String string) {
                return new File(string);
            }
        });
        txt_filePath.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                txt_filePath.setTooltip(tooltip);
            }
        });
        Button btn_fileSelection = new Button("...");
        hBox.getChildren().addAll(txt_filePath, btn_fileSelection);
        FileChooser fileChooser = new FileChooser();
        for (Node node1 : hBox.getChildren()) {
            node1.setOnMouseClicked(event -> {
                fileChooser.setTitle("Select " + fileNodeProperty.nameProperty().get().toLowerCase() + " for node \"" + model.previewedNodeProperty().get().nameProperty().get() + "\"...");
                if (fileNodeProperty.valueProperty().get() != null) {
                    fileChooser.setInitialDirectory(fileNodeProperty.valueProperty().get().getParentFile());
                }
                File selectedFile = fileChooser.showOpenDialog(model.getPrimaryStage());
                if (selectedFile != null) {
                    txt_filePath.setText(selectedFile.getAbsolutePath());
                }
            });
        }
        return hBox;
    }

}
