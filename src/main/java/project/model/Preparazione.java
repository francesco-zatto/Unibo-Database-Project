package project.model;

import java.util.Date;

public class Preparazione implements DatabaseObject<String> {

    private final String codiceCuoco;
    private final Date data;
    private final int numeroPortata;

    public Preparazione(String codiceCuoco, Date data, int numeroPortata) {
        this.codiceCuoco = codiceCuoco;
        this.data = data;
        this.numeroPortata = numeroPortata;
    }

    @Override
    public String getPrimaryKey() {
        return codiceCuoco + data;
    }

    public String getCodiceCuoco() {
        return codiceCuoco;
    }

    public Date getData() {
        return data;
    }

    public int getNumeroPortata() {
        return numeroPortata;
    }
    
}
