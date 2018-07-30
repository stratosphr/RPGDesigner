package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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

    private MasterModel model;

    @Override
    public void initModel(MasterModel model) {
        this.model = model;
        tree_nodesHierarchy.setEditable(true);
        tree_nodesHierarchy.setCellFactory(param -> new ElementTreeCell(model));
        updateTree();
    }

    private void updateTree() {
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

        private final MasterModel model;

        private ElementTreeCell(MasterModel model) {
            this.model = model;
        }

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
                setContextMenu(new ContextMenu());
                if (item.getParent() != null) {
                    MenuItem renameItem = new MenuItem("Rename...");
                    renameItem.setAccelerator(new KeyCodeCombination(KeyCode.F2));
                    renameItem.setOnAction(event -> startEdit());
                    MenuItem deleteItem = new MenuItem("Delete");
                    deleteItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
                    deleteItem.setOnAction(event -> {
                        item.getParent().getChildren().remove(item);
                        updateTree();
                    });
                    getContextMenu().getItems().addAll(renameItem, deleteItem);
                }
                item.accept(this);
            }
        }

        @Override
        public void visit(NodesFolder folder) {
            setEditable(false);
            Menu addMenu = new Menu("Add...");
            MenuItem addFolderItem = new MenuItem("Folder...");
            addFolderItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
            addFolderItem.setOnAction(event -> {
                folder.addChild(new NodesFolder(""));
                updateTree();
            });
            MenuItem addNodeItem = new MenuItem("Node...");
            addNodeItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
            addMenu.getItems().addAll(addFolderItem, new SeparatorMenuItem(), addNodeItem);
            getContextMenu().getItems().add(0, addMenu);
            setText(folder.getName());
        }

        @Override
        public void visit(NodeLeaf nodeLeaf) {
            setEditable(true);
            setText(nodeLeaf.getName());
            HBox graphic = new HBox();
            graphic.setAlignment(Pos.CENTER);
            graphic.setPadding(new Insets(4, 4, 4, 4));
            graphic.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(3), Insets.EMPTY)));
            Text nodeType = new Text(nodeLeaf.getNode().getClass().getSimpleName());
            graphic.getChildren().add(nodeType);
            setGraphic(graphic);
        }

    }

}
