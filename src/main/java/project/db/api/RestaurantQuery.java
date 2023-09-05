package project.db.api;

import static project.db.api.query_context.RestaurantQueryContextFactory.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import project.db.api.query_context.RestaurantQueryContext;
import project.db.api.query_runner.*;
import project.query.Table;

/**
 * Enum with the possible query to do on the database, except the ones to select every record of a table.
 */
public enum RestaurantQuery {

    INSERIRE_CONTO_A_PRENOTAZIONE_SALVATA(
        getInserireContoContext(),
        new InserireContoRunner()
    ),
    VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT(
        getTavoliPrenotatiContext(),
        new TavoliPrenotatiRunner()
    ),
    VISUALIZZARE_PORTATA_CUOCO_GIORNO(
        getPortataCuocoContext(),
        new PortataCuocoGiornoRunner()
    ),
    VISUALIZZARE_ALLERGENI_PIATTO(
        getAllergeniPiattoContext(),
        new AllergeniPiattoRunner()
    ),
    VISUALIZZARE_FORNITORI_DI_INGREDIENTE(
        getFornitoriIngredienteContext(),
        new FornitoriIngredienteRunner()
    ),
    VISUALIZZARE_INCASSO_TURNO(
        getIncassoTurnoContext(),
        new IncassoTurnoRunner()
    ),
    VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO(
        getDipendenteStipendioMassimoContext(),
        new DipendenteStipendioMassimoRunner()
    ),
    VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO(
        getPrenotazioniSlotAggiuntivoContext(),
        new PrenotazioniSlotAggiuntivoRunner()
    );
    
    private final RestaurantQueryContext context;
    private final RestaurantQueryRunner runner;

    private RestaurantQuery(RestaurantQueryContext context, RestaurantQueryRunner runner) {
        this.context = context;
        this.runner = runner;
    }

    /**
     * @return requested values to run correctly the query
     */
    public List<String> getRequestedValues() {
        return this.context.requiredValues();
    }

    /**
     * @return query text to execute query in a database
     */
    public String getQueryText() {
        return this.context.queryText();
    }

    /**
     * @param connection database's connection
     * @return result's table
     */
    public Table runQuery(Connection connection, List<String> values) throws SQLException {
        return this.runner.runQuery(connection, values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.context.description();
    }
    
}
