package data;

import java.util.List;

public class Exam {
    private ExamInfo exam;
    private List<Answers> answers;

    public Exam() {
    }

    public Exam(ExamInfo exam, List<Answers> answers) {
        this.exam = exam;
        this.answers = answers;
    }

    public ExamInfo getExam() {
        return exam;
    }

    public void setExam(ExamInfo exam) {
        this.exam = exam;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }
}
