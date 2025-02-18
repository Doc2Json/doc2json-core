package io.github.doc2json.doc2json_core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DateUtils {

    public static final Set<String> DATE_PATTERNS = ImmutableSet.of(
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
        for (String pattern : DATE_PATTERNS) {
            try {
                final var sdf = new SimpleDateFormat(pattern, Locale.getDefault());
                sdf.setLenient(false);
                return Optional.of(sdf.parse(dateStr));
            } catch (ParseException e) {
                // Continue to the next pattern
            }
        }

        return Optional.empty();
    }
}
