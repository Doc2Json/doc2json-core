package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonRow;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSheet;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ApachePoiWorkbookToDoc2JsonSpreadsheetMapper {

    private final ApachePoiCellToDoc2JsonCellMapper cellMapper;

    public Doc2JsonSpreadsheet toDoc2JsonSpreadsheet(Workbook workbook) {
        final Doc2JsonSpreadsheet doc2JsonSpreadsheet = Doc2JsonSpreadsheet.builder().build();
        final int numberOfSheets = workbook.getNumberOfSheets();

        IntStream.range(0, numberOfSheets)
                .mapToObj(workbook::getSheetAt)
                .forEach(sheet -> {
                    final Doc2JsonSheet doc2JsonSheet = Doc2JsonSheet.builder().build();
                    doc2JsonSpreadsheet.getSheets().add(doc2JsonSheet);

                    for (Row row : sheet) {
                        final Doc2JsonRow doc2JsonRow = Doc2JsonRow.builder().build();
                        doc2JsonSheet.getRows().add(doc2JsonRow);

                        for (Cell cell : row) {
                            doc2JsonRow.getCells().add(cellMapper.toDoc2JsonCell(cell));
                        }
                    }
                });

        return doc2JsonSpreadsheet;
    }
}
