//UI library
//Tutorial https://www.youtube.com/watch?v=y7YbQrrcpAQ
import javax.swing.JFrame;
import javax.swing.JPanel;

//Key Board Event library
import java.awt.event.*;

//Color library
import java.awt.*;

//Timer library
//Tutorial https://www.youtube.com/watch?v=pHNAd-G8-9w
import java.util.Timer;
import java.util.TimerTask;

// Window
// Game Window Class
public class Window extends JPanel implements KeyListener {
    private JFrame window = new JFrame("Tetris Game Window"); //Global
//    Create a data instance, handling backend
    Data data = new Data();
    Music musicObject = new Music();
    // Create a Game Window instance
    public void create() {
        // start the background music
        String filepath = "IMG_3056.wav";
        musicObject.playBackgroundMusic(filepath);

        data.init();
        window.add(this);
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);
        window.setResizable(false);

        // Create a timer
        startTimer();
    }
    
    private void startTimer() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
            	
            	if(data.init_overlap) { //Make it lose
            	moveDown();
            	}
            	
            	if(!data.check("bottom") && data.game_pause==false && (System.nanoTime()-data.last_drop_time)/1000000>=data.period) {
                    moveDown();
                    data.last_drop_time = System.nanoTime();
//                    System.out.println("Level: "+ data.level + "Period: " + period);
            	}
            	checkHitBottom();
            	
                // set difficulties / level
                if(data.score < 2000) {// 2000
                	data.level = 1;
                	data.period = 1000;
                }
                else if(data.score < 5000) {// 5000
                	data.level = 2;
                	data.period = 800;
        	    }
                else if(data.score < 10000) {// 10000
                	data.level = 3;
                	data.period = 500;
        		}
                else {
                	data.level = 4;
                	data.period = 200;
                }
//            	data.period = 10;
                
            	
            }
        }, 100, 100);
    }

    // Function run by timerx
    // Auto move down block every second
    public void moveDown() {

        if(data.check("down")) {
            data.move("down");
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public void checkHitBottom() {
        //Check if the block hits bottom
        if(data.check("bottom")) {
        	//Set 500ms delay, so player see block clearance & control block
            Timer drop_t = new Timer();
            drop_t.schedule(new TimerTask() {
                public void run() {
                	if(data.game_pause==false && data.check("bottom")) { //Check if at bottom again
                        data.fixed();
                        drop_t.cancel();
                	} else {
                        drop_t.cancel();
                	}
                }
            }, data.block_drop_cooldown, data.block_drop_cooldown);
        }
    }

//    front end - Draw the game constantly
//    All front end related is moved to UI.java
    public void paintComponent(Graphics cube){
        super.paintComponent(cube);

        UI.paintBoard(cube,data);

        if(data.init_overlap)
            UI.gameOver(cube);//,window);
    }

//    Handle keyboard event
//    All keyboard event related is moved to KeyBoard.java
    public void keyPressed(KeyEvent e) {
        KeyBoard.keyPressed(e,data, musicObject);

        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void keyReleased(KeyEvent e) {
        KeyBoard.keyReleased(e,data);
    }
    public void keyTyped(KeyEvent e) {
        KeyBoard.keyTyped(e,data);
    }

}
