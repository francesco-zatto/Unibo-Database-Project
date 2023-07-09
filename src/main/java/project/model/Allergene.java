package project.model;

public class Allergene implements DatabaseObject<String> {

    private final String codicePiatto;
    private final String codiceIngrediente;

    public Allergene(String codicePiatto, String codiceIngrediente) {
        this.codicePiatto = codicePiatto;
        this.codiceIngrediente = codiceIngrediente;
    }

    @Override
    public String getPrimaryKey() {
        return getCodicePiatto() + getCodiceIngrediente();
    }

    public String getCodicePiatto() {
        return codicePiatto;
    }

    public String getCodiceIngrediente() {
        return codiceIngrediente;
    }
    
}
