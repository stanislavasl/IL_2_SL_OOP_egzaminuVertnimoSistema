package data;

import java.util.Map;

public class Result {
    private Person person;
    private ExamInfo examInfo;
    private Map<String, String> answers;

    public Result() {}

    public Result(Person person, ExamInfo examInfo, Map<String, String> answers) {
        this.person = person;
        this.examInfo = examInfo;
        this.answers = answers;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}
