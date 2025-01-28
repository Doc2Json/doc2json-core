package io.github.doc2json.doc2json_core.model.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Doc2JsonRow {
    @Builder.Default
    private List<Doc2JsonCell> cells = new ArrayList<>();
}
