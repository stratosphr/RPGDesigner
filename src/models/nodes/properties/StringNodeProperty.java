package models.nodes.properties;

import javafx.scene.layout.Region;
import visitors.IRegionEditableVisitor;

/**
 * Created by stratosphr on 21/07/2018.
 */
public final class StringNodeProperty extends ANodeProperty<String> {

    public StringNodeProperty(String name, String defaultValue) {
        super(name, defaultValue);
    }

    @Override
    public Region accept(IRegionEditableVisitor visitor) {
        return visitor.visit(this);
    }

}
