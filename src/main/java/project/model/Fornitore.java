package project.model;

public class Fornitore implements DatabaseObject<String> {

    private final String partitaIVA;
    private final String nomeAzienda;
    private final int numeroTelefono;
    private final String email;

    public Fornitore(String partitaIVA, String nomeAzienda, int numeroTelefono, String email) {
        this.partitaIVA = partitaIVA;
        this.nomeAzienda = nomeAzienda;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
    }

    @Override
    public String getPrimaryKey() {
        return getPartitaIVA();
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getEmail() {
        return email;
    }
    
}
