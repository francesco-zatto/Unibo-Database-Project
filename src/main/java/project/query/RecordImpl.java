package project.query;

import java.util.Collections;
import java.util.List;

/**
 * Basic implementation of Record.
 */
public class RecordImpl implements Record {

    private final List<String> elements;

    /**
     * Constructor with a list of new record's elements.
     * @param elements record's elements
     */
    public RecordImpl(List<String> elements) {
        this.elements = elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getElements() {
        return Collections.unmodifiableList(this.elements);
    }
    
}
