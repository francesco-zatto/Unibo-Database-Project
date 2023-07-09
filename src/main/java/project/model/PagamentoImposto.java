package project.model;

public class PagamentoImposto implements DatabaseObject<String> {

    private final String codicePagamento;
    private final String codiceiPA;

    public PagamentoImposto(String codicePagamento, String codiceiPA) {
        this.codicePagamento = codicePagamento;
        this.codiceiPA = codiceiPA;
    }

    @Override
    public String getPrimaryKey() {
        return getCodicePagamento();
    }

    public String getCodicePagamento() {
        return codicePagamento;
    }

    public String getCodiceiPA() {
        return codiceiPA;
    }
    
}
