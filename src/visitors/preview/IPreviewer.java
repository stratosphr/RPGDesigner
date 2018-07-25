package visitors.preview;

import javafx.scene.Node;
import models.nodes.Animation;
import models.nodes.TileSet;

/**
 * Created by stratosphr on 22/07/2018.
 */
public interface IPreviewer {

    Node visit(TileSet tileSet);

    Node visit(Animation animation);

}
