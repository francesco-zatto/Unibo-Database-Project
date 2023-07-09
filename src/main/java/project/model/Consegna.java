package project.model;

public class Consegna implements DatabaseObject<String> {

    private final String codiceOrdine;
    private final String codiceProdotto;

    public Consegna(String codiceOrdine, String codiceProdotto) {
        this.codiceOrdine = codiceOrdine;
        this.codiceProdotto = codiceProdotto;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceOrdine() + getCodiceProdotto();
    }

    public String getCodiceOrdine() {
        return codiceOrdine;
    }

    public String getCodiceProdotto() {
        return codiceProdotto;
    }
    
}
