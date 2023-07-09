package project.view;

import java.util.List;

public interface View {

    void startGUI();

    List<String> startConnection();

    List<String> runQuery();

    void showResult(List<List<String>> resultList);
    
}
