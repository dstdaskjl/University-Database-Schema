import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JSeparator;

public class OperationAdd {
    private Data data = new Data();
    public JFrame frame;
    public static JLabel succMessage;
    public static JLabel emptyErrMessage;
    public static JLabel dupErrMessage;
    public static JLabel noParentErrMessage;
    public static JLabel dataTypeErrMessage;
    public static JTextField textField;
    public static JTextField textField_1;
    public static JTextField textField_2;
    public static JTextField textField_3;
    public static JTextField textField_4;
    public static JTextField textField_5;

    public OperationAdd() throws Exception {
        initialize();
    }

    public static void setLabelsInvisible() {
        noParentErrMessage.setVisible(false);
        dupErrMessage.setVisible(false);
        succMessage.setVisible(false);
        emptyErrMessage.setVisible(false);
        dataTypeErrMessage.setVisible(false);
    }

    public static void setTextFieldsNull() {
        textField.setText(null);
        textField_1.setText(null);
        textField_2.setText(null);
        textField_3.setText(null);
        textField_4.setText(null);
        textField_5.setText(null);
        textField.requestFocus();
    }

    private void initialize() throws Exception {
        frame = new JFrame();
        frame.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.setTitle("Add");
        frame.setBounds(100, 100, 405, 484);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JButton btnClear = new JButton("Clear");
        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnClear.setBackground(Color.white);
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnClear.setBounds(221, 404, 60, 23);
        btnClear.setFocusPainted(false);
        btnClear.setContentAreaFilled(false);
        frame.getContentPane().add(btnClear);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnAdd.setBackground(Color.white);
        btnAdd.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnAdd.setBounds(291, 404, 60, 23);
        btnAdd.setFocusPainted(false);
        btnAdd.setContentAreaFilled(false);
        frame.getContentPane().add(btnAdd);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNewLabel.setForeground(new Color(128, 128, 128));
        lblNewLabel.setBounds(38, 11, 28, 19);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblStaff = new JLabel("course_id");
        lblStaff.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblStaff.setForeground(new Color(128, 128, 128));
        lblStaff.setBounds(38, 69, 71, 19);
        frame.getContentPane().add(lblStaff);

        JLabel lblCategory = new JLabel("sec_id");
        lblCategory.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblCategory.setForeground(new Color(128, 128, 128));
        lblCategory.setBounds(38, 128, 58, 19);
        frame.getContentPane().add(lblCategory);

        JLabel lblLocation = new JLabel("semester");
        lblLocation.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblLocation.setForeground(new Color(128, 128, 128));
        lblLocation.setBounds(38, 186, 71, 19);
        frame.getContentPane().add(lblLocation);

        JLabel lblCondition = new JLabel("year");
        lblCondition.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblCondition.setForeground(new Color(128, 128, 128));
        lblCondition.setBounds(38, 242, 60, 19);
        frame.getContentPane().add(lblCondition);

        JLabel lblQuantity = new JLabel("grade");
        lblQuantity.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblQuantity.setForeground(new Color(128, 128, 128));
        lblQuantity.setBounds(38, 302, 53, 19);
        frame.getContentPane().add(lblQuantity);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField.setBounds(38, 30, 313, 28);
        frame.getContentPane().add(textField);


        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField_1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField_1.setBounds(38, 89, 313, 28);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField_2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField_2.setBounds(38, 147, 313, 28);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField_3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField_3.setBounds(38, 203, 313, 28);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField_4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField_4.setBounds(38, 263, 313, 28);
        frame.getContentPane().add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField_5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField_5.setBounds(38, 322, 313, 28);
        frame.getContentPane().add(textField_5);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 191, 255));
        separator.setBounds(38, 63, 313, 2);
        frame.getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(new Color(0, 191, 255));
        separator_1.setBounds(38, 122, 313, 2);
        frame.getContentPane().add(separator_1);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(new Color(0, 191, 255));
        separator_2.setBounds(38, 181, 313, 2);
        frame.getContentPane().add(separator_2);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBackground(new Color(0, 191, 255));
        separator_3.setBounds(38, 238, 313, 2);
        frame.getContentPane().add(separator_3);

        JSeparator separator_4 = new JSeparator();
        separator_4.setBackground(new Color(0, 191, 255));
        separator_4.setBounds(38, 297, 313, 2);
        frame.getContentPane().add(separator_4);

        JSeparator separator_5 = new JSeparator();
        separator_5.setBackground(new Color(0, 191, 255));
        separator_5.setBounds(38, 356, 313, 2);
        frame.getContentPane().add(separator_5);

        noParentErrMessage = new JLabel("Check the referenced table");
        noParentErrMessage.setForeground(new Color(255, 0, 0));
        noParentErrMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        noParentErrMessage.setBounds(179, 374, 172, 19);
        noParentErrMessage.setVisible(false);
        frame.getContentPane().add(noParentErrMessage);

        dupErrMessage = new JLabel("Data already exists");
        dupErrMessage.setForeground(new Color(255, 0, 0));
        dupErrMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        dupErrMessage.setBounds(230, 374, 121, 19);
        dupErrMessage.setVisible(false);
        frame.getContentPane().add(dupErrMessage);

        emptyErrMessage = new JLabel("Any field except grade cannot be left empty");
        emptyErrMessage.setForeground(new Color(255, 0, 0));
        emptyErrMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        emptyErrMessage.setBounds(77, 374, 274, 19);
        emptyErrMessage.setVisible(false);
        frame.getContentPane().add(emptyErrMessage);

        dataTypeErrMessage = new JLabel("Check data types");
        dataTypeErrMessage.setForeground(new Color(255, 0, 0));
        dataTypeErrMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        dataTypeErrMessage.setBounds(241, 374, 110, 19);
        dataTypeErrMessage.setVisible(false);
        frame.getContentPane().add(dataTypeErrMessage);

        succMessage = new JLabel("Added successfully");
        succMessage.setForeground(new Color(0, 128, 128));
        succMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        succMessage.setBounds(212, 374, 139, 19);
        succMessage.setVisible(false);
        frame.getContentPane().add(succMessage);

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setLabelsInvisible();
                setTextFieldsNull();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setLabelsInvisible();
                String str1 = textField.getText();
                String str2 = textField_1.getText();
                String str3 = textField_2.getText();
                String str4 = textField_3.getText();
                String str5 = textField_4.getText();
                String str6 = textField_5.getText();
                if (str1.length() != 0 && str2.length() != 0 && str3.length() != 0 && str4.length() != 0 && str5.length() != 0) {
                    try {
                        int testVal = Integer.valueOf(str5);
                    } catch (Exception ee){
                        System.out.println(ee);
                        dataTypeErrMessage.setVisible(true);
                        setTextFieldsNull();
                        return;
                    }

                    try {
                        Takes takes = new Takes(str1, str2, str3, str4, Integer.valueOf(str5), null);
                        if (data.hasDup(takes)){
                            dupErrMessage.setVisible(true);
                            setTextFieldsNull();
                            return;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        setTextFieldsNull();
                        return;
                    }

                    try {
                        Takes takes = new Takes(str1, str2, str3, str4, Integer.valueOf(str5), null);
                        if (!data.hasParent(takes)){
                            noParentErrMessage.setVisible(true);
                            setTextFieldsNull();
                            return;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        setTextFieldsNull();
                        return;
                    }

                    try {
                        if (str6.equals("") || str6.toLowerCase().equals("null")){
                            Takes takes = new Takes(str1, str2, str3, str4, Integer.valueOf(str5), null);
                            data.insert(takes);
                        }
                        else{
                            Takes takes = new Takes(str1, str2, str3, str4, Integer.valueOf(str5), str6);
                            data.insert(takes);
                        }
                        setTextFieldsNull();
                        Operation.updateTable();
                        return;
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        noParentErrMessage.setVisible(true);
                        setTextFieldsNull();
                        return;
                    }
                }
                else {
                    emptyErrMessage.setVisible(true);
                    setTextFieldsNull();
                    return;
                }
            }
        });



        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField_1.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }
        });
        textField_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField_2.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }
        });
        textField_2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField_3.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }
        });
        textField_3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField_4.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }
        });
        textField_4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField_5.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
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

        btnAdd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdd.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }
        });
    }
}
