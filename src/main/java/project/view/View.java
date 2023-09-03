package project.view;

import java.util.List;

public interface View {

    void start();

    void startConnection();

    void loadTableNames(List<String> names);

    void viewTable(List<String> columns, List<List<String>> results);

    void printControlMessage(boolean insertCorrect);

    void viewQueryValues(List<String> values);

    void setColumnsNames(List<String> columns);
    
}
