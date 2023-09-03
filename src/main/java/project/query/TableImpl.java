package project.query;

import java.util.List;

/**
 * Basic implementation of Table.
 */
public class TableImpl implements Table {

    private final List<?extends Record> records;

    /**
     * Constructor with a given list of records
     * @param records list of records
     */
    public TableImpl(final List<? extends Record> records) {
        this.records = records;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record getColumnsNames() {
        return this.records.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Record> getRecords() {
        return (List<Record>) this.records.subList(1, this.records.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record getRecord(final int index) {
        final String messageError = "Wrong index! Records start from 1!";
        checkIndex(index, messageError);
        return this.records.get(index);
    }

    private void checkIndex(final int index, final String messageError) {
        if (index < 1 || index >= this.records.size()) {
            throw new IndexOutOfBoundsException(messageError);
        }
    }
    
}
