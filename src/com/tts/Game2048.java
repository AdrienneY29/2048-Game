package com.tts;
package com.codegym.games.game2048;
        import com.codegym.engine.cell.*;


public class Game2048 extends Game { //step 1 and 2
    //initialize the fields here
    private static final int SIDE = 4;
    private int [][] gameField = new int [SIDE][SIDE];

    //calling the methods (this is analogous to main method)
    public void initialize(){ //step 4
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
        moveLeft();
        drawScene();

    }


    private void createGame(){ //step 4
        createNewNumber();
        createNewNumber();
    }
    //
    private void drawScene(){ //step 4
        //iterate thru the combination of squares x and y 16 squares
        //build a nested for loops
        for (int x= 0; x < SIDE; x++){
            for (int y = 0; y < SIDE; y++) {
                setCellColoredNumber(x, y,gameField[y][x]);
            }
        }
    }
    //
    private void createNewNumber(){ //step 5
        int x = getRandomNumber(SIDE);
        int y = getRandomNumber(SIDE);
        if (gameField[x][y] == 0){
            int num = getRandomNumber(10);
            if (num==8) {
                gameField[x][y] = 4;
            }  else {
                gameField[x][y]= 2;
            }
        }  else {
            createNewNumber();
        }
    }
    //

    private Color getColorByValue(int value){ //step 6
        switch(value){
            case 2:
                return Color.YELLOW;
            case 4:
                return Color.RED;
            case 8:
                return Color.BLACK;
            case 16:
                return Color.GREEN;
            case 32:
                return Color.PINK;
            case 64:
                return Color.ORANGE;
            case 128:
                return Color.BLUE;
            case 256:
                return Color.PURPLE;
            case 512:
                return Color.TAN;
            case 1024:
                return Color.AQUA;
            case 2048:
                return Color.MAGENTA;
            default:
                return Color.NONE;

        }
    }
//

    private void setCellColoredNumber(int x, int y, int value){ //step 6
        Color color =  getColorByValue(value);
        String empty;
        if (value >0) {
            empty = "" + value;
        } else {
            empty = "";
        }
        setCellValueEx(x, y, color, empty);
    }
    //
    private boolean compressRow(int [] row){ //step 7
        int position = 0;       //assume no movement in squares
        boolean result = false; // this means no change in squares
        for (int x = 0; x < row.length; x++) {
            if (row[x]>0){   //this is for x number >0;
                if ( x!= position){ //if x is not where it is at, move right;
                    row[position] = row[x]; //this means this new position is now row [x] (example row 2 position, number is 8.)
                    row[x] = 0; // this changes the number in the original spot to 0
                    result = true; //this just means that x has moved to the right and is 0;
                }
                position++;
            }
        }
        return result;
    }
    //
    private boolean mergeRow(int[]row){ // step 8 // build an example array and go thru code to double check example array [256,256,256,0]
        // int current = row[0];  //[256] is initial value
        boolean changed = false;
        for (int i =1; i< SIDE; i++){
            if (row [i-1] == row [i] && row [i] != 0) { //if position changed let i be in new position and that element in not be 0;
                row [i-1] = row [i] * 2; // this is new element value in new position;
                row [i] = 0; // the old element position value is 0;
                changed = true;
            }
        }
        return changed;
    }
    // step 9

    @Override
    public void onKeyPress(Key key){
        switch(key){
            case LEFT:
                moveLeft();
                drawScene();
                break;
            case RIGHT:
                moveRight();
                drawScene();
                break;
            case UP:
                moveUp();
                drawScene();
                break;
            case DOWN:
                moveDown();
                drawScene();
                break;
            default:
                return;
        }
    }


    // 4 move methods
    // private void moveLeft(){ //adrienne attempt here
    //     boolean compressRow(int [] row) = false;
    //     boolean mergeRow(int [] row) = false;

    // for (i = 0; i < SIDE.length; i ++){

    //     if compressRow(int [x]row){

    //     }
    // }

    //
    private void moveLeft(){
        boolean ifCompress = false;
        boolean ifMerge = false;
        boolean needNewNumber = false;

        for (int[] row: gameField){
            ifCompress = compressRow(row);
            ifMerge = mergeRow(row);
            if(ifMerge){
                compressRow(row);
            }
            if(ifCompress || ifMerge){
                needNewNumber = true;
            }
        }
        if(needNewNumber){
            createNewNumber();
        }
    }
    //

    private void moveRight(){
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }
    private void moveUp(){
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }
    private void moveDown(){
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    //step 10
    // we worked on moveLeft()

    // step 11

    private void rotateClockwise(){
        int [][] rotatedMatrix  = new int [SIDE][SIDE];
        for (int i = 0; i< SIDE; i++){
            for (int j = 0;j < SIDE; j++){
                rotatedMatrix[j][gameField.length-1-i] = gameField [i][j];
            }
        }
        gameField = rotatedMatrix;
    }
    // step 12
    //go see moveRight, moveDown and moveUp methods




    //     int position = 0;   //example array [4,4,8,0] new array [8,0,8,0] Adrienne's failed attempt at coding step 8
    //     boolean result = false;
    //     for ( int x =0; x < row.length; x ++) {
    //         if (row [x] >0){
    //             if ( x!= position){
    //                 row[position-1] = row[x]; //this means this new position is now row [x]
    //                 row[x] = x * 2;
    //                  result = true; //this just means that x has moved to the right and is 0;
    //              }
    //             position--;
    //         }
    //         //
    //     }
    //     return result;
    // }

    ////
}

//




