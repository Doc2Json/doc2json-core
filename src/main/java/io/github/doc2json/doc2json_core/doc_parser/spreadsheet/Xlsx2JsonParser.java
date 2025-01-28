package io.github.doc2json.doc2json_core.doc_parser.spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.github.doc2json.doc2json_core.doc_parser.Doc2JsonParser;
import io.github.doc2json.doc2json_core.mapper.ApachePoiWorkbookToDoc2JsonSpreadsheetMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@AllArgsConstructor
@Getter
public class Xlsx2JsonParser implements Doc2JsonParser {

    private final Set<String> supportedExtensions = Set.of("xlsx", "xls");

    @Getter(AccessLevel.NONE)
    private final ApachePoiWorkbookToDoc2JsonSpreadsheetMapper mapper;

    @Override
    public String toJson(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file); final Workbook workbook = WorkbookFactory.create(fis);) {
            return new Gson().toJson(mapper.toDoc2JsonSpreadsheet(workbook));
        }
    }

}
