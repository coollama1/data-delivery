import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeliveryApp {

    public static void main(String [] args){
        DeliveryApp app = new DeliveryApp();
        app.run();
    }

    public void run(){
        JFrame frame = new JFrame("Still Testing Swing");
        Container contentPane = frame.getContentPane();
        JPanel panel = new JPanel();

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JTextField usernameTextField = new JTextField(10);
        JTextField passwordTextField = new JTextField(10);
        JButton logInButton = new JButton("Log in");

        usernameLabel.setPreferredSize(new Dimension(100,20));
        usernameTextField.setPreferredSize(new Dimension(200,20));
        passwordLabel.setPreferredSize(new Dimension(100,20));
        passwordTextField.setPreferredSize(new Dimension(200,20));

        panel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));

        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);

        contentPane.add(BorderLayout.CENTER,panel);
        contentPane.add(BorderLayout.SOUTH,logInButton);

        frame.setSize(300,170);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}