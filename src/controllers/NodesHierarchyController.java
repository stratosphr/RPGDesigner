package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
        tree_nodesHierarchy.setCellFactory(param -> new NodeTreeCell());
        model.getProjectHierarchy().getRoot().accept(this);
        tree_nodesHierarchy.setRoot(currentFolder);
    }

    @Override
    public void visit(NodesFolder folder) {
        TreeItem<ANodesHierarchyElement> currentFolder = new TreeItem<>(folder);
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

    private class NodeTreeCell extends TreeCell<ANodesHierarchyElement> implements IElementVisitor {

        @Override
        protected void updateItem(ANodesHierarchyElement item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.getName());
            }
        }

        @Override
        public void visit(NodesFolder folder) {
        }

        @Override
        public void visit(NodeLeaf nodeLeaf) {
        }

    }

}
