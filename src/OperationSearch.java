import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OperationSearch {
    Data data = new Data();

    public JFrame frame;
    public static JTable table;
    private static String currSelection1;
    private static String currSelection2;

    public OperationSearch() throws Exception {
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

        String[] ids = data.getIDs();
        currSelection1 = ids[0];
        JComboBox comboBox1 = new JComboBox(ids);
        comboBox1.setBackground(Color.white);
        comboBox1.setBounds(418, 11, 81, 22);
        frame.getContentPane().add(comboBox1);

        JButton btnView1 = new JButton("View");
        btnView1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnView1.setBackground(Color.white);
        btnView1.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnView1.setBounds(509, 11, 48, 19);
        btnView1.setFocusPainted(false);
        btnView1.setContentAreaFilled(false);
        frame.getContentPane().add(btnView1);

        String[] infos = data.getSectionInfo();
        currSelection2 = infos[0];
        JComboBox comboBox2 = new JComboBox(infos);
        comboBox2.setBackground(Color.white);
        comboBox2.setBounds(606, 11, 185, 22);
        frame.getContentPane().add(comboBox2);

        JButton btnView2 = new JButton("View");
        btnView2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnView2.setBackground(Color.white);
        btnView2.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnView2.setBounds(801, 11, 48, 19);
        btnView2.setFocusPainted(false);
        btnView2.setContentAreaFilled(false);
        frame.getContentPane().add(btnView2);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                currSelection1 = (String)cb.getSelectedItem();
            }
        });

        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                currSelection2 = (String)cb.getSelectedItem();
            }
        });


        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operation operation;
                try {
                    operation = new Operation();
                    frame.dispose();
                    operation.frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnView1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object[][] rows = data.getRowsFromTakesID(currSelection1);
                    String[] cols = data.getColsFromTakesID(currSelection1);
                    table.setModel(new DefaultTableModel(rows, cols) {
                        Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class };
                        public Class getColumnClass(int columnIndex) { return columnTypes[columnIndex]; }
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnView2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Object[][] rows = data.getRowsFromTakesInfo(currSelection2);
                    String[] cols = data.getColsFromTakesInfo(currSelection2);
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
