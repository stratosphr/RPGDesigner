package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MasterController implements Initializable {

    @SuppressWarnings({"WeakerAccess", "unused"})
    public MenuItem item_quit;
    @SuppressWarnings({"WeakerAccess", "unused"})
    public Button btn_preview;
    @SuppressWarnings({"WeakerAccess", "unused"})
    public SplitPane split_main;
    @SuppressWarnings({"WeakerAccess", "unused"})
    public ScrollPane scroll_preview;
    @SuppressWarnings({"WeakerAccess", "unused"})
    public TreeView tree_nodesHierarchy;
    @SuppressWarnings({"WeakerAccess", "unused"})
    public TreeTableView table_previewedNodeProperties;
    @SuppressWarnings({"WeakerAccess", "unused"})
    public TreeTableColumn col_properties;
    @SuppressWarnings({"WeakerAccess", "unused"})
    public TreeTableColumn col_values;

    private static MasterController singleton;

    public static MasterController getSingleton() {
        if (singleton == null) {
            singleton = new MasterController();
        }
        return singleton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item_quit.setOnAction(event -> ((Stage) btn_preview.getScene().getWindow()).close());
    }

}
