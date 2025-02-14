package io.github.doc2json.doc2json_core.mapper.spreadsheet.odftoolkit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.doc2json.doc2json_core.model.DataType;

@SpringBootTest
class OdfSpreadsheetToDoc2JsonSpreadsheetSpringBootTest {

    @Autowired
    private OdfSpreadsheetDocumentToDoc2JsonSpreadsheetMapper mapper;

    @Test
    void testtoDoc2JsonSpreadsheetWithOnlyKnownDataTypes() throws Exception {
        final var file = new File(getClass().getClassLoader().getResource("spreadsheet-mixed.ods").getFile());

        try (final var spreadsheet = OdfSpreadsheetDocument.loadDocument(file)) {

            mapper.toDoc2JsonSpreadsheet(spreadsheet).getSheets().forEach(sheet -> {
                sheet.getRows().forEach(row -> {
                    row.getCells().forEach(cell -> {
                        assertEquals(true, !DataType.UNKNOWN.equals(cell.getType()));
                    });
                });
            });
        }

    }
}