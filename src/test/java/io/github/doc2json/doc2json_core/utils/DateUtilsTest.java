package io.github.doc2json.doc2json_core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @Test
    void testIsValidDate() {
        assertTrue(DateUtils.isValidDate("2023-10-10"));
        assertTrue(DateUtils.isValidDate("10/10/2023"));
        assertTrue(DateUtils.isValidDate("10-10-2023"));
        assertTrue(DateUtils.isValidDate("2023.10.10"));
        assertTrue(DateUtils.isValidDate("10.10.2023"));
        assertTrue(DateUtils.isValidDate("October 10, 2023"));
        assertTrue(DateUtils.isValidDate("10 October 2023"));
        assertTrue(DateUtils.isValidDate("2023/10/10"));
        assertFalse(DateUtils.isValidDate("10-2023-10"));
        assertFalse(DateUtils.isValidDate("invalid-date"));
    }

    @Test
    void testParseDate() {
        final var expectedDate = Date.from(LocalDate.of(2023, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        final var convertedDate = DateUtils.parseDate("2023-10-10");
        assertTrue(convertedDate.isPresent());
        assertEquals(expectedDate, convertedDate.get());

        assertEquals(expectedDate, DateUtils.parseDate("10/10/2023").orElse(null));
        assertEquals(expectedDate, DateUtils.parseDate("10-10-2023").orElse(null));
        assertEquals(expectedDate, DateUtils.parseDate("2023.10.10").orElse(null));
        assertEquals(expectedDate, DateUtils.parseDate("10.10.2023").orElse(null));
        assertEquals(expectedDate, DateUtils.parseDate("October 10, 2023").orElse(null));
        assertEquals(expectedDate, DateUtils.parseDate("10 October 2023").orElse(null));
        assertEquals(expectedDate, DateUtils.parseDate("2023/10/10").orElse(null));
        assertFalse(DateUtils.parseDate("10-2023-10").isPresent());
        assertFalse(DateUtils.parseDate("invalid-date").isPresent());
    }
}
