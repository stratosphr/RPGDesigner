package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.TileSet;
import models.nodes.visitors.INodeVisitor;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class PreviewController extends AController {

    @FXML
    private ScrollPane scroll_preview;

    @Override
    public void initModel(MasterModel model) {
        model.previewedNodeProperty().addListener((observable, oldValue, newValue) -> preview(newValue));
    }

    private void preview(ANode node) {
        scroll_preview.setContent(new NodePreview(node));
    }

    private final class NodePreview extends Node implements INodeVisitor {

        private NodePreview(ANode node) {
            node.accept(this);
        }

        @Override
        public void visit(TileSet tileSet) {
        }

    }

}
