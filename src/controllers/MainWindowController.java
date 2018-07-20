package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MainWindowController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private ToolBar toolBar;
    @FXML
    private SplitPane split_main;
    @FXML
    private ScrollPane preview;
    @FXML
    private TitledPane nodesHierarchy;
    @FXML
    private TitledPane nodeProperties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("##" + split_main.getStyle());
    }

}
