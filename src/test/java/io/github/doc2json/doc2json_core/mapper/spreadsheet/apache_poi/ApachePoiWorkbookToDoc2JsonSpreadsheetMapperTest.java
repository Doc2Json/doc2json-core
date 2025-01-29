package io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;

class ApachePoiWorkbookToDoc2JsonSpreadsheetMapperTest {

    @Mock
    private ApachePoiCellToDoc2JsonCellMapper cellMapper;

    @InjectMocks
    private ApachePoiWorkbookToDoc2JsonSpreadsheetMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDoc2JsonSpreadsheet() {
        Workbook workbook = mock(Workbook.class);
        Sheet sheet = mock(Sheet.class);
        Row row = mock(Row.class);
        Cell cell = mock(Cell.class);
        Doc2JsonCell doc2JsonCell = mock(Doc2JsonCell.class);

        when(workbook.getNumberOfSheets()).thenReturn(1);
        when(workbook.getSheetAt(0)).thenReturn(sheet);
        when(sheet.iterator()).thenReturn(Arrays.asList(row).iterator());
        when(row.iterator()).thenReturn(Arrays.asList(cell).iterator());
        when(cellMapper.toDoc2JsonCell(cell)).thenReturn(doc2JsonCell);

        Doc2JsonSpreadsheet result = mapper.toDoc2JsonSpreadsheet(workbook);

        assertEquals(1, result.getSheets().size());
        assertEquals(1, result.getSheets().get(0).getRows().size());
        assertEquals(1, result.getSheets().get(0).getRows().get(0).getCells().size());
        assertEquals(doc2JsonCell, result.getSheets().get(0).getRows().get(0).getCells().get(0));
    }
}