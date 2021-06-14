import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;


public class UI extends Application {
	int turn = 1;

	private static Text player_name_text = new Text();
	private static Text player_hp_text = new Text();
	private static Text player_xp_text = new Text();

	private static Text monster_name_text = new Text();
	private static Text monster_hp_text = new Text();

	private static Text message1 = new Text();
	//	private static Text text; //Not working

	// Define monster
	static HashMap<String, Object> monster = new HashMap<String, Object>();

	// Define character
	static HashMap<String, Object> player = new HashMap<String, Object>();


	public UI() {
//		System.out.println("UI init");
	}

	public static void postData(Object data,String type) {
		if(type=="player") {
			player = (HashMap) data;
			player_name_text.setText(player.get("name").toString());
			player_hp_text.setText("HP: "+player.get("hp").toString()+"/"+player.get("max_hp").toString());
			player_xp_text.setText("XP: "+player.get("xp").toString()+"/"+player.get("next_xp").toString());
		} else if (type=="monster") {
			monster = (HashMap) data;
			monster_name_text.setText(monster.get("name").toString());
			monster_hp_text.setText("HP: "+monster.get("hp").toString()+"/"+monster.get("max_hp").toString());
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Project 2 - Idle Game");


		Group root = new Group();

		player_name_text.setText("Name");
		player_name_text.setX(50);
		player_name_text.setY(10+50);
		root.getChildren().add( player_name_text);

		player_hp_text.setText("HP: 100/100");
		player_hp_text.setX(50);
		player_hp_text.setY(35+50);
		root.getChildren().add( player_hp_text);

		player_xp_text.setText("XP: 0/100");
		player_xp_text.setX(50);
		player_xp_text.setY(50+50);
		root.getChildren().add( player_xp_text);

		monster_name_text.setText("Monster");
		monster_name_text.setX(50+100);
		monster_name_text.setY(10+50);
		root.getChildren().add( monster_name_text);

		monster_hp_text.setText("HP: 17/17");
		monster_hp_text.setX(50+100);
		monster_hp_text.setY(35+50);
		root.getChildren().add( monster_hp_text);

		message1.setText("Message 1");
		message1.setX(50);
		message1.setY(35+50+50);
		root.getChildren().add( message1);

		Scene scene = new Scene(root, 300, 250);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void postMsg(String string) {
		message1.setText(string);
	}

}
