package services;

import data.*;

import java.text.DecimalFormat;
import java.util.*;

public class ExamPassingService {

    private final Scanner sc;
    private final FileService fs;

    public ExamPassingService(Scanner sc, FileService fs) {
        this.sc = sc;
        this.fs = fs;
    }

    public void passingTheExam(String userId) {
        List<ExamInfo> listOfExams = getListOfExams();
        System.out.println();
        System.out.println("Please select exam from list and enter exam ID:");
        String examId = sc.nextLine();
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
            System.out.println("Question Nr." + i + ": " + questions.get(Integer.toString(i)));
            System.out.println("Your answer: ");
            String answer = sc.nextLine();
            answers.put(Integer.toString(i), answer);
            if (answers.get(Integer.toString(i)).equals(exam.getAnswers().get(Integer.toString(i)))){
                grade += valueOfOneAnswer;
            }
        }

        Result result = new Result(student, exam.getExamInfo(), answers);
        fs.writeData(studentAnswersFilename, result);

        List<ShortResultsInfo> resultsList = new ArrayList<>();
        List<ShortExamResults> exams = fs.readAllResultsFromFile(Path.LITS_OF_ALL_RESULTS.getCataloque());
        for (ShortExamResults e : exams) {
            if (e.getExamId().equals(examId)){
                resultsList = e.getStudentsResults();
            }
        }
        ShortResultsInfo shortResultsInfo = new ShortResultsInfo(student.getId(), student.getName(), student.getSurname(), grade);
        resultsList.add(shortResultsInfo);
        ShortExamResults shortExamResults = new ShortExamResults(examId, examTitle, resultsList);
        exams.add(shortExamResults);
        fs.writeData(Path.LITS_OF_ALL_RESULTS.getCataloque(), exams);


    }

    public List<ExamInfo> getListOfExams() {
        String filename = Path.LIST_OF_EXAMS.getCataloque();
        List<ExamInfo> listOfExams = fs.readExamDataFromFile(filename);
        listOfExams.forEach(e -> System.out.println("Exam ID: " + e.getId() + "  |  Exam type: " + e.getType() + "  |  Exam title: " + e.getTitle()));
        return listOfExams;
    }


}
