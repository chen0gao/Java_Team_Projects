import java.awt.event.KeyEvent;

public class KeyBoard {
    public static void keyReleased(KeyEvent e,Data data) {
//        System.out.println("keyReleased");
    }
    public static void keyTyped(KeyEvent e,Data data) {
//        System.out.println("keyTyped");
    }

//    Key board pressed event
    public static void keyPressed(KeyEvent e,Data data, Music music) {

//    	DEBUG
        if(e.getKeyCode()== KeyEvent.VK_Q) {
        	data.score = data.score+2000;
        }
        if(e.getKeyCode()== KeyEvent.VK_E) {
        	data.score = data.score-2000;
        }
//    	DEBUG
        
        if(e.getKeyCode()== KeyEvent.VK_SPACE) {
        	
        	if(data.game_over == false && data.game_restart == false) {
                // System.out.println("Paused");
                data.game_pause = !data.game_pause; //Reverse the boolean
                  if(data.game_pause){
                      music.pause();
                      // System.out.println("It is now paused");
                  }
                  else {
                      music.resume();
                      // System.out.println("It is now resumed");
                  }
        	} else if(data.game_restart == true) {
        		data.restart();
        	}
        }
        
        if(data.game_pause==false) {

            if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
                if(data.check("right")) {
                    data.move("right");
                }
            }
            else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
                if(data.check("left")) {
                    data.move("left");
                }
            }
            else if(e.getKeyCode()== KeyEvent.VK_UP) {
                if(data.check("up")) {
                    data.rotate();
                }
            }
            else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
                if(data.check("down") && !data.check("bottom")) {
                    data.move("down");
                }

            }
        }
    }
}
