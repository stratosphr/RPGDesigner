package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;
import models.nodes.ANode;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MasterModel {

    private final Stage primaryStage;
    private final ObjectProperty<ANode> previewedNode;

    public MasterModel(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.previewedNode = new SimpleObjectProperty<>(new ANode("test") {
        });
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObjectProperty<ANode> previewedNodeProperty() {
        return previewedNode;
    }

}
