package project.query;

import java.util.List;

/**
 * Class that represents a record of a database's table.
 */
public interface Record {
    
    /**
     * Getter for the elements of a record as a list of strings.
     * @return the record's elements
     */
    List<String> getElements();

}
