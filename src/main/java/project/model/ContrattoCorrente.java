package project.model;

import java.util.Date;

public class ContrattoCorrente implements DatabaseObject<String> {

    private final String codiceDipendente;
    private final Date dataFirma;

    public ContrattoCorrente(Date dataFirma, String codiceDipendente) {
        this.dataFirma = dataFirma;
        this.codiceDipendente = codiceDipendente;
    }

    public Date getDataFirma() {
        return dataFirma;
    }

    public String getCodiceDipendente() {
        return codiceDipendente;
    }

    @Override
    public String getPrimaryKey() {
        return codiceDipendente + dataFirma;
    }  
    
}
