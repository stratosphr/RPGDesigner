package models.nodes;

import models.nodes.properties.ImageProperty;
import models.nodes.properties.IntegerProperty;
import models.nodes.properties.Vector2Property;
import models.nodes.visitors.INodeVisitor;
import utilities.Vector2;

import java.io.File;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class TileSet extends ANode {

    private final ImageProperty image;
    private final Vector2Property dimensions;

    public TileSet(String name) {
        super(name);
        this.image = addProperty(new ImageProperty("Image", null));
        this.dimensions = addProperty(new Vector2Property("Dimensions", new Vector2(1, 1), new IntegerProperty("Columns", 1, 1, Integer.MAX_VALUE, 1), new IntegerProperty("Rows", 1, 1, Integer.MAX_VALUE, 1)));
    }

    public ImageProperty imageProperty() {
        return image;
    }

    public File getImage() {
        return image.get();
    }

    public Vector2Property dimensionsProperty() {
        return dimensions;
    }

    public Vector2 getDimensions() {
        return dimensions.get();
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }

}
