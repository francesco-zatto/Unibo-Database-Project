package project.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ViewPanel extends JPanel {

    private static final String BUTTON_TEXT = "View";
    private JComboBox<Object> tableNames = new JComboBox<>();

    public void buildViewPanel(ActionListener loadTableAction) {
        JButton viewButton = new JButton(BUTTON_TEXT);
        viewButton.addActionListener(loadTableAction);
        this.add(viewButton);
        this.add(this.tableNames);
    }

    public void setTableNames(List<String> names) {
        this.tableNames = new JComboBox<>(names.toArray());
    }

    public String getSelectedItemAsString() {
        return this.tableNames.getSelectedItem().toString();
    } 

}
