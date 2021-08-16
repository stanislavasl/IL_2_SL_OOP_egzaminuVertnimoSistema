package services;


import data.Exam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ExamService {

    private Scanner sc;

    public ExamService(Scanner sc) {
        this.sc = sc;
    }

    public void insertExam(){
        System.out.println("Please insert exam ID:");
        String examID = sc.nextLine();
        System.out.println("Please insert exam title:");
        String examTitle = sc.nextLine();
        System.out.println("Please insert exam type");
        String examType = sc.nextLine();
        LocalDateTime dateAndTime = LocalDateTime.now();
        String dt = dateAndTime.format(DateTimeFormatter.ISO_DATE_TIME);
        Exam exam = new Exam(examID, examTitle, examType, dt);

        String filename = "src/correct_answers/" + examID + ".json";

        List<Exam> examList = List.of(exam);
        FileService fs = new FileService();
        fs.writeExamsToFile(fs, filename, examList);

}









}
