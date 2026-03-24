package snakeandladder;
import java.util.*;

public class GameFactory {
    public static Game createGame(int n, String difficulty, Queue<Player> players) {
        Board board = new Board(n); 
        Dice dice = new Dice();
        Validator validator = difficulty.equalsIgnoreCase("hard") ? new HardValidator() : new EasyValidator();
        return new Game(board, dice, validator, players);
    }
}