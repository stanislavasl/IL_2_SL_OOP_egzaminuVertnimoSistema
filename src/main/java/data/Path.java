package data;

public enum Path {
    CORRECT_ANSWERS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/correct_answers/ca"),
    QUESTIONS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/questions/qs"),
    RESULTS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/results/res"),
    STUDENT_ANSWERS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/student_answers/sa"),
    STUDENTS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/users/students.json"),
    TEACHERS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/users/teachers.json"),
    LITS_OF_ALL_RESULTS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/listOfResults.json"),
    LIST_OF_EXAMS("C:/Users/stanislavas/IdeaProjects/IL_2_SL_OOP_egzaminuVertnimoSistema/src/files/listOfExams.json");

    private final String cataloque;

    Path(String cataloque) {
        this.cataloque = cataloque;
    }

    public String getCataloque() {
        return cataloque;
    }
}
