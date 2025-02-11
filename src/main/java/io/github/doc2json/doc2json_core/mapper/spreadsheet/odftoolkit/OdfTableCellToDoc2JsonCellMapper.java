package io.github.doc2json.doc2json_core.mapper.spreadsheet.odftoolkit;

import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;

@Component
public class OdfTableCellToDoc2JsonCellMapper {

    public Doc2JsonCell toDoc2JsonCell(OdfTableCell cell) {
        Object value = null;
        DataType type = null;

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
                value = cell.getStringValue();
                type = DataType.STRING;
                break;
            case "boolean":
                value = cell.getBooleanValue();
                type = DataType.BOOLEAN;
                break;
            case "date":
                value = cell.getDateValue().getTime();
                type = DataType.DATE;
                break;
            // TODO: add formula type
            default:
                value = cell.toString();
                type = DataType.UNKNOWN;
                break;
        }

        return Doc2JsonCell.builder().value(value).type(type).build();
    }

}
