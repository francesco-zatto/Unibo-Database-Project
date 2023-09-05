package project.db.api.utilities;

import java.util.List;

/**
 * Utility class to get values to insert in a RestaurantQuery.
 */
public class RestaurantQueryValues {

    private static final String CONTO = "Conto";
    private static final String COD_PRENOTAZIONE = "CodPrenotazione";
    private static final String COD_PIATTO = "CodPiatto";
    private static final String COD_INGREDIENTE = "CodIngrediente";
    private static final String DATA = "Data";
    private static final String ORA_INIZIO_TURNO = "OraInizioTurno";
    private static final String ORA_FINE_TURNO = "OraFineTurno";
    private static final String COD_CUOCO = "CodCuoco";
    private static final String NUMERO_SALA = "NumeroSala";
    private static final String COD_SLOT = "CodSlot";

    private RestaurantQueryValues() {}

    /**
     * @return values to insert for Inserire Conto query
     */
    public static List<String> getInserireContoValues() {
        return List.of(CONTO, COD_PRENOTAZIONE);
    }

    /**
     * @return values to insert for Visualizzare Tavoli Prenotati query
     */
    public static List<String> getVisualizzareTavoliPrenotatiValues() {
        return List.of(NUMERO_SALA, COD_SLOT);
    }

    /**
     * @return values to insert for Visualizzare Portata query
     */
    public static List<String> getVisualizzarePortataValues() {
        return List.of(DATA, COD_CUOCO);
    }
    
    /**
     * @return values to insert for Visualizzare Allergeni query
     */
    public static List<String> getVisualizzareAllergeniPiattoValues() {
        return List.of(COD_PIATTO);
    }

    /**
     * @return values to insert for Visualizzare Fornitori query
     */
    public static List<String> getVisualizzareFornitoriIngredienteValues() {
        return List.of(COD_INGREDIENTE);
    }

    /**
     * @return values to insert for Visualizzare Incasso Turno query
     */
    public static List<String> getVisualizzareIncassoTurnoValues() {
        return List.of(DATA, ORA_INIZIO_TURNO, ORA_FINE_TURNO);
    }

    /**
     * @return values to insert for Visualizzare Dipendente Stipendio Massimo query
     */
    public static List<String> getVisualizzareDipendenteStipendioMassimoValues() {
        return List.of();
    }

    /**
     * @return values to insert for Visualizzare Prenotazioni Slot Aggiuntivo query
     */
    public static List<String> getVisualizzarePrenotazioniSlotAggiuntivoValues() {
        return List.of(DATA);
    }
    
}
