package models.nodes.properties;

import javafx.scene.layout.Region;
import models.nodes.ANode;
import visitors.nodeproperties.IRegionEditableVisitor;

/**
 * Created by stratosphr on 25/07/2018.
 */
public final class NodeNodeProperty<Node extends ANode> extends ANodeProperty<Node> {

    private final Class<Node> nodeClass;

    public NodeNodeProperty(String name, Node defaultValue, Class<Node> nodeClass) {
        super(name, defaultValue);
        this.nodeClass = nodeClass;
    }

    public Class<Node> getNodeClass() {
        return nodeClass;
    }

    @Override
    public Region accept(IRegionEditableVisitor visitor) {
        return visitor.visit(this);
    }

}
