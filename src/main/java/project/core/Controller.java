package project.core;

import java.util.List;

import project.db.api.RestaurantQuery;

public interface Controller {

    void tryAccess(String username, String password);

    void loadTableNames();

    void loadTable(String name);

    void insertInTable(List<String> elements, String table);

    void loadQueryValues(RestaurantQuery query);

    void runQuery(RestaurantQuery query, List<String> values);

    void loadColumnsNames(String table);

}