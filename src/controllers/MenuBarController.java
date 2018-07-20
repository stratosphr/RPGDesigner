package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class MenuBarController {

    public MenuItem item_quit;

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

}
