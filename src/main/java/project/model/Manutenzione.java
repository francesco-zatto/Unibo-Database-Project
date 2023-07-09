package project.model;

import java.util.Date;
import java.util.Optional;

public class Manutenzione implements DatabaseObject<String> {

    private final String codiceManutenzione;
    private final String codicePagamento;
    private final String motivazione;
    private final Date data;
    private final String zona;
    private final String addettoNome;
    private final String addettoCognome;
    private final Optional<String> addettoPartitaIVA;


    public Manutenzione(String codiceManutenzione, String codicePagamento, String motivazione, Date data, String zona,
            String addettoNome, String addettoCognome, Optional<String> addettoPartitaIVA) {
        this.codiceManutenzione = codiceManutenzione;
        this.codicePagamento = codicePagamento;
        this.motivazione = motivazione;
        this.data = data;
        this.zona = zona;
        this.addettoNome = addettoNome;
        this.addettoCognome = addettoCognome;
        this.addettoPartitaIVA = addettoPartitaIVA;
    }


    @Override
    public String getPrimaryKey() {
        return getCodiceManutenzione();
    }


    public String getCodiceManutenzione() {
        return codiceManutenzione;
    }


    public String getCodicePagamento() {
        return codicePagamento;
    }


    public String getMotivazione() {
        return motivazione;
    }


    public Date getData() {
        return data;
    }


    public String getZona() {
        return zona;
    }


    public String getAddettoNome() {
        return addettoNome;
    }


    public String getAddettoCognome() {
        return addettoCognome;
    }


    public Optional<String> getAddettoPartitaIVA() {
        return addettoPartitaIVA;
    }
    
}
