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

private int period = 1000;

    // Create a Game Window instance
    public void create() {
        data.init();
        window.add(this);
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);

        // Create a timer
        startTimer();
    }
    
    private void startTimer() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
            	if(data.game_pause==false) {
                    moveDown();
//                    System.out.println("Level: "+ data.level + "Period: " + period);

                    if(Data.level == 1)
                        period = 1000;
                    if(Data.level == 2)
                        period = 800;
                    if(Data.level == 3)
                        period = 500;
                    if(Data.level == 4)
                        period = 200;
                    t.cancel();
                    startTimer();
            	}
            }
        }, period, period);
    }

    // Function run by timerx
    // Auto move down block every second
    public void moveDown() {
        if(data.check("down")) {
            data.move("down");
        }
        //Check if the block hits bottom
        if(data.check("bottom")) {
            data.fixed();
        }
        this.invalidate();
        this.validate();
        this.repaint();
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
        KeyBoard.keyPressed(e,data);

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
