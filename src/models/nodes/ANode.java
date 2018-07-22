package models.nodes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.nodes.properties.ANodeProperty;
import visitors.preview.IPreviewable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stratosphr on 20/07/2018.
 */
public abstract class ANode implements IPreviewable {

    private final StringProperty name;
    private final List<ANodeProperty<?>> properties;

    protected ANode(String name) {
        this.name = new SimpleStringProperty(name);
        this.properties = new ArrayList<>();
    }

    public final StringProperty nameProperty() {
        return name;
    }

    public final List<ANodeProperty<?>> getProperties() {
        return properties;
    }

    protected final <Value> void addProperty(ANodeProperty<Value> nodeProperty, ObjectProperty<Value> property) {
        properties.add(nodeProperty);
        property.bindBidirectional(nodeProperty.valueProperty());
    }

    @Override
    public final String toString() {
        return nameProperty().get() + " - [" + getProperties().stream().map(property -> property.nameProperty().get() + "=" + property.valueProperty().get()).collect(Collectors.joining(", ")) + "]";
    }

}
