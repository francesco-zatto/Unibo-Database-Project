package project.model;

public class Slot implements DatabaseObject<String> {

    private final String codiceSlot;
    private final int oraInizio;
    private final int oraInizioTurno;
    private final int oraFineTurno;

    public Slot(String codiceSlot, int oraInizio, int oraInizioTurno, int oraFineTurno) {
        this.codiceSlot = codiceSlot;
        this.oraInizio = oraInizio;
        this.oraInizioTurno = oraInizioTurno;
        this.oraFineTurno = oraFineTurno;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceSlot();
    }

    public String getCodiceSlot() {
        return codiceSlot;
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public int getOraInizioTurno() {
        return oraInizioTurno;
    }

    public int getOraFineTurno() {
        return oraFineTurno;
    }
    
}
