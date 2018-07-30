package models.nodes.hierarchy;

import models.nodes.hierarchy.visitors.IElementVisitor;

import java.util.ArrayList;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class NodesFolder extends ANodesHierarchyElement {

    private final ArrayList<ANodesHierarchyElement> children;

    public NodesFolder(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public ArrayList<ANodesHierarchyElement> getChildren() {
        return children;
    }

    @Override
    public void accept(IElementVisitor visitor) {
        visitor.visit(this);
    }

}
