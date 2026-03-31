package elevator;

import java.util.*;

public class ElevatorCar implements Subject {
    private final int id;
    private int currentFloor = 0;
    private double currentWeight = 0;
    private double weightLimit = 700.0; 
    private ElevatorState state = ElevatorState.IDLE;
    private Direction direction = Direction.IDLE;
    
    private final TreeSet<Integer> requests = new TreeSet<>();
    private final List<Observer> observers = new ArrayList<>();
    private SchedulingStrategy schedulingStrategy;

    public ElevatorCar(int id, SchedulingStrategy strategy) {
        this.id = id;
        this.schedulingStrategy = strategy;
    }

    public void addRequest(int floor) {
        if (this.state == ElevatorState.UNDER_MAINTENANCE) return;
        requests.add(floor);
        notifyObservers("New floor " + floor + " added to queue.");
    }

    public void moveStep() {
        if (state == ElevatorState.UNDER_MAINTENANCE || requests.isEmpty()) return;

        if (currentWeight > weightLimit) {
            notifyObservers("Can not move since weight limit exceeded");
            return; 
        }

        Integer nextStop = schedulingStrategy.getNextFloor(currentFloor, direction, requests);
        
        if (nextStop != null) {
            if (nextStop > currentFloor) {
                currentFloor++;
                direction = Direction.UP;
                state = ElevatorState.MOVING_UP;
            } else if (nextStop < currentFloor) {
                currentFloor--;
                direction = Direction.DOWN;
                state = ElevatorState.MOVING_DOWN;
            } else {
                requests.remove(currentFloor);
                state = ElevatorState.IDLE;
                direction = Direction.IDLE;
                notifyObservers("Arrived at Floor " + currentFloor);
                return;
            }
            notifyObservers("Passing Floor " + currentFloor);
        }
    }

    public void emergencyHalt() {
        this.requests.clear();
        this.state = ElevatorState.IDLE;
        this.direction = Direction.IDLE;
        notifyObservers("emergency stop triggered");
    }

    public void updateWeight(double weight) {
        this.currentWeight = weight;
        notifyObservers(weight); 
    }

    @Override
    public void registerObserver(Observer o) { 
        observers.add(o); 
    }
    @Override
    public void removeObserver(Observer o) { 
        observers.remove(o); 
    }
    @Override
    public void notifyObservers(Object data) {
        for (Observer o : observers) {
            o.update(id, data);
        }
    }

    public int getId() { 
        return id; 
    }
    public int getCurrentFloor() { 
        return currentFloor; 
    }
    public ElevatorState getState() { 
        return state; 
    }
    public void setState(ElevatorState state) { 
        this.state = state; 
    }
}