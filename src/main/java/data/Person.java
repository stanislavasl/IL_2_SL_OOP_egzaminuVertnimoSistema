package data;

public class Person {
    private final String id;
    private String name;
    private String surname;
    private String role;
    private String password;

    public Person(){
        id = null;
    }
    
    public Person(String id, String password, String name, String surname, String role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(id);
        sb.append("_")
                .append(role)
                .append(name)
                .append("_")
                .append(surname);
        return sb.toString();
    }
}
