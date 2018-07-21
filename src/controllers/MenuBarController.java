package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MenuBarController {

    @FXML
    private MenuItem item_quit;

    @FXML
    private void quit() {
        Platform.exit();
    }

}
