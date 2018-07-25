package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import models.MasterModel;
import models.nodes.ANode;
import models.nodes.Animation;
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
        model.previewedNodeProperty().addListener((previewedNodeObservable, oldPreviewedNode, newPreviewedNode) -> previewNode(newPreviewedNode));
    }

    private void previewNode(ANode node) {
        scroll_preview.setContent(null);
        scroll_preview.setContent(node.accept(this));
    }

    @Override
    public GridPane visit(TileSet tileSet) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        try {
            Image image = new Image(tileSet.imageProperty().get().toURI().toURL().toString());
            tileSet.tilesSizeProperty().addListener((observable, oldValue, newValue) -> loadGrid(tileSet, grid, image));
            tileSet.imageProperty().addListener((observable, oldValue, newValue) -> previewNode(tileSet));
            loadGrid(tileSet, grid, image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return grid;
    }

    @Override
    public Node visit(Animation animation) {
        return animation.tileSetProperty().get().accept(this);
    }

    private void loadGrid(TileSet tileSet, GridPane grid, Image image) {
        grid.getChildren().clear();
        double columns = image.getWidth() / tileSet.tilesSizeProperty().get().firstProperty().get();
        double rows = image.getHeight() / tileSet.tilesSizeProperty().get().secondProperty().get();
        for (Integer i = 0; i < columns; i++) {
            for (Integer j = 0; j < rows; j++) {
                StackPane imageBorder = new StackPane();
                imageBorder.setStyle("-fx-border-color: red; -fx-border-width: 1;");
                ImageView imageView = new ImageView(image);
                double tilesWidth = tileSet.tilesSizeProperty().get().firstProperty().get();
                double tilesHeight = tileSet.tilesSizeProperty().get().secondProperty().get();
                imageView.setViewport(new Rectangle2D(i * tilesWidth, j * tilesHeight, tilesWidth, tilesHeight));
                imageBorder.getChildren().add(imageView);
                grid.add(imageBorder, i, j);
            }
        }
    }

}
