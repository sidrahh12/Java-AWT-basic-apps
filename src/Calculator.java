import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    private final JTextField textField;
    private final JButton[] numberButtons;
    private final JButton addButton;
    private final JButton subtractButton;
    private final JButton multiplyButton;
    private final JButton divideButton;
    private final JButton decimalButton;
    private final JButton equalButton;
    private final JButton eraseButton;
    private final JButton clearButton;

    private double firstNumber = 0;
    private double result = 0;
    private char operator;

    public Calculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 30, 240, 30);
        textField.setEditable(false);

        numberButtons = new JButton[10];
        JButton[] functionButtons = new JButton[9];

        JPanel panel = new JPanel();
        panel.setBounds(30, 80, 240, 240);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        eraseButton = new JButton("Erase");
        clearButton = new JButton("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = equalButton;
        functionButtons[6] = eraseButton;
        functionButtons[7] = clearButton;

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
        }

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalButton);
        panel.add(divideButton);

        frame.add(textField);
        frame.add(panel);

        eraseButton.setBounds(30, 330, 100, 30);
        clearButton.setBounds(170, 330, 100, 30);
        eraseButton.addActionListener(this);
        clearButton.addActionListener(this);
        frame.add(eraseButton);
        frame.add(clearButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decimalButton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subtractButton) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == multiplyButton) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divideButton) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        double secondNumber;
        if (e.getSource() == equalButton) {
            secondNumber = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+' -> result = firstNumber + secondNumber;
                case '-' -> result = firstNumber - secondNumber;
                case '*' -> result = firstNumber * secondNumber;
                case '/' -> result = firstNumber / secondNumber;
            }

            textField.setText(String.valueOf(result));
            firstNumber = result;
        }
        if (e.getSource() == eraseButton) {
            String currentText = textField.getText();
            if (currentText.length() > 0) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
        if (e.getSource() == clearButton) {
            textField.setText("");
            firstNumber = 0;
            result = 0;
        }
    }
}