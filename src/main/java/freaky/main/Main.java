package freaky.main;

import java.io.IOException;

import freaky.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Freaky using FXML.
 */
public class Main extends Application {

    private Freaky freaky = new Freaky("./data/freaky.txt");

    /**
     * Starts the JavaFX application and initializes the main GUI window.
     * This method loads the FXML layout for the main window, sets up the scene,
     * configures the minimum window size, injects the Freaky chatbot instance
     * into the controller, and displays the stage.
     *
     * @param stage The primary stage provided by the JavaFX runtime on which
     *              the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(600);

            fxmlLoader.<MainWindow>getController().setFreaky(freaky); // inject the Freaky instance
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
