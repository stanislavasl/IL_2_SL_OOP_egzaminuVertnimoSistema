package data;

import java.util.List;
import java.util.Map;

public class Exam {
    private ExamInfo exam;
    private Map<String, String> answers;

    public Exam() {
    }

    public Exam(ExamInfo exam, Map<String, String> answers) {
        this.exam = exam;
        this.answers = answers;
    }

    public ExamInfo getExam() {
        return exam;
    }

    public void setExam(ExamInfo exam) {
        this.exam = exam;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}
