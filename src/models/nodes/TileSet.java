package models.nodes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import models.nodes.properties.FileNodeProperty;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.VectorNodeProperty;
import utilities.Vector;
import visitors.preview.IPreviewer;

import java.io.File;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class TileSet extends ANode {

    private final ObjectProperty<File> image;
    private final ObjectProperty<Vector> dimensions;
    private final ObjectProperty<Vector> tilesSize;

    public TileSet(String name) {
        super(name);
        this.image = new SimpleObjectProperty<>();
        this.dimensions = new SimpleObjectProperty<>();
        this.tilesSize = new SimpleObjectProperty<>();
        addProperty(new FileNodeProperty("Image", null), image);
        addProperty(new VectorNodeProperty("Dimensions", new Vector(50, 50), new IntegerNodeProperty("Columns", 50, 1, MAX_VALUE), new IntegerNodeProperty("Rows", 50, 1, MAX_VALUE)), dimensions);
        addProperty(new VectorNodeProperty("Tiles size", new Vector(32, 32), new IntegerNodeProperty("Width", 32, 1, MAX_VALUE), new IntegerNodeProperty("Height", 32, 1, MAX_VALUE)), tilesSize);
    }

    public ObjectProperty<File> imageProperty() {
        return image;
    }

    public ObjectProperty<Vector> dimensionsProperty() {
        return dimensions;
    }

    public ObjectProperty<Vector> tilesSizeProperty() {
        return tilesSize;
    }

    @Override
    public Node accept(IPreviewer previewer) {
        return previewer.visit(this);
    }

}
