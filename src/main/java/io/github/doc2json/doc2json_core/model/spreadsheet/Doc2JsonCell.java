package io.github.doc2json.doc2json_core.model.spreadsheet;

import io.github.doc2json.doc2json_core.model.DataType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Doc2JsonCell {
    private final Object value;
    private final DataType type;
}
