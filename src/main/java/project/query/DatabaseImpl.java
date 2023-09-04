package project.query;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import project.db.api.RestaurantQuery;
import project.db.api.utilities.InsertSelectQueryTexts;
import project.db.api.utilities.MetaDataQueries;
import project.tableFactory.StaticTableFactory;

/**
 * Basic implementation of Database to connect with a real database using JDBC.
 */
public class DatabaseImpl implements Database {

    private final Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Constructor with the database's connection
     * @param connection database's connection
     */
    public DatabaseImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getTableNames() {
        final List<String> list;
        String query = InsertSelectQueryTexts.getSelectTableNames();
        try {
            list = executeTableNamesQuery(query);
        } catch (SQLException e) {
            return List.of();
        }
        return list;
    }

    private List<String> executeTableNamesQuery(final String query) throws SQLException {
        startQueryExecution(query);
        return getListFromResultSet();
    }

    private void startQueryExecution(final String query) throws SQLException {
        this.statement = connection.createStatement();
        this.resultSet = statement.executeQuery(query);
    }

    private List<String> getListFromResultSet() throws SQLException {
        final List<String> list = new LinkedList<>();
         while (this.resultSet.next()) {
            list.add(this.resultSet.getString(MetaDataQueries.TABLE_NAME));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table getTable(String tableName) {
        Table table = StaticTableFactory.getEmptyTable();
        String query = InsertSelectQueryTexts.getSelectEveryRecordFromTable(tableName);
        try {
            table = getEveryRecordFromTable(query, tableName);
        } catch (SQLException e) {
            return table;
        }
        return table;
    }

    private Table getEveryRecordFromTable(String query, String tableName) throws SQLException {
        startQueryExecution(query);
        List<String> columnsNames = getColumnNamesFromMetaData(this.resultSet.getMetaData());
        List<Record> recordsList = new LinkedList<>();
        recordsList.add(new RecordImpl(columnsNames));
        recordsList.addAll(getRecordsListFromResultSet());
        return StaticTableFactory.createTable(recordsList);
    }

    private List<String> getColumnNamesFromMetaData(ResultSetMetaData metaData) throws SQLException{
        int columnCount = metaData.getColumnCount();
        return Stream.iterate(1, i -> i <= columnCount, i -> i + 1)
                .map(mapperToColumnName(metaData))
                .toList();
    }

    private Function<Integer, String> mapperToColumnName(ResultSetMetaData metaData) {
        return i -> {
            try {
                return metaData.getColumnName(i);
            } catch (SQLException e) {
                return MetaDataQueries.EMPTY_VALUE;
            }
        };
    }

    private List<Record> getRecordsListFromResultSet() throws SQLException {
        List<Record> recordList = new LinkedList<>();
        while (this.resultSet.next()) {
            recordList.add(getCurrentRecord());
        }
        return recordList;
    }

    private Record getCurrentRecord() throws SQLException {
        int columnCount = this.resultSet.getMetaData().getColumnCount();
        List<String> record = new LinkedList<>();
        for (int i = 1; i <= columnCount; i++) {
            record.add(getCurrentElementToString(i));
        }
        return new RecordImpl(record);
    }

    private String getCurrentElementToString(int i) throws SQLException {
        return Optional.ofNullable(this.resultSet.getObject(i))
                .map(Object::toString)
                .orElse(MetaDataQueries.NULL_VALUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertInTable(Record record, String table) {
        try {
            return executeInsert(record, table);
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean executeInsert(Record record, String table) throws SQLException {
        List<String> recordWithNulls = fromRecordToListWithNulls(record);
        int numberColumns = findNumberOfColumns(table);
        List<Integer> tableTypes = findTableTypes(table);
        String valuesFormat = MetaDataQueries.getValuesFormat(numberColumns);
        String query = InsertSelectQueryTexts.getInsertInTable(table, valuesFormat);
        PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < numberColumns; i++) {
                statement.setObject(i + 1, recordWithNulls.get(i), tableTypes.get(i));
            }
            statement.executeUpdate();
        return true;
    }

    private List<String> fromRecordToListWithNulls(Record record) {
        return record.getElements().stream()
            .map(this::fromEmptyToNull)
            .toList();
    }

    private String fromEmptyToNull(String string) {
        if (string.isBlank()) {
            return null;
        }
        return string;
    }

    private int findNumberOfColumns(String table) {
        return getColumnNames(table).size();
    }

    private List<Integer> findTableTypes(String table) {
        String query = InsertSelectQueryTexts.getSelectTableDataTypes(table);
        try {
            startQueryExecution(query);
            return MetaDataQueries.getDataTypes(this.resultSet);
        } catch (SQLException e) {
            return List.of();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getQueryValues(RestaurantQuery query) {
        switch (query) {
            case INSERIRE_CONTO_A_PRENOTAZIONE_SALVATA:
                return List.of("Conto", "CodPrenotazione");
            case VISUALIZZARE_ALLERGENI_PIATTO:
                return List.of("CodPiatto");
            case VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO:
                return List.of();
            case VISUALIZZARE_FORNITORI_DI_INGREDIENTE:
                return List.of("CodIngrediente");
            case VISUALIZZARE_INCASSO_TURNO:
                return List.of("Data", "OraInizioTurno", "OraFineTurno");
            case VISUALIZZARE_PORTATA_CUOCO_GIORNO:
                return List.of("Data", "CodiceCuoco");
            case VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO:
                return List.of("Data");
            case VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT:
                return List.of("NumeroSala", "CodSlot");
        }
        return List.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Table> runQuery(RestaurantQuery query, List<String> values) {
        List<List<String>> resultList = new LinkedList<>();
        try {
            switch (query) {
            case INSERIRE_CONTO_A_PRENOTAZIONE_SALVATA:
                insertContoPrenotazioneSalvata(values, resultList);
                break;
            case VISUALIZZARE_ALLERGENI_PIATTO:
                viewAllergeniPiatto(values, resultList);
                break;
            case VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO:
                viewDipendenteStipendioMassimo(resultList);
                break;
            case VISUALIZZARE_FORNITORI_DI_INGREDIENTE:
                viewFornitoriDiIngrediente(values, resultList);
                break;
            case VISUALIZZARE_INCASSO_TURNO:
                viewIncassoTurno(values, resultList);
                break;
            case VISUALIZZARE_PORTATA_CUOCO_GIORNO:
                viewPortataCuocoGiorno(values, resultList);
                break;
            case VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO:
                viewPrenotazioniSlotAggiuntivo(values, resultList);
                break;
            case VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT:
                viewTavoliPrenotatiSalaSLot(values, resultList);
                break;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getTableFromResultList(resultList);        
    }

    private Optional<Table> getTableFromResultList(final List<List<String>> resultList) {
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.empty(); //TODO
        //Optional.of(getTableFromLists(resultList));
    }

    private void viewTavoliPrenotatiSalaSLot(List<String> values, List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        queryString = "SELECT * " +
                "FROM Tavoli T " + 
                "WHERE T.NumeroSala = ? " +
                "AND T.Codice IN ( " + 
                "   SELECT CodTavolo " + 
                "   FROM Prenotazioni " + 
                "   WHERE CodSlotIniziale = ? " + 
                "   OR CodiceSlotAggiuntivo = ? )";
        resultList.add(List.of("CodTavolo", "NumeroInSala", "NumeroPersone", "NumeroSala"));
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.INTEGER);
        statement.setObject(2, values.get(1), JDBCType.CHAR);
        statement.setObject(3, values.get(1), JDBCType.CHAR);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> rowData = new LinkedList<>();
            Optional<Object> codTavolo = Optional.of(resultSet.getObject(1));
            Optional<Object> numeroInSala = Optional.of(resultSet.getObject(2));
            Optional<Object> numeroPersone = Optional.of(resultSet.getObject(3));
            Optional<Object> numeroSala = Optional.of(resultSet.getObject(4));
            rowData.addAll(List.of(
                codTavolo.get().toString(), 
                numeroInSala.get().toString(),
                numeroPersone.get().toString(), 
                numeroSala.get().toString()
            ));
            resultList.add(rowData);
        }
    }

    private void viewPrenotazioniSlotAggiuntivo(List<String> values, List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        queryString = "SELECT CodPrenotazione, CodTavolo " +
                "FROM Prenotazioni " +
                "WHERE Data = ? " +
                "AND CodiceSlotAggiuntivo IS NOT NULL ";
        resultList.add(List.of("CodPrenotazione", "CodTavolo"));
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.DATE);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> rowData = new LinkedList<>();
            Optional<Object> codPrenotazione = Optional.of(resultSet.getObject(1));
            Optional<Object> codTavolo = Optional.of(resultSet.getObject(2));
            rowData.addAll(List.of(
                codPrenotazione.get().toString(),
                codTavolo.get().toString()
            ));
            resultList.add(rowData);
        }
    }

    private void viewIncassoTurno(List<String> values, List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        queryString = "SELECT SUM(P.Conto) " + 
                "FROM Prenotazioni P " +
                "WHERE P.Data = ? " +
                "AND P.CodSlotIniziale IN ( " + 
                "   SELECT CodiceSlot " + 
                "   FROM Slot " + 
                "   WHERE OraInizioTurno = ? " + 
                "   AND OraFineTurno = ? )";
        resultList.add(List.of("Incasso"));
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.DATE);
        statement.setObject(2, values.get(1), JDBCType.INTEGER);
        statement.setObject(3, values.get(2), JDBCType.INTEGER);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> rowData = new LinkedList<>();
            Optional<Object> incasso = Optional.of(resultSet.getObject(1));
            rowData.addAll(List.of(incasso.get().toString()));
            resultList.add(rowData);
        }
    }

    private void viewFornitoriDiIngrediente(List<String> values, List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        queryString = "SELECT F.PartitaIVA, F.NomeAzienda " +
                "FROM Fornitori F, ORDINI O " +
                "WHERE F.PartitaIVA = O.PartitaIVA " + 
                "AND O.CodOrdine IN ( " +
                "SELECT C.CodOrdine " +
                "FROM Consegne C, Prodotti P " + 
                "WHERE C.CodProdotto = P.CodiceEAN13 " +
                "AND P.CodiceEAN13 = ?)";
        resultList.add(List.of("Partita IVA", "Nome Azienda"));
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.CHAR);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> rowData = new LinkedList<>();
            Optional<Object> partitaIVA = Optional.of(resultSet.getObject(1));
            Optional<Object> nomeAzienda = Optional.of(resultSet.getObject(2));
            rowData.addAll(List.of(
                partitaIVA.get().toString(), 
                nomeAzienda.get().toString()
            ));
            resultList.add(rowData);
        }
    }

    private void viewAllergeniPiatto(List<String> values, List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        queryString = "SELECT P.Nome, P.Descrizione " + 
            "FROM Prodotti P JOIN Ingredienti I ON P.CodiceEAN13 = I.CodiceProdotto " + 
            "WHERE P.TipoGenerico = 0 " + 
            "AND I.CodiceProdotto IN ( " + 
            "SELECT CodiceIngrediente " +
            "FROM Allergeni " +
            "WHERE CodicePiatto = ? " +
            ")";
        resultList.add(List.of("NomeIngrediente", "Descrizione"));
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.CHAR);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> rowData = new LinkedList<>();
            Optional<Object> nome = Optional.of(resultSet.getObject(1));
            Optional<Object> descrizione = Optional.ofNullable(resultSet.getObject(2));
            rowData.addAll(List.of(
                nome.get().toString(), 
                descrizione.isEmpty() ? MetaDataQueries.NULL_VALUE : descrizione.get().toString()
            ));
            resultList.add(rowData);
        }
    }

    private void insertContoPrenotazioneSalvata(List<String> values, List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        queryString = "UPDATE Prenotazioni " +
            "SET Conto = ? " +
            "WHERE CodPrenotazione = ?";
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.INTEGER);
        statement.setObject(2, values.get(1), JDBCType.CHAR);
        statement.executeUpdate();
    }

    private void viewDipendenteStipendioMassimo(List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        queryString = "SELECT D.Codice, D.CF, D.Nome, D.Cognome " +
            "FROM Dipendenti D, Contratti C " +  
            "WHERE C.CodDipendente = D.Codice " + 
            "AND D.Codice IN (SELECT CodDipendente FROM ContrattiCorrenti) " + 
            "ORDER BY C.StipendioMensile DESC " +
            "LIMIT 1";
        resultList.add(List.of("Codice", "Codice Fiscale", "Nome", "Cognome"));
        statement = connection.prepareStatement(queryString);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> rowData = new LinkedList<>();
            Optional<Object> codice = Optional.of(resultSet.getObject(1));
            Optional<Object> CF = Optional.of(resultSet.getObject(2));
            Optional<Object> nome = Optional.of(resultSet.getObject(3));
            Optional<Object> cognome = Optional.of(resultSet.getObject(4));
            rowData.addAll(List.of(
                codice.get().toString(), 
                CF.get().toString(), 
                nome.get().toString(), 
                cognome.get().toString()
            ));
            resultList.add(rowData);
        }
    }

    private void viewPortataCuocoGiorno(List<String> values, List<List<String>> resultList) throws SQLException {
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        queryString = "SELECT Po.Nome " +
            "FROM Portate Po JOIN Preparazioni Pr " + 
            "ON Po.Numero = Pr.NumeroPortata " +
            "WHERE Pr.Data = ? " +
            "AND Pr.CodCuoco = ?";
        resultList.add(List.of("Nome Portata"));
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.DATE);
        statement.setObject(2, values.get(1), JDBCType.CHAR);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> rowData = new LinkedList<>();
            Optional<Object> nomePortata = Optional.of(resultSet.getObject(1));
            rowData.addAll(List.of(nomePortata.get().toString()));
            resultList.add(rowData);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getColumnNames(String tableName) {
        List<String> columnsList = new LinkedList<>();
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        //TODO fix query text in Insert class
        queryString = InsertSelectQueryTexts.getSelectEveryRecordFromTable(tableName);
        try {
            statement = connection.prepareStatement(queryString);
            resultSet = statement.executeQuery();
            var metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnsList.add(metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
        return columnsList;
    }
    
}
