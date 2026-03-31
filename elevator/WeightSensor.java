package elevator;

public class WeightSensor implements Observer {
    private double limit;

    public WeightSensor(double limit) { this.limit = limit; }

    @Override
    public void update(int elevatorId, Object data) {
        if (data instanceof Double weight && weight > limit) {
            System.out.println("Sensor: Elevator " + elevatorId + " is overloaded (" + weight + "kg)");
        }
    }
}