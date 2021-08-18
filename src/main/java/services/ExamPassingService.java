package services;

import data.*;

import java.util.*;

public class ExamPassingService {

    private final Scanner sc;
    private final FileService fs;

    public ExamPassingService(Scanner sc, FileService fs) {
        this.sc = sc;
        this.fs = fs;
    }

    public void passingTheExam(String userId) {
        Map<String, String> listOfExams = getListOfExams();

        System.out.println();
        System.out.println("Please select exam from list and enter exam ID:");
        String examId = sc.nextLine();
        System.out.println(examId);

        fillTheAnswers(listOfExams.get(examId), examId, userId);


    }

    private void fillTheAnswers(String examShortInfo, String examId, String userId) {
        String questionsFilename = Path.QUESTIONS.getCataloque() + examId + "_" + examShortInfo + ".json";
        String studentAnswersFilename = Path.STUDENT_ANSWERS.getCataloque() + examId + "_" + examShortInfo + ".json";
        String correctAnswersFilename = Path.CORRECT_ANSWERS.getCataloque() + examId + "_" + examShortInfo + ".json";

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
        /* irasyti teisingu formatu studento atsakymus*/
        writeExamResults(studentAnswersFilename, answers, userId, examId, correctAnswersFilename);
        fs.writeExamDataToFile(studentAnswersFilename, answers);

    }

    private Map<String, String> getListOfExams() {
        String filename = Path.CORRECT_ANSWERS.getCataloque() + "listOfExams.json";
        Map<String, String> listOfExams = fs.readExamDataFromFile(filename);
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

    private void writeExamResults(String studentAnswersFilename, Map<String, String> answers, String userId, String examId, String correctAnswersFilename) {
        Map<String, Person> students = fs.readUserFromFile(Path.USERS.getCataloque() + "student.json");
        Person st = students.get(userId);
        Exam notConsideredExam = fs.readExamsFromFile(correctAnswersFilename);
        ExamInfo ex = notConsideredExam.getExamInfo();
        Answers examAnswers = new Answers(st, ex, answers);
        fs.writeAnswersToFile(studentAnswersFilename, examAnswers);




    }

}
