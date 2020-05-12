import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Operation {
    private static Data data = new Data();
    public JFrame frame;
    public static JTable table;

    public Operation() throws Exception {
        initialize();
    }

    private void initialize() throws Exception {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.setTitle("Operation");
        frame.setBounds(0, 0, 887, 577);
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

        JButton btnSearch = new JButton("Search");
        btnSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnSearch.setBackground(Color.white);
        btnSearch.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnSearch.setBounds(801, 11, 48, 19);
        btnSearch.setFocusPainted(false);
        btnSearch.setContentAreaFilled(false);
        frame.getContentPane().add(btnSearch);

        JButton btnClear = new JButton("Clear");
        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnClear.setBackground(Color.white);
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnClear.setBounds(483, 499, 58, 23);
        btnClear.setFocusPainted(false);
        btnClear.setContentAreaFilled(false);
        frame.getContentPane().add(btnClear);

        JButton btnSelectAll = new JButton("Select All");
        btnSelectAll.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnSelectAll.setBackground(Color.white);
        btnSelectAll.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnSelectAll.setBounds(551, 499, 88, 23);
        btnSelectAll.setFocusPainted(false);
        btnSelectAll.setContentAreaFilled(false);
        frame.getContentPane().add(btnSelectAll);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnUpdate.setBackground(Color.white);
        btnUpdate.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnUpdate.setBounds(780, 499, 58, 23);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setContentAreaFilled(false);
        frame.getContentPane().add(btnUpdate);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnAdd.setBackground(Color.white);
        btnAdd.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnAdd.setBounds(649, 499, 42, 23);
        btnAdd.setFocusPainted(false);
        btnAdd.setContentAreaFilled(false);
        frame.getContentPane().add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnDelete.setBackground(Color.white);
        btnDelete.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnDelete.setBounds(704, 499, 67, 23);
        btnDelete.setFocusPainted(false);
        btnDelete.setContentAreaFilled(false);
        frame.getContentPane().add(btnDelete);

        table = new JTable();
        table.setFont(new Font("Dialog", Font.PLAIN, 14));
        updateTable();
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setForeground(Color.black);
        scrollPane.setBounds(20, 41, 829, 447);
        scrollPane.setViewportView(table);
        frame.getContentPane().add(scrollPane);

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

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationSearch operationSearch;
                try{
                    operationSearch = new OperationSearch();
                    frame.dispose();
                    operationSearch.frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numOfRows = data.getNumOfRows("takes");
                    int numOfCols = data.getNumOfCols("takes");
                    for (int i = 0; i < numOfRows; i++) {
                        table.getModel().setValueAt(Boolean.FALSE, i, numOfCols);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        btnSelectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numOfRows = data.getNumOfRows("takes");
                    int numOfCols = data.getNumOfCols("takes");
                    for (int i = 0; i < numOfRows; i++) {
                        table.getModel().setValueAt(Boolean.TRUE, i, numOfCols);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numOfRows = data.getNumOfRows("takes");
                    int numOfCols = data.getNumOfCols("takes");
                    for (int i = 0; i < numOfRows; i++) {
                        if (table.getModel().getValueAt(i, numOfCols) == Boolean.TRUE) {
                            table.getModel().setValueAt(Boolean.FALSE, i, numOfCols);
                        }
                    }
                    OperationAdd operationAdd = new OperationAdd();
                    operationAdd.frame.setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int count = 0;
                    Takes takes = null;
                    int numOfRows = data.getNumOfRows("takes");
                    int numOfCols = data.getNumOfCols("takes");
                    for (int i = 0; i < numOfRows; i++) {
                        if (table.getModel().getValueAt(i, numOfCols) == Boolean.TRUE) {
                            takes = new Takes(table.getModel().getValueAt(i, 0)
                                    ,table.getModel().getValueAt(i, 1)
                                    ,table.getModel().getValueAt(i, 2)
                                    ,table.getModel().getValueAt(i, 3)
                                    ,table.getModel().getValueAt(i, 4)
                                    ,table.getModel().getValueAt(i, 5));
                            count++;
                        }
                    }
                    if (count == 1){
                        OperationUpdate operationUpdate = new OperationUpdate(takes);
                        operationUpdate.frame.setVisible(true);
                    }
                    else if (count > 1){
                        JOptionPane.showMessageDialog(frame,
                                "Select a single row to update",
                                "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    for (int i = 0; i < numOfRows; i++) {
                        if (table.getModel().getValueAt(i, numOfCols) == Boolean.TRUE) {
                            table.getModel().setValueAt(Boolean.FALSE, i, numOfCols);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Takes> deleteList = new ArrayList<>();
                    int numOfRows = data.getNumOfRows("takes");
                    int numOfCols = data.getNumOfCols("takes");
                    for (int i = 0; i < numOfRows; i++) {
                        if (table.getModel().getValueAt(i, numOfCols) == Boolean.TRUE) {
                            Takes takes = new Takes(table.getModel().getValueAt(i, 0)
                                                    ,table.getModel().getValueAt(i, 1)
                                                    ,table.getModel().getValueAt(i, 2)
                                                    ,table.getModel().getValueAt(i, 3)
                                                    ,table.getModel().getValueAt(i, 4)
                                                    ,table.getModel().getValueAt(i, 5));
                            deleteList.add(takes);
                        }
                    }
                    if (deleteList.size() != 0) {
                        int result = JOptionPane.showConfirmDialog(frame,"Are you sure?", "Warning",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if(result == JOptionPane.YES_OPTION){
                            for (Takes takes : deleteList){
                                try{
                                    data.delete(takes);
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            updateTable();
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public static void updateTable() throws SQLException {
        Object[][] rows = data.getRows("takes");
        String[] tempCols = data.getColumns("takes");
        String[] cols = new String[tempCols.length+1];
        for (int i = 0; i < tempCols.length; i++) cols[i] = tempCols[i];
        cols[tempCols.length] = "check";
        table.setModel(new DefaultTableModel(rows, cols) {
            Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class };
            public Class getColumnClass(int columnIndex) { return columnTypes[columnIndex]; }
        });
    }
}
