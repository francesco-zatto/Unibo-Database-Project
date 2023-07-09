package project.model;

import java.util.Date;

public class Menu implements DatabaseObject<Integer> {

    private final int numero;
    private final Date dataCreazione;

    public Menu(int numero, Date dataCreazione) {
        this.numero = numero;
        this.dataCreazione = dataCreazione;
    }

    @Override
    public Integer getPrimaryKey() {
        return getNumero();
    }

    public int getNumero() {
        return numero;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }
    
}
