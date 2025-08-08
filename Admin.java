import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Admin extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l10;
	JTextField t1,t2;
	JButton b1;
	Admin()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon i1=new ImageIcon("Register.jpg");
		Image image=i1.getImage();
		Image newimg=image.getScaledInstance(1500,1000,java.awt.Image.SCALE_SMOOTH);
		i1=new ImageIcon(newimg);
		JLabel l10=new JLabel(i1);
		add(l10);

        l1 = new JLabel("Admin Login");
		l2 = new JLabel("User Name:");
		l3 = new JLabel("Password:");
		t1 = new JTextField(20);
		t2 = new JPasswordField(20);
		b1 = new JButton("Login");
		
		Font f1 = new Font("Time new Roman ",Font.BOLD,50);
		Font f2 = new Font("Time new Roman ",Font.BOLD,20);
		
		l1.setFont(f1);
		l1.setForeground(Color.WHITE);
		l2.setFont(f2);
		l2.setForeground(Color.WHITE);
		l3.setFont(f2);
		l3.setForeground(Color.WHITE);
		t1.setFont(f2);
		t2.setFont(f2);
		b1.setFont(f2);
		l1.setBounds(300,100,500,60);
		l2.setBounds(300,200,200,40);
		l3.setBounds(300,300,200,40);
		t1.setBounds(500,200,150,40);
		t2.setBounds(500,300,150,40);
		b1.setBounds(400,400,100,50);
		b1.addActionListener(this);
		
		l10.add(l1);
		l10.add(l2);
		l10.add(l3);
		l10.add(t1);
		l10.add(t2);
		l10.add(b1);

		setSize(1500,1000);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		String s1 = t1.getText();
		String s2 = t2.getText();
		if(s1.equals("") && s2.equals("") )
		{
			JOptionPane.showMessageDialog(this,"Empty User_name and Password");

		}else if(s1.equals("TYCM") && s2.equals("TYCM@500"))
		{
			JOptionPane.showMessageDialog(this,"!!!! Login Successfully !!!!! Welcome to Admin Page");
			new Adminpage().setVisible(true);
			this.setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Wrong User_name and Password");
		}
	}
	public static void main(String args[])
	{
	Admin f1=new Admin();
	}
}