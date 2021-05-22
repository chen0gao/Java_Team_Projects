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

        int init_x = 25;
        int init_y = 25;

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
                int w = 25;
                int h = 25;
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
