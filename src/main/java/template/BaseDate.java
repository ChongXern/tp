package template;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

public class BaseDate {
    public static DateTimeFormatter formatter = null;
    private static final List<String> dateFormats = List.of(
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd:MM:yyyy",
            "ddMMyyyy",
            "ddMMyy",
            "MMM dd yyyy");

    private static final List<String> timeFormats = List.of(
            "HH:mm",
            "HHmm",
            "hh:mma");

    private static final ArrayList<String> dateTimeFormats = dateTimeVary();

    private LocalDateTime dateTime = null;

    public BaseDate(String args) {
        args = args.strip();
        if (!args.contains(" ")) {
            String defaultTime = " 0000";
            args = args + defaultTime;
        }
        for (String format : dateTimeFormats) {
            try {
                formatter = DateTimeFormatter.ofPattern(format);
                dateTime = LocalDateTime.parse(args, formatter);
            } catch (DateTimeParseException ignored) {
            }

        }
    }

    private static ArrayList<String> dateTimeVary() {
        ArrayList<String> varyList = new ArrayList<>();
        for (String dateFormat : dateFormats) {
            for (String timeFormat : timeFormats) {
                varyList.add(String.format("%s %s", dateFormat, timeFormat));
                varyList.add(String.format("%s %s", timeFormat, dateFormat));
            }
        }
        return varyList;
    }

    @Override
    public String toString() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma", Locale.US);
        return String.format("%s", dateTime.format(formatter));
    }

    public boolean equals(BaseDate otherDate) {
        if (otherDate != null && this.dateTime != null) {
            return this.dateTime.toLocalDate().equals(otherDate.dateTime.toLocalDate());
        }
        return false;
    }

    public boolean isBefore(BaseDate otherDate) {
        if (otherDate != null && this.dateTime != null) {
            return this.dateTime.isBefore(otherDate.dateTime);
        }
        return false;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
