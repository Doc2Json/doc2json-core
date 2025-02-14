package io.github.doc2json.doc2json_core.doc_parser;

import java.io.File;
import java.util.Set;

public interface Doc2JsonParser {

    String toJson(File file) throws Exception;

    Set<String> getSupportedExtensions();

}
