package pen;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("--- Testing Gel Pen ---");
        Pen myGelPen = new GelPen(Color.BLUE, new ClickMechanism());

        try {
            myGelPen.write(); 
        } catch (IllegalStateException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        myGelPen.start();
        myGelPen.write(); 
        myGelPen.refill(Color.BLACK); 
        myGelPen.write(); 
        myGelPen.close();

        System.out.println("\n--- Testing Ink Pen ---");
        
        Pen myInkPen = new InkPen(Color.RED, new CapMechanism());
        
        myInkPen.start();
        myInkPen.write(); 
        myInkPen.refill(Color.GREEN);
        myInkPen.close();

        System.out.println("\n--- Testing Ballpoint Pen ---");
        
        Pen myBallPen = new BallpointPen(Color.BLACK, new ClickMechanism());
        myBallPen.start();
        myBallPen.write();
        myBallPen.close();
    }
}

// The main challenge of pen design was handling different types of pens and mechanisms. 
// If we used simple inheritance, we would end up with many combinations of classes.
// To solve this, I used the strategy pattern. 
// I decoupled the starting mechanism into its own interface. 
// This allows any pen to pair with any mechanism at runtime.
// For the pens I created an Abstract Base Class which acts as our single source for shared logic, such as color management and state validation. 
// It also enforces our core business rule like if we try to write() before you start() the pen, the system throws an IllegalStateException.

// Client (Main) chooses a color, a StartMechanism (click or cap), and a pen type. The mechanism is injected into the pen's constructor.
// Pen holds a boolean isOpen.
// Calling .start() triggers the specific mechanism's logic and sets isOpen = true.
// When .write() is called, the Pen first runs checkOpenState(). If isOpen is false, it throws an exception.
// If valid, the specific pen's write() method executes its unique print statement.
// Calling .refill(newColor) updates the internal state and provides visual feedback.