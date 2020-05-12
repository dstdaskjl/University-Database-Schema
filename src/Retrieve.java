import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Retrieve {
    Data data = new Data();

    public JFrame frame;
    public static JTable table;
    private static String currSelection;

    public Retrieve() throws Exception {
        initialize();
    }

    private void initialize() throws Exception {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.setTitle("Retrieve");
        frame.setBounds(0, 0, 886, 557);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JButton btnBack = new JButton("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnBack.setBackground(Color.white);
        btnBack.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnBack.setBounds(10, 11, 48, 19);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        frame.getContentPane().add(btnBack);

        table = new JTable();
        table.setFont(new Font("Dialog", Font.PLAIN, 14));
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setForeground(Color.black);
        scrollPane.setBounds(20, 41, 829, 447);
        scrollPane.setViewportView(table);
        frame.getContentPane().add(scrollPane);

        String[] tableNames = data.getTableNames();
        currSelection = tableNames[0];
        JComboBox comboBox = new JComboBox(tableNames);
        comboBox.setBackground(Color.white);
        comboBox.setBounds(685, 11, 106, 22);
        frame.getContentPane().add(comboBox);

        JButton btnView = new JButton("View");
        btnView.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnView.setBackground(Color.white);
        btnView.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnView.setBounds(801, 11, 48, 19);
        btnView.setFocusPainted(false);
        btnView.setContentAreaFilled(false);
        frame.getContentPane().add(btnView);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                currSelection = (String)cb.getSelectedItem();
            }
        });


        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FlashScreen fs;
                try {
                    fs = new FlashScreen();
                    frame.dispose();
                    fs.frame.setVisible(true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object[][] rows = data.getRows(currSelection);
                    String[] cols = data.getColumns(currSelection);
                    table.setModel(new DefaultTableModel(rows, cols) {
                        Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class };
                        public Class getColumnClass(int columnIndex) { return columnTypes[columnIndex]; }
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
