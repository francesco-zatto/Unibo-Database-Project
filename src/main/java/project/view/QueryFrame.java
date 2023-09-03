package project.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class QueryFrame {

    private static final String TITLE = "Inserire correttamente i dati";
    private static final int RATIO_CONSTANT = 4;
    private static final String RUN_TEXT = "Run query";
    private final JFrame frame = new JFrame(TITLE);
    private final JPanel queryPanel = new JPanel();
    private final List<JLabelTextField> valuesList = new LinkedList<>();

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

    public List<String> getInsertedValues() {
        return this.valuesList.stream()
                .map(JLabelTextField::getText)
                .toList();
    }
    
}
