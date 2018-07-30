package models.nodes.properties;

import javafx.beans.property.SimpleObjectProperty;
import utilities.Vector2;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class Vector2Property extends SimpleObjectProperty<Vector2> {

    public Vector2Property() {
    }

    public Vector2Property(Vector2 initialValue) {
        super(initialValue);
    }

    public Vector2Property(Object bean, String name) {
        super(bean, name);
    }

    public Vector2Property(Object bean, String name, Vector2 initialValue) {
        super(bean, name, initialValue);
    }

}
