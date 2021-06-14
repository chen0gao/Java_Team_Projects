package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.Optional;


public class Controller {

    @FXML
    BorderPane mainBorderPane;

    // place holder for save
    // To be changed later
    @FXML
    public void placeHolderforSave(){
        System.out.println("Save is clicked");
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Save Progress");
        // dialog.setHeaderText("Place Holder");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("loadAndSave.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            LoadAndSaveController controller = fxmlLoader.getController();
            // PLACE HOLDER
            System.out.println("Saved");
        }
    }

    // place holder for load
    // To be changed later
    @FXML
    public void placeHolderforload(){
        System.out.println("Load is clicked");
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Load Progress");
        // dialog.setHeaderText("Place Holder");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("loadAndSave.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            LoadAndSaveController controller = fxmlLoader.getController();
            // PLACE HOLDER
            System.out.println("Loaded");
        }
    }

    // handling the event - exit
    // when click exit, it will exit the program
    @FXML
    public void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Game");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit the game?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Finished and quit");
            Platform.exit();
        }
    }
}
