package models.nodes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import models.nodes.properties.FileNodeProperty;
import visitors.preview.IPreviewer;

import java.io.File;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class TileSet extends ANode {

    private final ObjectProperty<File> image;

    public TileSet(String name) {
        super(name);
        this.image = new SimpleObjectProperty<>();
        addProperty(new FileNodeProperty("Image", null), image);
    }

    public ObjectProperty<File> imageProperty() {
        return image;
    }

    @Override
    public Node accept(IPreviewer previewer) {
        return previewer.visit(this);
    }

}
