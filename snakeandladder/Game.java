package snakeandladder;
import java.util.*;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Validator validator;
    private final Queue<Player> players;
    private final List<String> leaderboard;

    public Game(Board board, Dice dice, Validator validator, Queue<Player> players) {
        this.board = board;
        this.dice = dice;
        this.validator = validator;
        this.players = players;
        this.leaderboard = new ArrayList<>();
    }

    public void startGame() {
        System.out.println("--- Game Started ---");

        while (players.size() > 1) {
            Player player = players.poll();
            boolean finishedTurn = playTurn(player);

            if (finishedTurn) {
                leaderboard.add(player.getName());
                System.out.println(player.getName() + " reached the end!");
            } else {
                players.offer(player); 
            }
        }

        printFinalResults();
    }

    private boolean playTurn(Player player) {
        int consecutiveSixCount = 0;
        boolean rollAgain = true;

        while (rollAgain) {
            int diceValue = dice.roll();
            System.out.println(player.getName() + " rolled " + diceValue);

            if (!validator.validateMove(player.getCurrentPosition(), diceValue, board.getSize())) {
                System.out.println("Exceeds board size. Stays at " + player.getCurrentPosition());
                return false; 
            }

            int nextPos = board.resolvePosition(player.getCurrentPosition() + diceValue);
            player.setCurrentPosition(nextPos);
            System.out.println(player.getName() + " is now at " + nextPos);

            if (validator.hasWon(nextPos, board.getSize())) {
                return true;
            }

            consecutiveSixCount = (diceValue == 6) ? consecutiveSixCount + 1 : 0;
            rollAgain = validator.canRollAgain(diceValue, consecutiveSixCount);
            if (rollAgain) System.out.println(player.getName() + " gets another turn!");
        }
        return false;
    }

    private void printFinalResults() {
        System.out.println("\n--- Final Rankings ---");
        for (int i = 0; i < leaderboard.size(); i++) {
            System.out.println((i + 1) + ". " + leaderboard.get(i));
        }
        if (!players.isEmpty()) {
            System.out.println("Last player: " + players.poll().getName());
        }
    }
}