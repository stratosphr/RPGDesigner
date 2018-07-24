import controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.MasterModel;

import java.io.IOException;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class Main extends Application {

    @FXML
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/main-window.fxml"));
        primaryStage.setTitle("RPGDesigner");
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("views/css/darkula.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();
        ((MainWindowController) loader.getController()).initModel(new MasterModel(primaryStage));
    }

}
