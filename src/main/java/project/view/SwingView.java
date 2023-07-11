package project.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import project.core.Controller;
import project.db.api.RestaurantQuery;

public class SwingView implements View{

    private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int LENGTH_FIELD = 20;
    private static final int START_TABLE_SIZE = 10;

    private final Controller controller;
    private final JFrame frame = new JFrame();
    private final JPanel accessPanel = new JPanel(new BorderLayout());
    private final JPanel menuPanel = new JPanel(new BorderLayout());
    private final JTextField userText = new JTextField(LENGTH_FIELD);
    private final JTextField passwordText = new JPasswordField(LENGTH_FIELD);
    private final JComboBox<RestaurantQuery> queriesComboBox = new JComboBox<>(RestaurantQuery.values());
    private final JTable output = new JTable(new DefaultTableModel());
    private final JPanel viewPanel = new JPanel();
    private final JPanel insertPanel = new JPanel();
    private JComboBox<Object> tableNames;

    public SwingView(Controller controller) {
        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.buildAccessPanel();
    }

    private void buildAccessPanel() {
        JLabel userLabel = new JLabel("Inserisci username: ");
        JLabel passwordLabel = new JLabel("Inserisci password: ");
        JButton loginButton = new JButton("Login");
        var northPanel = new JPanel();
        var centerPanel = new JPanel();
        loginButton.addActionListener(this::tryConnectionToDatabase);
        northPanel.add(userLabel);
        northPanel.add(this.userText);
        centerPanel.add(passwordLabel);
        centerPanel.add(this.passwordText);
        accessPanel.add(northPanel, BorderLayout.NORTH);
        accessPanel.add(centerPanel, BorderLayout.CENTER);
        accessPanel.add(loginButton, BorderLayout.SOUTH);
        this.frame.setContentPane(this.accessPanel);
        this.frame.pack();
    }

    private void tryConnectionToDatabase(ActionEvent e) {
        if (this.controller.tryAccess(this.userText.getText(), this.passwordText.getText())) {
            this.startConnection();
        }
    }

    @Override
    public void startGUI() {
        this.frame.setLocation((int)(SCREEN_DIMENSION.getWidth() / 4), (int)(SCREEN_DIMENSION.getHeight() / 4));
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
        this.frame.pack();
        this.frame.repaint();
    }

    private void buildMenuPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(this.output);
        leftPanel.add(this.queriesComboBox, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        this.menuPanel.add(leftPanel, BorderLayout.WEST);
        JPanel rightPanel = new JPanel(new BorderLayout());
        this.buildViewPanel();
        rightPanel.add(this.viewPanel, BorderLayout.NORTH);
        this.buildInsertPanel();
        rightPanel.add(this.insertPanel, BorderLayout.CENTER);
        this.menuPanel.add(rightPanel, BorderLayout.EAST);
        this.frame.pack();
    }

    private void buildViewPanel() {
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> this.controller.loadTable(this.tableNames.getSelectedItem().toString()));
        this.controller.loadTableNames();
        this.viewPanel.add(viewButton);
        this.viewPanel.add(tableNames);
    }

    private void buildInsertPanel() {

    }

    @Override
    public void loadTableNames(List<String> names) {
        this.tableNames = new JComboBox<>(names.toArray());
    }

    @Override
    public void viewTable(List<String> columns, List<List<String>> results) {
        int numRows = results.size();
        int numColumns = columns.size();
        Object[][] allResultsArray = new Object[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            allResultsArray[i] = results.get(i).toArray();
        }
        TableModel tableModel = new DefaultTableModel(allResultsArray, columns.toArray());
        this.output.setModel(tableModel);
        this.output.setEnabled(false);
        this.output.sizeColumnsToFit(-1);
    }    
}
