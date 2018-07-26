package models.nodes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.NodeNodeProperty;
import models.nodes.properties.Vector2Property;
import utilities.Vector2;
import visitors.preview.IPreviewer;

/**
 * Created by stratosphr on 25/07/2018.
 */
public final class Animation extends ANode {

    private final ObjectProperty<ANode> tileSet;
    private final ObjectProperty<Vector2> frames;

    public Animation(String name) {
        super(name);
        this.tileSet = new SimpleObjectProperty<>();
        this.frames = new SimpleObjectProperty<>();
        addProperty(new NodeNodeProperty("TileSet", null, TileSet.class), tileSet);
        addProperty(new Vector2Property("Frames", new Vector2(0, 0), new IntegerNodeProperty("First", 0, 0, Integer.MAX_VALUE), new IntegerNodeProperty("Last", 0, 0, Integer.MAX_VALUE)), frames);
    }

    public ObjectProperty<ANode> tileSetProperty() {
        return tileSet;
    }

    public ObjectProperty<Vector2> framesProperty() {
        return frames;
    }

    @Override
    public Node accept(IPreviewer previewer) {
        return previewer.visit(this);
    }

}
