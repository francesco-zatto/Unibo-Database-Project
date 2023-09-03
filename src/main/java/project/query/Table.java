package project.query;

import java.util.List;

/**
 * Interface that represents a database's table.
 */
public interface Table {

    /**
     * Getter for the columns' names.
     * @return columns's names
     */
    Record getColumnsNames();

    /**
     * Getter for the columns' names as a list of strings.
     * @return columns' names
     */
    default List<String> getColumnsAsList() {
        return getColumnsNames().getElements();
    }

    /**
     * Getter for the table's records.
     * @return table's records
     */
    List<Record> getRecords();

    /**
     * Getter for a record in the given index.
     * @param index record's index, it must be inside of table's size
     * @return a record
     */
    Record getRecord(int index);

    /**
     * Getter for a record as a list of strings.
     * @param index record's index, it must be inside of table's size
     * @return a record as a list of strings.
     */
    default List<String> getRecordAsList(int index) {
        return getRecord(index).getElements();
    }

}
