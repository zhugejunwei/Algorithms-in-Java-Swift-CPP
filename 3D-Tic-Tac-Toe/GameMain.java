package io.kongming;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by zhugejunwei on 12/4/16.
 * 2D-ttt tutorial: http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
 */
public class GameMain {
    private Board board; // the game board
    private GameState currentState; // the current state of the game (playing or quit, and who is the winner)
    private Seed currentPlayer; // who's turn to play
    
    private static Scanner in = new Scanner(System.in); // input scanner
    
    private static int[][] directions = {{1,0,0}, {0,1,0}, {0,0,1}, // 26 directions
    {-1,0,0}, {0,-1,0},{0,0,-1},
    {1,1,0}, {1,0,1},{0,1,1},
    {-1,-1,0}, {-1,0,-1},{0,-1,-1},
    {1,-1,0}, {-1,1,0},{-1,-1,-1},
    {-1,1,-1}, {0,1,-1},{1,1,-1},
    {1,-1,-1}, {1,0,-1},{0,-1,1},
    {-1,0,1}, {-1,-1,1},{-1,1,1},
    {1,-1,1}, {1,1,1}};
    private static int lastRow = -1, lastCol = -1, lastLevel = -1;
    private static List<int[]> aiSteps = new ArrayList();
    
    /** Constructor to setup the game */
    public GameMain() {
        board = new Board(); // allocate game-board
        
        choosePlayOrNot();
        if (currentState != GameState.PLAYING) return;
        
        initGame();
        
        boolean startNew = false;
        
        do {
            startNew = false;
            playerMove(currentPlayer); // update the content, currentRow, currentCol and currentLevel
            board.paint();
            updateGame(currentPlayer); // update currentState;
            
            // Print message if game is over
            if (currentState == GameState.DRAW) {
                System.out.print("It's Draw!");
                choosePlayOrNot();
                if (currentState == GameState.PLAYING) {
                    initGame();
                    startNew = true;
                }
            } else if (currentState == GameState.X_WON) {
                System.out.println("You Win! Cong!");
                choosePlayOrNot();
                if (currentState == GameState.PLAYING) {
                    initGame();
                    startNew = true;
                }
            } else if (currentState == GameState.O_WON) {
                System.out.println("You Lose! Bye!");
                choosePlayOrNot();
                if (currentState == GameState.PLAYING) {
                    initGame();
                    startNew = true;
                }
            }
            if (startNew) continue;
            
            // Switch player
            currentPlayer = (currentPlayer == Seed.X) ? Seed.O : Seed.X;
        } while (currentState == GameState.PLAYING);
    }
    
    private void choosePlayOrNot() {
        System.out.println("=============================");
        System.out.println("Let's Start to play?");
        System.out.println("1 - Start");
        System.out.println("2 - Quit");
        System.out.println("=============================");
        
        int start = in.nextInt();
        
        if (start == 1) {
            currentState = GameState.PLAYING;
        } else if (start == 2) {
            currentState = GameState.QUIT;
        } else {
            System.out.println("Please type a valid number!");
            choosePlayOrNot();
        }
    }
    
    private void chooseStartPlayer() {
        System.out.println("=============================");
        System.out.println("Choose who start first:");
        System.out.println("1 - YOU first");
        System.out.println("2 - AI first");
        System.out.println("=============================");
        
        int userFirst = in.nextInt();
        if (userFirst == 1) {
            currentPlayer = Seed.X;
        } else if (userFirst == 2) {
            currentPlayer = Seed.O;
        } else {
            System.out.println("Please type a valid number!");
            chooseStartPlayer();
        }
    }
    
    private void initGame() {
        board.init();
        aiSteps.clear();
        lastRow = -1; lastCol = -1; lastLevel = -1;
        chooseStartPlayer();
    }
    
    /** The player with "theSeed" makes one move, with input validation.
     Update Cell's content, Board's currentRow and currentCol. */
    private void playerMove(Seed theSeed) {
        boolean validInput = false;
        do {
            if (theSeed == Seed.X) {
                // if it's my's turn, I will enter a position
                System.out.print("Enter your move (row[1-4] column[1-4] level[1-4]): ");
            } else {
                // if it's ai's turn, ai will move a step
                aiMove();
                validInput = true;
            }
            if (validInput) break;
            
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            int level = in.nextInt() - 1;
            
            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                && board.cells[row][col][level].content == Seed.EMPTY) {
                board.cells[row][col][level].content = theSeed;
                board.currentRow = row;
                board.currentCol = col;
                board.currentLevel = level;
                validInput = true;
                
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                                   + "," + (level + 1) + ") is not valid. Try again...");
            }
        } while (!validInput);
    }
    
    /** ai's move */
    private void aiMove() {
        boolean hasFound = false;
        if (board.currentRow != -1 && board.currentCol != -1 && board.currentLevel != -1) {
            for (int[] dir : directions) {
                int count = 1;
                int row = board.currentRow + dir[0];
                int col = board.currentCol + dir[1];
                int level = board.currentLevel + dir[2];
                while (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                       && board.cells[row][col][level].content == Seed.X) {
                    count++;
                    if (count == 3) {
                        if (row + dir[0] >= 0 && row + dir[0] < Board.ROWS && col + dir[1] >= 0 && col + dir[1] < Board.COLS
                            && level + dir[2] >= 0 && level + dir[2] < Board.LEVELS
                            && board.cells[row + dir[0]][col + dir[1]][level + dir[2]].content == Seed.EMPTY) {
                            board.cells[row + dir[0]][col + dir[1]][level + dir[2]].content = Seed.O;
                            lastRow = board.currentRow = row + dir[0];
                            lastCol = board.currentCol = col + dir[1];
                            lastLevel = board.currentLevel = level + dir[2];
                            hasFound = true;
                        } else if (row - 3 * dir[0] >= 0 && row - 3 * dir[0] < Board.ROWS && col - 3 * dir[1] >= 0 && col - 3 * dir[1] < Board.COLS
                                   && level - 3 * dir[2] >= 0 && level - 3 * dir[2] < Board.LEVELS
                                   && board.cells[row - 3 * dir[0]][col - 3 * dir[1]][level - 3 * dir[2]].content == Seed.EMPTY) {
                            board.cells[row - 3 * dir[0]][col - 3 * dir[1]][level - 3 * dir[2]].content = Seed.O;
                            lastRow = board.currentRow = row - 3 * dir[0];
                            lastCol = board.currentCol = col - 3 * dir[1];
                            lastLevel = board.currentLevel = level - 3 * dir[2];
                            hasFound = true;
                        }
                    }
                    if (hasFound) break;
                    row += dir[0];
                    col += dir[1];
                    level += dir[2];
                }
                if (hasFound) break;
            }
        }
        if (!hasFound) {
            int row, col, level;
            Random rand = new Random();
            if (aiSteps.isEmpty()) {
                while (!hasFound) {
                    row = rand.nextInt(4);
                    col = rand.nextInt(4);
                    level = rand.nextInt(4);
                    if (board.cells[row][col][level].content == Seed.EMPTY) {
                        board.cells[row][col][level].content = Seed.O;
                        lastRow = board.currentRow = row;
                        lastCol = board.currentCol = col;
                        lastLevel = board.currentLevel = level;
                        hasFound = true;
                        aiSteps.add(new int[]{lastRow, lastCol, lastLevel});
                    }
                    if (hasFound) break;
                }
            } else if (lastRow != -1 && lastCol != -1 && lastLevel != -1){
                for (int[] dir : directions) {
                    row = lastRow + dir[0];
                    col = lastCol + dir[1];
                    level = lastLevel + dir[2];
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                        && board.cells[row][col][level].content == Seed.EMPTY) {
                        board.cells[row][col][level].content = Seed.O;
                        lastRow = board.currentRow = row;
                        lastCol = board.currentCol = col;
                        lastLevel = board.currentLevel = level;
                        hasFound = true;
                        aiSteps.add(new int[]{lastRow, lastCol, lastLevel});
                    } else if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                               && board.cells[row][col][level].content == Seed.O) {
                        /** find the next empty cell */
                        while (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                               && board.cells[row][col][level].content == Seed.O) {
                            row += dir[0];
                            col += dir[1];
                            level += dir[2];
                        }
                        if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                            && board.cells[row][col][level].content == Seed.EMPTY) {
                            board.cells[row][col][level].content = Seed.O;
                            lastRow = board.currentRow = row;
                            lastCol = board.currentCol = col;
                            lastLevel = board.currentLevel = level;
                            hasFound = true;
                            aiSteps.add(new int[]{lastRow, lastCol, lastLevel});
                        } else {
                            /** find the next empty cell at another side */
                            row -= dir[0];
                            col -= dir[1];
                            level -= dir[2];
                            while (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                                   && board.cells[row][col][level].content == Seed.O) {
                                row -= dir[0];
                                col -= dir[1];
                                level -= dir[2];
                            }
                            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                                && board.cells[row][col][level].content == Seed.EMPTY) {
                                board.cells[row][col][level].content = Seed.O;
                                lastRow = board.currentRow = row;
                                lastCol = board.currentCol = col;
                                lastLevel = board.currentLevel = level;
                                hasFound = true;
                                aiSteps.add(new int[]{lastRow, lastCol, lastLevel});
                            }
                        }
                    }
                    if (hasFound) break;
                }
            } else {
                for (int[] pos : aiSteps) {
                    for (int[] dir : directions) {
                        row = pos[0] + dir[0];
                        col = pos[1] + dir[1];
                        level = pos[2] + dir[2];
                        if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && level >= 0 && level < Board.LEVELS
                            && board.cells[row][col][level].content == Seed.EMPTY) {
                            board.cells[row][col][level].content = Seed.O;
                            lastRow = board.currentRow = row;
                            lastCol = board.currentCol = col;
                            lastLevel = board.currentLevel = level;
                            hasFound = true;
                            aiSteps.add(new int[]{lastRow, lastCol, lastLevel});
                        }
                        if (hasFound) break;
                    }
                    if (hasFound) break;
                }
            }
        }
    }
    
    /** Update the currentState after the player with "theSeed" has moved */
    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) {  // check for win
            currentState = (theSeed == Seed.X) ? GameState.X_WON : GameState.O_WON;
        } else if (board.isDraw()) {  // check for draw
            currentState = GameState.DRAW;
        }
        // Otherwise, no change to current state (still GameState.PLAYING).
    }
}
