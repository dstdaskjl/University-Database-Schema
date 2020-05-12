import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class OperationUpdate {
    private Data data = new Data();
    public JFrame frame;
    private JTextField textField_1;
    private Takes takes;

    public OperationUpdate(Takes takes) throws Exception {
        this.takes = takes;
        initialize();
    }

    private void initialize() throws Exception {
        frame = new JFrame();
        frame.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.setTitle("Add");
        frame.setBounds(100, 100, 405, 208);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JButton btnClear = new JButton("Clear");
        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnClear.setBackground(Color.white);
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnClear.setBounds(221, 135, 60, 23);
        btnClear.setFocusPainted(false);
        btnClear.setContentAreaFilled(false);
        frame.getContentPane().add(btnClear);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnUpdate.setBackground(Color.white);
        btnUpdate.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnUpdate.setBounds(291, 135, 60, 23);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setContentAreaFilled(false);
        frame.getContentPane().add(btnUpdate);

        JLabel lblOldGrade = new JLabel("Current Grade");
        lblOldGrade.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblOldGrade.setForeground(new Color(128, 128, 128));
        lblOldGrade.setBounds(38, 11, 98, 19);
        frame.getContentPane().add(lblOldGrade);

        JLabel lblLocation = new JLabel("New Grade");
        lblLocation.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblLocation.setForeground(new Color(128, 128, 128));
        lblLocation.setBounds(38, 69, 78, 19);
        frame.getContentPane().add(lblLocation);

        JLabel lblFixedOldGrade;
        if (takes.grade == null || takes.grade.toString().length() == 0){
            lblFixedOldGrade = new JLabel("No Grade");
        }
        else{
            lblFixedOldGrade = new JLabel((String) takes.grade);
        }
        lblFixedOldGrade.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblFixedOldGrade.setForeground(Color.black);
        lblFixedOldGrade.setForeground(new Color(128, 128, 128));
        lblFixedOldGrade.setBounds(38, 41, 313, 14);
        frame.getContentPane().add(lblFixedOldGrade);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField_1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField_1.setBounds(38, 89, 313, 28);
        frame.getContentPane().add(textField_1);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 191, 255));
        separator.setBounds(38, 63, 313, 2);
        frame.getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(new Color(0, 191, 255));
        separator_1.setBounds(38, 122, 313, 2);
        frame.getContentPane().add(separator_1);



        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_1.setText(null);
                textField_1.requestFocus();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object text = textField_1.getText();
                if (text.toString().length() > 2){
                    JOptionPane.showMessageDialog(frame,
                            "Check data types",
                            "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
                else{
                    try {
                        data.update(takes, text);
                        Operation.updateTable();
                        frame.setVisible(false);
                        frame.dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnClear.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnClear.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }
        });

        btnUpdate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnUpdate.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }
        });
    }
}
