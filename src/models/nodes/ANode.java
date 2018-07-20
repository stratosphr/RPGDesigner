package models.nodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.nodes.properties.ANodeProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stratosphr on 20/07/2018.
 */
public abstract class ANode {

    private final StringProperty name;
    private final List<ANodeProperty<?>> properties;

    protected ANode(String name, ANodeProperty<?>... properties) {
        this.name = new SimpleStringProperty(name);
        this.properties = Arrays.asList(properties);
    }

    public final StringProperty nameProperty() {
        return name;
    }

    public List<ANodeProperty<?>> getProperties() {
        return properties;
    }

    @Override
    public final String toString() {
        return nameProperty().get();
    }

}
