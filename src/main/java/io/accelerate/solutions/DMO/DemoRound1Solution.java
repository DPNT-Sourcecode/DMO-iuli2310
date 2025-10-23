package io.accelerate.solutions.DMO;

public class DemoRound1Solution {

    // Increment a given integer by 1
    public int increment(int x) {
        return x + 1;
    }

    // Convert the input string to uppercase
    public String toUppercase(String text) {
        return text.toUpperCase();
    }

    // Compose a letter to Santa containing the words "Dear" and "Santa"
    public String letterToSanta() {
        return "Dear Santa,\nI hope you are doing well. I have been good this year!";
    }

    // Count the number of lines in a given multi-line string
    public int countLines(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return text.split("\r\n|\r|\n").length;
    }
}
