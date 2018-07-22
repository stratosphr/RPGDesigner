package models.nodes.hierarchy;

import models.nodes.ANode;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class NodesHierarchyLeaf extends ANodesHierarchyElement {

    private final ANode node;

    public NodesHierarchyLeaf(ANode node) {
        super(node.nameProperty().get(), false);
        this.node = node;
        nameProperty().bindBidirectional(node.nameProperty());
    }

    public ANode getNode() {
        return node;
    }

}
