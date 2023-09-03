package project.query;

import java.util.List;

public interface Table {

    Record getColumnsNames();

    default List<String> getColumnsAsList() {
        return getColumnsNames().getElements();
    }

    List<Record> getRecords();

    Record getRecord(int index);

    default List<String> getRecordAsList(int index) {
        return getRecord(index).getElements();
    }

}
