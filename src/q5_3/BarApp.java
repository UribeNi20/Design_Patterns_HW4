/*
Nicolas Uribe
Homework 4
COP 4331 002
 */
package q5_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Handling the data within the graph
 * Post: An updated list of graph numbers
 */
class GraphNumber extends Observable {
    private List<Integer> data = new ArrayList<>();
    //List of number
    public void setNumbers(List<Integer> numbers) {
        this.data = numbers;
        setChanged();
        notifyObservers();
    }
    //Returns current list of numbers

    public List<Integer> getNumbers() {
        return data;
    }

    /**
     * Updates number in list and user is noted
     * @param index
     * @param value
     */
    public void updateNumber(int index, int value) {
        data.set(index, value);
        setChanged();
        notifyObservers();
    }
}

/**
 * JPanel visualizes graph numbers
 * How to actually see the bar graph
 * Pre: GraphDataModel
 * Post: The graph created from the data
 */

class BarGraph extends JPanel implements Observer {
    private GraphNumber model;
    private int spacing = 10; // space between the bars

    public BarGraph(GraphNumber model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Integer> numbers = model.getNumbers();

        int maxBarWidth = getWidth();
        int barHeight = (getHeight() - (numbers.size() - 1) * spacing) / numbers.size();
        int y = 0;

        Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE}; //Different colors for the bars

        for (int i = 0; i < numbers.size(); i++) {
            int value = numbers.get(i);
            int barWidth = (int) ((double) value / 100 * maxBarWidth);
            int x = 0;
            g.setColor(colors[i % colors.length]);
            g.fillRect(x, y, barWidth, barHeight);
            y += barHeight + spacing;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

/**
 * Taking user input
 * Pre: GraphDataModel, JTextField
 * Post: The user input updates the model
 */class NumberController implements KeyListener {
    private GraphNumber model;
    private JTextField text;
    private int index;

    public NumberController(GraphNumber model, JTextField text, int index) {
        this.model = model;
        this.text = text;
        this.index = index;
        text.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            int value = Integer.parseInt(text.getText());
            model.updateNumber(index, value);
        } catch (NumberFormatException ex) {
            // Handle invalid input
        }
    }
}

public class BarApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphNumber model = new GraphNumber();
            JFrame frame = new JFrame("Editable Bar Graphs");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1, 2));

            // Left- Graphs
            BarGraph barGraphView = new BarGraph(model);
            frame.add(barGraphView);

            // Right- TextFields
            JPanel textFieldsPanel = new JPanel();
            textFieldsPanel.setLayout(new GridLayout(3, 1));

            // Placeholder numbers
            JTextField text1 = new JTextField("0");
            JTextField text2 = new JTextField("50");
            JTextField text3 = new JTextField("100");

            textFieldsPanel.add(text1);
            textFieldsPanel.add(text2);
            textFieldsPanel.add(text3);

            frame.add(textFieldsPanel);

            // Controllers
            NumberController controller1 = new NumberController(model, text1, 0);
            NumberController controller2 = new NumberController(model, text2, 1);
            NumberController controller3 = new NumberController(model, text3, 2);

            // Set initial data
            List<Integer> startingNumbers = new ArrayList<>();
            startingNumbers.add(0);
            startingNumbers.add(50);
            startingNumbers.add(100);
            model.setNumbers(startingNumbers);

            frame.pack();
            frame.setVisible(true);
        });
    }
}