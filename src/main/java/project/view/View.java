package project.view;

import java.util.List;

public interface View {

    void start();

    List<String> runQuery();

    void showResult(List<List<String>> resultList);
    
}
