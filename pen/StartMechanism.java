package pen;

// Used strategy design pattern
// By making the starting mechanism an interface, I can add new mechanisms without modifying the Pen class.
public interface StartMechanism {
    void open();
    void close();
}