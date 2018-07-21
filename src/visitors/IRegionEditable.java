package visitors;

import javafx.scene.layout.Region;

/**
 * Created by stratosphr on 21/07/2018.
 */
public interface IRegionEditable {

    Region accept(IRegionEditableVisitor visitor);

}
