package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import models.nodes.TileSet;
import models.nodes.hierarchy.ANodesHierarchyElement;
import models.nodes.hierarchy.NodeLeaf;
import models.nodes.hierarchy.NodesFolder;
import models.nodes.hierarchy.visitors.IElementVisitor;
import models.nodes.properties.ImageProperty;
import models.nodes.properties.IntegerProperty;
import models.nodes.properties.TileSetProperty;
import models.nodes.properties.Vector2Property;
import models.nodes.properties.visitors.IPropertyVisitor;
import models.nodes.visitors.INodeVisitor;

/**
 * Created by stratosphr on 31/07/2018.
 */
public class ItemGraphic extends HBox implements IElementVisitor, INodeVisitor, IPropertyVisitor {

    private Paint color;
    private String text;

    public ItemGraphic(ANodesHierarchyElement element) {
        element.accept(this);
    }

    private void createLabel() {
        setAlignment(Pos.BOTTOM_CENTER);
        setPadding(new Insets(4, 4, 4, 4));
        setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), Insets.EMPTY)));
        Text nodeType = new Text(text);
        getChildren().add(nodeType);
    }

    @Override
    public void visit(NodesFolder folder) {
    }

    @Override
    public void visit(NodeLeaf nodeLeaf) {
        nodeLeaf.getNode().accept(this);
        text = nodeLeaf.getNode().getClass().getSimpleName();
        createLabel();
    }

    @Override
    public void visit(TileSet tileSet) {
        color = Color.LIGHTBLUE;
    }

    @Override
    public void visit(Vector2Property vector2Property) {
        color = Color.GREEN;
    }

    @Override
    public void visit(TileSetProperty tileSetProperty) {

    }

    @Override
    public void visit(ImageProperty imageProperty) {
        color = Color.LIGHTGRAY;
    }

    @Override
    public void visit(IntegerProperty integerProperty) {
        color = Color.RED;
    }

}
