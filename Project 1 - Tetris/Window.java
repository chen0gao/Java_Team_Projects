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


    // Create a Game Window instance
    public void create() {
        data.init();
        window.add(this);
        window.setSize(400, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);

        // Create a timer
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            	if(data.game_pause==false) {
                    moveDown();
            	}
            }
        };
        Timer t = new Timer();
        t.schedule(timerTask, 1000,1000);
    }

    // Function run by timer
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
