package models.nodes.properties;

import models.nodes.properties.visitors.IPropertyVisitor;
import utilities.Vector2;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class Vector2Property extends AProperty<Vector2> {

    private final IntegerProperty firstProperty;
    private final IntegerProperty secondProperty;

    public Vector2Property(String name, Vector2 initialValue, IntegerProperty firstProperty, IntegerProperty secondProperty) {
        super(name, initialValue);
        this.firstProperty = firstProperty;
        this.secondProperty = secondProperty;
        firstProperty.addListener((observable, oldValue, newValue) -> set(new Vector2(newValue, get().getSecond())));
        secondProperty.addListener((observable, oldValue, newValue) -> set(new Vector2(get().getFirst(), newValue)));
    }

    public IntegerProperty getFirstProperty() {
        return firstProperty;
    }

    public IntegerProperty getSecondProperty() {
        return secondProperty;
    }

    @Override
    public void accept(IPropertyVisitor visitor) {
        visitor.visit(this);
    }

}
