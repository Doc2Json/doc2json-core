package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;

@Component
public class ApachePoiCellToDoc2JsonCellMapper {
    
    public Doc2JsonCell toDoc2JsonCell(Cell cell) {
        return Doc2JsonCell.builder().value(cell.toString())
                .type(DataType.UNKNOWN).build();
    }
}
