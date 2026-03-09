public class AttendanceScanner implements Powerable, Scanner {
    // I removed the dummy temperature and brightness methods. 
    // This prevents the controller from accidentally trying to dim a scanner.

    @Override public void powerOn() { }
    @Override public void powerOff() { }

    @Override public int scanAttendance() { return 3; }
}

/* initial code
public class AttendanceScanner implements SmartClassroomDevice {
    @Override public void powerOn() { }
    @Override public void powerOff() { }
    @Override public void setBrightness(int pct) { }
    @Override public void setTemperatureC(int c) { }
    @Override public int scanAttendance() { return 3; }
    @Override public void connectInput(String port) { }
}
*/