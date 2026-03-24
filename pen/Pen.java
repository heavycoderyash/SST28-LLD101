package pen;

// Made this pen class abstract as it provides a template for all pens 
// which minimizes code duplication by handling common attributes like color, mechanism, state in one place.
public abstract class Pen {
    private Color color;
    private final StartMechanism startMechanism;
    private boolean isOpen = false;

    public Pen(Color color, StartMechanism startMechanism) {
        this.color = color;
        this.startMechanism = startMechanism;
    }

    public void start() {
        startMechanism.open();
        this.isOpen = true;
    }

    public void close() {
        startMechanism.close();
        this.isOpen = false;
    }

    
    // Refill is here to minimize duplication. 
    // Specific pens can override it if they have unique refill logic like ink pens.

    public void refill(Color newColor) {
        System.out.println("Refilling with " + newColor + " ink.");
        this.color = newColor;
    }

    protected void checkOpenState() {
        if (!isOpen) {
            throw new IllegalStateException("Error: Cannot write. The pen is closed!");
        }
    }

    public abstract void write();

    public Color getColor() {
        return color;
    }
}