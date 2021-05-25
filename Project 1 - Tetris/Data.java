import java.util.concurrent.ThreadLocalRandom;

// Data Class to acess the backend info
public class Data {
    public static int[][] system = new int[13][5];
    public static int cur_row = 0; //current block row index
    public static int cur_col = 1; //current block column index
    public static int[][] cur_block = new int[3][3]; //real block array
    public static int[][] temp_block = new int[3][3]; //temp block array check for movement & rotation
    public static int current_shape = 0;
    public static int current_rotation_type = 0;

    //    Initialize the game
    public void init() {
        cur_row = 0;
        cur_col = 1;
        generate_shape();

        // test, set 9-2 a pre fixed block
        system[12][2] = 10;

        render_block();
    }

    //    print current block matrix into game matrix
    public void render_block() {
        //Render matrix based on current block
        for (int row = 0; row < cur_block.length; row++) {
            for (int col = 0; col < cur_block[row].length; col++) {
                if (cur_block[row][col] == 0)
                    continue; //skip empty block
                system[cur_row + row][cur_col + col] = cur_block[row][col];
            }
        }
    }

    // Check if block array overlap with game array
    // new_row: new row index to check
    // new_col: new column index to check
    // pass: if true= no overlapping, if false= overlapping
    static boolean check_overlap(int new_row, int new_col) {

        boolean pass = true;
        for (int row = 0; row < temp_block.length; row++) {
            for (int col = 0; col < temp_block[row].length; col++) {
                if (temp_block[row][col] == 0)
                    continue; //skip empty block

                //Check top
                if (new_row + row < 0) {
                    pass = false;
                    continue;
                }

                //Check Bottom
                if (new_row + row > system.length - 1) {
                    pass = false;
                    continue;
                }


                //Check Left Boundary
                if (new_col + col < 0) {
                    System.out.println("[Warning] Will leave left boundary");
                    pass = false;
                    continue;
                }

                //If first column is all zero, still to move to left
                if (new_col < 0) {
                    continue;
                }

                //Check Right Boundary
                if (new_col + col > system[0].length - 1) {
                    System.out.println("[Warning] Will leave right boundary");
                    pass = false;
                    continue;
                }

                //Check every new block if there is value > 2 (overlapping)
                if (system[new_row + row][new_col + col] + temp_block[row][col] > 2) {
                    pass = false;
                }

//                System.out.println(pass);
            }
        }
        return pass;
    }

    //    Once hit bottom, set the blocks into fixed
    //    check if any row need to be cleared, then start a new block
    public void fixed() {
        for (int row = 0; row < cur_block.length; row++) {
            for (int col = 0; col < cur_block[row].length; col++) {
                if (cur_block[row][col] == 1)
                    system[cur_row + row][cur_col + col] = 10;
            }
        }
        check_all_rows();
        init();
        // system[cur_row][cur_col] = 10;
        // init();
    }

    //    Check if a direction is overlapping
    static boolean check(String direction) {
        boolean result = false;
        temp_block = cur_block;

        switch (direction) {
            case "right":
                if (check_overlap(cur_row, cur_col + 1))
                    result = true;
                break;
            case "left":
                if (check_overlap(cur_row, cur_col - 1))
                    result = true;
                break;
            case "up":
                if (check_overlap(cur_row - 1, cur_col))
                    result = true;
                break;
            case "down":
                if (check_overlap(cur_row + 1, cur_col))
                    result = true;
                break;
            case "bottom":
                if (hits_ground() || hits_gray()) //Hit the bottom
                    result = true;
                break;
        }
        return result;
    }

    // check if it hits the ground
    public static boolean hits_ground() {
        for (int row = 0; row < cur_block.length; row++) {
            for (int col = 0; col < cur_block[row].length; col++) {
                if (cur_block[row][col] == 0)
                    continue; //skip empty block
                if (cur_row + row == system.length - 1)
                    return true;
            }
        }
        return false;
    }

    // check if it hits the gray part
    public static boolean hits_gray() {
        for (int row = 0; row < cur_block.length; row++) {
            for (int col = 0; col < cur_block[row].length; col++) {
                if (cur_block[row][col] == 0)
                    continue; //skip empty block
                if (system[cur_row + row + 1][cur_col + col] == 10)
                    return true;
            }
        }
        return false;
    }

    //    Move the block
    public void move(String direction) {
        //reset 3 x 3 to 0
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cur_row + row > system.length - 1)
                    continue; //skip the index that's outside of game window 5x10 array
                if (cur_col + col > system[0].length - 1)
                    continue; //skip the index that's outside of game window 5x10 array
                if (cur_col + col < 0)
                    continue; //skip the index that's outside of game window 5x10 array
                if (system[cur_row + row][cur_col + col] == 10)
                    continue; //skip the index that already contains fixed block
                system[cur_row + row][cur_col + col] = 0;
            }
        }
        switch (direction) {
            case "right":
                cur_col = cur_col + 1;
                break;
            case "left":
                cur_col = cur_col - 1;
                break;
            case "up":
                cur_row = cur_row - 1;
                break;
            case "down":
                cur_row = cur_row + 1;
                break;
        }
//        system[cur_row][cur_col] = 1;
        render_block();
    }

    // random function to generate random numbers
    public int random_num(int range, int start_num) {
        return ThreadLocalRandom.current().nextInt(start_num,range);
    }

    // randomly generate different shapes
    // we will have 6 different shapes,
    // so we will generate 5 different numbers from 1 - 6
    public void generate_shape() {
        int num = random_num(6, 1);
        // num = 6;// test
        if (num == 1) {
            int rotate_num = random_num(2,1);
            shape_1_types(rotate_num);
        }
        if (num == 2) {
            int rotate_num = random_num(4,1);
            shape_2_types(rotate_num);
        }
        if (num == 3) {
            int rotate_num = random_num(4,1);
            shape_3_types(rotate_num);
        }
        if (num == 4) {
            int rotate_num = random_num(4,1);
            shape_4_types(rotate_num);
        }
        if (num == 5) {
            int rotate_num = random_num(4,1);
            shape_5_types(rotate_num);
        }
        if (num == 6) {
            int rotate_num = 1;
            shape_6_types(rotate_num);
        }
    }

    // add rotation types for each shape

    // rotation types for shape 1
    // two types for shape 1
    public void shape_1_types(int type_num){
        if(type_num == 1){
            int[][] new_block = new int[][]{
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 1;
                current_shape = 1;
            }
        }
        if(type_num == 2){
            int[][] new_block = new int[][]{
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 2;
                current_shape = 1;
            }
        }
    }
    // rotation types for shape 2
    // 4 types for shape 2
    public void shape_2_types(int type_num){
        if(type_num == 1){
            int[][] new_block = new int[][]{
                    {0, 0, 0},
                    {0, 1, 1},
                    {0, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 1;
                current_shape = 2;
            }
        }
        if(type_num == 2){
            int[][] new_block = new int[][]{
                    {0, 0, 0},
                    {1, 1, 0},
                    {0, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 2;
                current_shape = 2;
            }
        }
        if(type_num == 3){
            int[][] new_block = new int[][]{
                    {0, 1, 0},
                    {1, 1, 0},
                    {0, 0, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 3;
                current_shape = 2;
            }
        }
        if(type_num == 4){
            int[][] new_block = new int[][]{
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 4;
                current_shape = 2;
            }
        }
    }
    // rotation types for shape 3
    // 4 types for shape 3
    public void shape_3_types(int type_num){
        if(type_num == 1){
            int[][] new_block = new int[][]{
                    {1, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 1;
                current_shape = 3;
            }
        }
        if(type_num == 2){
            int[][] new_block = new int[][]{
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 2;
                current_shape = 3;
            }
        }
        if(type_num == 3){
            int[][] new_block = new int[][]{
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 1}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 3;
                current_shape = 3;
            }
        }
        if(type_num == 4){
            int[][] new_block = new int[][]{
                    {0, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 4;
                current_shape = 3;
            }
        }
    }
    // rotation types for shape 4
    // 4 types for shape 4
    public void shape_4_types(int type_num){
        if(type_num == 1){
            int[][] new_block = new int[][]{
                    {0, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 1;
                current_shape = 4;
            }
        }
        if(type_num == 2){
            int[][] new_block = new int[][]{
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 1}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 2;
                current_shape = 4;
            }
        }
        if(type_num == 3){
            int[][] new_block = new int[][]{
                    {0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 3;
                current_shape = 4;
            }
        }
        if(type_num == 4){
            int[][] new_block = new int[][]{
                    {1, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 4;
                current_shape = 4;
            }
        }
    }
    // rotation types for shape 5
    // 4 types for shape 5
    public void shape_5_types(int type_num){
        if(type_num == 1){
            int[][] new_block = new int[][]{
                    {1, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 1;
                current_shape = 5;
            }
        }
        if(type_num == 2){
            int[][] new_block = new int[][]{
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 1}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 2;
                current_shape = 5;
            }
        }
        if(type_num == 3){
            int[][] new_block = new int[][]{
                    {0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 1}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 3;
                current_shape = 5;
            }
        }
        if(type_num == 4){
            int[][] new_block = new int[][]{
                    {1, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 4;
                current_shape = 5;
            }
        }
    }
    // rotation types for shape 6
    // 1 type for shape 6
    public void shape_6_types(int type_num){
        if(type_num == 1){
            int[][] new_block = new int[][]{
                    {0, 0, 0},
                    {1, 1, 0},
                    {1, 1, 0}
            };
            temp_block = new_block;
            if(check_overlap(cur_row,cur_col)) {
                cur_block = new_block;
                current_rotation_type = 1;
                current_shape = 6;
            }
        }
    }

    // Rotate
    public void rotate(){
        int max_type;
        if(current_shape == 1)
            max_type = 2;
        else if(current_shape == 2 || current_shape == 3 ||
                current_shape == 4 || current_shape == 5)
            max_type = 4;
        else // current_shape == 6
            max_type = 1;

        if(current_shape == 1){
            int next_rotation = current_rotation_type + 1;
            if(next_rotation > max_type)
                next_rotation = 1;
            shape_1_types(next_rotation);
        }

        if(current_shape == 2){
            int next_rotation = current_rotation_type + 1;
            if(next_rotation > max_type)
                next_rotation = 1;
            shape_2_types(next_rotation);
        }

        if(current_shape == 3){
            int next_rotation = current_rotation_type + 1;
            if(next_rotation > max_type)
                next_rotation = 1;
            shape_3_types(next_rotation);
        }

        if(current_shape == 4){
            int next_rotation = current_rotation_type + 1;
            if(next_rotation > max_type)
                next_rotation = 1;
            shape_4_types(next_rotation);
        }

        if(current_shape == 5){
            int next_rotation = current_rotation_type + 1;
            if(next_rotation > max_type)
                next_rotation = 1;
            shape_5_types(next_rotation);
        }

        if(current_shape == 6){
            int next_rotation = current_rotation_type + 1;
            if(next_rotation > max_type)
                next_rotation = 1;
            shape_6_types(next_rotation);
        }
    }

    // check if there is a line filled fully with blocks
    // return true if it is filled with block and need to clear
    // return false if there is some empty space
    public boolean filled(int current_row){
        // i = 5 because there are 5 blocks in a row.
        for(int i = 0; i < 5; i++){
            if(system[current_row][i] != 10) {
                // System.out.println(current_row + " will not need to be cleared");
                return false;
            }
        }
        // System.out.println(current_row + " need to clear");
        return true;
    }

    // to check if the line is filled with blocks
    public void check_all_rows(){
        boolean all_clear = false;
        while(!all_clear){
            // 13 columns, 5 rows
            for (int i = 0; i < 13; i++){
                boolean need_clear = filled(i);
                if(need_clear){
                    clear(i);
                    all_clear = false;
                    break;
                }
                else
                    all_clear = true;
            }
        }
    }

    // clear the line which is filled with blocks
    public void clear(int current_row){
        int[][] temp = new int[current_row][5];
        for (int i = 0; i < current_row; i++){
            for (int j = 0; j < 5; j++){
                temp[i][j] = system[i][j];
            }
        }
        for (int i = 1; i <= current_row; i++){
            for (int j = 0; j < 5; j++){
                system[i][j] = temp[i - 1][j];
            }
        }
        for (int i = 0; i < 5; i++)
            system[0][i] = 0;
    }
}
