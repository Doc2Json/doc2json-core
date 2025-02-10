package io.github.doc2json.doc2json_core.model.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Doc2JsonSheet {
    @Builder.Default
    private final List<Doc2JsonRow> rows = new ArrayList<>();

    public int numberOfRows() {
        return this.rows.size();
    }
}
