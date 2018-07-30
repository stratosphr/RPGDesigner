package models.nodes.hierarchy;

import javafx.beans.property.SimpleStringProperty;
import models.nodes.hierarchy.visitors.IVisitableElement;

/**
 * Created by stratosphr on 30/07/2018.
 */
public abstract class ANodesHierarchyElement implements IVisitableElement {

    private SimpleStringProperty name;
    private NodesFolder parent;

    public ANodesHierarchyElement(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public final SimpleStringProperty nameProperty() {
        return name;
    }

    public final String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public NodesFolder getParent() {
        return parent;
    }

    protected void setParent(NodesFolder parent) {
        this.parent = parent;
    }

}
