package project.model;

public class StrutturazioneMenu implements DatabaseObject<String> {

    private final String codicePiatto;
    private final int numeroMenu;

    public StrutturazioneMenu(String codicePiatto, int numeroMenu) {
        this.codicePiatto = codicePiatto;
        this.numeroMenu = numeroMenu;
    }

    @Override
    public String getPrimaryKey() {
        return getCodicePiatto() + getNumeroMenu();
    }

    public String getCodicePiatto() {
        return codicePiatto;
    }

    public int getNumeroMenu() {
        return numeroMenu;
    }
    
}
