package models.nodes.hierarchy;

import models.nodes.ANode;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<ANode> getAllNodes() {
        return Collections.singletonList(node);
    }

}
