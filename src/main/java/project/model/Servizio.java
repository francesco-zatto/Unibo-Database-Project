package project.model;

import java.util.Date;

public class Servizio implements DatabaseObject<String> {

    private final String codiceCameriere;
    private final Date data;
    private final int numeroSala;

    public Servizio(String codiceCameriere, Date data, int numeroSala) {
        this.codiceCameriere = codiceCameriere;
        this.data = data;
        this.numeroSala = numeroSala;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceCameriere() + getData();
    }

    public String getCodiceCameriere() {
        return codiceCameriere;
    }

    public Date getData() {
        return data;
    }

    public int getNumeroSala() {
        return numeroSala;
    }
    
}
