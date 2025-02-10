package io.github.doc2json.doc2json_core.doc_parser.spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.mapper.spreadsheet.apache_poi.ApachePoiWorkbookToDoc2JsonSpreadsheetMapper;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@AllArgsConstructor
@Getter
public class Xlsx2SpreadsheetParser {

    private final Set<String> supportedExtensions = Set.of("xlsx", "xls");

    @Getter(AccessLevel.NONE)
    private final ApachePoiWorkbookToDoc2JsonSpreadsheetMapper mapper;

    public Doc2JsonSpreadsheet toSpreadsheet(File file) throws IOException {
        String fileExtension = FilenameUtils.getExtension(file.getName());

        if (!supportedExtensions.contains(fileExtension)) {
            throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }

        try (FileInputStream fis = new FileInputStream(file); final Workbook workbook = WorkbookFactory.create(fis);) {
            return mapper.toDoc2JsonSpreadsheet(workbook);
        }
    }

}
