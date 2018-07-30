package models.nodes.properties;

import models.nodes.TileSet;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class TileSetProperty extends AProperty<TileSet> {

    public TileSetProperty() {
    }

    public TileSetProperty(TileSet initialValue) {
        super(initialValue);
    }

    public TileSetProperty(Object bean, String name) {
        super(bean, name);
    }

    public TileSetProperty(Object bean, String name, TileSet initialValue) {
        super(bean, name, initialValue);
    }

}
