import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile s) {
        return (s.earnedCredits < 20) ? Optional.of("credits below 20") : Optional.empty();
    }
}