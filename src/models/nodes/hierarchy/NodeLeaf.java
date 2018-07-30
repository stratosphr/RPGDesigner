package models.nodes.hierarchy;

import models.nodes.ANode;
import models.nodes.hierarchy.visitors.IElementVisitor;

/**
 * Created by stratosphr on 30/07/2018.
 */
public final class NodeLeaf extends ANodesHierarchyElement {

    public NodeLeaf(ANode node) {
        super(node.getName());
        nameProperty().bindBidirectional(node.nameProperty());
    }

    @Override
    public void accept(IElementVisitor visitor) {
        visitor.visit(this);
    }

}
