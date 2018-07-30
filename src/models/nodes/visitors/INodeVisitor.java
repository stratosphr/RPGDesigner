package models.nodes.visitors;

import models.nodes.TileSet;

/**
 * Created by stratosphr on 30/07/2018.
 */
public interface INodeVisitor {

    void visit(TileSet tileSet);

}
