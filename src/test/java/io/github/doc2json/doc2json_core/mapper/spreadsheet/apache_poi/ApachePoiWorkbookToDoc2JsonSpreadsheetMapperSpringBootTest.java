package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.doc2json.doc2json_core.model.DataType;

@SpringBootTest
class ApachePoiWorkbookToDoc2JsonSpreadsheetMapperSpringBootTest {

    @Autowired
    private ApachePoiWorkbookToDoc2JsonSpreadsheetMapper mapper;

    @Test
    void testtoDoc2JsonSpreadsheetWithOnlyKnownDataTypes() throws IOException {
        final var file = new File(getClass().getClassLoader().getResource("spreadsheet-mixed.xlsx").getFile());

        try (final var fis = new FileInputStream(file); final var workbook = WorkbookFactory.create(fis);) {
            mapper.toDoc2JsonSpreadsheet(workbook).getSheets().forEach(sheet -> {
                sheet.getRows().forEach(row -> {
                    row.getCells().forEach(cell -> {
                        assertEquals(true, !DataType.UNKNOWN.equals(cell.getType()));
                    });
                });
            });
        }

    }
}