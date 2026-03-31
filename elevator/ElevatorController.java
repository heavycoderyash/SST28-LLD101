package elevator;
import java.util.*;

public class ElevatorController {
    private List<ElevatorCar> elevators = new ArrayList<>();
    private DispatchStrategy dispatchStrategy;

    public ElevatorController(int numCars, DispatchStrategy ds, SchedulingStrategy ss, double weightLimit) {
        this.dispatchStrategy = ds;
        for (int i = 0; i < numCars; i++) {
            ElevatorCar car = new ElevatorCar(i, ss);
            car.registerObserver(new Display("Internal"));
            car.registerObserver(new Display("External"));
            car.registerObserver(new WeightSensor(weightLimit));
            elevators.add(car);
        }
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }

    public void handleFloorCall(int floor, Direction dir) {
        ElevatorCar selected = dispatchStrategy.selectElevator(elevators, floor, dir);
        if (selected != null) {
            selected.addRequest(floor);
        }
    }

    public void handleGlobalEmergency() {
        System.out.println("\nSystem: braodcasting emergency stop");
        for (ElevatorCar car : elevators) {
            car.emergencyHalt();
        }
    }

    public void setMaintenance(int carId, boolean maintenance) {
        ElevatorCar car = elevators.get(carId);
        if (maintenance) {
            car.setState(ElevatorState.UNDER_MAINTENANCE);
            car.emergencyHalt();
            System.out.println("System: car " + carId + " is now out of service.");
        } else {
            car.setState(ElevatorState.IDLE);
            System.out.println("System: car " + carId + " is now back in service.");
        }
    }

    public void step() {
        for (ElevatorCar car : elevators) car.moveStep();
    }
}