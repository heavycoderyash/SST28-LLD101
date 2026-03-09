import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile s) {
        return (s.attendancePct < 75) ? Optional.of("attendance below 75") : Optional.empty();
    }
}