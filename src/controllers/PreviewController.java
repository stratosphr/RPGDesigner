package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
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
        model.previewedNodeProperty().addListener((observable, oldValue, previewedNode) -> {
            previewedNode.getProperties().forEach(property -> property.addListener((observable1, oldValue1, newValue1) -> {
                preview(previewedNode);
            }));
            preview(previewedNode);
        });
    }

    private void preview(ANode node) {
        scroll_preview.setContent(new NodePreview(node));
    }

    private final class NodePreview extends Pane implements INodeVisitor {

        private NodePreview(ANode node) {
            node.accept(this);
        }

        @Override
        public void visit(TileSet tileSet) {
            getChildren().add(new Label(tileSet.toString()));
        }

    }

}
