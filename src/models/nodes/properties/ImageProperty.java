package models.nodes.properties;

import java.io.File;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class ImageProperty extends AProperty<File> {

    public ImageProperty() {
    }

    public ImageProperty(File initialValue) {
        super(initialValue);
    }

    public ImageProperty(Object bean, String name) {
        super(bean, name);
    }

    public ImageProperty(Object bean, String name, File initialValue) {
        super(bean, name, initialValue);
    }

}
