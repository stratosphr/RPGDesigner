package models.nodes.properties;

import models.nodes.properties.visitors.IPropertyVisitor;

/**
 * Created by stratosphr on 31/07/2018.
 */
public final class IntegerProperty extends AProperty<Integer> {

    private final int min;
    private final int max;
    private final int initialValue;
    private final int amountToStepBy;

    public IntegerProperty(String name, int initialValue, int min, int max, int amountToStepBy) {
        super(name, initialValue);
        this.min = min;
        this.max = max;
        this.initialValue = initialValue;
        this.amountToStepBy = amountToStepBy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public int getAmountToStepBy() {
        return amountToStepBy;
    }

    public void accept(IPropertyVisitor visitor) {
        visitor.visit(this);
    }

}
