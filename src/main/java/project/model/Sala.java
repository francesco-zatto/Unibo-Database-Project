package project.model;

public class Sala implements DatabaseObject<Integer> {

    private final int numero;

    public Sala(int numero) {
        this.numero = numero;
    }

    @Override
    public Integer getPrimaryKey() {
        return getNumero();
    }

    public int getNumero() {
        return numero;
    }
    
}
