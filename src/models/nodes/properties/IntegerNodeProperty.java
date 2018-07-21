package models.nodes.properties;

import javafx.scene.layout.Region;
import visitors.IRegionEditableVisitor;

/**
 * Created by stratosphr on 21/07/2018.
 */
public final class IntegerNodeProperty extends ANodeProperty<Integer> {

    private final Integer min;
    private final Integer max;

    public IntegerNodeProperty(String name, Integer defaultValue, Integer min, Integer max) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    @Override
    public Region accept(IRegionEditableVisitor visitor) {
        return visitor.visit(this);
    }

}
