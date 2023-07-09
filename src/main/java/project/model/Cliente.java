package project.model;

import java.util.Optional;

public class Cliente implements DatabaseObject<String> {

    private final String codice;
    private final int numeroTelefono;
    private final String nome;
    private final String cognome;
    private final Optional<String> email;

    public Cliente(String codice, int numeroTelefono, String nome, String cognome, Optional<String> email) {
        this.codice = codice;
        this.numeroTelefono = numeroTelefono;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    @Override
    public String getPrimaryKey() {
        return getCodice();
    }

    public String getCodice() {
        return codice;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Optional<String> getEmail() {
        return email;
    }
    
}
