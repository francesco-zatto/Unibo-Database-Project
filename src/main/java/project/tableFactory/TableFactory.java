package project.tableFactory;

import java.util.List;

import project.query.Table;
import project.query.TableImpl;
import project.query.Record;

public interface TableFactory {
    
    Table createTable(List<? extends Record> records);

    public class Empty implements TableFactory {

        @Override
        public Table createTable(List<? extends Record> records) {
            return new TableImpl(List.of());
        }

    }

}
