package data;

public enum Path {
    CORRECT_ANSWERS("src/files/correct_answers/"),
    QUESTIONS("src/files/questions/"),
    RESULTS("src/files/results/results.json"),
    STUDENT_ANSWERS("src/files/student_answers/"),
    USERS("src/files/users/");

    private final String cataloque;

    Path(String cataloque) {
        this.cataloque = cataloque;
    }

    public String getCataloque() {
        return cataloque;
    }
}
