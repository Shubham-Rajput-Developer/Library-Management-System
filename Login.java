import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener 
{
    JLabel l1,l2,img;
    JTextField tf;
    JPasswordField p1;
    JButton loginButton,resetButton;
    JCheckBox showPassword;
    JFrame frame;
    Login()
 {
       frame=this;
        ImageIcon i1=new ImageIcon("Register.jpg");
        Image image=i1.getImage();
        Image newimg=image.getScaledInstance(1500,1500,java.awt.Image.SCALE_SMOOTH);
        i1=new ImageIcon(newimg);
        JLabel img=new JLabel(i1);
        add(img);
    
        JLabel heading=new JLabel(" Login Form");
        Font font1=new Font("Times New Roman",Font.BOLD,40);

        heading.setFont(font1);
        heading.setForeground(Color.WHITE);

        l1 = new JLabel("USERNAME");
        l2 = new JLabel("PASSWORD");
        tf = new JTextField();
        p1= new JPasswordField();
        loginButton = new JButton("LOGIN");
        resetButton = new JButton("RESET");
        showPassword = new JCheckBox("Show Password");
	    Font font2=new Font("Times New Roman",Font.BOLD,20);
        l1.setFont(font2);
        Font font3=new Font("Times New Roman",Font.BOLD,20);
        tf.setFont(font3);
        p1.setFont(font3);

        l1.setForeground(Color.WHITE);
        l2.setFont(font2);
        l2.setForeground(Color.WHITE);

        heading.setBounds(450,100,300,70);
        l1.setBounds(400, 200, 120, 30);
        l2.setBounds(400, 300, 120, 30);
        tf.setBounds(550, 200, 150, 30);
        p1.setBounds(550, 300, 150, 30);
        showPassword.setBounds(550, 335, 150, 30);
        loginButton.setBounds(410, 400, 100, 30);
        resetButton.setBounds(550, 400, 100, 30);

        showPassword.addActionListener(this);
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);

        img.add(heading);
        img.add(l1);
        img.add(l2);
        img.add(tf);
        img.add(p1);
        img.add(showPassword);
        img.add(loginButton);
        img.add(resetButton);

        setSize(1500,1500);
       setVisible(true);
       setLayout(null);

    }

     public void actionPerformed(ActionEvent e) 
{
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) 
{           
            int id=0;
            String userText;
            String pwdText;
            userText = tf.getText();
            pwdText = p1.getText();
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                System.out.println("Successfull");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT count(*) AS total from REGISTER_USERS where name ='"+userText+"' and password='"+pwdText+"'");
                rs.next();
                id=rs.getInt("total");
            }
            catch(Exception ex)
            {
            System.out.println(ex);
            }
            if (id>=1) 
            {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new User(userText);
                frame.dispose();
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) 
        {
            tf.setText("");
            p1.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) 
        {
            if (showPassword.isSelected()) 
            {
                p1.setEchoChar((char) 0);
            } 
            else 
            {
                p1.setEchoChar('*');
            }
        }
    }
    public static void main(String args[])
    {
        Login f=new Login();

    }
}