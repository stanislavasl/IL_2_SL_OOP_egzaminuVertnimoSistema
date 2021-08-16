package data;

import java.time.LocalDateTime;

public class Exam {

    private final String id;
    private String title;
    private String type;
    private String dateAndTime;

    public Exam() {
        id = null;
    }

    public Exam(String id, String title, String type, String dateAndTime) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.dateAndTime = dateAndTime;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateAndTime() {return dateAndTime; }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
