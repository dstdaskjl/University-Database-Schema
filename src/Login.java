import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JSeparator;

public class Login {
    Data data = new Data();

    public JFrame frame;
    public JLabel succMessage;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(Color.BLACK);
        frame.setResizable(false);
        frame.setTitle("Login");
        frame.setBounds(100, 100, 421, 362);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JLabel singinLabel = new JLabel("User Login");
        singinLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        singinLabel.setBounds(151, 26, 120, 23);
        frame.getContentPane().add(singinLabel);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(128, 128, 128));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        usernameLabel.setBounds(49, 73, 66, 19);
        frame.getContentPane().add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(128, 128, 128));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        passwordLabel.setBounds(49, 149, 62, 19);
        frame.getContentPane().add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setToolTipText("");
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 14));
        usernameField.setBounds(49, 93, 313, 28);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);
        usernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 14));
        passwordField.setEchoChar('*');
        passwordField.setBounds(49, 170, 313, 28);
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        frame.getContentPane().add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        loginButton.setBounds(49, 251, 313, 42);
        loginButton.setBackground(new Color(0, 191, 255));
        loginButton.setBorderPainted(false);
        frame.getContentPane().add(loginButton);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 191, 255));
        separator.setBounds(49, 126, 313, 2);
        frame.getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(new Color(0, 191, 255));
        separator_1.setBounds(49, 203, 313, 2);
        frame.getContentPane().add(separator_1);

        JLabel errorMessage = new JLabel("Invalid username or password");
        errorMessage.setForeground(new Color(255, 0, 0));
        errorMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        errorMessage.setBounds(101, 216, 209, 19);
        ImageIcon errIcon = new ImageIcon("C:\\Source\\exclamationmark.png");
        Image errImg = errIcon.getImage().getScaledInstance(errorMessage.getHeight()-5, errorMessage.getHeight()-5, Image.SCALE_SMOOTH);
        errorMessage.setIcon(new ImageIcon(errImg));
        errorMessage.setVisible(false);
        frame.getContentPane().add(errorMessage);

        succMessage = new JLabel("You have securely logged out");
        succMessage.setForeground(new Color(0, 128, 128));
        succMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        succMessage.setBounds(101, 216, 206, 19);
        ImageIcon succIcon = new ImageIcon("C:\\Source\\checkmark.png");
        Image succImg = succIcon.getImage().getScaledInstance(succMessage.getHeight()-5, succMessage.getHeight()-5, Image.SCALE_SMOOTH);
        succMessage.setIcon(new ImageIcon(succImg));
        succMessage.setVisible(false);
        frame.getContentPane().add(succMessage);

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordField.requestFocus();
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                succMessage.setVisible(false);
                String name = usernameField.getText();
                String pass = passwordField.getText();

                try {
                    if (data.hasAccount(name, pass)){
                        FlashScreen fs;
                        try {
                            fs = new FlashScreen();
                            fs.frame.setVisible(true);
                            frame.dispose();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        errorMessage.setVisible(true);
                        usernameField.setText("");
                        passwordField.setText("");
                        usernameField.requestFocus();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });
    }
}

