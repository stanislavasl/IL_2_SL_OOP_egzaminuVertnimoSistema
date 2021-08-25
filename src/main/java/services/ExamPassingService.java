package services;

import data.*;

import java.text.DecimalFormat;
import java.util.*;

public class ExamPassingService {

    private final FileService fs;

    public ExamPassingService(FileService fs) {
        this.fs = fs;
    }

    public void passingTheExam(String userId) {
        List<ExamInfo> listOfExams = getListOfExams();
        System.out.println();
        System.out.println("Please select exam from list and enter exam ID:");
        String examId = fs.getCorrectValue();
        listOfExams.forEach(e -> {if(e.getId().equals(examId)){
            fillTheAnswers(e.getTitle(), e.getType(), examId, userId);
        }});
    }

    private void fillTheAnswers(String examTitle, String examType, String examId, String userId) {
        String questionsFilename = fs.createFilename("qs", examId, examTitle, examType, userId);
        String studentAnswersFilename = fs.createFilename("sa", examId, examTitle, examType, userId);
        String correctAnswersFilename = fs.createFilename("ca", examId, examTitle, examType, userId);

        Person student = fs.readUserFromFile(Path.STUDENTS.getCataloque()).get(userId);
        Map<String, String> answers = new HashMap<>();
        Map<String, String> questions = new HashMap<>();
        questions = fs.readData(questionsFilename, questions);
        Exam exam = fs.readExamsFromFile(correctAnswersFilename);

        DecimalFormat df1 = new DecimalFormat("#.##");
        float valueOfOneAnswer = Float.parseFloat(df1.format((float) 10 / questions.size()));
        float grade = 0;

        for (int i = 1; i <= questions.size(); i++) {
            System.out.println("Question Nr." + i + ": " + questions.get("question Nr." + i));
            System.out.println("Your answer: ");
            String answer = fs.getCorrectValue();
            answers.put("question Nr." + i, answer);
            if (answers.get("question Nr." + i).equals(exam.getAnswers().get("question Nr." + i))){
                grade += valueOfOneAnswer;
            }
        }
        writeExamResults(examTitle, examId, studentAnswersFilename, student, answers, exam, grade);
    }


    public void writeExamResults(String examTitle, String examId, String studentAnswersFilename, Person student, Map<String, String> answers, Exam exam, float grade) {
        Result result = new Result(student, exam.getExamInfo(), answers);
        fs.writeData(studentAnswersFilename, result);

        List<ShortResultsInfo> listOfStudentResultsOfParticularExam = new ArrayList<>();
        List<ShortExamResults> listOfAllExamsPassed = fs.readAllResultsFromFile(Path.LITS_OF_ALL_RESULTS.getCataloque());
        int position = 0;
        boolean searchResult = true;
        if (!listOfAllExamsPassed.isEmpty()) {
            for (ShortExamResults particularExam : listOfAllExamsPassed) {
                if (particularExam.getExamId().equals(examId)) {
                    listOfStudentResultsOfParticularExam = particularExam.getStudentsResults();
                    position = listOfAllExamsPassed.indexOf(particularExam);
                } else {
                    searchResult = false;
                }
            }
        }
        ShortResultsInfo resultOfStudentPassedExam = new ShortResultsInfo(student.getId(), student.getName(), student.getSurname(), Math.round(grade));
        listOfStudentResultsOfParticularExam.add(resultOfStudentPassedExam);
        if (searchResult == false || listOfAllExamsPassed.isEmpty()) {
            ShortExamResults particularExamWithAllResults = new ShortExamResults(examId, examTitle, listOfStudentResultsOfParticularExam);
            listOfAllExamsPassed.add(particularExamWithAllResults);
        } else {
            listOfAllExamsPassed.get(position).setStudentsResults(listOfStudentResultsOfParticularExam);
        }
        fs.writeData(Path.LITS_OF_ALL_RESULTS.getCataloque(), listOfAllExamsPassed);
    }


    public List<ExamInfo> getListOfExams() {
        String filename = Path.LIST_OF_EXAMS.getCataloque();
        List<ExamInfo> listOfExams = fs.readExamDataFromFile(filename);
        listOfExams.forEach(e -> System.out.println("Exam ID: " + e.getId() + "  |  Exam type: " + e.getType() + "  |  Exam title: " + e.getTitle()));
        return listOfExams;
    }


}
