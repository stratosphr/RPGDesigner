package models.nodes.visitors;

/**
 * Created by stratosphr on 30/07/2018.
 */
public interface IVisitableNode {

    void accept(INodeVisitor visitor);

}
