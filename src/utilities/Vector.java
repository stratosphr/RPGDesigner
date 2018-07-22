package utilities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class Vector {

    private final ObjectProperty<Integer> first;
    private final ObjectProperty<Integer> second;

    public Vector(Integer first, Integer second) {
        this.first = new SimpleObjectProperty<>(first);
        this.second = new SimpleObjectProperty<>(second);
    }

    public ObjectProperty<Integer> firstProperty() {
        return first;
    }

    public ObjectProperty<Integer> secondProperty() {
        return second;
    }

}
