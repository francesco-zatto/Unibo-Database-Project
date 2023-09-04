package project.db.api.utilities;

/**
 * Utility class with static getters for restaurant queries' descriptions.
 */
public class RestaurantQueryDescriptions {

    public static String getInserireContoDescription() {
        return "Inserire il conto a una prenotazione salvata";
    }

    public static String getVisualizzareTavoliPrenotatiDescription() {
        return "Visualizzare i tavoli prenotati in una certa sala e in un certo slot";
    }

    public static String getVisualizzarePortataDescription() {
        return "Visualizzare la portata di cui e' stato responsabile un cuoco in un certo giorno";
    }

    public static String getVisualizzareAllergeniPiattoDescription() {
        return "Visualizzare gli allergeni di un piatto";
    }

    public static String getVisualizzareFornitoriIngredienteDescription() {
        return "Visualizzare da quali fornitori e' stato ordinato un certo ingrediente";
    }

    public static String getVisualizzareIncassoTurnoDescription() {
        return "Visualizzare l'incasso di un turno di apertura del ristorante in un dato giorno";
    }

    public static String getVisualizzareDipendenteStipendioMassimoDescription() {
        return "Visualizzare il dipendente con lo stipendio massimo";
    }

    public static String getVisualizzarePrenotazioniSlotAggiuntivoDescription() {
        return "Visualizzare le prenotazioni con lo slot aggiuntivo in un certo giorno";
    }

}
