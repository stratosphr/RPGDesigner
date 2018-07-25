package visitors.nodeproperties;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import models.nodes.ANode;
import models.nodes.properties.*;

/**
 * Created by stratosphr on 21/07/2018.
 */
public interface IRegionEditableVisitor {

    Spinner<Integer> visit(IntegerNodeProperty integerNodeProperty);

    TextField visit(StringNodeProperty stringNodeProperty);

    HBox visit(FileNodeProperty fileNodeProperty);

    HBox visit(Vector2Property vector2Property);

    HBox visit(Vector4Property vector4Property);

    <Node extends ANode> Region visit(NodeNodeProperty<Node> nodeNodeNodeProperty);

}
