package project.core;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;
import project.db.tables.AllergeniTable;

public class Database {

    private final Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public Optional<List<List<String>>> runQuery(RestaurantQuery query) {
        switch (query) {
            case AGGIUNGERE_CONTO:
                break;
            case AGGIUNGERE_MENU:
                break;
            case AGGIUNGERE_PIATTO:
                break;
            case AGGIUNGERE_TAVOLO_RISTORANTE:
                break;
            case INSERIRE_CONTRATTO_DIPENDENTE:
                break;
            case INSERIRE_NUOVA_PRENOTAZIONE:
                break;
            case REGISTRARE_ORDINE_FORNITORE:
                break;
            case REGISTRARE_PAGAMENTO_FATTURA:
                break;
            case REGISTRARE_PAGAMENTO_TASSA:
                break;
            case VISUALIZZARE_ALLERGENI_PIATTO:
                break;
            case VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO:
                break;
            case VISUALIZZARE_FORNITORI_DI_INGREDIENTE:
                break;
            case VISUALIZZARE_INCASSO_TURNO:
                break;
            case VISUALIZZARE_PORTATA_CUOCO_GIORNO:
                break;
            case VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO:
                break;
            case VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT:
                break;
        }
        return null;        
    }
    
}
