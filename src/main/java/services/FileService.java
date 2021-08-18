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
import java.util.Map;

public class FileService {


//    public List<Person> readFileAsList(String filename) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        File file = createFile(filename);
//        List<Person> list = new ArrayList<>();
//        if (file.length() != 0) {
//           try {
//                list = mapper.readValue(file, new TypeReference<>() {
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//       return list;
//    }


//    public String checkForId(FileService fs, String filename, String tempUserID) throws JsonProcessingException {
//        List<Person> users = fs.readFileAsList(filename);
//        String res = null;
//        if(users.size() != 0) {
//            for (Person p : users) {
//                if (tempUserID.equals(p.getId())) {
//                    res = p.getPassword();
//                    break;
//                }
//            }
//        }
//        return res;
//    }

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

//
//    public void writeUsersToFile(FileService fs, String filename, List<Person> users) {
//        ObjectMapper mapper = new ObjectMapper();
//        File file = fs.createFile(filename);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        try {
//            mapper.writeValue(file, users);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


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
//        System.out.println(exam.getAnswers().get("1"));
    }


//
//    public void readAnswerFile (){
//        ObjectMapper mapper = new ObjectMapper();
//        FileService fs = new FileService();
//        Exam ddd = new Exam();
//        String filename = "src/correct_answers/oop001.json";
//        File file = fs.createFile(filename);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        try {
//            ddd = mapper.readValue(file, new TypeReference<>() {});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//            System.out.println(ddd.getAnswers().get(0).getQuestion());
//            System.out.println(ddd.getAnswers().get(0).getAnswer());
//            System.out.println(ddd.getAnswers().get(1).getQuestion());
//            System.out.println(ddd.getAnswers().get(0).getAnswer());
//
//
//
//    }

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
        Map<String, String> questionsHashMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
                questionsHashMap = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return questionsHashMap;
    }









//---------------------------------------------------------------------------------------------------------------------
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


//-----------------------------------------------------------------------------------
    public Map<String, String> readExamsListFromFile() {
        String filename = "src/files/correct_answers/listOfExams.json";
        Map<String, String> listOfExamsHashMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = createFile(filename);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (file.length() != 0) {
            try {
                listOfExamsHashMap = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listOfExamsHashMap;
//        System.out.println(hm1.get("oop258").getName());
    }

    public void writeExamsListToFile(Map<String, String> list) {
        String filename = "src/files/correct_answers/listOfExams.json";
        File file = createFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}