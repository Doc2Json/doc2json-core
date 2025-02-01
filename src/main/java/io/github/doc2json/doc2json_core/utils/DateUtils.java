package io.github.doc2json.doc2json_core.utils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DateUtils {

    private static final List<String> DATE_PATTERNS = Arrays.asList(
            "yyyy-MM-dd",
            "dd/MM/yyyy",
            "MM-dd-yyyy",
            "yyyy.MM.dd",
            "dd-MM-yyyy",
            "MM/dd/yyyy",
            "yyyy/MM/dd",
            "dd.MM.yyyy",
            "MMM dd, yyyy",
            "MMMM dd, yyyy",
            "dd MMM yyyy",
            "dd MMMM yyyy");

    public static boolean isValidDate(String dateStr) {
        try {
            org.apache.commons.lang3.time.DateUtils.parseDateStrictly(dateStr, DATE_PATTERNS.toArray(new String[0]));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Optional<Date> parseDate(String dateStr) {
        try {
            return Optional.of(org.apache.commons.lang3.time.DateUtils.parseDateStrictly(dateStr,
                    DATE_PATTERNS.toArray(new String[0])));
        } catch (ParseException e) {
            return Optional.empty();
        }
    }
}
