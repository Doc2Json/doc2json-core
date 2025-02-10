package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;

class ApachePoiCellToDoc2JsonCellMapperTest {

    private ApachePoiCellToDoc2JsonCellMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ApachePoiCellToDoc2JsonCellMapper();
    }

    @Test
    void testToDoc2JsonCell_Numeric() {
        Cell cell = mock(Cell.class);
        when(cell.getCellType()).thenReturn(CellType.NUMERIC);
        when(cell.getNumericCellValue()).thenReturn(123.45);

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals(123.45, result.getValue());
        assertEquals(DataType.NUMERIC, result.getType());
    }

    @Test
    void testToDoc2JsonCell_String() {
        Cell cell = mock(Cell.class);
        when(cell.getCellType()).thenReturn(CellType.STRING);
        when(cell.getStringCellValue()).thenReturn("test");

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals("test", result.getValue());
        assertEquals(DataType.STRING, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Boolean() {
        Cell cell = mock(Cell.class);
        when(cell.getCellType()).thenReturn(CellType.BOOLEAN);
        when(cell.getBooleanCellValue()).thenReturn(true);

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals(true, result.getValue());
        assertEquals(DataType.BOOLEAN, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Formula() {
        Cell cell = mock(Cell.class);
        when(cell.getCellType()).thenReturn(CellType.FORMULA);
        when(cell.getCellFormula()).thenReturn("SUM(A1:A10)");

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals("SUM(A1:A10)", result.getValue());
        assertEquals(DataType.FORMULA, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Blank() {
        Cell cell = mock(Cell.class);
        when(cell.getCellType()).thenReturn(CellType.BLANK);

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals("", result.getValue());
        assertEquals(DataType.BLANK, result.getType());
    }

    @Test
    void testToDoc2JsonCell_Unknown() {
        Cell cell = mock(Cell.class);
        when(cell.getCellType()).thenReturn(CellType.ERROR);
        when(cell.toString()).thenReturn("error");

        Doc2JsonCell result = mapper.toDoc2JsonCell(cell);

        assertEquals("error", result.getValue());
        assertEquals(DataType.UNKNOWN, result.getType());
    }
}