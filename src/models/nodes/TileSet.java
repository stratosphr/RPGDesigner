package models.nodes;

import models.nodes.properties.ImageProperty;
import models.nodes.properties.Vector2Property;
import models.nodes.visitors.INodeVisitor;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class TileSet extends ANode {

    private final ImageProperty image;
    private final Vector2Property tilesSize;

    public TileSet(String name) {
        super(name);
        this.image = new ImageProperty();
        this.tilesSize = new Vector2Property();
    }

    public ImageProperty imageProperty() {
        return image;
    }

    public Vector2Property tilesSizeProperty() {
        return tilesSize;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }

}
