package elevator;

public class Main {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(2, new NearestCarDispatch(), new ScanScheduling(), 700.0);

        System.out.println("--- Scenario 1: Basic Floor Call ---");
        controller.handleFloorCall(5, Direction.UP);

        System.out.println("\n--- Scenario 2: Overload Sensor ---");
        controller.step(); 
        
        controller.getElevators().get(0).updateWeight(950.0); 

        System.out.println("\n--- Scenario 3: Maintenance ---");
        controller.setMaintenance(0, true);
        controller.handleFloorCall(2, Direction.DOWN); 

        System.out.println("\n--- Scenario 4: Global Emergency ---");
        controller.handleGlobalEmergency();
    }
}