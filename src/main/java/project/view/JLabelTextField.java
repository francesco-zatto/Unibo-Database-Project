package project.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class to represent a pair of a JLabel and JTextField, encapsulating them.
 */
public class JLabelTextField extends JPanel {

    private final JLabel label;
    private final JTextField textField;

    /**
     * Constructor that sets the label with the passed text.
     * @param text text of the label
     */
    public JLabelTextField(final String text) {
        this.label = new JLabel(text);
        this.textField = new JTextField(SwingView.LENGTH_FIELD);
        this.add(label);
        this.add(textField);
    }

    /**
     * Getter for the textField's text.
     * @return textField's text
     */
    public String getText() {
        return this.textField.getText();
    }

    /**
     * Method to set the textField empty.
     */
    public void setTextFieldEmpty() {
        this.textField.setText("");
    }
    
}
