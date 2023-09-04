package project.db.api.utilities;

/**
 * Utility class to get texts to executes restaurant's queries.
 */
public class RestaurantQueryTexts {

    private RestaurantQueryTexts() {}

    /**
     * @return query text to insert a Conto
     */
    public static String getInserireContoPrenotazioneSalvata() {
        return "UPDATE Prenotazioni " +
            "SET Conto = ? " +
            "WHERE CodPrenotazione = ?";
    }

    /**
     * @return query text to view Tavoli with a Prenotazione
     */
    public static String getVisualizzareTavoliPrenotati() {
        return "SELECT * " +
            "FROM Tavoli T " + 
            "WHERE T.NumeroSala = ? " +
            "AND T.Codice IN ( " + 
            "   SELECT CodTavolo " + 
            "   FROM Prenotazioni " + 
            "   WHERE CodSlotIniziale = ? " + 
            "   OR CodiceSlotAggiuntivo = ? )";
    }

    /**
     * @return query text to view, given a day and a Cuoco, the Portata
     */
    public static String getVisualizzarePortataCuocoGiorno() {
        return "SELECT Po.Nome " +
            "FROM Portate Po JOIN Preparazioni Pr " + 
            "ON Po.Numero = Pr.NumeroPortata " +
            "WHERE Pr.Data = ? " +
            "AND Pr.CodCuoco = ?";
    }

    /**
     * @return query text to view Allergeni of a Piatto
     */
    public static String getVisualizzareAllergeniPiatto() {
        return "SELECT P.Nome, P.Descrizione " + 
            "FROM Prodotti P JOIN Ingredienti I ON P.CodiceEAN13 = I.CodiceProdotto " + 
            "WHERE P.TipoGenerico = 0 " + 
            "AND I.CodiceProdotto IN ( " + 
            "SELECT CodiceIngrediente " +
            "FROM Allergeni " +
            "WHERE CodicePiatto = ?)";
    }

    /**
     * @return query text to view Fornitori of an Ingrediente
     */
    public static String getVisualizzareFornitoriIngrediente() {
        return  "SELECT F.PartitaIVA, F.NomeAzienda " +
            "FROM Fornitori F, ORDINI O " +
            "WHERE F.PartitaIVA = O.PartitaIVA " + 
            "AND O.CodOrdine IN ( " +
            "SELECT C.CodOrdine " +
            "FROM Consegne C, Prodotti P " + 
            "WHERE C.CodProdotto = P.CodiceEAN13 " +
            "AND P.CodiceEAN13 = ?)";
    }

    /**
     * @return query text to view the profit of a Turno
     */
    public static String getvisualizzareIncassoTurno() {
        return "SELECT SUM(P.Conto) " + 
            "FROM Prenotazioni P " +
            "WHERE P.Data = ? " +
            "AND P.CodSlotIniziale IN ( " + 
            "   SELECT CodiceSlot " + 
            "   FROM Slot " + 
            "   WHERE OraInizioTurno = ? " + 
            "   AND OraFineTurno = ? )";
    }
    
    /**
     * @return query text to view Dipendente with highest salary
     */
    public static String getvisualizzareDipendenteStipendioMassimo() {
        return "SELECT D.Codice, D.CF, D.Nome, D.Cognome " +
            "FROM Dipendenti D, Contratti C " +  
            "WHERE C.CodDipendente = D.Codice " + 
            "AND D.Codice IN (SELECT CodDipendente FROM ContrattiCorrenti) " + 
            "ORDER BY C.StipendioMensile DESC " +
            "LIMIT 1";
    }

    /**
     * @return query text to view Prenotazioni with more than one slot
     */
    public static String getVisualizzarePrenotazioniSlotAggiuntivo() {
        return "SELECT CodPrenotazione, CodTavolo " +
            "FROM Prenotazioni " +
            "WHERE Data = ? " +
            "AND CodiceSlotAggiuntivo IS NOT NULL ";
    }
}
