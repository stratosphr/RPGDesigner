package visitors.preview;

import javafx.scene.Node;

/**
 * Created by stratosphr on 22/07/2018.
 */
public interface IPreviewable {

    Node accept(IPreviewer previewer);

}
