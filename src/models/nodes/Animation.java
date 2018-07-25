package models.nodes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import models.nodes.properties.NodeNodeProperty;
import visitors.preview.IPreviewer;

/**
 * Created by stratosphr on 25/07/2018.
 */
public final class Animation extends ANode {

    private final ObjectProperty<TileSet> tileSet;

    public Animation(String name) {
        super(name);
        this.tileSet = new SimpleObjectProperty<>();
        addProperty(new NodeNodeProperty<>("TileSet", new TileSet(""), TileSet.class), tileSet);
    }

    public ObjectProperty<TileSet> tileSetProperty() {
        return tileSet;
    }

    @Override
    public Node accept(IPreviewer previewer) {
        return previewer.visit(this);
    }

}
