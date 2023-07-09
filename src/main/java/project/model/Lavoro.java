package project.model;

public class Lavoro implements DatabaseObject<String> {

    private final String codiceDipendente;
    private final int oraInizioTurno;
    private final int oraFineTurno;
    private final boolean lunedì;
    private final boolean martedì;
    private final boolean mercoledì;
    private final boolean giovedì;
    private final boolean venerdì;
    private final boolean sabato;
    private final boolean domenica;

    public Lavoro(String codiceDipendente, int oraInizioTurno, int oraFineTurno, boolean lunedì, boolean martedì,
            boolean mercoledì, boolean giovedì, boolean venerdì, boolean sabato, boolean domenica) {
        this.codiceDipendente = codiceDipendente;
        this.oraInizioTurno = oraInizioTurno;
        this.oraFineTurno = oraFineTurno;
        this.lunedì = lunedì;
        this.martedì = martedì;
        this.mercoledì = mercoledì;
        this.giovedì = giovedì;
        this.venerdì = venerdì;
        this.sabato = sabato;
        this.domenica = domenica;
    }

    @Override
    public String getPrimaryKey() {
        return getCodiceDipendente() + String.valueOf(getOraInizioTurno()) + getOraFineTurno();        
    }

    public String getCodiceDipendente() {
        return codiceDipendente;
    }

    public int getOraInizioTurno() {
        return oraInizioTurno;
    }

    public int getOraFineTurno() {
        return oraFineTurno;
    }

    public boolean isLunedì() {
        return lunedì;
    }

    public boolean isMartedì() {
        return martedì;
    }

    public boolean isMercoledì() {
        return mercoledì;
    }

    public boolean isGiovedì() {
        return giovedì;
    }

    public boolean isVenerdì() {
        return venerdì;
    }

    public boolean isSabato() {
        return sabato;
    }

    public boolean isDomenica() {
        return domenica;
    }
    
}
