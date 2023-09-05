package project.db.api.query_runner;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.db.api.RestaurantQuery;
import project.query.Record;
import project.query.RecordImpl;

public class IncassoTurnoRunner extends AbstractRestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of("Incasso");

    @Override
    protected String getQueryText() {
        return RestaurantQuery.VISUALIZZARE_INCASSO_TURNO.getQueryText();
    }

    @Override
    protected List<String> getColumnsNames() {
        return RESULT_LIST;
    }

    @Override
    protected ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.DATE);
        statement.setObject(2, values.get(1), JDBCType.INTEGER);
        statement.setObject(3, values.get(2), JDBCType.INTEGER);
        return statement.executeQuery();    
    }

    @Override
    protected Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object incasso = resultSet.getObject(1);
        rowData.addAll(List.of(incasso.toString()));
        return new RecordImpl(rowData);    
    }
    
}
