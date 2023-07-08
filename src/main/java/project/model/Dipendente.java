package project.model;

import java.util.Date;
import java.util.Optional;

public class Dipendente implements DatabaseObject<String> {
    
    private final String codice;
    private final String codiceFiscale;
    private final String nome;
    private final String cognome;
    private final Date dataNascita;
    private final char genere;
    private final String cittàResidenza;
    private final int numeroTelefono;
    private final boolean tipoAddettoPulizia;
    private final Optional<String> tipologiaPulizia;
    
    public Dipendente(String codice, String codiceFiscale, String nome, String cognome, Date dataNascita, char genere,
            String cittàResidenza, int numeroTelefono, boolean tipoAddettoPulizia, Optional<String> tipologiaPulizia) {
        this.codice = codice;
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.genere = genere;
        this.cittàResidenza = cittàResidenza;
        this.numeroTelefono = numeroTelefono;
        this.tipoAddettoPulizia = tipoAddettoPulizia;
        this.tipologiaPulizia = tipologiaPulizia;
    }

    public String getCodice() {
        return codice;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public char getGenere() {
        return genere;
    }

    public String getCittàResidenza() {
        return cittàResidenza;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public boolean isTipoAddettoPulizia() {
        return tipoAddettoPulizia;
    }

    public Optional<String> getTipologiaPulizia() {
        return tipologiaPulizia;
    }

    @Override
    public String getPrimaryKey() {
        return getCodice();
    }
    
}
