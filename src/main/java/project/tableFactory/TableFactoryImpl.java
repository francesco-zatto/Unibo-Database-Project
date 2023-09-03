package project.tableFactory;

import java.util.List;

import project.query.Table;
import project.query.TableImpl;
import project.query.Record;

/**
 * Simple implementation of a TableFactory.
 */
public class TableFactoryImpl implements TableFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Table createTable(List<? extends Record> records) {
        return new TableImpl(records);
    }
    
}
