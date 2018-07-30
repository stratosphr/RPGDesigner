package models;

import javafx.beans.property.SimpleObjectProperty;
import models.nodes.ANode;
import models.nodes.TileSet;
import models.nodes.hierarchy.NodeLeaf;
import models.nodes.hierarchy.NodesFolder;
import models.nodes.hierarchy.NodesHierarchy;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MasterModel {

    private final NodesHierarchy projectHierarchy;
    private SimpleObjectProperty<ANode> previewedNode;

    public MasterModel() {
        this.previewedNode = new SimpleObjectProperty<>(null);
        this.projectHierarchy = createProjectHierarchy();
    }

    private NodesHierarchy createProjectHierarchy() {
        NodesHierarchy nodesHierarchy = new NodesHierarchy();
        NodesFolder root = new NodesFolder("Project");
        NodesFolder folder1 = new NodesFolder("Folder1");
        NodesFolder folder2 = new NodesFolder("Folder2");
        NodesFolder folder3 = new NodesFolder("Folder3");
        folder1.getChildren().add(new NodeLeaf(new TileSet("tileset1_0")));
        folder1.getChildren().add(folder3);
        folder1.getChildren().add(new NodeLeaf(new TileSet("tileset1_1")));
        folder2.getChildren().add(new NodeLeaf(new TileSet("tileset2_0")));
        folder3.getChildren().add(new NodeLeaf(new TileSet("tileset3_0")));
        folder3.getChildren().add(new NodeLeaf(new TileSet("tileset3_1")));
        root.getChildren().add(folder1);
        root.getChildren().add(folder2);
        nodesHierarchy.setRoot(root);
        return nodesHierarchy;
    }

    public ANode getPreviewedNode() {
        return previewedNode.get();
    }

    public void setPreviewedNode(ANode previewedNode) {
        this.previewedNode.set(previewedNode);
    }

    public SimpleObjectProperty<ANode> previewedNodeProperty() {
        return previewedNode;
    }

    public NodesHierarchy getProjectHierarchy() {
        return projectHierarchy;
    }

}
