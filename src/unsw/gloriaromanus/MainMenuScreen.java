package unsw.gloriaromanus;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuScreen {

    private Stage stage;
    private String title;
    private MainMenuController controller;
    private Scene scene;

    public MainMenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Gloria Romanus";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        scene = new Scene(root, 361, 444);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public MainMenuController getController() {
        return controller;
    }
}