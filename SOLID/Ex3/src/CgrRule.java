import java.util.Optional;

public class CgrRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile s) {
        return (s.cgr < 8.0) ? Optional.of("CGR below 8.0") : Optional.empty();
    }
}