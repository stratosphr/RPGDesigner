package visitors;

import javafx.scene.layout.Region;
import models.nodes.properties.FileNodeProperty;
import models.nodes.properties.IntegerNodeProperty;
import models.nodes.properties.StringNodeProperty;

/**
 * Created by stratosphr on 21/07/2018.
 */
public interface IRegionEditableVisitor {

    Region visit(IntegerNodeProperty integerNodeProperty);

    Region visit(StringNodeProperty stringNodeProperty);

    Region visit(FileNodeProperty fileNodeProperty);

}
