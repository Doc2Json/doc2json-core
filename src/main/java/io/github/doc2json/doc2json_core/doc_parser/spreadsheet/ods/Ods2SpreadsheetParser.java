package io.github.doc2json.doc2json_core.doc_parser.spreadsheet.ods;

import java.io.File;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.mapper.spreadsheet.odftoolkit.OdfSpreadsheetDocumentToDoc2JsonSpreadsheetMapper;
import io.github.doc2json.doc2json_core.model.spreadsheet.Doc2JsonSpreadsheet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@AllArgsConstructor
@Getter
public class Ods2SpreadsheetParser {

    private final Set<String> supportedExtensions = Set.of("ods");

    @Getter(AccessLevel.NONE)
    private final OdfSpreadsheetDocumentToDoc2JsonSpreadsheetMapper mapper;

    public Doc2JsonSpreadsheet toSpreadsheet(File file) throws Exception {
        String fileExtension = FilenameUtils.getExtension(file.getName());

        if (!supportedExtensions.contains(fileExtension)) {
            throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }

        try (final var spreadsheet = OdfSpreadsheetDocument.loadDocument(file)) {
            return mapper.toDoc2JsonSpreadsheet(spreadsheet);
        }
    }

}
