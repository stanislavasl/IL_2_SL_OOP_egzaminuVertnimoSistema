package services;

import data.Exam;

import java.util.*;

public class ExamPassingService {

    private Scanner sc;
    private FileService fs;

    public ExamPassingService(Scanner sc, FileService fs) {
        this.sc = sc;
        this.fs = fs;
    }

    public void passingTheExam() {
        Map<String, String> listOfExams = getListOfExams();

        System.out.println();
        System.out.println("Please select exam from list and enter exam ID:");
        String examId = sc.nextLine();
        System.out.println(examId);

        fillTheAnswers(listOfExams.get(examId), examId);
    }

    private void fillTheAnswers(String examShortInfo, String examId) {
        String questionsFilename = "src/files/questions/" + examId + "_" + examShortInfo + ".json";
        String studentAnswersFilename = "src/files/student_answers/" + examId + "_" + examShortInfo + ".json";
        String correctAnswersFilename = "src/files/correct_answers/" + examId + "_" + examShortInfo + ".json";

        Map<String, String> answers = new HashMap<>();
        Map<String, String> questions = fs.readExamDataFromFile(questionsFilename);
        Exam exam = fs.readExamsFromFile(correctAnswersFilename);

        float valueOfOneAnswer = (float) 10 / questions.size();
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
        fs.writeExamDataToFile(studentAnswersFilename, answers);
        System.out.println(grade);
    }

    private Map<String, String> getListOfExams() {
        Map<String, String> listOfExams = fs.readExamsListFromFile();
        Set set = listOfExams.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.println("List of exams");
            System.out.print("Exam unique ID: "+ mentry.getKey() + "   |    Exam title: ");
            System.out.println(mentry.getValue());
        }
        return listOfExams;
    }

}
