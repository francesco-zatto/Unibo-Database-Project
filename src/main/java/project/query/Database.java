package project.core;

import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;

public interface Database {

    List<String> getTableNames();

    List<List<String>> getTable(String tableName);

    boolean insertInTable(List<String> elements, String table);

    List<String> getQueryValues(RestaurantQuery query);

    Optional<List<List<String>>> runQuery(RestaurantQuery query, List<String> values);

}