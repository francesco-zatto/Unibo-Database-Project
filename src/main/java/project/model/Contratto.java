package project.model;

import java.util.Date;

public class Contratto implements DatabaseObject<String> {

    private final String codiceDipendente;
    private final Date dataFirma;
    private final Date dataInizio;
    private final Date dataFine;
    private final int stipendioMensile;

    public Contratto(String codiceDipendente, Date dataFirma, Date dataInizio, Date dataFine, int stipendioMensile) {
        this.codiceDipendente = codiceDipendente;
        this.dataFirma = dataFirma;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.stipendioMensile = stipendioMensile;
    }

    public String getCodiceDipendente() {
        return codiceDipendente;
    }

    public Date getDataFirma() {
        return dataFirma;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public int getStipendioMensile() {
        return stipendioMensile;
    }

    @Override
    public String getPrimaryKey() {
        return codiceDipendente + dataFirma;
    }

}
