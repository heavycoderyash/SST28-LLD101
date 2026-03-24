package pen;

public class BallpointPen extends Pen {
    public BallpointPen(Color color, StartMechanism mechanism) {
        super(color, mechanism);
    }

    @Override
    public void write() {
        checkOpenState();
        System.out.println("Writing with a " + getColor() + " ballpoint pen.");
    }
}