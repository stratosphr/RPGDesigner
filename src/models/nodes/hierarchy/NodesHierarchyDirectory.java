package models.nodes.hierarchy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class NodesHierarchyDirectory extends ANodesHierarchyElement {

    private final List<ANodesHierarchyElement> children;

    public NodesHierarchyDirectory(String name) {
        super(name, true);
        this.children = new ArrayList<>();
    }

    public List<ANodesHierarchyElement> getChildren() {
        return children;
    }

}
