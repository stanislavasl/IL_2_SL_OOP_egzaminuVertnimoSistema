package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import data.*;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileService {

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

    public void writeExamsToFile(String filename, Exam exam) {
        File file = createFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, exam);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void writeExamDataToFile(String filename, Map<String, String> list) {
        File file = createFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> readExamDataFromFile(String filename) {
        Map<String, String> dataHashMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
                dataHashMap = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataHashMap;
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

    public void writeUserToFile(String filename, Map<String, Person> users) {
        ObjectMapper mapper = new ObjectMapper();
        File file = createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAnswersToFile(String filename, Answers answers) {
        File file = createFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, answers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}