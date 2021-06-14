import java.awt.event.KeyEvent;

public class KeyBoard {
    public static void keyReleased(KeyEvent e,Data data) {
//        System.out.println("keyReleased");
    }
    public static void keyTyped(KeyEvent e,Data data) {
//        System.out.println("keyTyped");
    }

//    Key board pressed event
    public static void keyPressed(KeyEvent e,Data data) {

//    	DEBUG
        if(e.getKeyCode()== KeyEvent.VK_Q) {
        }
        if(e.getKeyCode()== KeyEvent.VK_E) {
        }

        if(e.getKeyCode()== KeyEvent.VK_SPACE) {
        }

    }
}
