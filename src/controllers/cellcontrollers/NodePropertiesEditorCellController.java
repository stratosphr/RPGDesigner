package controllers.cellcontrollers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import models.MasterModel;
import models.nodes.properties.*;
import utilities.Vector;
import visitors.nodeproperties.IRegionEditableVisitor;

import java.io.File;

import static javafx.geometry.Pos.CENTER;

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
    public Spinner<Integer> visit(IntegerNodeProperty integerNodeProperty) {
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
    public TextField visit(StringNodeProperty stringNodeProperty) {
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(stringNodeProperty.valueProperty());
        return textField;
    }

    @Override
    public HBox visit(FileNodeProperty fileNodeProperty) {
        HBox hBox = new HBox();
        Tooltip tooltip = new Tooltip();
        tooltip.setShowDelay(new Duration(100));
        tooltip.setShowDuration(Duration.INDEFINITE);
        TextField txt_filePath = new TextField();
        txt_filePath.setPromptText("Click to select " + fileNodeProperty.nameProperty().get().toLowerCase() + "...");
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
        tooltip.textProperty().bind(txt_filePath.textProperty());
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
                btn_fileSelection.requestFocus();
            });
        }
        return hBox;
    }

    @Override
    public HBox visit(VectorNodeProperty vectorNodeProperty) {
        HBox hBox = new HBox();
        hBox.setAlignment(CENTER);
        hBox.setSpacing(5);
        VBox firstVBox = new VBox();
        VBox middleVBox = new VBox();
        VBox secondVBox = new VBox();
        Label firstLabel = new Label(vectorNodeProperty.getFirstNodeProperty().nameProperty().get());
        Spinner<Integer> firstSpinner = vectorNodeProperty.getFirstNodeProperty().accept(this);
        Label secondLabel = new Label(vectorNodeProperty.getSecondNodeProperty().nameProperty().get());
        Spinner<Integer> secondSpinner = vectorNodeProperty.getSecondNodeProperty().accept(this);
        firstSpinner.getEditor().textProperty().bindBidirectional(vectorNodeProperty.getFirstNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        secondSpinner.getEditor().textProperty().bindBidirectional(vectorNodeProperty.getSecondNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        firstSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vectorNodeProperty.valueProperty().set(new Vector(Integer.parseInt(newValue), vectorNodeProperty.valueProperty().get().secondProperty().get())));
        secondSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vectorNodeProperty.valueProperty().set(new Vector(vectorNodeProperty.valueProperty().get().firstProperty().get(), Integer.parseInt(newValue))));
        firstVBox.getChildren().addAll(firstLabel, firstSpinner);
        middleVBox.getChildren().addAll(new Label(""), new Label("-"));
        secondVBox.getChildren().addAll(secondLabel, secondSpinner);
        hBox.getChildren().addAll(firstVBox, middleVBox, secondVBox);
        return hBox;
    }

}
