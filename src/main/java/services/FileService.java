package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import data.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileService {

    public String createFilename(String appendix, String examId, String title, String type, String studentId) {
        String filename = "";
        switch (appendix) {
            case "ca" -> filename = Path.CORRECT_ANSWERS.getCataloque() + "_" + examId + "_" + type + "_" + title + ".json";
            case "qs" -> filename = Path.QUESTIONS.getCataloque() + "_" + examId + "_" + type + "_" + title + ".json";
            case "sa" -> filename = Path.STUDENT_ANSWERS.getCataloque() + "_" + examId + "_" + type + "_" + title + studentId +".json";
            case "rs" -> filename = Path.RESULTS.getCataloque() + "_" + examId + "_" + type + "_" + title + studentId +".json";
        }
//        File file = createFile(filename);
        return filename;
    }

    public File createFile(String filename) {
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Cannot create " + filename + " file.");
        }
        return file;
    }

    public Exam readExamsFromFile(String filename) {
        File file = createFile(filename);
        Exam exam = new Exam();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
                exam = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return exam;
    }

    public List<ExamInfo> readExamDataFromFile(String filename) {
        List<ExamInfo> dataList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
                dataList = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public Map<String, Person> readUserFromFile(String filename) {
        Map<String, Person> usersHashMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
                usersHashMap = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usersHashMap;
    }

    public List<ShortExamResults> readAllResultsFromFile(String filename) {
        List<ShortExamResults> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
                list = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public <T> T readData(String filename, T t) {
        File file = createFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
               t = mapper.readValue(file, new TypeReference<>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public <T> void writeData(String filename, T t) {
        File file = createFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCorrectValue() {
        Scanner sc = new Scanner(System.in);
        Exceptions ex = new Exceptions();
        String value;
        while(true) {
            try {
                System.out.println("iveskite reiksme:");
                value = sc.nextLine();
                ex.checkValue(value);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Value is empty or contains invalid characters! Please enter another one.");
            }
        }
        return value;
    }

}