package project.tableFactory;

import java.util.List;

import project.query.Table;
import project.query.TableImpl;
import project.query.Record;

/**
 * Factory to create tables given a list of records.
 */
public interface TableFactory {
    
    /**
     * Method that create a table filling it with a list of records.
     * @param records list of records to insert in the table
     * @return a table
     */
    Table createTable(List<? extends Record> records);

    /**
     * Class that represents a Factory for empty tables.
     */
    public class Empty implements TableFactory {

        /**
         * The passed records are not used, because it just creates an empty table.
         * {@inheritDoc}
         */
        @Override
        public Table createTable(List<? extends Record> records) {
            return new TableImpl(List.of());
        }

    }

}
