package clsl.ui.javafx;

import clsl.Clsl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/** Controls the main JavaFX window defined in {@code Main.fxml}. */
public class MainController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private Clsl clsl;
    private Image clslAvatar;
    private Image userAvatar;

    /** Initializes the application logic and displays the welcome message. */
    @FXML
    private void initialize() {
        clsl = new Clsl("data/csls.txt");
        clslAvatar = loadImage("/images/pig.jpg");
        userAvatar = loadImage("/images/dog.jpg");
        userInput.setOnAction(this::handleUserInput);
        dialogContainer.heightProperty().addListener((observable, oldHeight, newHeight) -> scrollToBottom());
        addDialog("Clsl", "Hello! I'm Clsl.\nWhat can I do for you?", false);
    }

    /** Processes the command entered by the user. */
    @FXML
    private void handleUserInput(ActionEvent event) {
        String command = userInput.getText();
        if (command.isBlank()) {
            return;
        }

        addDialog("You", command, true);
        addDialog("Clsl", clsl.getResponse(command), false);
        userInput.clear();
        scrollToBottom();
    }

    /** Adds a message to the conversation display. */
    private void addDialog(String speaker, String message, boolean isUser) {
        Label sender = new Label(speaker);
        sender.getStyleClass().add("sender-label");

        Label dialog = new Label(message);
        dialog.getStyleClass().add("message-text");
        dialog.setWrapText(true);

        VBox bubble = new VBox(sender, dialog);
        bubble.getStyleClass().add("message-bubble");

        ImageView avatar = createAvatar(isUser ? userAvatar : clslAvatar);
        HBox dialogRow = new HBox(10);
        dialogRow.getStyleClass().add("message-row");
        dialogRow.getStyleClass().add(isUser ? "user-message" : "clsl-message");
        dialogRow.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        if (isUser) {
            dialogRow.getChildren().addAll(bubble, avatar);
        } else {
            dialogRow.getChildren().addAll(avatar, bubble);
        }
        dialogContainer.getChildren().add(dialogRow);
    }

    /** Loads an avatar image from the application resources. */
    private Image loadImage(String resourcePath) {
        return new Image(getClass().getResourceAsStream(resourcePath));
    }

    /** Creates a circular avatar image for a chat message. */
    private ImageView createAvatar(Image image) {
        ImageView avatar = new ImageView(image);
        avatar.setFitWidth(42);
        avatar.setFitHeight(42);
        avatar.setPreserveRatio(true);
        avatar.setClip(new Circle(21, 21, 21));
        avatar.getStyleClass().add("message-avatar");
        return avatar;
    }

    /** Scrolls the conversation to its lowest position after JavaFX lays out the new message. */
    private void scrollToBottom() {
        Platform.runLater(() -> scrollPane.setVvalue(scrollPane.getVmax()));
    }
}
