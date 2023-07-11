package project.db.tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import project.db.api.Table;
import project.model.Prenotazione;

public class PrenotazioniTable implements Table<Prenotazione, String> {

    private static final String TABLE_NAME = "Prenotazioni";
    private final Connection connection; 

    public PrenotazioniTable(final Connection connection) {
         this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public boolean createTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        "cod INT NOT NULL PRIMARY KEY," +
                        "CF CHAR(16) NOT NULL," + 
                        "nome CHAR(20) NOT NULL," + 
                        "cognome CHAR(20) NOT NULL" +
                        "dataNascita DATE NOT NULL" +
                        "genere CHAR(5) NOT NULL" +
                        "cittàResidenza CHAR(25) NOT NULL" +
                        "Telefono CHAR(9) NOT NULL" +
                        "TipoAddettoPulizia BIT NOT NULL" +
                        "TipologiaPulizia CHAR(1)" +
                    ")");
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    @Override
    public boolean dropTable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropTable'");
    }

    @Override
    public Optional<Prenotazione> findByPrimaryKey(String primaryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPrimaryKey'");
    }

    @Override
    public List<Prenotazione> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean save(Prenotazione value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean update(Prenotazione updatedValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(String primaryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
