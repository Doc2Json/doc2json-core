package io.github.doc2json.doc2json_core.mapper.spreadsheet.odftoolkit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;

class OdfSpreadsheetDocumentToDoc2JsonSpreadsheetMapperTest {

    @Mock
    private OdfTableCellToDoc2JsonCellMapper cellMapper;

    @InjectMocks
    private OdfSpreadsheetDocumentToDoc2JsonSpreadsheetMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDoc2JsonSpreadsheet() {

        OdfSpreadsheetDocument spreadsheet = mock(OdfSpreadsheetDocument.class);
        OdfTable table = mock(OdfTable.class);
        
        OdfTableRow row = mock(OdfTableRow.class);
        when(row.getCellCount()).thenReturn(1);

        OdfTableCell cell = mock(OdfTableCell.class);
        when(cell.getValueType()).thenReturn("string");
        when(cellMapper.toDoc2JsonCell(cell)).thenReturn(Doc2JsonCell.builder().value("test").type(DataType.STRING).build());

        when(spreadsheet.getSpreadsheetTables()).thenReturn(List.of(table));
        when(table.getRowList()).thenReturn(List.of(row));
        when(row.getCellByIndex(0)).thenReturn(cell);


        Doc2JsonSpreadsheet result = mapper.toDoc2JsonSpreadsheet(spreadsheet);


        assertEquals(1, result.getSheets().size());
        assertEquals(1, result.getSheets().get(0).getRows().size());
        assertEquals(1, result.getSheets().get(0).getRows().get(0).getCells().size());
    }
}