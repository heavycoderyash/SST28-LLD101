package snakeandladder;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter board size n (for n x n board): ");
        int n = sc.nextInt();

        System.out.print("Enter number of players: ");
        int playerCount = sc.nextInt();
        sc.nextLine(); 
        List<String> names = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            names.add(sc.nextLine());
        }

        Queue<Player> players = PlayerFactory.createPlayers(names);

        Game game = GameFactory.createGame(n, "hard", players);

        game.startGame();
    }
}

// core entities:
// The Board: This manages the n^2 grid and the resolution of 'jumps'. It uses a HashMap to store snakes and ladders.
// The Player: A simple model that tracks the name and current position of each participant.
// The Dice: A utility class that handles the 1–6 randomization logic.
// The Game Orchestrator: This is the 'brain' of the app. It manages the loop and ensures players take turns correctly.
// I used:
// The Strategy Pattern: Instead of putting hard rules inside the Game class, I created a Validator interface. 
// We have an EasyValidator and a HardValidator. This aloows us to add new game rules without ever changing the main game engine.
// The Factory Pattern: I used GameFactory and PlayerFactory. This decouples the creation of objects from the execution of the game. 
// The Main class just asks for a game, and the Factory handles the complex wiring of the board and rules.

// Main asks the user for the board size and the number of players.
// The user types in each player's name, which are collected into a List<String>.
// PlayerFactory converts names into a Queue of Player objects.
// GameFactory uses n to create a Board (which generates random jumps), initializes the Dice, and selects a HardValidator.
// The Game starts a loop
// It pulls a player from the Queue.
// It rolls the Dice.
// It asks the Validator if the move is valid.
// If valid, it asks the Board to resolve the position by checking for snakes or ladders.
// If the player wins, they are added to the leaderboard. If not, they go to the back of the Queue.
// When only one player is left in the Queue, the game ends and prints the final rankings.