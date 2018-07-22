package models.nodes.properties;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import visitors.nodeproperties.IRegionEditable;

/**
 * Created by stratosphr on 20/07/2018.
 */
public abstract class ANodeProperty<Value> implements IRegionEditable {

    private final StringProperty name;
    private final SimpleObjectProperty<Value> value;

    protected ANodeProperty(String name, Value defaultValue) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleObjectProperty<>(defaultValue);
    }

    public final StringProperty nameProperty() {
        return name;
    }

    public final SimpleObjectProperty<Value> valueProperty() {
        return value;
    }

}
