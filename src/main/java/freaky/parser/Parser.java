package freaky.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing strings and dates.
 */
public class Parser {

    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a string as an integer.
     *
     * @param input String representing date and time.
     * @return int.
     * @throws NumberFormatException If parsing fails.
     */
    public static int parseInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input.trim());
    }

    /**
     * Parses a string as LocalDateTime using pattern yyyy-MM-dd HHmm.
     *
     * @param time String representing date and time.
     * @return LocalDateTime object.
     * @throws DateTimeParseException If parsing fails.
     */
    public static LocalDateTime parseLocalDateTime(String time) throws DateTimeParseException {
        return LocalDateTime.parse(time, INPUT_DATE_FORMAT);
    }

}
