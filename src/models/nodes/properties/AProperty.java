package models.nodes.properties;

import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by stratosphr on 20/07/2018.
 */
public abstract class AProperty<Value> extends SimpleObjectProperty<Value> {

    public AProperty() {
    }

    public AProperty(Value initialValue) {
        super(initialValue);
    }

    public AProperty(Object bean, String name) {
        super(bean, name);
    }

    public AProperty(Object bean, String name, Value initialValue) {
        super(bean, name, initialValue);
    }

}
