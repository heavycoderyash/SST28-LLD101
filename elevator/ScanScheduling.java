package elevator;

import java.util.*;

public class ScanScheduling implements SchedulingStrategy {
    @Override
    public Integer getNextFloor(int currentFloor, Direction direction, TreeSet<Integer> requests) {
        if (requests.isEmpty()) return null;

        Integer next = (direction == Direction.DOWN) ? requests.floor(currentFloor) : requests.ceiling(currentFloor);
        
        return (next != null) ? next : requests.first();
    }
}