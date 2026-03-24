package pen;

public class InkPen extends Pen {
    public InkPen(Color color, StartMechanism mechanism) {
        super(color, mechanism);
    }

    @Override
    public void write() {
        checkOpenState();
        System.out.println("Writing with a " + getColor() + " ink flow pen.");
    }

    // ink flow requires specific cleaning and refilling process unlike simple cartridge swaps so we override refill
    @Override
    public void refill(Color newColor) {
        System.out.println("Cleaning and refilling the pen with " + newColor + " ink.");
        super.refill(newColor);
    }
}