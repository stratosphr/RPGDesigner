package utilities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class Vector2 {

    private final ObjectProperty<Integer> first;
    private final ObjectProperty<Integer> second;

    public Vector2(Integer first, Integer second) {
        this.first = new SimpleObjectProperty<>(first);
        this.second = new SimpleObjectProperty<>(second);
    }

    public ObjectProperty<Integer> firstProperty() {
        return first;
    }

    public ObjectProperty<Integer> secondProperty() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + firstProperty().get() + ", " + secondProperty().get() + ")";
    }

}
