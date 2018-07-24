package utilities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by stratosphr on 24/07/2018.
 */
public final class Vector4 {

    private final IntegerProperty first;
    private final IntegerProperty second;
    private final IntegerProperty third;
    private final IntegerProperty fourth;

    public Vector4(Integer first, Integer second, Integer third, Integer fourth) {
        this.first = new SimpleIntegerProperty(first);
        this.second = new SimpleIntegerProperty(second);
        this.third = new SimpleIntegerProperty(third);
        this.fourth = new SimpleIntegerProperty(fourth);
    }

    public IntegerProperty firstProperty() {
        return first;
    }

    public IntegerProperty secondProperty() {
        return second;
    }

    public IntegerProperty thirdProperty() {
        return third;
    }

    public IntegerProperty fourthProperty() {
        return fourth;
    }

}
