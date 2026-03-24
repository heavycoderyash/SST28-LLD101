package snakeandladder;
import java.util.*;

public class Board {
    private final int size;
    private final Map<Integer, Jump> jumps;

    public Board(int n) {
        this.size = n * n;
        this.jumps = new HashMap<>();
        initializeRandomJumps(n);
    }

    private void initializeRandomJumps(int n) {
        Random rand = new Random();
        Set<Integer> occupied = new HashSet<>();

        for (int i = 0; i < n; i++) {
            createJump(true, rand, occupied);
            createJump(false, rand, occupied); 
        }
    }

    private void createJump(boolean isSnake, Random rand, Set<Integer> occupied) {
        while (true) {
            int start = rand.nextInt(size - 2) + 2; 
            if (occupied.contains(start)) continue;

            int end = isSnake ? rand.nextInt(start - 1) + 1 : rand.nextInt(size - start - 1) + start + 1;
            jumps.put(start, new Jump(start, end));
            occupied.add(start);
            break;
        }
    }

    public int resolvePosition(int position) {
        if (jumps.containsKey(position)) {
            Jump jump = jumps.get(position);
            System.out.println((jump.getEnd() > jump.getStart() ? "Ladder " : "Snake ") + " at " + position + " -> " + jump.getEnd());
            return jump.getEnd();
        }
        return position;
    }

    public int getSize() { return size; }
}