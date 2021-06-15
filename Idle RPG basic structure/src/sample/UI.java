package sample;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;


public class UI {    
	int turn = 1;

	// Define monster
	static HashMap<String, Object> monster = new HashMap<String, Object>();

	// Define character
	static HashMap<String, Object> player = new HashMap<String, Object>();


	public UI() {
//		System.out.println("UI init");
	}

	public static void postData(Object data,String type,GridPane gridPane) {
		if(type=="player") {
			player = (HashMap) data;
			((Labeled) gridPane.lookup("#player_name")).setText(player.get("name").toString());
			((Labeled) gridPane.lookup("#player_hp")).setText("HP: "+player.get("hp").toString()+"/"+player.get("max_hp").toString());
			((Labeled) gridPane.lookup("#player_xp")).setText("XP: "+player.get("xp").toString()+"/"+player.get("next_xp").toString());
		} else if (type=="monster") {
			monster = (HashMap) data;
			((Labeled) gridPane.lookup("#monster_name")).setText(monster.get("name").toString());
			((Labeled) gridPane.lookup("#monster_hp")).setText("HP: "+monster.get("hp").toString()+"/"+monster.get("max_hp").toString());
		}
	}

	public static void postMsg(String string,GridPane gridPane) {
		((Labeled) gridPane.lookup("#message1")).setText(string);
	}

}
