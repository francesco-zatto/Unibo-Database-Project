package project.core;

import java.sql.Connection;
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
        int numberColumns = findNumberOfColumns(table);
        String values = new StringBuilder().append("(")
            .append(String.join(",", getIteratorValues(numberColumns)))
            .append(")")
            .toString();
        System.out.println(values);
        String query = "INSERT" + table + "VALUES";
        ResultSet resultSet;
        /*try {
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
            return false;
        }*/
        return true;
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

    public Optional<List<List<String>>> runQuery(RestaurantQuery query) {
        switch (query) {
            case AGGIUNGERE_CONTO:
                break;
            case AGGIUNGERE_MENU:
                break;
            case AGGIUNGERE_PIATTO:
                break;
            case AGGIUNGERE_TAVOLO_RISTORANTE:
                break;
            case INSERIRE_CONTRATTO_DIPENDENTE:
                break;
            case INSERIRE_NUOVA_PRENOTAZIONE:
                break;
            case REGISTRARE_ORDINE_FORNITORE:
                break;
            case REGISTRARE_PAGAMENTO_FATTURA:
                break;
            case REGISTRARE_PAGAMENTO_TASSA:
                break;
            case VISUALIZZARE_ALLERGENI_PIATTO:
                break;
            case VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO:
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
        return null;        
    }
    
}
