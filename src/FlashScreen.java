import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FlashScreen {

    public JFrame frame;

    public FlashScreen() throws IOException {
        initialize();
    }

    private void initialize() throws IOException {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.setTitle("Menu");
        frame.setBounds(100, 100, 369, 258);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnLogOut.setBackground(Color.WHITE);
        btnLogOut.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnLogOut.setBounds(278, 11, 65, 19);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setContentAreaFilled(false);
        frame.getContentPane().add(btnLogOut);


        JButton btnRetrieve = new JButton("Retrieve a table");
        btnRetrieve.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnRetrieve.setBackground(Color.white);
        btnRetrieve.setFont(new Font("Dialog", Font.PLAIN, 16));
        btnRetrieve.setBounds(111, 56, 143, 47);
        btnRetrieve.setFocusPainted(false);
        btnRetrieve.setContentAreaFilled(false);
        frame.getContentPane().add(btnRetrieve);

        JButton btnOperation = new JButton("Operations on Takes");
        btnOperation.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnOperation.setBackground(Color.white);
        btnOperation.setFont(new Font("Dialog", Font.PLAIN, 16));
        btnOperation.setBounds(95, 114, 171, 47);
        btnOperation.setFocusPainted(false);
        btnOperation.setContentAreaFilled(false);
        frame.getContentPane().add(btnOperation);

        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.succMessage.setVisible(true);
                login.frame.setVisible(true);
                frame.dispose();
            }
        });


        btnRetrieve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Retrieve retrieve;
                try {
                    retrieve = new Retrieve();
                    retrieve.frame.setVisible(true);
                    frame.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });


        btnOperation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operation operation;
                try {
                    operation = new Operation();
                    operation.frame.setVisible(true);
                    frame.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });


        btnLogOut.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogOut.doClick();
                }
            }
        });

        btnRetrieve.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnRetrieve.doClick();
                }
            }
        });

        btnOperation.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnOperation.doClick();
                }
            }
        });
    }
}
