package elevator;

public class Display implements Observer {
    private String type;

    public Display(String type) { this.type = type; }

    @Override
    public void update(int elevatorId, Object data) {
        System.out.println("DISPLAY " + type + " for elevator " + elevatorId + ": " + data);
    }
}