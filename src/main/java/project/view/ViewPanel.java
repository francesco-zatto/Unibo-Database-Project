package project.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Panel that allows to choose between a set of table's names.
 */
public class ViewPanel extends JPanel {

    private static final String BUTTON_TEXT = "View";
    private JComboBox<Object> tableNames = new JComboBox<>();

    /**
     * Method to build correctly a ViewPanel.
     * @param loadTableAction the action perfomed when the button is clicked
     */
    public void buildViewPanel(ActionListener loadTableAction) {
        JButton viewButton = new JButton(BUTTON_TEXT);
        viewButton.addActionListener(loadTableAction);
        this.add(viewButton);
        this.add(this.tableNames);
    }

    /**
     * Setter for the names of the tables to choose.
     * @param names tables' names
     */
    public void setTableNames(List<String> names) {
        this.tableNames = new JComboBox<>(names.toArray());
    }

    /**
     * Getter for the selected item, i. e. the name of a table.
     * @return the name of the selected table.
     */
    public String getSelectedItemAsString() {
        return this.tableNames.getSelectedItem().toString();
    } 

}
