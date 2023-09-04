package project.db.api.utilities;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MetaDataQueries {

    public static String NULL_VALUE = "NULL";

    public static String TABLE_NAME = "table_name";

    public static String EMPTY_VALUE = "";

    public static List<Integer> getDataTypes(ResultSet resultSet) throws SQLException {
        List<Integer> tableTypes = new LinkedList<>();
        while (resultSet.next()) {
            tableTypes.add(getJDBCType(resultSet));
        }
        return tableTypes;
    }

    private static Integer getJDBCType(ResultSet resultSet) throws SQLException {
        if (resultSet.getString(1).equals("int")) {
            return JDBCType.INTEGER.getVendorTypeNumber();
        } 
        return JDBCType.valueOf(resultSet.getString(1).toUpperCase()).getVendorTypeNumber();
    }

    public static String getValuesFormat(int numberColumns) {
        return new StringBuilder().append("(")
            .append(String.join(",", getIterableOnValuesFormat(numberColumns)))
            .append(")")
            .toString();
    }

    private static Iterable<CharSequence> getIterableOnValuesFormat(int numberValues) {
        return () -> getIteratorForValuesFormat(numberValues);
    }

    private static Iterator<CharSequence> getIteratorForValuesFormat(int numberValues) {
        return new Iterator<CharSequence>() {

                    private int count = 0;

                    @Override
                    public boolean hasNext() {
                        return count < numberValues;
                    }

                    @Override
                    public CharSequence next() {
                        count++;
                        return "?";
                    }
                };
    }
    
}
