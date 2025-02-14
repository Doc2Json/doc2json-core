package io.github.doc2json.doc2json_core;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import io.github.doc2json.doc2json_core.doc_parser.spreadsheet.xslx.Xlsx2JsonParser;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Doc2Json {

    private final Xlsx2JsonParser xlsx2JsonParser;

    public String toJson(File file) throws IOException {
        final String fileExtesion = file.getName().substring(file.getName().lastIndexOf(".") + 1);

        if (xlsx2JsonParser.getSupportedExtensions().contains(fileExtesion)) {
            return xlsx2JsonParser.toJson(file);
        }

        throw new UnsupportedOperationException("Not yet implemented for " + fileExtesion + "files.");
    }

}
