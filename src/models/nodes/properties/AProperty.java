package models.nodes.properties;

import javafx.beans.property.SimpleObjectProperty;
import models.nodes.properties.visitors.IVisitableProperty;

/**
 * Created by stratosphr on 20/07/2018.
 */
public abstract class AProperty<Value> extends SimpleObjectProperty<Value> implements IVisitableProperty {

    private final String name;

    protected AProperty(String name, Value initialValue) {
        super(initialValue);
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return getName() + "=" + get();
    }

}
