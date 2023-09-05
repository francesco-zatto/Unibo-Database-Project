package project.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import project.db.api.RestaurantQuery;
import project.db.api.utilities.InsertSelectQueryTexts;
import project.db.api.utilities.MetaDataQueries;
import project.tableFactory.StaticTableFactory;

/**
 * Basic implementation of Database to connect with a real database using JDBC.
 */
public class DatabaseImpl implements Database {

    private final Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Constructor with the database's connection
     * @param connection database's connection
     */
    public DatabaseImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getTableNames() {
        final List<String> list;
        String query = InsertSelectQueryTexts.getSelectTableNames();
        try {
            list = executeTableNamesQuery(query);
        } catch (SQLException e) {
            return List.of();
        }
        return list;
    }

    private List<String> executeTableNamesQuery(final String query) throws SQLException {
        startQueryExecution(query);
        return getListFromResultSet();
    }

    private void startQueryExecution(final String query) throws SQLException {
        this.statement = connection.createStatement();
        this.resultSet = statement.executeQuery(query);
    }

    private List<String> getListFromResultSet() throws SQLException {
        final List<String> list = new LinkedList<>();
         while (this.resultSet.next()) {
            list.add(this.resultSet.getString(MetaDataQueries.TABLE_NAME));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table getTable(String tableName) {
        Table table = StaticTableFactory.getEmptyTable();
        String query = InsertSelectQueryTexts.getSelectEveryRecordFromTable(tableName);
        try {
            table = getEveryRecordFromTable(query, tableName);
        } catch (SQLException e) {
            return table;
        }
        return table;
    }

    private Table getEveryRecordFromTable(String query, String tableName) throws SQLException {
        startQueryExecution(query);
        List<String> columnsNames = getColumnNamesFromMetaData(this.resultSet.getMetaData());
        List<Record> recordsList = new LinkedList<>();
        recordsList.add(new RecordImpl(columnsNames));
        recordsList.addAll(getRecordsListFromResultSet());
        return StaticTableFactory.createTable(recordsList);
    }

    private List<String> getColumnNamesFromMetaData(ResultSetMetaData metaData) throws SQLException{
        int columnCount = metaData.getColumnCount();
        return Stream.iterate(1, i -> i <= columnCount, i -> i + 1)
                .map(mapperToColumnName(metaData))
                .toList();
    }

    private Function<Integer, String> mapperToColumnName(ResultSetMetaData metaData) {
        return i -> {
            try {
                return metaData.getColumnName(i);
            } catch (SQLException e) {
                return MetaDataQueries.EMPTY_VALUE;
            }
        };
    }

    private List<Record> getRecordsListFromResultSet() throws SQLException {
        List<Record> recordList = new LinkedList<>();
        while (this.resultSet.next()) {
            recordList.add(getCurrentRecord());
        }
        return recordList;
    }

    private Record getCurrentRecord() throws SQLException {
        int columnCount = this.resultSet.getMetaData().getColumnCount();
        List<String> record = new LinkedList<>();
        for (int i = 1; i <= columnCount; i++) {
            record.add(getCurrentElementToString(i));
        }
        return new RecordImpl(record);
    }

    private String getCurrentElementToString(int i) throws SQLException {
        return Optional.ofNullable(this.resultSet.getObject(i))
                .map(Object::toString)
                .orElse(MetaDataQueries.NULL_VALUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertInTable(Record record, String table) {
        try {
            return executeInsert(record, table);
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean executeInsert(Record record, String table) throws SQLException {
        List<String> recordWithNulls = fromRecordToListWithNulls(record);
        int numberColumns = findNumberOfColumns(table);
        List<Integer> tableTypes = findTableTypes(table);
        String valuesFormat = MetaDataQueries.getValuesFormat(numberColumns);
        String query = InsertSelectQueryTexts.getInsertInTable(table, valuesFormat);
        PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < numberColumns; i++) {
                statement.setObject(i + 1, recordWithNulls.get(i), tableTypes.get(i));
            }
            statement.executeUpdate();
        return true;
    }

    private List<String> fromRecordToListWithNulls(Record record) {
        return record.getElements().stream()
            .map(this::fromEmptyToNull)
            .toList();
    }

    private String fromEmptyToNull(String string) {
        return Optional.of(string)
                .filter(s -> !s.isBlank())
                .orElse(null);
    }

    private int findNumberOfColumns(String table) {
        return getColumnNames(table).size();
    }

    private List<Integer> findTableTypes(String table) {
        String query = InsertSelectQueryTexts.getSelectTableDataTypes(table);
        try {
            startQueryExecution(query);
            return MetaDataQueries.getDataTypes(this.resultSet);
        } catch (SQLException e) {
            return List.of();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getQueryValues(RestaurantQuery query) {
        return query.getRequestedValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Table> runQuery(RestaurantQuery query, List<String> values) {
        try {
            return Optional.of(query.runQuery(this.connection, values));
        } catch (SQLException e) {
            return Optional.empty();
        }       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getColumnNames(String tableName) {
        String queryString = InsertSelectQueryTexts.getColumnNamesFromTable(tableName);
        try {
            return runSelectColumnNames(queryString);
        } catch (SQLException e) {
            return List.of();
        }
    }

    private List<String> runSelectColumnNames(String queryString) throws SQLException {
        List<String> columnsList = new LinkedList<>();
        startQueryExecution(queryString);
        while (this.resultSet.next()) {
            columnsList.add(resultSet.getString(1));
        }
        return columnsList;
    }
    
}
