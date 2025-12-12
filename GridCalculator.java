package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridCalculator extends JFrame implements ActionListener {

    private JTextField display;

    private JPanel panel;
    private JButton[] digitButton = new JButton[10];
    private JButton[] opButton = new JButton[4];

    private JButton btnEqual;
    private JButton btnClear;

    private Font f, btnFont;

    double first, second, result;

    String op;
    String[] operator = {"+", "-", "*", "/"};

    GridCalculator() {
        setLayout(null);

        setTitle("BIT CALCULATOR");

        f = new Font("Arial", Font.BOLD, 20);
        btnFont = new Font("Arial", Font.BOLD, 18);

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(f);
        display.setForeground(Color.blue);
        display.setBounds(20, 20, 300, 50);
        add(display);

        panel = new JPanel();
        panel.setBounds(20, 80, 300, 350);
        panel.setLayout(new GridLayout(4, 4));

        // Number buttons
        for (int i = 0; i < digitButton.length; i++) {
            digitButton[i] = new JButton(String.valueOf(i));
            digitButton[i].setFont(btnFont);
            digitButton[i].addActionListener(this);
            panel.add(digitButton[i]);
        }

        // Operator buttons
        for (int i = 0; i < opButton.length; i++) {
            opButton[i] = new JButton(operator[i]);
            opButton[i].setFont(btnFont);
            opButton[i].addActionListener(this);
            panel.add(opButton[i]);
        }

        // Equal button
        btnEqual = new JButton("=");
        btnEqual.setFont(btnFont);
        btnEqual.addActionListener(this);
        panel.add(btnEqual);

        // Clear button
        btnClear = new JButton("C");
        btnClear.setFont(btnFont);
        btnClear.addActionListener(this);
        panel.add(btnClear);

        add(panel);

        setSize(350, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton src = (JButton) e.getSource();

        // Number buttons
        for (int i = 0; i < digitButton.length; i++) {
            if (src == digitButton[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }

        // Operator buttons
        for (int i = 0; i < opButton.length; i++) {
            if (src == opButton[i]) {
                first = Double.parseDouble(display.getText());
                display.setText("");
                op = operator[i];
                return;
            }
        }

        // Equal button
        if (src == btnEqual) {
            second = Double.parseDouble(display.getText());

            switch (op) {
                case "+":
                    result = first + second;
                    break;
                case "-":
                    result = first - second;
                    break;
                case "*":
                    result = first * second;
                    break;
                case "/":
                    result = (second != 0) ? first / second : Double.NaN;
                    break;
            }

            display.setText(String.valueOf(result));
        }

        // Clear button
        if (src == btnClear) {
            display.setText("");
            first = 0;
            second = 0;
            result = 0;
            op = "";
        }
    }
}

