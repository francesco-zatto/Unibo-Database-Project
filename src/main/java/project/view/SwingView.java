package project.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import project.core.Controller;
import project.db.api.RestaurantQuery;

public class SwingView implements View{

    public static final int LENGTH_FIELD = 20;
    private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

    private final Controller controller;
    private final JFrame frame = new JFrame("RESTAURANT DATABASE");
    private final AccessPanel accessPanel = new AccessPanel();
    private final JPanel menuPanel = new JPanel(new BorderLayout());
    private final JComboBox<RestaurantQuery> queriesComboBox = new JComboBox<>(RestaurantQuery.values());
    private final JTable outputTable = new JTable(new DefaultTableModel());
    private final ViewPanel viewPanel = new ViewPanel();
    private final InsertPanel insertPanel = new InsertPanel();

    public SwingView(Controller controller) {
        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.accessPanel.buildAccessPanel(this::tryConnectionToDatabase);
        this.frame.setContentPane(this.accessPanel);
        this.frame.pack();
    }

    private void tryConnectionToDatabase(final ActionEvent e) {
        this.controller.tryAccess(this.accessPanel.getUserText(), this.accessPanel.getPasswordText());
    }

    @Override
    public void start() {
        this.frame.setLocation((int)(SCREEN_DIMENSION.getWidth() / 4), (int)(SCREEN_DIMENSION.getHeight() / 4));
        this.frame.setVisible(true);
    }

    @Override
    public void startConnection() {
        this.frame.remove(this.accessPanel);
        this.controller.loadTableNames();
        this.buildMenuPanel();
        this.frame.setContentPane(this.menuPanel);
        this.frame.pack();
        this.frame.repaint();
    }

    @Override
    public void loadTableNames(List<String> names) {
        this.viewPanel.setTableNames(names);
        this.insertPanel.setTableNames(names);
    }

    @Override
    public void viewTable(List<String> columns, List<List<String>> results) {
        var allResultsArray = getAllResultsArray(columns, results);
        TableModel tableModel = new DefaultTableModel(allResultsArray, columns.toArray());
        updateOutputTable(tableModel);
    }

    private Object[][] getAllResultsArray(List<String> columns, List<List<String>> results) {
        int numRows = results.size();
        int numColumns = columns.size();
        Object[][] allResultsArray = new Object[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            allResultsArray[i] = results.get(i).toArray();
        }
        return allResultsArray;
    }

    private void updateOutputTable(TableModel tableModel) {
        this.outputTable.setModel(tableModel);
        this.outputTable.setEnabled(false);
        this.outputTable.sizeColumnsToFit(-1);
    }

    @Override
    public void printControlMessage(boolean insertCorrect) {
        if (insertCorrect) {
            showWrongInsertPane();
        } else {
            showCorrectInsertPane();
        }
    }

    private void showWrongInsertPane() {
        String correctMessage = "Inserimento andato a buon fine!";
        String ok = "OK!";
        showMessageAfterInsert(correctMessage, ok, JOptionPane.PLAIN_MESSAGE);
    }

    private void showCorrectInsertPane() {
        String wrongMessage = "Inserimento errato!";
        String danger = "!";
        showMessageAfterInsert(wrongMessage, danger, JOptionPane.WARNING_MESSAGE);
    }

    private void showMessageAfterInsert(String mainMessage, String secondMessage, int paneType) {
        JOptionPane.showMessageDialog(this.frame, mainMessage, secondMessage, paneType);
    }

    @Override
    public void viewQueryValues(List<String> values) {
        QueryFrame queryFrame = new QueryFrame(SCREEN_DIMENSION, values);
        var query = (RestaurantQuery) this.queriesComboBox.getSelectedItem();
        queryFrame.addRunQueryButton(e -> this.runQuery(query, queryFrame.getInsertedValues()));
    }

    private void runQuery(RestaurantQuery query, List<String> insertedValues) {
        this.controller.runQuery(query, insertedValues);
    }

    @Override
    public void setColumnsNames(List<String> columns) {
        this.insertPanel.updateLowerPanel(columns);
        this.frame.repaint();
        this.frame.pack();
    }

    private void buildMenuPanel() {
        buildLeftPanel();
        buildRightPanel();
        this.frame.pack();
    }

    private void buildLeftPanel() {
        JPanel upperPanel = new JPanel();
        JPanel leftPanel = new JPanel(new BorderLayout());
        JButton goButton = createGoButton();
        JScrollPane scrollPane = new JScrollPane(this.outputTable);
        upperPanel.add(this.queriesComboBox, BorderLayout.NORTH);
        upperPanel.add(goButton);
        leftPanel.add(upperPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        this.menuPanel.add(leftPanel, BorderLayout.WEST);
    }

    private JButton createGoButton() {
        JButton goButton = new JButton("Go!");
        goButton.addActionListener(
            e -> this.controller.loadQueryValues((RestaurantQuery) this.queriesComboBox.getSelectedItem())
        );
        return goButton;
    }

    private void buildRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        this.viewPanel.buildViewPanel(this::loadTable);
        rightPanel.add(this.viewPanel, BorderLayout.NORTH);
        this.insertPanel.buildInsertPanel(this::loadFields, this::checkNameSelection);
        rightPanel.add(this.insertPanel, BorderLayout.CENTER);
        this.menuPanel.add(rightPanel, BorderLayout.EAST);
        this.controller.loadColumnsNames(this.insertPanel.getSelectedItemAsString());
        this.frame.pack();
    }

    private void loadTable(ActionEvent e) {
        this.controller.loadTable(this.viewPanel.getSelectedItemAsString());
    }

    private void loadFields(ActionEvent e) {
        this.controller.insertInTable(
            this.insertPanel.getInsertedText(), 
            this.insertPanel.getSelectedItemAsString()
        );
    }

    private void checkNameSelection(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            controller.loadColumnsNames(e.getItem().toString());
        }
    }

}
