package project.db.tables;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import project.db.api.Table;
import project.model.Pagamento;

public class PagamentiTable implements Table<Pagamento, String> {

    private static final String TABLE_NAME = "Pagamenti";
    private final Connection connection; 

    public PagamentiTable(final Connection connection) {
         this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public boolean createTable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTable'");
    }

    @Override
    public boolean dropTable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropTable'");
    }

    @Override
    public Optional<Pagamento> findByPrimaryKey(String primaryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPrimaryKey'");
    }

    @Override
    public List<Pagamento> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean save(Pagamento value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean update(Pagamento updatedValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(String primaryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}