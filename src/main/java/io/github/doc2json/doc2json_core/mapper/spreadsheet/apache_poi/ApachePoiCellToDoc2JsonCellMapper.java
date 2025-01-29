package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;

@Component
public class ApachePoiCellToDoc2JsonCellMapper {

    public Doc2JsonCell toDoc2JsonCell(Cell cell) {
        Object value;
        DataType type;

        switch (cell.getCellType()) {
            case NUMERIC:
                value = cell.getNumericCellValue();
                type = DataType.NUMERIC;
                break;
            case STRING:
                value = cell.getStringCellValue();
                type = DataType.STRING;
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                type = DataType.BOOLEAN;
                break;
            case FORMULA:
                value = cell.getCellFormula();
                type = DataType.FORMULA;
                break;
            case BLANK:
                value = "";
                type = DataType.BLANK;
                break;
            default:
                value = cell.toString();
                type = DataType.UNKNOWN;
                break;
        }

        return Doc2JsonCell.builder().value(value).type(type).build();
    }

}
