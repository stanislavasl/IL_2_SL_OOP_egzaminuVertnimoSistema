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
        String newExamId = getUniqueExamId();
        ExamInfo examInfo = infoAboutNewExam(newExamId);
        String listOfExamsFilename = Path.LIST_OF_EXAMS.getCataloque();

        List<ExamInfo> listOfExams = fs.readExamDataFromFile(listOfExamsFilename);
        listOfExams.add(examInfo);
        fs.writeData(listOfExamsFilename, listOfExams);


        String answersFilename = fs.createFilename("ca", examInfo.getId(),  examInfo.getTitle(), examInfo.getType(), null);
        String questionsFilename = fs.createFilename("qs", examInfo.getId(),  examInfo.getTitle(),examInfo.getType(), null);
        Map<String, String> examCorrectAnswers = examQuestionsAndCorrectAnswers(questionsFilename);

        Exam exam = new Exam(examInfo, examCorrectAnswers);
        fs.writeData(answersFilename, exam);
    }


    private String getUniqueExamId() {
        String filename = Path.LIST_OF_EXAMS.getCataloque();
        String newExamId;
        boolean tag = true;
        List <ExamInfo> list = fs.readExamDataFromFile(filename);
        String text = "Please insert new exam ID";
        do {
            System.out.println(text);
            newExamId = sc.nextLine();
            for(ExamInfo e : list){
                if (!e.getId().equals(newExamId)){
                    tag = false;
                }
            }
            text = "This ID exist please insert another one";
        } while (tag);
        return newExamId;
    }

    private ExamInfo infoAboutNewExam(String newExamId) {
        System.out.println("Please insert exam title:");
        String examTitle = sc.nextLine();
        System.out.println("Please insert exam type");
        String examType = sc.nextLine();
        LocalDateTime dateAndTime = LocalDateTime.now();
        String dt = dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
        fs.writeData(filename,questions);
        return answers;
    }
}
