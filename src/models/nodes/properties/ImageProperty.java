package models.nodes.properties;

import models.nodes.properties.visitors.IPropertyVisitor;

import java.io.File;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class ImageProperty extends AProperty<File> {

    public ImageProperty(String name, File initialValue) {
        super(name, initialValue);
    }

    @Override
    public void accept(IPropertyVisitor visitor) {
        visitor.visit(this);
    }

}
