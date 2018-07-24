package models.nodes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import models.nodes.properties.FileNodeProperty;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.Vector2Property;
import utilities.Vector2;
import visitors.preview.IPreviewer;

import java.io.File;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by stratosphr on 22/07/2018.
 */
// TODO : Set default image back to null
public final class TileSet extends ANode {

    private final ObjectProperty<File> image;
    private final ObjectProperty<Vector2> tilesSize;

    public TileSet(String name) {
        super(name);
        this.image = new SimpleObjectProperty<>();
        this.tilesSize = new SimpleObjectProperty<>();
        addProperty(new FileNodeProperty("Image", new File("src/views/resources/hero.png")), image);
        addProperty(new Vector2Property("Tiles size", new Vector2(42, 42), new IntegerNodeProperty("Width", 42, 1, MAX_VALUE), new IntegerNodeProperty("Height", 42, 1, MAX_VALUE)), tilesSize);
    }

    public ObjectProperty<File> imageProperty() {
        return image;
    }

    public ObjectProperty<Vector2> tilesSizeProperty() {
        return tilesSize;
    }

    @Override
    public Node accept(IPreviewer previewer) {
        return previewer.visit(this);
    }

}
