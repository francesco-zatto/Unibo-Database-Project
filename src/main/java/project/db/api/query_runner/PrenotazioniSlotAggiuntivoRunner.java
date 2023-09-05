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

/**
 * Class to run to view Prenotazioni with another Slot.
 */
public class PrenotazioniSlotAggiuntivoRunner extends AbstractRestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of("CodPrenotazione", "CodTavolo");

    @Override
    protected ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.DATE);
        return statement.executeQuery();
    }

    @Override
    protected String getQueryText() {
        return RestaurantQuery.VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO.getQueryText();
    }

    @Override
    protected List<String> getColumnsNames() {
        return RESULT_LIST;
    }

    @Override
    protected Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object codPrenotazione = resultSet.getObject(1);
        Object codTavolo = resultSet.getObject(2);
        rowData.addAll(List.of(codPrenotazione.toString(),codTavolo.toString()));
        return new RecordImpl(rowData);        
    }
    
}
