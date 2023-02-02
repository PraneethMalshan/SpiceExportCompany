import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {
    public AppInitializer(){
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/spiceExportCompany/view/MainForm.fxml");
        Parent window = (Parent)FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Form");
        primaryStage.centerOnScreen();
        primaryStage.show();

        /* primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/spiceExportCompany/view/MainForm.fxml"))));
        primaryStage.show(); */
    }
}
