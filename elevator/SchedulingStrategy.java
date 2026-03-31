package elevator;

import java.util.*;

public interface SchedulingStrategy {
    Integer getNextFloor(int currentFloor, Direction direction, TreeSet<Integer> requests);
}