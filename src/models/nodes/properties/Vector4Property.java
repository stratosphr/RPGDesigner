package models.nodes.properties;

import javafx.scene.layout.HBox;
import utilities.Vector4;
import visitors.nodeproperties.IRegionEditableVisitor;

/**
 * Created by stratosphr on 24/07/2018.
 */
public class Vector4Property extends ANodeProperty<Vector4> {

    private final IntegerNodeProperty firstNodeProperty;
    private final IntegerNodeProperty secondNodeProperty;
    private final IntegerNodeProperty thirdNodeProperty;
    private final IntegerNodeProperty fourthNodeProperty;

    public Vector4Property(String name, Vector4 defaultValue, IntegerNodeProperty firstNodeProperty, IntegerNodeProperty secondNodeProperty, IntegerNodeProperty thirdNodeProperty, IntegerNodeProperty fourthNodeProperty) {
        super(name, defaultValue);
        this.firstNodeProperty = firstNodeProperty;
        this.secondNodeProperty = secondNodeProperty;
        this.thirdNodeProperty = thirdNodeProperty;
        this.fourthNodeProperty = fourthNodeProperty;
    }

    public IntegerNodeProperty getFirstNodeProperty() {
        return firstNodeProperty;
    }

    public IntegerNodeProperty getSecondNodeProperty() {
        return secondNodeProperty;
    }

    public IntegerNodeProperty getThirdNodeProperty() {
        return thirdNodeProperty;
    }

    public IntegerNodeProperty getFourthNodeProperty() {
        return fourthNodeProperty;
    }

    @Override
    public HBox accept(IRegionEditableVisitor visitor) {
        return visitor.visit(this);
    }

}
