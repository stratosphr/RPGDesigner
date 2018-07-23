package visitors.nodeproperties;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import models.nodes.properties.FileNodeProperty;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.StringNodeProperty;
import models.nodes.properties.VectorNodeProperty;

/**
 * Created by stratosphr on 21/07/2018.
 */
public interface IRegionEditableVisitor {

    Spinner<Integer> visit(IntegerNodeProperty integerNodeProperty);

    TextField visit(StringNodeProperty stringNodeProperty);

    HBox visit(FileNodeProperty fileNodeProperty);

    HBox visit(VectorNodeProperty vectorNodeProperty);

}
