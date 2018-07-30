package models.nodes.hierarchy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.nodes.hierarchy.visitors.IElementVisitor;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class NodesFolder extends ANodesHierarchyElement {

    private final ObservableList<ANodesHierarchyElement> children;

    public NodesFolder(String name) {
        super(name);
        this.children = FXCollections.observableArrayList();
    }

    public void addChild(ANodesHierarchyElement child) {
        children.add(child);
        child.setParent(this);
    }

    public ObservableList<ANodesHierarchyElement> getChildren() {
        return children;
    }

    @Override
    public void accept(IElementVisitor visitor) {
        visitor.visit(this);
    }

}
