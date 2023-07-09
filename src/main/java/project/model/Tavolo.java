package project.model;

public class Tavolo implements DatabaseObject<String> {

    private final String codice;
    private final int numeroInSala;
    private final int numeroPersone;
    private final int numeroSala;

    public Tavolo(String codice, int numeroInSala, int numeroPersone, int numeroSala) {
        this.codice = codice;
        this.numeroInSala = numeroInSala;
        this.numeroPersone = numeroPersone;
        this.numeroSala = numeroSala;
    }

    @Override
    public String getPrimaryKey() {
        return getCodice();
    }

    public String getCodice() {
        return codice;
    }

    public int getNumeroInSala() {
        return numeroInSala;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public int getNumeroSala() {
        return numeroSala;
    }
    
}
