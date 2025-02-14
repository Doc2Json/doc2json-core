package io.github.doc2json.doc2json_core.doc_parser.spreadsheet.ods;

import java.io.File;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.github.doc2json.doc2json_core.doc_parser.Doc2JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@AllArgsConstructor
@Getter
public class Ods2JsonParser implements Doc2JsonParser {

    private final Ods2SpreadsheetParser ods2SpreadsheetParser;

    @Override
    public String toJson(File file) throws Exception {
        return new Gson().toJson(ods2SpreadsheetParser.toSpreadsheet(file));
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return ods2SpreadsheetParser.getSupportedExtensions();
    }

}
