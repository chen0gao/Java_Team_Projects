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

        // create our own colors
        Color color_1 = new Color(255,204,0); // orange
        Color color_2 = new Color(230,230,0); // yellow
        Color color_3 = new Color(102,255,153); // green
        Color color_4 = new Color(102,140,255); // blue
        Color color_5 = new Color(179,102,255); // violet
        Color color_6 = new Color(255,0,0); // red

        // right side Information (scores, pause instruction, level etc...)
        int right_side_init_x = 50 + window_w;
        int right_side_init_y = 50;

        // print word "NEXT"
        cube.setFont(new Font("TimesRoman", Font.BOLD,20));
        cube.drawString("NEXT",right_side_init_x + 55,right_side_init_y - 8);

        // grid
        int grid_w = 25 * 3;
        int grid_h = 25 * 3;
        cube.setColor(Color.BLACK);
        createBorder(cube,right_side_init_x + 55, right_side_init_y, grid_w, grid_h);

        for(int row = 0; row < data.next_block.length; row++){
            for (int col = 0; col < data.next_block[row].length; col ++){
                if (data.next_block[row][col] == 0) {
                    cube.setColor(Color.WHITE);
                } else if (data.next_block[row][col] == 1 && Data.next_shape == 1) {
                    cube.setColor(color_1);
                } else if (data.next_block[row][col] == 1 && Data.next_shape == 2) {
                    cube.setColor(color_2);
                } else if (data.next_block[row][col] == 1 && Data.next_shape == 3) {
                    cube.setColor(color_3);
                } else if (data.next_block[row][col] == 1 && Data.next_shape == 4) {
                    cube.setColor(color_4);
                } else if (data.next_block[row][col] == 1 && Data.next_shape == 5) {
                    cube.setColor(color_5);
                } else if (data.next_block[row][col] == 1 && Data.next_shape == 6) {
                    cube.setColor(color_6);
                }
                int x = right_side_init_x + 55 + 25 * col;
                int y = right_side_init_y + 25 * row;
                cube.fillRect(x, y, w, h);
            }
        }
        cube.setColor(Color.BLACK);
        for(int row = right_side_init_x + 55; row < right_side_init_x + 55 + grid_w; row += 25){
            for (int col = right_side_init_y; col < right_side_init_y + grid_h; col += 25){
                createBorder(cube,row, col, 25, 25);
            }
        }
        
        // font for the word "Score"
        cube.setColor(Color.BLACK);
        cube.setFont(new Font("TimesRoman", Font.BOLD,30));
        int score_x_offset = 50;
        cube.drawString("SCORE",right_side_init_x + score_x_offset,right_side_init_y + 165);

        // font for the numeric score
        int numberic_score_offset = 60;
        int current_score_y_offset = 190;
        cube.setFont(new Font("TimesRoman", Font.BOLD,20));
        String current_score;
        if(data.score < 10)
            current_score = "000000";
        else if(data.score < 100)
            current_score = "00000";
        else if(data.score < 1000)
            current_score = "0000";
        else if(data.score < 10000)
            current_score = "000";
        else if(data.score < 100000)
            current_score = "00";
        else if(data.score < 1000000)
            current_score = "0";
        else
            current_score = "";
        current_score += String.valueOf(data.score);
        cube.drawString( current_score,right_side_init_x + numberic_score_offset,right_side_init_y + current_score_y_offset);

        // print word level
        int level_x_offset = 50;
        int level_y_offset = 265;
        cube.setFont(new Font("TimesRoman", Font.BOLD,30));
        cube.drawString("LEVEL",right_side_init_x + level_x_offset,right_side_init_y + level_y_offset);

        // print current level
        cube.setFont(new Font("TimesRoman", Font.ITALIC,25));
        String current_level = String.valueOf(Data.level);
        if(Data.level == 4){
            current_level = "MAX";
            cube.drawString(current_level,right_side_init_x + score_x_offset + 20,right_side_init_y + 295);
        }
        else
            cube.drawString(current_level,right_side_init_x + score_x_offset + 40,right_side_init_y + 295);

        // noticing press space for pause
        cube.setFont(new Font("TimesRoman", Font.BOLD,20));
        cube.drawString("PRESS SPACEBAR",right_side_init_x + 10,right_side_init_y + 360);
        cube.drawString("TO PAUSE",right_side_init_x + 50,right_side_init_y + 380);

        // return to the default font
        cube.setColor(Color.BLACK);
        cube.setFont(new Font("TimesRoman", Font.PLAIN,12));

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
                } else if (system[row][col] == 1 && Data.current_shape == 1) {
                    cube.setColor(color_1);
                } else if (system[row][col] == 1 && Data.current_shape == 2) {
                    cube.setColor(color_2);
                } else if (system[row][col] == 1 && Data.current_shape == 3) {
                    cube.setColor(color_3);
                } else if (system[row][col] == 1 && Data.current_shape == 4) {
                    cube.setColor(color_4);
                } else if (system[row][col] == 1 && Data.current_shape == 5) {
                    cube.setColor(color_5);
                } else if (system[row][col] == 1 && Data.current_shape == 6) {
                    cube.setColor(color_6);
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

    public static void gameOver(Graphics cube){//, JFrame window){
        //int x = window.getX();
        //int y = window.getY();
        cube.setColor(Color.BLACK);
        cube.fillRect(0, 0, 500, 500);
        cube.setColor(Color.lightGray);
        cube.setFont(new Font("TimesRoman", Font.BOLD, 40));

        cube.drawString("Game Over",150,240);
    }
}
