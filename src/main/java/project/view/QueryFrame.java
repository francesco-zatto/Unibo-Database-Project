package project.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class to create a frame where it is possible to insert values to run an already selected query.
 * The purpose of this class is not to choose the right query, but given the chosen query this class 
 * allow the user to insert values and get them to run the query.
 */
public class QueryFrame {

    private static final String TITLE = "Inserire correttamente i dati";
    private static final int RATIO_CONSTANT = 4;
    private static final String RUN_TEXT = "Run query";
    private final JFrame frame = new JFrame(TITLE);
    private final JPanel queryPanel = new JPanel();
    private final List<JLabelTextField> valuesList = new LinkedList<>();

    /**
     * Constructor for a query frame. 
     * @param screenDimension dimension of the screen where the frame is
     * @param values values of the table's fields 
     */
    public QueryFrame(Dimension screenDimension, List<String> values) {
        this.frame.setLocation(getFrameWidth(screenDimension), getFrameHeigth(screenDimension));
        fillValues(values);
        this.frame.setContentPane(this.queryPanel);
        this.frame.pack();
        this.frame.setAlwaysOnTop(true);
        this.frame.setVisible(true);
    }

    private int getFrameWidth(Dimension screenDimension) {
        return (int)(screenDimension.getWidth() / RATIO_CONSTANT);
    }

    private int getFrameHeigth(Dimension screenDimension) {
        return (int)(screenDimension.getHeight() / RATIO_CONSTANT);
    }

    private void fillValues(List<String> values) {
        values.forEach(this::addValue);
    }

    private void addValue(String value) {
        var valueField = new JLabelTextField(value);
        this.valuesList.add(valueField);
        this.queryPanel.add(valueField);
    }

    /**
     * Method to add the button to run the query. It must be added, otherwise it is impossible to run it.
     * @param runQuery the action performed when the button is clicked
     */
    public void addRunQueryButton(ActionListener runQuery) {
        JButton runQueryButton = createRunQueryButton(runQuery);
        this.queryPanel.add(runQueryButton);
        this.frame.pack();
    }

    private JButton createRunQueryButton(ActionListener runQuery) {
        var button = new JButton(RUN_TEXT);
        button.addActionListener(runQuery);
        return button;
    }

    /**
     * The frame has some fields to fill with the correct values to run the query.
     * Using this method it is possible to get the text inside those fields.
     * @return inserted values to run the query
     */
    public List<String> getInsertedValues() {
        return this.valuesList.stream()
                .map(JLabelTextField::getText)
                .toList();
    }
    
}
