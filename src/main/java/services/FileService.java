package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import data.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileService {


    public List<Person> readFileAsList(String filename) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = createFile(filename);
        List<Person> list = new ArrayList<>();
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


    public String checkForId(FileService fs, String filename, String tempUserID) throws JsonProcessingException {
        List<Person> users = fs.readFileAsList(filename);
        String res = null;
        if(users.size() != 0) {
            for (Person p : users) {
                if (tempUserID.equals(p.getId())) {
                    res = p.getPassword();
                    break;
                }
            }
        }
        return res;
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


    public void writeUsersToFile(FileService fs, String filename, List<Person> users) {
        ObjectMapper mapper = new ObjectMapper();
        File file = fs.createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeExamsToFile(FileService fs, String filename, Exam e1) {
        ObjectMapper mapper = new ObjectMapper();
        File file = fs.createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, e1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }










    private void writeToFile(File file, Student student, ExamInfo exam, List<Answers> answersList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> examData = new HashMap<>();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        examData.put(student.getRole(), List.of(student.getId(), student.getName(), student.getSurname()));
        examData.put("exam", exam);
        examData.put("answers", answersList);
        mapper.writeValue(file, examData);

    }






}