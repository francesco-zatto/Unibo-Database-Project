package project.tableFactory;

import java.util.List;

import project.query.Table;
import project.query.TableImpl;
import project.query.Record;

public class TableFactoryImpl implements TableFactory {

    @Override
    public Table createTable(List<? extends Record> records) {
        return new TableImpl(records);
    }
    
}
