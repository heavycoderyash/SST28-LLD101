package elevator;

import java.util.*;

public interface DispatchStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, int floor, Direction direction);
}