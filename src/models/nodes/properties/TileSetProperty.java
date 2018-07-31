package models.nodes.properties;

import models.nodes.TileSet;
import models.nodes.properties.visitors.IPropertyVisitor;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class TileSetProperty extends AProperty<TileSet> {

    public TileSetProperty(String name, TileSet initialValue) {
        super(name, initialValue);
    }

    @Override
    public void accept(IPropertyVisitor visitor) {
        visitor.visit(this);
    }

}
