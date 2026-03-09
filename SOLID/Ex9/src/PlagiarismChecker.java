public class PlagiarismChecker implements IChecker {
    // I added implements IChecker to fulfill the contract required by the EvaluationPipeline.
    public int check(Submission s) {
        return (s.code.contains("class") ? 12 : 40);
    }
}

/* initial code
public class PlagiarismChecker {
    public int check(Submission s) {
        return (s.code.contains("class") ? 12 : 40);
    }
}
*/