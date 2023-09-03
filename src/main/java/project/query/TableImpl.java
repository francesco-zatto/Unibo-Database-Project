package project.query;

import java.util.List;

public class TableImpl implements Table {

    private final List<Record> records;

    public TableImpl(final List<Record> records) {
        this.records = records;
    }

    @Override
    public List<Record> getRecords() {
        return this.records;
    }
    
}
