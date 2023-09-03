package project.db.api;

/**
 * Enum with the possible query to do on the database, except the ones to select every record of a table.
 */
public enum RestaurantQuery {

    INSERIRE_CONTO_A_PRENOTAZIONE_SALVATA("Inserire il conto a una prenotazione salvata"),
    VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT("Visualizzare i tavoli prenotati in una certa sala e in un certo slot"),
    VISUALIZZARE_PORTATA_CUOCO_GIORNO("Visualizzare la portata di cui e' stato responsabile un cuoco in un certo giorno"),
    VISUALIZZARE_ALLERGENI_PIATTO("Visualizzare gli allergeni di un piatto"),
    VISUALIZZARE_FORNITORI_DI_INGREDIENTE("Visualizzare da quali fornitori e' stato ordinato un certo ingrediente"),
    VISUALIZZARE_INCASSO_TURNO("Visualizzare l'incasso di un turno di apertura del ristorante in un dato giorno"),
    VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO("Visualizzare il dipendente con lo stipendio massimo"),
    VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO("Visualizzare le prenotazioni con lo slot aggiuntivo in un certo giorno");
    
    private final String operation;

    private RestaurantQuery(String operation) {
        this.operation = operation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.operation;
    }
    
}
