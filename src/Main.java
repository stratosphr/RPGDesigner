import controllers.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by stratosphr on 20/07/2018.
 */
public final class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/main-window.fxml"));
        loader.setController(MasterController.getSingleton());
        primaryStage.setTitle("RPGDesigner");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

}
