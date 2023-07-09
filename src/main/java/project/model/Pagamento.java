package project.model;

import java.util.Date;
import java.util.Optional;

public class Pagamento implements DatabaseObject<String> {

    private final String codice;
    private final Date data;
    private final int ammontareDenaro;
    private final Optional<String> codiceTributo;
    private final Optional<Date> dataEmissioneTassa;
    private final Optional<String> descrizione;
    private final String tipo;

    public Pagamento(String codice, Date data, int ammontareDenaro, Optional<String> codiceTributo,
            Optional<Date> dataEmissioneTassa, Optional<String> descrizione, String tipo) {
        this.codice = codice;
        this.data = data;
        this.ammontareDenaro = ammontareDenaro;
        this.codiceTributo = codiceTributo;
        this.dataEmissioneTassa = dataEmissioneTassa;
        this.descrizione = descrizione;
        this.tipo = tipo;
    }

    @Override
    public String getPrimaryKey() {
        return getCodice();
    }

    public String getCodice() {
        return codice;
    }

    public Date getData() {
        return data;
    }

    public int getAmmontareDenaro() {
        return ammontareDenaro;
    }

    public Optional<String> getCodiceTributo() {
        return codiceTributo;
    }

    public Optional<Date> getDataEmissioneTassa() {
        return dataEmissioneTassa;
    }

    public Optional<String> getDescrizione() {
        return descrizione;
    }

    public String getTipo() {
        return tipo;
    }
    
}
