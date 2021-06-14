import java.util.HashMap; // import the HashMap class

import java.awt.event.*; //Key Board Event library

import java.util.Timer; //Timer library
import java.util.TimerTask;

// Window
// Game Window Class
public class Window implements KeyListener {

	int turn = 1; //Some turns with random event?

	// Define monster
	HashMap<String, Object> monster = new HashMap<String, Object>();
	
	// Define character
	HashMap<String, Object> player = new HashMap<String, Object>();

	Data data = new Data();
	UI ui = new UI();
	//    Music musicObject = new Music();

	// Create a Game Window instance
	public Window() {
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
		// start the background music
		//        String filepath = "music/Tetris_theme.wav";
		//        musicObject.playBackgroundMusic(filepath);

		// Create a timer
		startTimer();
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
		UI.postData(player,"player");
		UI.postData(monster,"monster");
	}

	private void startTimer() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {

				calculateTurn(monster);
				turn = turn+1;
			}
		}, 1000, 1000);
	}

	private void attack() {
		int damage = (int) player.get("att") - (int) monster.get("def");
		//      System.out.println(player.get("name") + " deals " + damage + " to " + monster.get("name"));
		UI.postMsg(player.get("name") + " deals " + damage + " to " + monster.get("name"));

		int monster_new_hp = (int) monster.get("hp") - damage;
		monster.put("hp", monster_new_hp);
	}

	private void spawnMonster() {
		monster.put("hp", 17);
	}

	public void win() {
		//System.out.println(monster.get("name") + " is defeat!!");
		UI.postMsg(monster.get("name") + " is defeat!!");

		player.put("xp", (int) player.get("xp")+(int) monster.get("xp"));

		//Level up
		if((int) player.get("xp")>=(int) player.get("next_xp")) {
			//System.out.println("Level up!!!");
			UI.postMsg("Level up!!!");

			player.put("xp", (int) player.get("next_xp")-(int) player.get("xp"));
		}
	}

	//    Handle keyboard event
	//    All keyboard event related is moved to KeyBoard.java
	public void keyPressed(KeyEvent e) {
		KeyBoard.keyPressed(e,data);
	}

	public void keyReleased(KeyEvent e) {
		KeyBoard.keyReleased(e,data);
	}
	public void keyTyped(KeyEvent e) {
		KeyBoard.keyTyped(e,data);
	}

}
