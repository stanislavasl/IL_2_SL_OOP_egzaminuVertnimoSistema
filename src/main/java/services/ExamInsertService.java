package services;

import data.ExamInfo;
import data.Exam;
import data.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExamInsertService {

    private Scanner sc;
    private FileService fs;

    public ExamInsertService(Scanner sc, FileService fs) {
        this.sc = sc;
        this.fs = fs;
    }

    public void insertExam(String userId) {
        /* Unique exam ID*/
        String newExamId = getUniqueExamId();

        /* Basic info about exam*/
        ExamInfo examInfo = infoAboutNewExam(newExamId);

        /* Write short info about the exam in the exams list for future selection*/
        Map<String, String> listOfExams = new HashMap<>();
        listOfExams.put(examInfo.getId(), examInfo.getType() + "_" + examInfo.getTitle());
        String listOfExamsFilename = Path.CORRECT_ANSWERS.getCataloque() + "listOfExams.json";
        fs.writeExamDataToFile(listOfExamsFilename, listOfExams);

        /* Write questions and correct answers to json.files */
        String answersfilename = Path.CORRECT_ANSWERS.getCataloque() + examInfo.getId() + "_" + examInfo.getType() + "_" + examInfo.getTitle() + ".json";
        String questionsfilename = Path.QUESTIONS.getCataloque() + examInfo.getId() + "_" + examInfo.getType() +"_" + examInfo.getTitle() + ".json";
        Map<String, String> examCorrectAnswers = examQuestionsAndCorrectAnswers(questionsfilename);
        Exam exam = new Exam(examInfo, examCorrectAnswers);
        fs.writeExamsToFile(answersfilename, exam);
    }


    private String getUniqueExamId() {
        String filename = Path.CORRECT_ANSWERS.getCataloque() +"listOfExams.json";
        String newExamId;
        String text = "Please insert new exam ID";
        do {
            System.out.println(text);
            newExamId = sc.nextLine();
            text = "This ID exist please insert another one";
        } while (fs.readExamDataFromFile(filename).get(newExamId) != null);
        return newExamId;
    }

    private ExamInfo infoAboutNewExam(String newExamId) {
        System.out.println("Please insert exam title:");
        String examTitle = sc.nextLine();
        System.out.println("Please insert exam type");
        String examType = sc.nextLine();
        LocalDateTime dateAndTime = LocalDateTime.now();
        String dt = dateAndTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return new ExamInfo(newExamId, examTitle, examType, dt);
    }

    public Map<String, String> examQuestionsAndCorrectAnswers(String filename) {
        Map<String, String> questions = new HashMap<>();
        Map<String, String> answers = new HashMap<>();
        System.out.println("Please insert how much questions will the exam have:");
        String numbOfQuestions = sc.nextLine();
        int n = Integer.parseInt(numbOfQuestions);
        for (int i = 1; i <= n; i++) {
            System.out.println("Please enter " + i + " question:");
            String question = sc.nextLine();
            questions.put(Integer.toString(i), question);
            System.out.println("Please, insert the answer to the " + i + " question: ");
            String answer = sc.nextLine();
            answers.put(Integer.toString(i), answer);
        }
        fs.writeExamDataToFile(filename, questions);
        return answers;
    }
}
