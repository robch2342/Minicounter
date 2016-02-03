package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by robin on 2016-02-01.
 */
public class MinicounterGUI implements ActionListener{
    Main main = new Main();
    private JPanel panel;
    private JTextField textField1;
    private JButton räknaButton;

    public MinicounterGUI() {
        räknaButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            textField1.setText("" + main.parse(textField1.getText()));
        }catch (Exception e) {
            textField1.setText("Luser error");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Minicounter");
        frame.setContentPane(new MinicounterGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
