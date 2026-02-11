package freaky.ui;

import freaky.main.Freaky;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Freaky freaky;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image freakyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the UI controller.
     * <p>
     * Automatically scrolls the chat window to the bottom whenever new
     * dialog boxes are added to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@code Freaky} chatbot instance into this controller.
     * <p>
     * Displays the chatbot's greeting message in the dialog container
     * when the UI is first loaded.
     *
     * @param freaky the chatbot instance to use
     */
    public void setFreaky(Freaky freaky) {
        this.freaky = freaky;
        String greet = freaky.greet();
        dialogContainer.getChildren().addAll(DialogBox.getFreakyDialog(greet, freakyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Freaky's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = freaky.handleCommand(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFreakyDialog(response, freakyImage));

        userInput.clear();
    }
}
