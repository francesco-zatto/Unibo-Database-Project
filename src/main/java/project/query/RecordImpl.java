package project.query;

import java.util.Collections;
import java.util.List;

public class RecordImpl implements Record {

    private final List<String> elements;

    public RecordImpl(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public List<String> getElements() {
        return Collections.unmodifiableList(this.elements);
    }
    
}
