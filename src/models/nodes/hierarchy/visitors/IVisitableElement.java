package models.nodes.hierarchy.visitors;

/**
 * Created by stratosphr on 30/07/2018.
 */
public interface IVisitableElement {

    void accept(IElementVisitor visitor);

}
