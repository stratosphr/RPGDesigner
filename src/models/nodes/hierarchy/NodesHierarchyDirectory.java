package models.nodes.hierarchy;

import models.nodes.ANode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ANode> getAllNodes() {
        return getChildren().stream().map(ANodesHierarchyElement::getAllNodes).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
