package models.nodes.properties;

import javafx.scene.layout.HBox;
import utilities.Vector2;
import visitors.nodeproperties.IRegionEditableVisitor;

/**
 * Created by stratosphr on 23/07/2018.
 */
public final class Vector2Property extends ANodeProperty<Vector2> {

    private final IntegerNodeProperty firstNodeProperty;
    private final IntegerNodeProperty secondNodeProperty;

    public Vector2Property(String name, Vector2 defaultValue, IntegerNodeProperty firstNodeProperty, IntegerNodeProperty secondNodeProperty) {
        super(name, defaultValue);
        this.firstNodeProperty = firstNodeProperty;
        this.secondNodeProperty = secondNodeProperty;
    }

    public IntegerNodeProperty getFirstNodeProperty() {
        return firstNodeProperty;
    }

    public IntegerNodeProperty getSecondNodeProperty() {
        return secondNodeProperty;
    }

    @Override
    public HBox accept(IRegionEditableVisitor visitor) {
        return visitor.visit(this);
    }

}
