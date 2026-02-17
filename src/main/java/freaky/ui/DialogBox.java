package freaky.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text and display image.
     *
     * This constructor loads the corresponding FXML layout file
     * ({@code DialogBox.fxml}), sets this instance as both the root node and
     * controller, and initializes the UI components.
     *
     * The {@code Label} is configured to automatically resize based on its
     * content so that the full message is displayed without being clipped or
     * dynamically resized later. The associated {@code ImageView} is set to
     * display the provided image.
     *
     * @param text the message text to be displayed in the dialog box
     * @param img  the image representing the speaker (e.g., user or Freaky)
     */
    private DialogBox(String text, Image img) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        // Make the Label resize to fit the text exactly
        dialog.setPrefWidth(Label.USE_COMPUTED_SIZE);
        dialog.setMinWidth(Label.USE_PREF_SIZE);
        dialog.setMaxWidth(Label.USE_PREF_SIZE);
        dialog.setPrefHeight(Label.USE_COMPUTED_SIZE);
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setMaxHeight(Label.USE_PREF_SIZE);

        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getFreakyDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }

}
