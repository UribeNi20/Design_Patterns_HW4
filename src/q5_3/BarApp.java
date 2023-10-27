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
    private List<Integer> numbers = new ArrayList<>();

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
        setChanged();
        notifyObservers();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void updateNumber(int index, int value) {
        numbers.set(index, value);
        setChanged();
        notifyObservers();
    }
}

/**
 * How to actually see the bar graph
 * Pre: GraphDataModel
 * Post: The graph created from the data
 */

class BarGraph extends JPanel implements Observer {
    private GraphNumber model;
    private int barSpacing = 10; // Adjust the spacing between bars here

    public BarGraph(GraphNumber model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Integer> numbers = model.getNumbers();

        int maxBarWidth = getWidth();
        int barHeight = (getHeight() - (numbers.size() - 1) * barSpacing) / numbers.size();
        int y = 0;

        Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE}; // Define the colors

        for (int i = 0; i < numbers.size(); i++) {
            int value = numbers.get(i);
            int barWidth = (int) ((double) value / 100 * maxBarWidth);
            int x = 0;
            g.setColor(colors[i % colors.length]); // Set the color for the bar
            g.fillRect(x, y, barWidth, barHeight);
            y += barHeight + barSpacing;
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
 */
class NumberController implements KeyListener {
    private GraphNumber model;
    private JTextField textField;
    private int index;

    public NumberController(GraphNumber model, JTextField textField, int index) {
        this.model = model;
        this.textField = textField;
        this.index = index;
        textField.addKeyListener(this);
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
            int value = Integer.parseInt(textField.getText());
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

            // Left Panel (Bar Graphs)
            BarGraph barGraphView = new BarGraph(model);
            frame.add(barGraphView);

            // Right Panel (TextFields)
            JPanel textFieldsPanel = new JPanel();
            textFieldsPanel.setLayout(new GridLayout(3, 1));

            JTextField textField1 = new JTextField();
            JTextField textField2 = new JTextField();
            JTextField textField3 = new JTextField();

            textFieldsPanel.add(textField1);
            textFieldsPanel.add(textField2);
            textFieldsPanel.add(textField3);

            frame.add(textFieldsPanel);

            // Controllers
            NumberController controller1 = new NumberController(model, textField1, 0);
            NumberController controller2 = new NumberController(model, textField2, 1);
            NumberController controller3 = new NumberController(model, textField3, 2);

            // Set initial data
            List<Integer> initialNumbers = new ArrayList<>();
            initialNumbers.add(50);
            initialNumbers.add(75);
            initialNumbers.add(30);
            model.setNumbers(initialNumbers);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
