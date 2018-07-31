package models.nodes;

import javafx.beans.property.SimpleStringProperty;
import models.nodes.properties.AProperty;
import models.nodes.visitors.IVisitableNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stratosphr on 20/07/2018.
 */
public abstract class ANode implements IVisitableNode {

    private final SimpleStringProperty name;
    private final List<AProperty<?>> properties;

    public ANode(String name) {
        this.name = new SimpleStringProperty(name);
        this.properties = new ArrayList<>();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    protected <Property extends AProperty<?>> Property addProperty(Property property) {
        this.properties.add(property);
        return property;
    }

    public List<AProperty<?>> getProperties() {
        return properties;
    }

    @Override
    public final String toString() {
        return getName() + "[" + getProperties().stream().map(AProperty::toString).collect(Collectors.joining(", ")) + "]";
    }

}
