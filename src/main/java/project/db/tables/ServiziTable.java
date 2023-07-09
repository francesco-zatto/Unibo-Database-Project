package project.db.tables;

import java.util.List;
import java.util.Optional;

import project.db.api.Table;
import project.model.Servizio;

public class ServiziTable implements Table<Servizio, String> {

    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTableName'");
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
    public Optional<Servizio> findByPrimaryKey(String primaryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPrimaryKey'");
    }

    @Override
    public List<Servizio> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean save(Servizio value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean update(Servizio updatedValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(String primaryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
