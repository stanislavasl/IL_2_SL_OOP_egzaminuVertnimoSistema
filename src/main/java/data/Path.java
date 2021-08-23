package data;

public enum Path {
    CORRECT_ANSWERS("src/files/correct_answers/ca"),
    QUESTIONS("src/files/questions/qs"),
    RESULTS("src/files/results/res"),
    STUDENT_ANSWERS("src/files/student_answers/sa"),
    STUDENTS("src/files/users/students.json"),
    TEACHERS("src/files/users/teachers.json"),
    LITS_OF_ALL_RESULTS("src/files/listOfResults.json"),
    LIST_OF_EXAMS("src/files/listOfExams.json");

    private final String cataloque;

    Path(String cataloque) {
        this.cataloque = cataloque;
    }

    public String getCataloque() {
        return cataloque;
    }
}
