import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class SRegister extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JTextField t1,t2,t6,t7;
    JButton b1,b2;
    JPasswordField p1,p2;
    JComboBox t5;
    SRegister()
    {
    setSize(1500,1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon i1=new ImageIcon("Register.jpg");
    Image image=i1.getImage();
    Image newimg=image.getScaledInstance(1500,1000,java.awt.Image.SCALE_SMOOTH);
    i1=new ImageIcon(newimg);
    JLabel l10=new JLabel(i1);
    add(l10);

    JLabel l1=new JLabel("Register Form");
    Font font1=new Font("Times New Roman",Font.BOLD,70);
    l1.setFont(font1);
    l1.setForeground(Color.WHITE);

    String country[]={"India","America","Japan","USA","UK"};
    t5=new JComboBox(country);
    //data
    Font font2=new Font("Times New Roman",Font.BOLD,40);
    Font font3=new Font("Times New Roman",Font.BOLD,20);

    JLabel l2 = new JLabel("Name:");  
    l3 = new JLabel("Email-ID:");  
    l4 = new JLabel("Create Password:");  
    l5 = new JLabel("Confirm Password:");  
    l6 = new JLabel("Country:");  
    l7 = new JLabel("State:");  
    l8 = new JLabel("Phone No:");   
    t1 = new JTextField();  
    t2 = new JTextField();  
    p1 = new JPasswordField();  
    p2 = new JPasswordField();    
    t6 = new JTextField();  
    t7 = new JTextField();  
    b1 = new JButton("Submit");  
    b2 = new JButton("Clear"); 
	l9 = new JLabel();
    b1.addActionListener(this);
    b2.addActionListener(this);
    b1.setFont(font3);
    b2.setFont(font3);

    l2.setFont(font2);
    l3.setFont(font2);
    l4.setFont(font2);
    l5.setFont(font2);
    l6.setFont(font2);
    l7.setFont(font2);
    l8.setFont(font2);
    l9.setFont(font2);

    l9.setForeground(Color.RED);
    l2.setForeground(Color.WHITE);
    l3.setForeground(Color.WHITE);
    l4.setForeground(Color.WHITE);
    l5.setForeground(Color.WHITE);
    l6.setForeground(Color.WHITE);
    l7.setForeground(Color.WHITE);
    l8.setForeground(Color.WHITE); 
    t1.setFont(font3);
    t2.setFont(font3);
    p1.setFont(font3);
    p2.setFont(font3);
    t5.setFont(font3);
    t6.setFont(font3);
    t7.setFont(font3);
 
    l1.setBounds(550, 70, 500, 80);  
    l2.setBounds(400, 200, 300, 50);  
    l3.setBounds(400, 250, 300, 50);  
    l4.setBounds(400, 300, 350, 50);  
    l5.setBounds(400, 350, 350, 50);  
    l6.setBounds(400, 400, 300, 50);  
    l7.setBounds(400, 450, 300, 50);  
    l8.setBounds(400, 500, 300, 50);
    t1.setBounds(800, 200, 200, 40);  
    t2.setBounds(800, 250, 200, 40); 
    p1.setBounds(800, 300, 200, 40);  
    p2.setBounds(800, 350, 200, 40);
    t5.setBounds(800, 400, 200, 40);  
    t6.setBounds(800, 450, 200, 40);  
    t7.setBounds(800, 500, 200, 40);
    b1.setBounds(600, 600, 100, 50);  
    b2.setBounds(750, 600, 100, 50); 
 	l9.setBounds(500,800,500,60);

        l10.add(l1);
        l10.add(l2);  
        l10.add(t1);  
        l10.add(l3);  
        l10.add(t2);  
        l10.add(l4);  
        l10.add(p1);  
        l10.add(l5);  
        l10.add(p2);  
        l10.add(l6);  
        l10.add(t5);  
        l10.add(l7);  
        l10.add(t6);  
        l10.add(l8);  
        l10.add(t7);  
        l10.add(b1);  
        l10.add(b2);
	    l10.add(l9); 
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
        String s1=t1.getText();
        String s2=t2.getText();
        String s3=p1.getText();
        String s4=p2.getText();
        String s5=(String) t5.getItemAt(t5.getSelectedIndex());
        String s6=t6.getText();
        String s7=t7.getText();
        if((s1.equals("")) || (s2.equals("")) || (s3.equals("")) || (s4.equals("")) ||(s6.equals("")) || (s7.equals("")))
        {
            JOptionPane.showMessageDialog(this,"Enter data in the textField");
        }
        else
        {   
        if(s3.equals(s4))
        {
        try
        {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
        System.out.println("Successfull");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT count(*) AS total from REGISTER_USERS");
        rs.next();
        int id=rs.getInt("total");
        id++;
        st.executeQuery("INSERT INTO REGISTER_USERS VALUES ('"+id+"','"+s1+"','"+s2+"','"+s3+"','"+s5+"','"+s6+"','"+s7+"')");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        JOptionPane.showMessageDialog(this,"Register Successfull and Login again");
        new Login().setVisible(true);
        this.setVisible(false);
        }
        else
        {
        JOptionPane.showMessageDialog(this,"Password not matched.");
        }
        }
    }
    // for Clear button
    if(e.getSource()==b2)
    {
    t1.setText("");
    t2.setText("");
    t6.setText("");
    t7.setText("");
    p1.setText("");
    p2.setText("");
    }
    }
    public static void main(String args[])
    {
    SRegister f1=new SRegister();
    f1.setVisible(true);
    }
}