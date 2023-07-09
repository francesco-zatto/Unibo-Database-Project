package project.model;

import java.util.Date;

public class EffettuazionePrenotazione implements DatabaseObject<String> {

    private final String codicePrenotazione;
    private final Date dataEffettuazionePrenotazione;
    private final String codiceCliente;

    public EffettuazionePrenotazione(String codicePrenotazione, Date dataEffettuazionePrenotazione,
            String codiceCliente) {
        this.codicePrenotazione = codicePrenotazione;
        this.dataEffettuazionePrenotazione = dataEffettuazionePrenotazione;
        this.codiceCliente = codiceCliente;
    }

    @Override
    public String getPrimaryKey() {
        return getCodicePrenotazione();
    }

    public String getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public Date getDataEffettuazionePrenotazione() {
        return dataEffettuazionePrenotazione;
    }

    public String getCodiceCliente() {
        return codiceCliente;
    }
    
}
