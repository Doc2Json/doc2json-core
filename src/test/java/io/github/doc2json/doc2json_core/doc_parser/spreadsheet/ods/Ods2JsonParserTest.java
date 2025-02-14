package io.github.doc2json.doc2json_core.doc_parser.spreadsheet.ods;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;

import io.github.doc2json.doc2json_core.model.DataType;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonCell;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonRow;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSheet;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;

@SpringBootTest
class Ods2JsonParserTest {

        @Autowired
        private Ods2JsonParser ods2JsonParser;

        @Test
        void testToJsonWithUnsupportedFileExtension() {
                File unsupportedFile = new File(getClass().getClassLoader().getResource("test.txt").getFile());

                assertThrows(IllegalArgumentException.class, () -> {
                        ods2JsonParser.toJson(unsupportedFile);
                });
        }

        @Test
        void testToJsonWithOdsAndNumerics() throws Exception {

                File testFile = new File(
                                getClass().getClassLoader().getResource("spreadsheet-numerics.ods").getFile());

                Doc2JsonSpreadsheet mockSpreadsheet = Doc2JsonSpreadsheet.builder().build();
                mockSpreadsheet.getSheets().add(Doc2JsonSheet.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value(1.0).type(DataType.NUMERIC).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value(2.0).type(DataType.NUMERIC).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value(3.0).type(DataType.NUMERIC).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value(4.0).type(DataType.NUMERIC).build());

                String expectedJson = new Gson().toJson(mockSpreadsheet);

                String actualJson = ods2JsonParser.toJson(testFile);

                assertEquals(expectedJson, actualJson);
        }

        @Test
        void testToJsonWithOdsAndStrings() throws Exception {

                File testFile = new File(getClass().getClassLoader().getResource("spreadsheet-strings.ods").getFile());

                Doc2JsonSpreadsheet mockSpreadsheet = Doc2JsonSpreadsheet.builder().build();
                mockSpreadsheet.getSheets().add(Doc2JsonSheet.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value("A").type(DataType.STRING).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value("B").type(DataType.STRING).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value("C").type(DataType.STRING).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value("D").type(DataType.STRING).build());

                String expectedJson = new Gson().toJson(mockSpreadsheet);

                String actualJson = ods2JsonParser.toJson(testFile);

                assertEquals(expectedJson, actualJson);
        }

        @Test
        void testToJsonWithOdsAndBooleans() throws Exception {

                File testFile = new File(
                                getClass().getClassLoader().getResource("spreadsheet-booleans.ods").getFile());

                Doc2JsonSpreadsheet mockSpreadsheet = Doc2JsonSpreadsheet.builder().build();
                mockSpreadsheet.getSheets().add(Doc2JsonSheet.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value(true).type(DataType.BOOLEAN).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value(false).type(DataType.BOOLEAN).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value(false).type(DataType.BOOLEAN).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value(true).type(DataType.BOOLEAN).build());

                String expectedJson = new Gson().toJson(mockSpreadsheet);

                String actualJson = ods2JsonParser.toJson(testFile);

                assertEquals(expectedJson, actualJson);
        }

        @Test
        void testToJsonWithOdsAndDates() throws Exception {

                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                File testFile = new File(getClass().getClassLoader().getResource("spreadsheet-dates.ods").getFile());

                Doc2JsonSpreadsheet mockSpreadsheet = Doc2JsonSpreadsheet.builder().build();
                mockSpreadsheet.getSheets().add(Doc2JsonSheet.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value(sdf.parse("1990-09-13")).type(DataType.DATE).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value(sdf.parse("1990-09-14")).type(DataType.DATE).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value(sdf.parse("1990-09-15")).type(DataType.DATE).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value(sdf.parse("1990-09-16")).type(DataType.DATE).build());

                String expectedJson = new Gson().toJson(mockSpreadsheet);

                String actualJson = ods2JsonParser.toJson(testFile);

                assertEquals(expectedJson, actualJson);
        }

        @Test
        void testToJsonWithOdsAndFormulas() throws Exception {

                File testFile = new File(getClass().getClassLoader().getResource("spreadsheet-formulas.ods").getFile());

                Doc2JsonSpreadsheet mockSpreadsheet = Doc2JsonSpreadsheet.builder().build();
                mockSpreadsheet.getSheets().add(Doc2JsonSheet.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().add(Doc2JsonRow.builder().build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value("SUM(B1:B2)").type(DataType.FORMULA).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(0).getCells()
                                .add(Doc2JsonCell.builder().value(10.0).type(DataType.NUMERIC).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value("AVERAGE(B1:B2)").type(DataType.FORMULA).build());
                mockSpreadsheet.getSheets().get(0).getRows().get(1).getCells()
                                .add(Doc2JsonCell.builder().value(20.0).type(DataType.NUMERIC).build());

                String expectedJson = new Gson().toJson(mockSpreadsheet);

                String actualJson = ods2JsonParser.toJson(testFile);

                assertEquals(expectedJson, actualJson);
        }
}
