public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        // Main responsible for instantiating the concrete implementations  and injecting them into the pipeline. 
        // This makes the system flexible because I can swap these implementations here without touching the EvaluationPipeline code.
        IChecker checker = new PlagiarismChecker();
        IGrader grader = new CodeGrader();
        IWriter writer = new ReportWriter();

        EvaluationPipeline pipeline = new EvaluationPipeline(checker, grader, writer);
        pipeline.evaluate(sub);
    }
}

/* initial code
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        new EvaluationPipeline().evaluate(sub);
    }
}
*/