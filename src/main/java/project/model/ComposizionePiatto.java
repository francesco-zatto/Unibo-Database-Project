package project.model;

public class ComposizionePiatto implements DatabaseObject<String> {

    private final String codicePiatto;
    private final String codiceIngrediente;

    public ComposizionePiatto(String codicePiatto, String codiceIngrediente) {
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
