package project.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class InsertPanel extends JPanel {

    private static final String INSERT_TEXT = "Insert";
    private static final String CLEAR_TEXT = "Clear text fields";
    private JComboBox<Object> tableNames = new JComboBox<>();
    private JButton clearButton;

    public InsertPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void buildInsertPanel(
        ActionListener buttonListener, 
        ActionListener clearListener, 
        ItemListener namesListener
    ) {
        var insertButton = createInsertButton(buttonListener);
        var upperPanel = createUpperPanel(insertButton);
        this.add(upperPanel);
        this.tableNames.addItemListener(namesListener);
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

    public void setTableNames(List<String> names) {
        this.tableNames = new JComboBox<>(names.toArray());
    }

    public String getSelectedItemAsString() {
        return this.tableNames.getSelectedItem().toString();
    } 

    public void updateClearButton() {
        this.remove(this.clearButton);
        this.add(this.clearButton);
    }
    
}
