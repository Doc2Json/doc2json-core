package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonRow;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSheet;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;

@Component
public class ApachePoiWorkbookToDoc2JsonSpreadsheetMapper {

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
                            final Doc2JsonCell doc2JsonCell = Doc2JsonCell.builder().value(cell.toString())
                                    .type(DataType.UNKNOWN).build();
                            doc2JsonRow.getCells().add(doc2JsonCell);
                        }
                    }
                });

        return doc2JsonSpreadsheet;
    }
}
