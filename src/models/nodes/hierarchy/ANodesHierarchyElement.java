package models.nodes.hierarchy;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.nodes.ANode;

import java.util.List;

/**
 * Created by stratosphr on 22/07/2018.
 */
public abstract class ANodesHierarchyElement {

    private final StringProperty name;
    private boolean isDirectory;

    public ANodesHierarchyElement(String name, boolean isDirectory) {
        this.name = new SimpleStringProperty(name);
        this.isDirectory = isDirectory;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public abstract List<ANode> getAllNodes();

}
