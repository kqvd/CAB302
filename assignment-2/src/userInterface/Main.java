package userInterface;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * CAB302 - ASSIGNMENT 02 - VEC PAINT
 *
 * Main: Launch Application
 *
 * @Author Waldo Fouche, n9950095
 * @Author Kevin Doung, n9934731
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("ui_layout.fxml"));
            primaryStage.setTitle("VEC Paint - Untitled");
            Scene scene = new Scene(root, 950, 700);

            // Stylesheet for icons, design
            scene.getStylesheets().add("userInterface/stylesheet.css");
            scene.getStylesheets().add("userInterface/menuBarStylesheet.css");

            // Display GUI
            primaryStage.setScene(scene);
            primaryStage.show();

            root.prefWidthProperty().bind(scene.widthProperty());
            root.prefHeightProperty().bind(scene.heightProperty());

            // Exit
            primaryStage.setOnCloseRequest(e -> Platform.exit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}