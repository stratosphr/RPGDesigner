package controllers.cellcontrollers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.properties.*;
import utilities.Vector2;
import utilities.Vector4;
import visitors.nodeproperties.IRegionEditableVisitor;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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
                if (fileNodeProperty.valueProperty().get() != null && fileNodeProperty.valueProperty().get().isDirectory()) {
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
    public HBox visit(Vector2Property vector2Property) {
        HBox hBox = new HBox();
        hBox.setAlignment(CENTER);
        hBox.setSpacing(5);
        VBox firstVBox = new VBox();
        VBox middleVBox = new VBox();
        VBox secondVBox = new VBox();
        Label firstLabel = new Label(vector2Property.getFirstNodeProperty().nameProperty().get());
        Spinner<Integer> firstSpinner = vector2Property.getFirstNodeProperty().accept(this);
        firstSpinner.setEditable(true);
        Label secondLabel = new Label(vector2Property.getSecondNodeProperty().nameProperty().get());
        Spinner<Integer> secondSpinner = vector2Property.getSecondNodeProperty().accept(this);
        secondSpinner.setEditable(true);
        firstSpinner.getEditor().textProperty().bindBidirectional(vector2Property.getFirstNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        secondSpinner.getEditor().textProperty().bindBidirectional(vector2Property.getSecondNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        firstSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vector2Property.valueProperty().set(new Vector2(Integer.parseInt(newValue), vector2Property.valueProperty().get().secondProperty().get())));
        secondSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vector2Property.valueProperty().set(new Vector2(vector2Property.valueProperty().get().firstProperty().get(), Integer.parseInt(newValue))));
        firstVBox.getChildren().addAll(firstLabel, firstSpinner);
        middleVBox.getChildren().addAll(new Label(""), new Label("-"));
        secondVBox.getChildren().addAll(secondLabel, secondSpinner);
        hBox.getChildren().addAll(firstVBox, middleVBox, secondVBox);
        return hBox;
    }

    @Override
    public HBox visit(Vector4Property vector4Property) {
        HBox hBox = new HBox();
        hBox.setAlignment(CENTER);
        hBox.setSpacing(5);
        VBox firstVBox = new VBox();
        VBox secondVBox = new VBox();
        VBox thirdVBox = new VBox();
        VBox fourthVBox = new VBox();
        Label firstLabel = new Label(vector4Property.getFirstNodeProperty().nameProperty().get());
        Spinner<Integer> firstSpinner = vector4Property.getFirstNodeProperty().accept(this);
        firstSpinner.setEditable(true);
        Label secondLabel = new Label(vector4Property.getSecondNodeProperty().nameProperty().get());
        Spinner<Integer> secondSpinner = vector4Property.getSecondNodeProperty().accept(this);
        secondSpinner.setEditable(true);
        Label thirdLabel = new Label(vector4Property.getThirdNodeProperty().nameProperty().get());
        Spinner<Integer> thirdSpinner = vector4Property.getThirdNodeProperty().accept(this);
        thirdSpinner.setEditable(true);
        Label fourthLabel = new Label(vector4Property.getFourthNodeProperty().nameProperty().get());
        Spinner<Integer> fourthSpinner = vector4Property.getFourthNodeProperty().accept(this);
        fourthSpinner.setEditable(true);
        firstSpinner.getEditor().textProperty().bindBidirectional(vector4Property.getFirstNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        secondSpinner.getEditor().textProperty().bindBidirectional(vector4Property.getSecondNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        thirdSpinner.getEditor().textProperty().bindBidirectional(vector4Property.getThirdNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        fourthSpinner.getEditor().textProperty().bindBidirectional(vector4Property.getFourthNodeProperty().valueProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        firstSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vector4Property.valueProperty().set(new Vector4(Integer.parseInt(newValue), vector4Property.valueProperty().get().secondProperty().get(), vector4Property.valueProperty().get().thirdProperty().get(), vector4Property.valueProperty().get().fourthProperty().get())));
        secondSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vector4Property.valueProperty().set(new Vector4(vector4Property.valueProperty().get().firstProperty().get(), Integer.parseInt(newValue), vector4Property.valueProperty().get().thirdProperty().get(), vector4Property.valueProperty().get().fourthProperty().get())));
        thirdSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vector4Property.valueProperty().set(new Vector4(vector4Property.valueProperty().get().firstProperty().get(), vector4Property.valueProperty().get().secondProperty().get(), Integer.parseInt(newValue), vector4Property.valueProperty().get().fourthProperty().get())));
        fourthSpinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> vector4Property.valueProperty().set(new Vector4(vector4Property.valueProperty().get().firstProperty().get(), vector4Property.valueProperty().get().secondProperty().get(), vector4Property.valueProperty().get().thirdProperty().get(), Integer.parseInt(newValue))));
        firstVBox.getChildren().addAll(firstLabel, firstSpinner);
        secondVBox.getChildren().addAll(secondLabel, secondSpinner);
        thirdVBox.getChildren().addAll(thirdLabel, thirdSpinner);
        fourthVBox.getChildren().addAll(fourthLabel, fourthSpinner);
        hBox.getChildren().addAll(firstVBox, new VBox(new Label(""), new Label("-")), secondVBox, new VBox(new Label(""), new Label("-")), thirdVBox, new VBox(new Label(""), new Label("-")), fourthVBox);
        return hBox;
    }

    @Override
    public <NodeType extends ANode> Region visit(NodeNodeProperty<NodeType> nodeNodeProperty) {
        ComboBox<ANode> comboBox = new ComboBox<>();
        List<ANode> nodes = model.getProjectNodes().stream().filter(node -> nodeNodeProperty.getNodeClass().isInstance(node)).collect(Collectors.toList());
        comboBox.setItems(FXCollections.observableArrayList(nodes));
        return comboBox;
    }

}
