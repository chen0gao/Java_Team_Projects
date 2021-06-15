package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;


public class Controller {

	int turn = 1; //Some turns with random event?

	// Define monster
	HashMap<String, Object> monster = new HashMap<String, Object>();
	
	// Define character
	HashMap<String, Object> player = new HashMap<String, Object>();
	
    @FXML
    BorderPane mainBorderPane;
    @FXML
    GridPane ui;

	public void initialize() {
		player.put("name", "Player");
		player.put("hp", 100);
		player.put("max_hp", 100);
		player.put("att", 10);
		player.put("def", 10);
		player.put("xp", 0);
		player.put("next_xp", 30);

		monster.put("name", "--Monster--");
		monster.put("hp", 17);
		monster.put("max_hp", 17);
		monster.put("att", 5);
		monster.put("def", 2);
		monster.put("xp", 10);
		monster.put("next_xp", 100);
//        System.out.println("Timer");
		startTimer();
    }

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

	public void calculateTurn(HashMap<String, Object> monster) {
		//Player attack first
		//player.attack() //Change player/monster into a class in future
		attack();


		//Defeat the monster
		if((int) monster.get("hp")<=0) {
			win();
			spawnMonster();
		}
		UI.postData(player,"player",ui);
		UI.postData(monster,"monster",ui);
	}

	private void startTimer() {
	    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
			calculateTurn(monster);
			turn = turn+1;
	    }));
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();
	}

	private void attack() {
		int damage = (int) player.get("att") - (int) monster.get("def");
		//      System.out.println(player.get("name") + " deals " + damage + " to " + monster.get("name"));
		UI.postMsg(player.get("name") + " deals " + damage + " to " + monster.get("name"),ui);

		int monster_new_hp = (int) monster.get("hp") - damage;
		monster.put("hp", monster_new_hp);
	}

	private void spawnMonster() {
		monster.put("hp", 17);
	}

	public void win() {
		//System.out.println(monster.get("name") + " is defeat!!");
		UI.postMsg(monster.get("name") + " is defeat!!",ui);

		player.put("xp", (int) player.get("xp")+(int) monster.get("xp"));

		//Level up
		if((int) player.get("xp")>=(int) player.get("next_xp")) {
			//System.out.println("Level up!!!");
			UI.postMsg("Level up!!!",ui);

			player.put("xp", (int) player.get("next_xp")-(int) player.get("xp"));
		}
	}

}
