package project.tableFactory;

import java.util.List;

import project.query.Record;
import project.query.Table;

public final class StaticTableFactory  {
    
    private static final TableFactory EMPTY_TABLE_FACTORY = new TableFactory.Empty();
    private static TableFactory factory = new TableFactoryImpl();

    private StaticTableFactory() {}

    public static Table createTable(final List<? extends Record> records) {
        return factory.createTable(records);
    }

    public static void setTableFactory(final TableFactory newFactory) {
        factory = newFactory;
    }

    public static Table getEmptyTable() {
        return EMPTY_TABLE_FACTORY.createTable(List.of());
    }
}
