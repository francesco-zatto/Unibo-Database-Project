package project.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.core.Controller;
import project.db.api.RestaurantQuery;

public class SwingView implements View{

    private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

    private final Controller controller;
    private final JFrame frame = new JFrame();
    private final JPanel accessPanel = new JPanel(new BorderLayout());
    private final JPanel menuPanel = new JPanel(new BorderLayout());
    private final JTextField userLabel = new JTextField("Inserisci username");
    private final JTextField passwordLabel = new JTextField("Inserisci password");
    private final JComboBox<RestaurantQuery> queriesComboBox = new JComboBox<>(RestaurantQuery.values());

    public SwingView(Controller controller) {
        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.buildAccessPanel();
    }

    private void buildAccessPanel() {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::tryConnectionToDatabase);
        accessPanel.add(this.userLabel, BorderLayout.NORTH);
        accessPanel.add(this.passwordLabel, BorderLayout.CENTER);
        accessPanel.add(loginButton, BorderLayout.SOUTH);
        this.frame.setContentPane(this.accessPanel);
        this.frame.pack();
    }

    private void tryConnectionToDatabase(ActionEvent e) {
        if (this.controller.tryAccess(this.userLabel.getText(), this.passwordLabel.getText())) {
            this.startConnection();
        }
    }

    @Override
    public void startGUI() {
        this.frame.setLocation((int)(SCREEN_DIMENSION.getWidth() / 2), (int)(SCREEN_DIMENSION.getHeight() / 2));
        this.frame.setVisible(true);
    }

    @Override
    public List<String> runQuery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runQuery'");
    }

    @Override
    public void showResult(List<List<String>> resultList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showResult'");
    }

    @Override
    public void startConnection() {
        this.frame.remove(this.accessPanel);
        this.buildMenuPanel();
        this.frame.setContentPane(this.menuPanel);
        this.frame.repaint();
        System.out.println("CONNESSO!");
    }

    private void buildMenuPanel() {
        this.menuPanel.add(this.queriesComboBox);
    }
    
}
