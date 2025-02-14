package io.github.doc2json.doc2json_core.mapper.spreadsheet.odftoolkit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;

class OdfTableCellToDoc2JsonCellMapperTest {

    private OdfTableCellToDoc2JsonCellMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new OdfTableCellToDoc2JsonCellMapper();
    }

    @Test
    void testToDoc2JsonCell_Numeric() {
        OdfTableCell cell = mock(OdfTableCell.class);
        when(cell.getValueType()).thenReturn("float");
        when(cell.getDoubleValue()).thenReturn(123.45);

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals(123.45, result.getValue());
        assertEquals(DataType.NUMERIC, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Currency() {
        OdfTableCell cell = mock(OdfTableCell.class);
        when(cell.getValueType()).thenReturn("currency");
        when(cell.getCurrencyValue()).thenReturn(99.99);

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals(99.99, result.getValue());
        assertEquals(DataType.CURRENCY, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Percentage() {
        OdfTableCell cell = mock(OdfTableCell.class);
        when(cell.getValueType()).thenReturn("percentage");
        when(cell.getPercentageValue()).thenReturn(0.85);

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals(0.85, result.getValue());
        assertEquals(DataType.PERCENTAGE, result.getType());
    }

    @Test
    void testToDoc2JsonCell_String() {
        OdfTableCell cell = mock(OdfTableCell.class);
        when(cell.getValueType()).thenReturn("string");
        when(cell.getStringValue()).thenReturn("test");

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals("test", result.getValue());
        assertEquals(DataType.STRING, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Boolean() {
        OdfTableCell cell = mock(OdfTableCell.class);
        when(cell.getValueType()).thenReturn("boolean");
        when(cell.getBooleanValue()).thenReturn(true);

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals(true, result.getValue());
        assertEquals(DataType.BOOLEAN, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Unknown() {
        OdfTableCell cell = mock(OdfTableCell.class);
        when(cell.getValueType()).thenReturn("unknown");
        when(cell.toString()).thenReturn("error");

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals("error", result.getValue());
        assertEquals(DataType.UNKNOWN, result.getType());
    }
}