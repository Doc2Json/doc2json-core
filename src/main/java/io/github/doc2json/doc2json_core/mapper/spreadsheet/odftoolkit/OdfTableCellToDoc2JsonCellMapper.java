package io.github.doc2json.doc2json_core.mapper.spreadsheet.odftoolkit;

import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;
import io.github.doc2json.doc2json_core.utils.DateUtils;

@Component
public class OdfTableCellToDoc2JsonCellMapper {

    public Doc2JsonCell toDoc2JsonCell(OdfTableCell cell) {
        Object value = null;
        DataType type = null;

        if (cell.getFormula() != null) {
            value = cell.getFormula().replace("of:=", "").replace(".", "").replace("[", "").replace("]", "");
            type = DataType.FORMULA;
        } else {

            switch (cell.getValueType()) {
                case "float":
                    value = cell.getDoubleValue();
                    type = DataType.NUMERIC;
                    break;
                case "currency":
                    value = cell.getCurrencyValue();
                    type = DataType.CURRENCY;
                    break;
                case "percentage":
                    value = cell.getPercentageValue();
                    type = DataType.PERCENTAGE;
                    break;
                case "string":
                    var valueStr = cell.getStringValue();
                    var valueAsDateOpt = DateUtils.parseDate(valueStr);

                    if (valueAsDateOpt.isPresent()) {
                        value = valueAsDateOpt.get();
                        type = DataType.DATE;
                    } else {
                        value = valueStr;
                        type = DataType.STRING;
                    }
                    break;
                case "boolean":
                    value = cell.getBooleanValue();
                    type = DataType.BOOLEAN;
                    break;
                case "date":
                    value = cell.getDateValue().getTime();
                    type = DataType.DATE;
                    break;
                case "time":
                    value = cell.getTimeValue().getTime();
                    type = DataType.TIME;
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
