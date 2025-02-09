package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;
import io.github.doc2json.doc2json_core.utils.DateUtils;

@Component
public class ApachePoiCellToDoc2JsonCellMapper {

    public Doc2JsonCell toDoc2JsonCell(Cell cell) {
        Object value = null;
        DataType type = null;

        boolean couldConvertToDate = false;

        try {
            if (DateUtil.isCellDateFormatted(cell)) {
                value = cell.getDateCellValue();
                type = DataType.DATE;
                couldConvertToDate = true;
            }
        } catch (Exception e) {
            // do nothing, since it couldn't convert to date, it will try to convert to other types
        }

        if (!couldConvertToDate) {
            switch (cell.getCellType()) {
                case NUMERIC:
                    value = cell.getNumericCellValue();
                    type = DataType.NUMERIC;
                    break;
                case STRING:
                    var valueStr = cell.getStringCellValue();
                    var valueAsDateOpt = DateUtils.parseDate(valueStr);

                    if (valueAsDateOpt.isPresent()) {
                        value = valueAsDateOpt.get();
                        type = DataType.DATE;
                    } else {
                        value = valueStr;
                        type = DataType.STRING;
                    }
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
        }

        return Doc2JsonCell.builder().value(value).type(type).build();
    }

}
