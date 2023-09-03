package project.tableFactory;

import java.util.List;

import project.query.Record;
import project.query.Table;

/**
 * Class with static method to call to create tables.
 */
public final class StaticTableFactory  {
    
    private static final TableFactory EMPTY_TABLE_FACTORY = new TableFactory.Empty();
    private static TableFactory factory = new TableFactoryImpl();

    private StaticTableFactory() {}

    /**
     * Method to create a table using the current factory. By default it is a TableFactoryImpl.
     * @param records list of records to put in the table
     * @return a new created table
     */
    public static Table createTable(final List<? extends Record> records) {
        return factory.createTable(records);
    }

    /**
     * Setter for the current factory to use to create new tables.
     * @param newFactory factory to set
     */
    public static void setTableFactory(final TableFactory newFactory) {
        factory = newFactory;
    }

    /**
     * Method to create an empty table.
     * @return an empty table
     */
    public static Table getEmptyTable() {
        return EMPTY_TABLE_FACTORY.createTable(List.of());
    }
}
