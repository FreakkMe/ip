package Freaky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// The parser for parsing data
public class Parser {

    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static String[] parse(String input) {
        return input.trim().split(" ", 2);
    }

    public static int parseInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input.trim());
    }

    public static LocalDateTime parseLocalDateTime(String time) throws DateTimeParseException {
        return LocalDateTime.parse(time, INPUT_DATE_FORMAT);
    }

}