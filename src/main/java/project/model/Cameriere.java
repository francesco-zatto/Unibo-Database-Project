package project.model;

public class Cameriere implements DatabaseObject<String> {

    private final String codiceDipendente;

    public Cameriere(String codiceDipendente) {
        this.codiceDipendente = codiceDipendente;
    }

    public String getCodiceDipendente() {
        return codiceDipendente;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceDipendente();
    }
    
}
