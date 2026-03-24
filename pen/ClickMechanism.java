package pen;

public class ClickMechanism implements StartMechanism {
    @Override
    public void open() {
        System.out.println("Clicking to extend the nib");
    }

    @Override
    public void close() {
        System.out.println("Clicking to retract the nib");
    }
}