package io.github.doc2json.doc2json_core.model.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Doc2JsonSpreadsheet {
    @Builder.Default
    private final List<Doc2JsonSheet> sheets = new ArrayList<>();

    public int numberOfSheets() {
        return this.sheets.size();
    }
}
