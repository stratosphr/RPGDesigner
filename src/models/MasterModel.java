package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import models.nodes.ANode;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MasterModel {

    private final ObjectProperty<ANode> previewedNode;

    public MasterModel() {
        this.previewedNode = new SimpleObjectProperty<>(new ANode("test") {
        });
    }

    public ObjectProperty<ANode> previewedNodeProperty() {
        return previewedNode;
    }

}
