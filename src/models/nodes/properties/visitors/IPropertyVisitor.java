package models.nodes.properties.visitors;

import models.nodes.properties.ImageProperty;
import models.nodes.properties.IntegerProperty;
import models.nodes.properties.TileSetProperty;
import models.nodes.properties.Vector2Property;

/**
 * Created by stratosphr on 31/07/2018.
 */
public interface IPropertyVisitor {

    void visit(Vector2Property vector2Property);

    void visit(TileSetProperty tileSetProperty);

    void visit(ImageProperty imageProperty);

    void visit(IntegerProperty integerProperty);

}
