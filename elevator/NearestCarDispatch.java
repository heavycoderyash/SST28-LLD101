package elevator;

import java.util.*;

public class NearestCarDispatch implements DispatchStrategy {
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int floor, Direction direction) {
        return elevators.stream()
            .filter(e -> e.getState() != ElevatorState.UNDER_MAINTENANCE)
            .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - floor)))
            .orElse(null);
    }
}