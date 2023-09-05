package project.db.api;

import static project.db.api.utilities.RestaurantQueryDescriptions.*;
import static project.db.api.utilities.RestaurantQueryTexts.*;
import static project.db.api.utilities.RestaurantQueryValues.*;

import java.util.Collections;
import java.util.List;

/**
 * Enum with the possible query to do on the database, except the ones to select every record of a table.
 */
public enum RestaurantQuery {

    INSERIRE_CONTO_A_PRENOTAZIONE_SALVATA(
        getInserireContoDescription(), 
        getInserireContoPrenotazioneSalvata(),
        getInserireContoValues()
    ),
    VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT(
        getVisualizzareTavoliPrenotatiDescription(), 
        getVisualizzareTavoliPrenotati(),
        getVisualizzareTavoliPrenotatiValues()
    ),
    VISUALIZZARE_PORTATA_CUOCO_GIORNO(
        getVisualizzarePortataDescription(), 
        getVisualizzarePortataCuocoGiorno(),
        getVisualizzarePortataValues()
    ),
    VISUALIZZARE_ALLERGENI_PIATTO(
        getVisualizzareAllergeniPiattoDescription(), 
        getVisualizzareAllergeniPiatto(),
        getVisualizzareAllergeniPiattoValues()
    ),
    VISUALIZZARE_FORNITORI_DI_INGREDIENTE(
        getVisualizzareFornitoriIngredienteDescription(),
        getVisualizzareFornitoriIngrediente(),
        getVisualizzareFornitoriIngredienteValues()
    ),
    VISUALIZZARE_INCASSO_TURNO(
        getVisualizzareIncassoTurnoDescription(), 
        getvisualizzareIncassoTurno(),
        getVisualizzareIncassoTurnoValues()
    ),
    VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO(
        getVisualizzareDipendenteStipendioMassimoDescription(),
        getVisualizzareDipendenteStipendioMassimo(),
        getVisualizzareDipendenteStipendioMassimoValues()
    ),
    VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO(
        getVisualizzarePrenotazioniSlotAggiuntivoDescription(),
        getVisualizzarePrenotazioniSlotAggiuntivo(),
        getVisualizzarePrenotazioniSlotAggiuntivoValues()
    );
    
    private final String description;
    private final String queryText;
    private final List<String> requestedValues;

    private RestaurantQuery(String description, String queryText, List<String> values) {
        this.description = description;
        this.queryText = queryText;
        this.requestedValues = Collections.unmodifiableList(values);
    }

    /**
     * @return requested values to run correctly the query
     */
    public List<String> getRequestedValues() {
        return this.requestedValues;
    }

    /**
     * @return query text to execute query in a database
     */
    public String getQueryText() {
        return this.queryText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.description;
    }
    
}
