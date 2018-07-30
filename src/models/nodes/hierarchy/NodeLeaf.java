package models.nodes.hierarchy;

import models.nodes.ANode;
import models.nodes.hierarchy.visitors.IElementVisitor;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class NodeLeaf extends ANodesHierarchyElement {

    private final ANode node;

    public NodeLeaf(ANode node) {
        super(node.getName());
        this.node = node;
        nameProperty().bindBidirectional(node.nameProperty());
    }

    public ANode getNode() {
        return node;
    }

    @Override
    public void accept(IElementVisitor visitor) {
        visitor.visit(this);
    }

}
