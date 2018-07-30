package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.MasterModel;
import models.nodes.hierarchy.ANodesHierarchyElement;
import models.nodes.hierarchy.NodeLeaf;
import models.nodes.hierarchy.NodesFolder;
import models.nodes.hierarchy.visitors.IElementVisitor;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class NodesHierarchyController extends AController implements IElementVisitor {

    @FXML
    private TreeView<ANodesHierarchyElement> tree_nodesHierarchy;

    private TreeItem<ANodesHierarchyElement> currentFolder;

    @Override
    public void initModel(MasterModel model) {
        tree_nodesHierarchy.setEditable(true);
        tree_nodesHierarchy.setCellFactory(param -> new ElementTreeCell());
        model.getProjectHierarchy().getRoot().accept(this);
        tree_nodesHierarchy.setRoot(currentFolder);
        tree_nodesHierarchy.getRoot().setExpanded(true);
    }

    @Override
    public void visit(NodesFolder folder) {
        TreeItem<ANodesHierarchyElement> currentFolder = new TreeItem<>(folder);
        currentFolder.setExpanded(true);
        if (this.currentFolder != null) {
            this.currentFolder.getChildren().add(currentFolder);
        }
        this.currentFolder = currentFolder;
        for (ANodesHierarchyElement nodesHierarchyElement : folder.getChildren()) {
            nodesHierarchyElement.accept(this);
            this.currentFolder = currentFolder;
        }
    }

    @Override
    public void visit(NodeLeaf nodeLeaf) {
        currentFolder.getChildren().add(new TreeItem<>(nodeLeaf));
    }

    private class ElementTreeCell extends TreeCell<ANodesHierarchyElement> implements IElementVisitor {

        @Override
        public void startEdit() {
            super.startEdit();
        }

        @Override
        protected void updateItem(ANodesHierarchyElement item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                item.accept(this);
            }
        }

        @Override
        public void visit(NodesFolder folder) {
            setText(folder.getName());
        }

        @Override
        public void visit(NodeLeaf nodeLeaf) {
            setText(nodeLeaf.getName());
            HBox graphic = new HBox();
            graphic.setAlignment(Pos.CENTER);
            graphic.setPadding(new Insets(4, 4, 4, 4));
            graphic.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(3), Insets.EMPTY)));
            Text nodeType = new Text(nodeLeaf.getNode().getClass().getSimpleName());
            graphic.getChildren().addAll(nodeType);
            setGraphic(graphic);
        }

    }

}
