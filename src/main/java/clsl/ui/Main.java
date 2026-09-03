package clsl.ui;

import clsl.Clsl;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Starts the JavaFX graphical user interface. */
public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Clsl clsl;

    @Override
    public void start(Stage stage) {
        dialogContainer = new VBox();
        scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);

        userInput = new TextField();
        userInput.setPromptText("Enter a command...");
        Button sendButton = new Button("Send");
        clsl = new Clsl("data/csls.txt");

        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        HBox inputArea = new HBox(userInput, sendButton);
        HBox.setHgrow(userInput, Priority.ALWAYS);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(scrollPane);
        mainLayout.setBottom(inputArea);

        Scene scene = new Scene(mainLayout, 400, 600);

        stage.setTitle("Clsl");
        stage.setScene(scene);
        stage.show();

        addDialog("Clsl", "Hello! I'm Clsl.\nWhat can I do for you?", false);
    }

    private void handleUserInput() {
        String command = userInput.getText();
        if (command.isBlank()) {
            return;
        }

        addDialog("You", command, true);
        addDialog("Clsl", clsl.getResponse(command), false);
        userInput.clear();
        scrollPane.setVvalue(1.0);
    }

    private void addDialog(String speaker, String message, boolean isUser) {
        Label dialog = new Label(speaker + ": " + message);
        dialog.setWrapText(true);

        HBox dialogRow = new HBox(dialog);
        dialogRow.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        dialogContainer.getChildren().add(dialogRow);
    }
}
