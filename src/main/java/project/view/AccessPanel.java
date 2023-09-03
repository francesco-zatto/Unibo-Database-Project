package project.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AccessPanel extends JPanel {

    private static final String USER_TEXT = "Inserisci username: ";
    private static final String PASSWORD_TEXT = "Inserisici password: ";
    private static final String LOGIN_TEXT = "Login"; 
    private final JLabelTextField userField = new JLabelTextField(USER_TEXT);
    private final JLabelTextField passwordField = new JLabelTextField(PASSWORD_TEXT);

    public AccessPanel() {
        this.setLayout(new BorderLayout());
    }

    public void buildAccessPanel(ActionListener loginAction) {
        JButton loginButton = createLoginButton(loginAction);
        var northPanel = createPanelWithField(this.userField);
        var centerPanel = createPanelWithField(this.passwordField);
        addPanels(loginButton, northPanel, centerPanel);
    }

    private JButton createLoginButton(ActionListener loginAction) {
        var button = new JButton(LOGIN_TEXT);
        button.addActionListener(loginAction);
        return button;
    } 

    private JPanel createPanelWithField(JLabelTextField field) {
        var panel = new JPanel();
        panel.add(field);
        return panel;
    }

    private void addPanels(final JButton loginButton, final JPanel northPanel, final JPanel centerPanel) {
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(loginButton, BorderLayout.SOUTH);
    }

    public String getUserText() {
        return this.userField.getText();
    }

    public String getPasswordText() {
        return this.passwordField.getText();
    }
    
}
