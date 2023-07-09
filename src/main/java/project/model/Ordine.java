package project.model;

import java.util.Date;

public class Ordine implements DatabaseObject<String> {

    private final String codice;
    private final Date data;
    private final String partitaIVA;
    private final String codicePagamento;
    private final String corriereNome;
    private final String corriereCognome;

    public Ordine(String codice, Date data, String partitaIVA, String codicePagamento, String corriereNome,
            String corriereCognome) {
        this.codice = codice;
        this.data = data;
        this.partitaIVA = partitaIVA;
        this.codicePagamento = codicePagamento;
        this.corriereNome = corriereNome;
        this.corriereCognome = corriereCognome;
    }

    @Override
    public String getPrimaryKey() {
        return getCodice();
    }

    public String getCodice() {
        return codice;
    }

    public Date getData() {
        return data;
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public String getCodicePagamento() {
        return codicePagamento;
    }

    public String getCorriereNome() {
        return corriereNome;
    }

    public String getCorriereCognome() {
        return corriereCognome;
    }
    
}
