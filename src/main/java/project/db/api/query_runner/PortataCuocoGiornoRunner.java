package project.db.api.query_runner;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;
import project.query.Record;
import project.query.RecordImpl;

/**
 * Class to run to view the Portata cooked by a Cuoco in a Giorno.
 */
public class PortataCuocoGiornoRunner extends AbstractRestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of("Nome Portata");

    @Override
    protected ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.DATE);
        statement.setObject(2, values.get(1), JDBCType.CHAR);
        return statement.executeQuery();
    }

    @Override
    protected Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> record = new LinkedList<>();
        Optional<Object> nomePortata = Optional.of(resultSet.getObject(1));
        record.addAll(List.of(nomePortata.get().toString()));
        return new RecordImpl(record);
    }

    @Override
    protected String getQueryText() {
        return RestaurantQuery.VISUALIZZARE_PORTATA_CUOCO_GIORNO.getQueryText();
    }

    @Override
    protected List<String> getColumnsNames() {
        return RESULT_LIST;
    }
    
}
