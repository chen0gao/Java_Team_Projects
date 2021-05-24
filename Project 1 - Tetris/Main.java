//Main
//Host the game window
public class Main {
    public static void main(String[] args) {
        Window win = new Window();
        win.create();
        win.addKeyListener(win); //Able to handle key event
        win.setFocusable(true);
    }
}
