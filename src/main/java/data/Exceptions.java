package data;

public class Exceptions {
    public void checkValue(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ' || value.charAt(i) == '/' || value.charAt(i) == '+' || value.charAt(i) == ',') {
                throw new IllegalArgumentException();
            }
        }
    }
}
