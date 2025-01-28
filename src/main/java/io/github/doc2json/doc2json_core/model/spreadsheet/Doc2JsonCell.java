package io.github.doc2json.doc2json_core.model.spreadsheet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Doc2JsonCell {
    private final String value;
    private final Doc2JsonCellType type;
}
