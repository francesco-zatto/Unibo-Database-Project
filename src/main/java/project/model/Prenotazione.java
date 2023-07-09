package project.model;

import java.util.Date;
import java.util.Optional;

public class Prenotazione implements DatabaseObject<String> {

    private final String codice;
    private final String codiceTavolo;
    private final String codiceSlotIniziale;
    private final Date data;
    private final int numeroPersone;
    private final Optional<Integer> conto;
    private final Optional<String> codiceSlotAggiuntivo;

    public Prenotazione(String codice, String codiceTavolo, String codiceSlotIniziale, Date data, int numeroPersone,
            Optional<Integer> conto, Optional<String> codiceSlotAggiuntivo) {
        this.codice = codice;
        this.codiceTavolo = codiceTavolo;
        this.codiceSlotIniziale = codiceSlotIniziale;
        this.data = data;
        this.numeroPersone = numeroPersone;
        this.conto = conto;
        this.codiceSlotAggiuntivo = codiceSlotAggiuntivo;
    }

    @Override
    public String getPrimaryKey() {
        return getCodice();
    }

    public String getCodice() {
        return codice;
    }

    public String getCodiceTavolo() {
        return codiceTavolo;
    }

    public String getCodiceSlotIniziale() {
        return codiceSlotIniziale;
    }

    public Date getData() {
        return data;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public Optional<Integer> getConto() {
        return conto;
    }

    public Optional<String> getCodiceSlotAggiuntivo() {
        return codiceSlotAggiuntivo;
    }
    
}
