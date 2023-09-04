package project.db.api.utilities;

/**
 * Utility class for other queries, like selecting every record, inserting a record 
 * or selecting table's names. 
 */
public class InsertSelectQueryTexts {

    private InsertSelectQueryTexts() {}

    /**
     * @return query text to select names of database's tables
     */
    public static String getSelectTableNames() {
        return "SELECT table_name" +
            "FROM information_schema.tables" + 
            "WHERE table_schema = 'restaurant'";
    }

    /**
     * @param tableName table's name
     * @return query text to select every record of given table
     */
    public static String getSelectEveryRecordFromTable(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    /**
     * @param tableName table's name
     * @param values values of the record to insert
     * @return query text to insert values in a table
     */
    public static String getInsertInTable(String tableName, String values) {
        return "INSERT " + tableName + " VALUES " + values;
    }

    /**
     * @param tableName table's name
     * @return query text to select table's columns data types
     */
    public static String getSelectTableDataTypes(String tableName) {
        return "SELECT DATA_TYPE" + 
            "FROM INFORMATION_SCHEMA.COLUMNS " +
            "WHERE table_schema = 'restaurant' AND table_name = '" + tableName + "'";
    }

    /**
     * @param tableName table's name
     * @return query text to count table's number of columns
     */
    public static String getCountNumberOfColumns(String tableName) {
        return "SELECT count(*)" + 
            "FROM information_schema.columns" +
            "WHERE table_name = '" + tableName + "'";
    }

    /**
     * @param tableName table's name
     * @return query text to get table's columns names
     */
    public static String getColumnNamesFromTable(String tableName) {
        return "SELECT column_name " +
            "FROM information_schema.columns " + 
            "WHERE table_name = '" + tableName + "'";
    }

}