package project.model;

public class Turno implements DatabaseObject<String> {

    private final int oraInizio;
    private final int oraFine;
    private final boolean tipoApertura;
    private final boolean tipoLavorativo;

    public Turno(int oraInizio, int oraFine, boolean tipoApertura, boolean tipoLavorativo) {
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.tipoApertura = tipoApertura;
        this.tipoLavorativo = tipoLavorativo;
    }

    @Override
    public String getPrimaryKey() {
        return String.valueOf(getOraInizio()) + getOraFine();
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public int getOraFine() {
        return oraFine;
    }

    public boolean isTipoApertura() {
        return tipoApertura;
    }

    public boolean isTipoLavorativo() {
        return tipoLavorativo;
    }
    
}
