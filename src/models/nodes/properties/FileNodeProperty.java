package models.nodes.properties;

import javafx.scene.layout.Region;
import visitors.nodeproperties.IRegionEditableVisitor;

import java.io.File;

/**
 * Created by stratosphr on 21/07/2018.
 */
public final class FileNodeProperty extends ANodeProperty<File> {

    public FileNodeProperty(String name, File defaultFile) {
        super(name, defaultFile);
    }

    @Override
    public Region accept(IRegionEditableVisitor visitor) {
        return visitor.visit(this);
    }

}
