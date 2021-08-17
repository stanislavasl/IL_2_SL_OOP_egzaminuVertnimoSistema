package services;


import data.Answers;
import data.ExamInfo;
import data.Exam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamService {

    private Scanner sc;

    public ExamService(Scanner sc) {
        this.sc = sc;
    }


    public void insertExam(){
        ExamInfo examInfo = infoAboutExam();
        List<Answers> examQuestionsList = examQuestions();
        String filename = "src/correct_answers/" + examInfo.getId() + ".json";
        FileService fs = new FileService();
        Exam e1 = new Exam(examInfo, examQuestionsList);
        fs.writeExamsToFile(fs, filename, e1);
    }


    public void passingTheExam(){

    }



    private ExamInfo infoAboutExam(){
        System.out.println("Please insert exam ID:");
        String examID = sc.nextLine();
        System.out.println("Please insert exam title:");
        String examTitle = sc.nextLine();
        System.out.println("Please insert exam type");
        String examType = sc.nextLine();
        LocalDateTime dateAndTime = LocalDateTime.now();
        String dt = dateAndTime.format(DateTimeFormatter.ISO_DATE_TIME);
        ExamInfo examInfo = new ExamInfo(examID, examTitle, examType, dt);
        return examInfo;
}

    public List<Answers> examQuestions(){
        List<Answers> examQuestionsList = new ArrayList<>();
        System.out.println("Please insert how much questions will the exam have:");
        String quantity = sc.nextLine();
        int n = Integer.parseInt(quantity);
        for (int i = 1; i <= n; i++) {
            System.out.println("Please enter " + i + " question:");
            String question = sc.nextLine();
            System.out.println("Please, insert your answer: ");
            String answer = sc.nextLine();
            examQuestionsList.add(new Answers(question, answer));
        }
        return examQuestionsList;
    }

    private List<String> examAnswers(){
        List<String> examAnswersList = new ArrayList<>();
        System.out.println("Please, insert your answer: ");

        return examAnswersList;
    }





}
