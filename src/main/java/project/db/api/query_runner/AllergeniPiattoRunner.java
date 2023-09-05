package project.db.api.query_runner;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;
import project.db.api.utilities.MetaDataQueries;
import project.query.Record;
import project.query.RecordImpl;

/**
 * Class to run to view Allergeni in a Piatto query.
 */
public class AllergeniPiattoRunner extends AbstractRestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of("NomeIngrediente", "Descrizione");

    @Override
    protected ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.CHAR);
        return statement.executeQuery();
    }

    @Override
    protected Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object nome = resultSet.getObject(1);
        Optional<Object> descrizione = Optional.ofNullable(resultSet.getObject(2));
        rowData.addAll(List.of(
            nome.toString(), 
            descrizione.isEmpty() ? MetaDataQueries.NULL_VALUE : descrizione.get().toString()
        ));
        return new RecordImpl(rowData);
    }

    @Override
    protected String getQueryText() {
        return RestaurantQuery.VISUALIZZARE_ALLERGENI_PIATTO.getQueryText(); 
    }

    @Override
    protected List<String> getColumnsNames() {
        return RESULT_LIST;
    }
    
}
