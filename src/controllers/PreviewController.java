package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.TileSet;
import visitors.preview.IPreviewer;

import java.net.MalformedURLException;

/**
 * Created by stratosphr on 20/07/2018.
 */
// TODO: the node should not be recomputed if this was already done before AND no property has changed
public final class PreviewController implements IPreviewer {

    @FXML
    private ScrollPane scroll_preview;

    public void initModel(MasterModel model) {
        model.previewedNodeProperty().addListener((previewedNodeObservable, oldPreviewedNode, newPreviewedNode) -> {
            previewNode(newPreviewedNode);
            newPreviewedNode.nameProperty().addListener((nameObservable, oldName, newName) -> previewNode(newPreviewedNode));
            newPreviewedNode.getProperties().forEach(nodeProperty -> nodeProperty.valueProperty().addListener((nodePropertyObservable, oldNodeProperty, newNodeProperty) -> previewNode(newPreviewedNode)));
        });
    }

    private void previewNode(ANode node) {
        scroll_preview.setContent(null);
        scroll_preview.setContent(node.accept(this));
    }

    @Override
    public Node visit(TileSet tileSet) {
        StackPane node = new StackPane();
        ImageView imageView = new ImageView();
        VBox grid = new VBox();
        for (Integer i = 0; i < tileSet.dimensionsProperty().get().secondProperty().get(); i++) {
            HBox hBox = new HBox();
            for (Integer j = 0; j < tileSet.dimensionsProperty().get().firstProperty().get(); j++) {
                Rectangle cell = new Rectangle(tileSet.tilesSizeProperty().get().firstProperty().get().doubleValue(), tileSet.tilesSizeProperty().get().secondProperty().get().doubleValue());
                cell.setStroke(new Color(0.8, 0.8, 0.8, 0.3));
                cell.setStrokeWidth(0.5);
                cell.setFill(Color.TRANSPARENT);
                hBox.getChildren().add(cell);
            }
            grid.getChildren().add(hBox);
        }
        if (tileSet.imageProperty().get() != null) {
            ProgressBar imageLoadingProgress = new ProgressBar(0);
            node.getChildren().add(imageLoadingProgress);
            try {
                Image image = new Image(tileSet.imageProperty().get().toURI().toURL().toString(), true);
                imageLoadingProgress.progressProperty().bind(image.progressProperty());
                imageLoadingProgress.progressProperty().addListener((observable, oldValue, newValue) -> {
                    if (((Double) newValue) == 1) {
                        node.getChildren().remove(imageLoadingProgress);
                    }
                });
                imageView.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        node.getChildren().addAll(imageView, grid);
        return node;
    }

}
