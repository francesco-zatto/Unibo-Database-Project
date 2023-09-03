package project.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    private final JTable output = new JTable(new DefaultTableModel());
    private final ViewPanel viewPanel = new ViewPanel();
    private final InsertPanel insertPanel = new InsertPanel();
    private JComboBox<Object> tableNamesInsert;
    private List<JLabelTextField> fieldList = new LinkedList<>();
    //private JButton clearButton = new JButton("Clear text fields");
    private JPanel lowerInsertPanel = new JPanel();

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

    @Override
    public void printControlMessage(boolean insertCorrect) {
        if (!insertCorrect) {
            JOptionPane.showMessageDialog(
                this.frame, 
                "Inserimento Errato!", 
                "!", 
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                this.frame, 
                "Inserimento andato a buon fine!", 
                "OK!", 
                JOptionPane.PLAIN_MESSAGE
            );
        }
    }

    @Override
    public void viewQueryValues(List<String> values) {
        JFrame queryFrame = new JFrame("Inserire correttamente i dati:");
        queryFrame.setLocation((int)(SCREEN_DIMENSION.getWidth() / 4), (int)(SCREEN_DIMENSION.getHeight() / 4));
        JPanel queryPane = new JPanel();
        List<JTextField> valueList = new LinkedList<>();
        for (var value : values) {
            JLabel label = new JLabel(value);
            JTextField textField = new JTextField(LENGTH_FIELD);
            valueList.add(textField);
            JPanel pairPanel = new JPanel();
            pairPanel.add(label);
            pairPanel.add(textField);
            queryPane.add(pairPanel);
        }
        JButton runQueryButton = new JButton("Run query");
        runQueryButton.addActionListener(
            e -> this.controller.runQuery(
                (RestaurantQuery) this.queriesComboBox.getSelectedItem(), 
                valueList.stream()
                    .map(JTextField::getText)
                    .toList()
            )
        );
        queryPane.add(runQueryButton);
        queryFrame.setContentPane(queryPane);
        queryFrame.pack();
        queryFrame.setAlwaysOnTop(true);
        queryFrame.setVisible(true);
    }

    @Override
    public void setColumnsNames(List<String> columns) {
        this.lowerInsertPanel.setLayout(new BoxLayout(lowerInsertPanel, BoxLayout.Y_AXIS));
        this.lowerInsertPanel.removeAll();
        this.fieldList.clear();
        for (var column : columns) {
            JLabelTextField tempPanel = new JLabelTextField(column);
            this.fieldList.add(tempPanel);
            lowerInsertPanel.add(tempPanel);
        }
        this.insertPanel.add(lowerInsertPanel);
        this.insertPanel.updateClearButton();
        /*TODO this.insertPanel.remove(this.clearButton);
        this.insertPanel.add(this.clearButton);*/
        this.frame.repaint();
        this.frame.pack();
    }

    private void buildMenuPanel() {
        buildLeftPanel();
        buildRightPanel();
        this.frame.pack();
    }

    private void buildRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        this.viewPanel.buildViewPanel(this::loadTable);
        rightPanel.add(this.viewPanel, BorderLayout.NORTH);
        this.insertPanel.buildInsertPanel(
            e -> this.loadFields(),
            e -> this.fieldList.forEach(JLabelTextField::setTextFieldEmpty),
            e -> checkNameSelection(e)
        );
        this.controller.loadColumnsNames(this.insertPanel.getSelectedItemAsString());
        this.frame.pack();
        rightPanel.add(this.insertPanel, BorderLayout.CENTER);
        this.menuPanel.add(rightPanel, BorderLayout.EAST);
    }

    private void checkNameSelection(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            controller.loadColumnsNames(e.getItem().toString());
        }
    }

    private void loadTable(ActionEvent e) {
        this.controller.loadTable(this.viewPanel.getSelectedItemAsString());
    }

    private void buildLeftPanel() {
        JPanel upperPanel = new JPanel();
        JPanel leftPanel = new JPanel(new BorderLayout());
        JButton goButton = new JButton("Go!");
        goButton.addActionListener(
            e -> this.controller.loadQueryValues((RestaurantQuery) this.queriesComboBox.getSelectedItem())
        );
        JScrollPane scrollPane = new JScrollPane(this.output);
        upperPanel.add(this.queriesComboBox, BorderLayout.NORTH);
        upperPanel.add(goButton);
        leftPanel.add(upperPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        this.menuPanel.add(leftPanel, BorderLayout.WEST);
    }

    private void loadFields() {
        this.controller.insertInTable(
            this.fieldList.stream()
                .map(JLabelTextField::getText)
                .toList(), 
            this.tableNamesInsert.getSelectedItem().toString());
    }    
}
