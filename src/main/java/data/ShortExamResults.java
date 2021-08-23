package data;

import java.util.List;

public class ShortExamResults {
    private String examId;
    private String title;
    private List<ShortResultsInfo> studentsResults;

    public ShortExamResults() {}

    public ShortExamResults(String examId, String title, List<ShortResultsInfo> studentsResults) {
        this.examId = examId;
        this.title = title;
        this.studentsResults = studentsResults;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ShortResultsInfo> getStudentsResults() {
        return studentsResults;
    }

    public void setStudentsResults(List<ShortResultsInfo> studentsResults) {
        this.studentsResults = studentsResults;
    }
}
