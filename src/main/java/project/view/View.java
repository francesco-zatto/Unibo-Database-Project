package project.view;

import java.util.List;

public interface View {

    void startGUI();

    void startConnection();

    void loadTableNames(List<String> names);

    void viewTable(List<String> columns, List<List<String>> results);

    void printControlMessage(boolean insertCorrect);

    List<String> runQuery();
    
}
