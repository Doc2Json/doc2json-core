package io.github.doc2json.doc2json_core.doc_parser.spreadsheet;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.github.doc2json.doc2json_core.doc_parser.Doc2JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@AllArgsConstructor
@Getter
public class Xlsx2JsonParser implements Doc2JsonParser {

    private final Xlsx2SpreadsheetParser xlsx2SpreadSheetParser;

    @Override
    public String toJson(File file) throws IOException {
        return new Gson().toJson(xlsx2SpreadSheetParser.toSpreadsheet(file));
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return xlsx2SpreadSheetParser.getSupportedExtensions();
    }

}
