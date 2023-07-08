package project.model;

public class Piatto implements DatabaseObject<String> {

    private final String codice;
    private final String nome;
    private final int numeroPortata;

    public Piatto(String codice, String nome, int numeroPortata) {
        this.codice = codice;
        this.nome = nome;
        this.numeroPortata = numeroPortata;
    }

    @Override
    public String getPrimaryKey() {
        return getCodice();
    }

    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroPortata() {
        return numeroPortata;
    }
    
}
