package project.db.api;

public enum RestaurantQuery {

    INSERIRE_NUOVA_PRENOTAZIONE("Inserire una nuova prenotazione"),
    INSERIRE_CONTRATTO_DIPENDENTE("Inserire un contratto di un dipendente"),
    AGGIUNGERE_TAVOLO_RISTORANTE("Aggiungere un tavolo al ristorante"),
    REGISTRARE_ORDINE_FORNITORE("Registrare un ordine da parte di un fornitore"),
    REGISTRARE_PAGAMENTO_TASSA("Registrare il pagamento di una tassa"),
    REGISTRARE_PAGAMENTO_FATTURA("Registrare il pagamento di una fattura"),
    AGGIUNGERE_PIATTO("Aggiungere un piatto"),
    AGGIUNGERE_MENU("Aggiungere un menu"),
    AGGIUNGERE_CONTO("Aggiungere il conto a una prenotazione già salvata"),
    VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT("Visualizzare i tavoli prenotati in una certa sala e in un certo slot"),
    VISUALIZZARE_PORTATA_CUOCO_GIORNO("Visualizzare la portata di cui è stato responsabile un cuoco in un certo giorno"),
    VISUALIZZARE_ALLERGENI_PIATTO("Visualizzare gli allergeni di un piatto"),
    VISUALIZZARE_FORNITORI_DI_INGREDIENTE("Visualizzare da quali fornitori e' stato ordinato un certo ingrediente"),
    VISUALIZZARE_INCASSO_TURNO("Visualizzare l'incasso di un turno di apertura del ristorante in un dato giorno"),
    VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO("Visualizzare il dipendente con lo stipendio massimo"),
    VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO("Visualizzare le prenotazioni con lo slot aggiuntivo in un certo giorno");
    
    private final String operation;

    private RestaurantQuery(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return this.operation;
    }

    
}
