package io.github.doc2json.doc2json_core.mapper.spreadsheet.odftoolkit;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonRow;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSheet;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OdfSpreadsheetDocumentToDoc2JsonSpreadsheetMapper {

    private final OdfTableCellToDoc2JsonCellMapper cellMapper;

    public Doc2JsonSpreadsheet toDoc2JsonSpreadsheet(OdfSpreadsheetDocument spreadsheet) {

        final Doc2JsonSpreadsheet doc2JsonSpreadsheet = Doc2JsonSpreadsheet.builder().build();

        spreadsheet.getSpreadsheetTables().forEach(sheet -> {
             final var doc2JsonSheet = Doc2JsonSheet.builder().build();
             doc2JsonSpreadsheet.getSheets().add(doc2JsonSheet);

                sheet.getRowList().forEach(row -> {
                    final var doc2JsonRow = Doc2JsonRow.builder().build();
                    doc2JsonSheet.getRows().add(doc2JsonRow);
                    
                    for (int i = 0; i < row.getCellCount(); i++) {
                        doc2JsonRow.getCells().add(cellMapper.toDoc2JsonCell(row.getCellByIndex(i)));
                    }

                });
        });

        return doc2JsonSpreadsheet;
    }
}
