package project.core;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;

public class Database {

    private static final String NULL_VALUE = "NULL";

    private final Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public List<String> getTableNames() {
        List<String> list = new LinkedList<>();
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'restaurant'";
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(resultSet.getString("table_name"));
            }
        } catch (SQLException e) {
            return List.of();
        }
        return list;
    }

    public List<List<String>> getTable(String tableName) {
        List<String> columns = new LinkedList<>();
        List<List<String>> list = new LinkedList<>();
        String query = "SELECT * FROM " + tableName;
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(resultSetMetaData.getColumnName(i));
            }
            list.add(columns);
            while (resultSet.next()) {
                List<String> rowData = new LinkedList<>();
                for (int i = 1; i <= columnCount; i++) {
                    Optional<Object> currentElement = Optional.ofNullable(resultSet.getObject(i));
                    if (currentElement.isEmpty()) {
                        rowData.add(NULL_VALUE);
                    } else {
                        rowData.add(currentElement.get().toString());
                    }
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            return List.of();
        }
        return list;
    }

    public boolean insertInTable(List<String> elements, String table) {
        List<String> elementsWithNulls = elements.stream()
            .map(e -> {
                if (e.isBlank()) return (String)(null);
                return e;
            }).toList();
        int numberColumns = findNumberOfColumns(table);
        List<Integer> tableTypes = findTableTypes(table);
        String values = this.getValues(numberColumns);
        String query = "INSERT " + table + " VALUES " + values;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < numberColumns; i++) {
                statement.setObject(i + 1, elementsWithNulls.get(i), tableTypes.get(i));
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private List<Integer> findTableTypes(String table) {
        List<Integer> tableTypes = new LinkedList<>();
        String query = "SELECT DATA_TYPE from INFORMATION_SCHEMA.COLUMNS " +
            "where table_schema = 'restaurant' AND table_name = '" + table + "'";
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tableTypes.add(getJDBCType(resultSet));
            }
            return tableTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private Integer getJDBCType(ResultSet resultSet) throws SQLException {
        if (resultSet.getString(1).equals("int")) {
            return JDBCType.INTEGER.getVendorTypeNumber();
        } 
        return JDBCType.valueOf(resultSet.getString(1).toUpperCase()).getVendorTypeNumber();
    }

    private String getValues(int numberColumns) {
        return new StringBuilder().append("(")
            .append(String.join(",", getIteratorValues(numberColumns)))
            .append(")")
            .toString();
    }

    private Iterable<CharSequence> getIteratorValues(int number) {
        return new Iterable<CharSequence>() {

            @Override
            public Iterator<CharSequence> iterator() {
                return new Iterator<CharSequence>() {

                    private int count = 0;

                    @Override
                    public boolean hasNext() {
                        return count < number;
                    }

                    @Override
                    public CharSequence next() {
                        count++;
                        return "?";
                    }
                };
            
            } 
        };
    }

    private int findNumberOfColumns(String table) {
        String query = "SELECT count(*) FROM information_schema.columns WHERE table_name = '" + table + "'";
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        return 0;
    }

    public List<String> getQueryValues(RestaurantQuery query) {
        switch (query) {
            case VISUALIZZARE_ALLERGENI_PIATTO:
                return List.of("CodPiatto");
            case VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO:
                return List.of();
            case VISUALIZZARE_FORNITORI_DI_INGREDIENTE:
                return List.of("CodIngrediente");
            case VISUALIZZARE_INCASSO_TURNO:
                return List.of("Data", "OraInizioTurno", "OraFineTurno");
            case VISUALIZZARE_PORTATA_CUOCO_GIORNO:
                return List.of("Data");
            case VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO:
                return List.of("Data");
            case VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT:
                return List.of("NumeroSala", "CodSlot");
        }
        return List.of();
    }

    public Optional<List<List<String>>> runQuery(RestaurantQuery query, List<String> values) {
        List<List<String>> resultList = new LinkedList<>();
        String queryString;
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            switch (query) {
            case VISUALIZZARE_ALLERGENI_PIATTO:
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
                    rowData.add(nome.get().toString());
                    Optional<Object> descrizione = Optional.ofNullable(resultSet.getObject(2));
                    if (descrizione.isEmpty()) {
                        rowData.add(NULL_VALUE);
                    } else {
                        rowData.add(descrizione.get().toString());
                    }
                    resultList.add(rowData);
                }
                break;
            case VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO:
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
                break;
            case VISUALIZZARE_FORNITORI_DI_INGREDIENTE:
                break;
            case VISUALIZZARE_INCASSO_TURNO:
                break;
            case VISUALIZZARE_PORTATA_CUOCO_GIORNO:
                break;
            case VISUALIZZARE_PRENOTAZIONI_CON_SLOT_AGGIUNTIVO:
                break;
            case VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT:
                break;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(resultList);        
    }
    
}
