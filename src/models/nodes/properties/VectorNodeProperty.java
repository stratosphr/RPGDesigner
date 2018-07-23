package models.nodes.properties;

import javafx.scene.layout.HBox;
import utilities.Vector;
import visitors.nodeproperties.IRegionEditableVisitor;

/**
 * Created by stratosphr on 23/07/2018.
 */
public final class VectorNodeProperty extends ANodeProperty<Vector> {

    private final IntegerNodeProperty firstNodeProperty;
    private final IntegerNodeProperty secondNodeProperty;

    public VectorNodeProperty(String name, Vector defaultValue, IntegerNodeProperty firstNodeProperty, IntegerNodeProperty secondNodeProperty) {
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
