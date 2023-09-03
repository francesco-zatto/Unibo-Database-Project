package project.query;

import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;

public interface Database {

    List<String> getTableNames();

    Table getTable(String tableName);

    boolean insertInTable(Record record, String table);

    List<String> getQueryValues(RestaurantQuery query);

    Optional<Table> runQuery(RestaurantQuery query, List<String> values);

    List<String> getColumnNames(String tableName);

}