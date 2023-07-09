package project.model;

public class EntePubblico implements DatabaseObject<String> {

    private final String codiceiPA;
    private final String nome;

    public EntePubblico(String codiceiPA, String nome) {
        this.codiceiPA = codiceiPA;
        this.nome = nome;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceiPA();
    }

    public String getCodiceiPA() {
        return codiceiPA;
    }

    public String getNome() {
        return nome;
    }
    
}
