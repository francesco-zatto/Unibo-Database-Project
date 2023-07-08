package project.model;

public class Cuoco implements DatabaseObject<String> {

    private final String codiceDipendente;

    public Cuoco(String codiceDipendente) {
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
