package models.nodes;

import javafx.beans.property.SimpleStringProperty;
import models.nodes.visitors.IVisitableNode;

/**
 * Created by stratosphr on 20/07/2018.
 */
public abstract class ANode implements IVisitableNode {

    private final SimpleStringProperty name;

    public ANode(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

}
