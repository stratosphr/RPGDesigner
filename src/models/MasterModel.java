package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;
import models.nodes.ANode;
import models.nodes.Animation;
import models.nodes.TileSet;
import models.nodes.hierarchy.NodesHierarchyDirectory;
import models.nodes.hierarchy.NodesHierarchyLeaf;

import java.util.List;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MasterModel {

    private final Stage primaryStage;
    private final ObjectProperty<ANode> previewedNode;
    private final SimpleObjectProperty<NodesHierarchyDirectory> projectHierarchy;

    public MasterModel(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.previewedNode = new SimpleObjectProperty<>(null);
        this.projectHierarchy = new SimpleObjectProperty<>(createProjectHierarchy());
    }

    private NodesHierarchyDirectory createProjectHierarchy() {
        NodesHierarchyDirectory root = new NodesHierarchyDirectory("Project");
        NodesHierarchyDirectory subFolder = new NodesHierarchyDirectory("SubFolder");
        NodesHierarchyDirectory subSubFolder = new NodesHierarchyDirectory("SubSubFolder");
        root.getChildren().add(new NodesHierarchyLeaf(new TileSet("node1")));
        root.getChildren().add(new NodesHierarchyLeaf(new Animation("node2")));
        subFolder.getChildren().add(new NodesHierarchyLeaf(new TileSet("node3")));
        subSubFolder.getChildren().add(new NodesHierarchyLeaf(new TileSet("node4")));
        subFolder.getChildren().add(subSubFolder);
        root.getChildren().add(subFolder);
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObjectProperty<ANode> previewedNodeProperty() {
        return previewedNode;
    }

    public ObjectProperty<NodesHierarchyDirectory> projectHierarchyProperty() {
        return projectHierarchy;
    }

    public List<ANode> getProjectNodes() {
        return projectHierarchyProperty().get().getAllNodes();
    }

}
