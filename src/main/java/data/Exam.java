package data;

import java.util.Map;

public class Exam {
    private ExamInfo examInfo;
    private Map<String, String> answers;

    public Exam() {
    }

    public Exam(ExamInfo examInfo, Map<String, String> answers) {
        this.examInfo = examInfo;
        this.answers = answers;
    }

   public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo exam) {
        this.examInfo = exam;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }


    @Override
    public String toString() {
        return "Exam{" +
                "examInfo=" + examInfo.toString() +
                ", answers=" + answers +
                '}';
    }
}
