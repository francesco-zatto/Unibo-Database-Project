package project.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Panel where it is possible to insert records, selecting the table and inserting correctly values.
 */
public class InsertPanel extends JPanel {

    private static final String INSERT_TEXT = "Insert";
    private static final String CLEAR_TEXT = "Clear text fields";
    private final JPanel lowerPanel = new JPanel();
    private final List<JLabelTextField> fieldList = new LinkedList<>();
    private JComboBox<Object> tableNames = new JComboBox<>();
    private JButton clearButton;

    /**
     * Constructor for an InsertPanel.
     */
    public InsertPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lowerPanel.setLayout(new BoxLayout(this.lowerPanel, BoxLayout.Y_AXIS));
    }

    /**
     * Method to build correctly the insertPanel.
     * @param buttonListener the action performed by the button to try inserting values
     * @param namesListener the action performed when the name of the button changes
     */
    public void buildInsertPanel(ActionListener buttonListener, ItemListener namesListener) {
        var insertButton = createInsertButton(buttonListener);
        var upperPanel = createUpperPanel(insertButton);
        this.add(upperPanel);
        this.tableNames.addItemListener(namesListener);
        ActionListener clearListener = e -> this.fieldList.forEach(JLabelTextField::setTextFieldEmpty);
        this.clearButton = createClearButton(clearListener);
        this.add(clearButton);
    }

    private JButton createClearButton(ActionListener clearListener) {
        var button = new JButton(CLEAR_TEXT);
        button.addActionListener(clearListener);
        return button;
    }

    private JButton createInsertButton(ActionListener buttonListener) {
        var button = new JButton(INSERT_TEXT);
        button.addActionListener(buttonListener);
        return button;
    }

    private JPanel createUpperPanel(final JButton insertButton) {
        var panel = new JPanel();
        panel.add(insertButton);
        panel.add(this.tableNames);
        return panel;
    } 

    /**
     * Setter for the names of the tables.
     * @param names every tables' name
     */
    public void setTableNames(List<String> names) {
        this.tableNames = new JComboBox<>(names.toArray());
    }

    /**
     * Getter for the name of the selected item, i.e. the table's name.
     * @return the table's name
     */
    public String getSelectedItemAsString() {
        return this.tableNames.getSelectedItem().toString();
    } 

    /**
     * Getter for the text inserted by the user in the fields.
     * @return a list of the values inserted
     */
    public List<String> getInsertedText() {
        return this.fieldList.stream()
                .map(JLabelTextField::getText)
                .toList();
    }

    public void updateLowerPanel(List<String> columns) {
        this.lowerPanel.removeAll();
        this.fieldList.clear();
        for (var column : columns) {
            addPanelBy(column);
        }
        this.add(lowerPanel);
        this.updateClearButton();
    }

    private void addPanelBy(String column) {
        JLabelTextField tempPanel = new JLabelTextField(column);
        this.fieldList.add(tempPanel);
        this.lowerPanel.add(tempPanel);
    }

    private void updateClearButton() {
        this.remove(this.clearButton);
        this.add(this.clearButton);
    }
    
}
