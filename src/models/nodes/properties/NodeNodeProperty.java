package models.nodes.properties;

import javafx.scene.layout.Region;
import models.nodes.ANode;
import visitors.nodeproperties.IRegionEditableVisitor;

/**
 * Created by stratosphr on 25/07/2018.
 */
public final class NodeNodeProperty extends ANodeProperty<ANode> {

    private final Class<? extends ANode> nodeClass;

    public NodeNodeProperty(String name, ANode defaultValue, Class<? extends ANode> nodeClass) {
        super(name, defaultValue);
        this.nodeClass = nodeClass;
    }

    public Class<? extends ANode> getNodeClass() {
        return nodeClass;
    }

    @Override
    public Region accept(IRegionEditableVisitor visitor) {
        return visitor.visit(this);
    }

}
