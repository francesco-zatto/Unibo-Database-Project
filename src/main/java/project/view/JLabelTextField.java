package project.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JLabelTextField extends JPanel {

    private static final int LENGTH_FIELD = 20;
    private final JLabel label;
    private final JTextField textField;

    public JLabelTextField(String text) {
        this.label = new JLabel(text);
        this.textField = new JTextField(LENGTH_FIELD);
        this.add(label);
        this.add(textField);
    }

    public String getText() {
        return this.textField.getText();
    }

    public void setTextFieldEmpty() {
        this.textField.setText("");
    }
    
}
