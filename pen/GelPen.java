package pen;

public class GelPen extends Pen {
    public GelPen(Color color, StartMechanism mechanism) {
        super(color, mechanism);
    }

    @Override
    public void write() {
        checkOpenState();
        System.out.println("Writing with a " + getColor() + " gel pen.");
    }
}