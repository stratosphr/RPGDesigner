package models.nodes.hierarchy.visitors;

import models.nodes.hierarchy.NodeLeaf;
import models.nodes.hierarchy.NodesFolder;

/**
 * Created by stratosphr on 30/07/2018.
 */
public interface IElementVisitor {

    void visit(NodesFolder folder);

    void visit(NodeLeaf nodeLeaf);

}
