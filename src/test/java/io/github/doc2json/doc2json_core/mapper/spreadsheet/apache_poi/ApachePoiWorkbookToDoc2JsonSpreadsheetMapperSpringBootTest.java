package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.EnumSet;

import org.apache.poi.ss.usermodel.Workbook;
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
        File file = new File(getClass().getClassLoader().getResource("spreadsheet-mixed.xlsx").getFile());

        final EnumSet<DataType> knownDataTypes = EnumSet.of(DataType.STRING, DataType.NUMERIC, DataType.BOOLEAN,
                DataType.DATE, DataType.FORMULA, DataType.BLANK);

        try (FileInputStream fis = new FileInputStream(file); final Workbook workbook = WorkbookFactory.create(fis);) {
            mapper.toDoc2JsonSpreadsheet(workbook).getSheets().forEach(sheet -> {
                sheet.getRows().forEach(row -> {
                    row.getCells().forEach(cell -> {
                        assertEquals(true, knownDataTypes.contains(cell.getType()));
                    });
                });
            });
        }

    }
}