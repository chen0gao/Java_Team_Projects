import java.awt.*;

public class UI {
//    Create border around a cube
    public static void createBorder(Graphics cube,int x,int y,int w,int h) {
        cube.drawLine(x, y, x + w, y); //Top
        cube.drawLine(x, y + h, x + w, y + h); //Bottom
        cube.drawLine(x, y, x, y + h); //Left
        cube.drawLine(x + w, y, x + w, y + h); //Right
    }

//    Paint the game board based on the backend
    public static void paintBoard(Graphics cube,Data data) {

        int[][] system = data.system;

        int init_x = 25; //initial x of game window
        int init_y = 25; //initial y of game window
        int w = 25; //width of block
        int h = 25; //height of block
		int window_w = w*system[0].length; //width of game window
		int window_h = h*system.length; //height of game window
        
    	//Pause Game
    	if(data.game_pause==true) {
    		
            cube.setColor(Color.BLACK);
            cube.fillRect(init_x, init_y, window_w, window_h);
            cube.setColor(Color.WHITE);
            cube.setFont(new Font("TimesRoman", Font.PLAIN, 24)); 
            
            int text_x_offset = 30;
            cube.drawString("Paused", init_x+window_w/2 - text_x_offset, init_y+window_h/2);
    		return;
    	}
    	
    	//Unpause Game

        for (int row = 0; row < system.length; row++) {
            for (int col = 0; col < system[row].length; col++) {
                // Different block color based on the value
                if (system[row][col] == 0) {
                    cube.setColor(Color.WHITE);
                } else if (system[row][col] == 1) {
                    cube.setColor(Color.BLUE);
                } else if (system[row][col] == 10) {
                    cube.setColor(Color.GRAY);
                }
                int x = init_x + w * col;
                int y = init_y + h * row;
                cube.fillRect(x, y, w, h);
                //Border
                cube.setColor(Color.BLACK);
                createBorder(cube,x,y,w,h);

                //Debug Mode - show array number
                if (system[row][col] != 0) {
                cube.setColor(Color.WHITE);
                } else {
                cube.setColor(Color.BLACK);
                }
                cube.drawString(String.valueOf(system[row][col]), x+w/2, y+h/2);
            }
        }
    }
}
