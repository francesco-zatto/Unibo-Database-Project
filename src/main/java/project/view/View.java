package project.view;

import java.util.List;

public interface View {

    void startGUI();

    void startConnection();

    List<String> runQuery();

    void showResult(List<List<String>> resultList);
    
}
