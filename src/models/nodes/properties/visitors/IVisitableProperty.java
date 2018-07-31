package models.nodes.properties.visitors;

/**
 * Created by stratosphr on 31/07/2018.
 */
public interface IVisitableProperty {

    void accept(IPropertyVisitor visitor);

}
