package project.model;

public class Portata implements DatabaseObject<Integer> {

    private final int numero;
    private final String nome;
    

    public Portata(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Integer getPrimaryKey() {
        return getNumero();
    }
    
}
