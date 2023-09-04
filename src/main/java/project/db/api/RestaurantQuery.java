package project.db.api;

import static project.db.api.utilities.RestaurantQueryDescriptions.*;
import static project.db.api.utilities.RestaurantQueryTexts.*;

/**
 * Enum with the possible query to do on the database, except the ones to select every record of a table.
 */
public enum RestaurantQuery {

    INSERIRE_CONTO_A_PRENOTAZIONE_SALVATA(
        getInserireContoDescription(), 
        getInserireContoPrenotazioneSalvata()
    ),
    VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT(
        getVisualizzareTavoliPrenotatiDescription(), 
        getVisualizzareTavoliPrenotati()
    ),
    VISUALIZZARE_PORTATA_CUOCO_GIORNO(
        getVisualizzarePortataDescription(), 
        getVisualizzarePortataCuocoGiorno()
    ),
    VISUALIZZARE_ALLERGENI_PIATTO(
        getVisualizzareAllergeniPiattoDescription(), 
        getVisualizzareAllergeniPiatto()
    ),
    VISUALIZZARE_FORNITORI_DI_INGREDIENTE(
        getVisualizzareFornitoriIngredienteDescription(),
        getVisualizzareFornitoriIngrediente()
    ),
    VISUALIZZARE_INCASSO_TURNO(
        getVisualizzareIncassoTurnoDescription(), 
        getvisualizzareIncassoTurno()
    ),
    VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO(
        getVisualizzareDipendenteStipendioMassimoDescription(),
        getVisualizzareDipendenteStipendioMassimo()
    ),
    VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO(
        getVisualizzarePrenotazioniSlotAggiuntivoDescription(),
        getVisualizzarePrenotazioniSlotAggiuntivo()
    );
    
    private final String description;
    private final String queryText;

    private RestaurantQuery(String description, String queryText) {
        this.description = description;
        this.queryText = queryText;
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
