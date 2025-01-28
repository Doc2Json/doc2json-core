package io.github.doc2json.doc2json_core.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;

 class ApachePoiWorkbookToDoc2JsonSpreadsheetMapperTest {

    @InjectMocks
    private ApachePoiWorkbookToDoc2JsonSpreadsheetMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testToDoc2JsonSpreadsheet() {
        // Mock the Workbook, Sheet, Row, and Cell
        Workbook workbook = mock(Workbook.class);
        Sheet sheet = mock(Sheet.class);
        Row row = mock(Row.class);
        Cell cell = mock(Cell.class);

        // Mock the behavior of the workbook
        when(workbook.getNumberOfSheets()).thenReturn(1);
        when(workbook.getSheetAt(0)).thenReturn(sheet);

        // Mock the behavior of the sheet
        when(sheet.iterator()).thenReturn(new Iterator<Row>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                boolean current = hasNext;
                hasNext = false;
                return current;
            }

            @Override
            public Row next() {
                return row;
            }
        });

        // Mock the behavior of the row
        when(row.iterator()).thenReturn(new Iterator<Cell>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                boolean current = hasNext;
                hasNext = false;
                return current;
            }

            @Override
            public Cell next() {
                return cell;
            }
        });

        // Mock the behavior of the cell
        when(cell.toString()).thenReturn("test");

        // Call the method and assert the result
        Doc2JsonSpreadsheet spreadsheet = mapper.toDoc2JsonSpreadsheet(workbook);
        assertNotNull(spreadsheet);
        assertNotNull(spreadsheet.getSheets());
        assertNotNull(spreadsheet.getSheets().get(0).getRows());
        assertNotNull(spreadsheet.getSheets().get(0).getRows().get(0).getCells());
        assertEquals("test", spreadsheet.getSheets().get(0).getRows().get(0).getCells().get(0).getValue());
    }
}
