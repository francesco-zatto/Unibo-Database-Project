package project.model;

import java.util.Optional;

public class Prodotto implements DatabaseObject<String> {

    private final String codiceEAN13;
    private final String nome;
    private final Optional<String> descrizione;
    private final boolean tipoGenerico;
    private final Optional<String> scopo;

    public Prodotto(String codiceEAN13, String nome, Optional<String> descrizione, boolean tipoGenerico,
            Optional<String> scopo) {
        this.codiceEAN13 = codiceEAN13;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoGenerico = tipoGenerico;
        this.scopo = scopo;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceEAN13();
    }

    public String getCodiceEAN13() {
        return codiceEAN13;
    }

    public String getNome() {
        return nome;
    }

    public Optional<String> getDescrizione() {
        return descrizione;
    }

    public boolean isTipoGenerico() {
        return tipoGenerico;
    }

    public Optional<String> getScopo() {
        return scopo;
    }
    
}
