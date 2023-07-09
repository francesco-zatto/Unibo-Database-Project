package project.model;

public class Ingrediente implements DatabaseObject<String> {

    private final String codiceProdotto;
    private final String durabilità;

    public Ingrediente(String codiceProdotto, String durabilità) {
        this.codiceProdotto = codiceProdotto;
        this.durabilità = durabilità;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceProdotto();
    }

    public String getCodiceProdotto() {
        return codiceProdotto;
    }

    public String getDurabilità() {
        return durabilità;
    }
    
}
