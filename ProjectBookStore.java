import java.awt.*;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
public class ProjectBookStore extends Frame implements ActionListener
{
	JButton b1,b2,b3;
	ProjectBookStore()
	{
	Font font1=new Font("Times New Roman",Font.BOLD,80);
	Font font2=new Font("Times New Roman",Font.BOLD,20);
	Font font3=new Font("Times New Roman",Font.BOLD,25);

	ImageIcon i1=new ImageIcon("Home.jpg");
	Image image=i1.getImage();	
	Image newimg=image.getScaledInstance(1500,1000,java.awt.Image.SCALE_SMOOTH);
	i1=new ImageIcon(newimg);

	b1=new JButton("ADMIN");
	b2=new JButton("REGISTER");
	b3=new JButton("LOGIN");
	b1.setBounds(900,50,150,50);
	b2.setBounds(1060,50,170,50);
	b3.setBounds(1240,50,150,50);
	b1.setFont(font2);
	b2.setFont(font2);
	b3.setFont(font2);

	JLabel l2=new JLabel("Book Store Management System");
	l2.setBounds(300,420,1200,90);
	l2.setFont(font1);
	l2.setForeground(Color.WHITE);

	JPanel home=new JPanel();
	JLabel l1=new JLabel(i1);
	home.add(l1);
	l1.add(b1);
	l1.add(b2);
	l1.add(b3);
	l1.add(l2);

	JTabbedPane tp=new JTabbedPane();
	tp.addTab("HOME",home);
	tp.setFont(font3);
	
	tp.setBounds(50,100,100,400);
	add(tp);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	setSize(1500,1000);
	setVisible(true);

	}
	public void actionPerformed(ActionEvent e)
	{
	if(e.getSource()==b1)
	{
		new Admin().setVisible(true);
		this.setVisible(false);
	}
	if(e.getSource()==b2)
	{
	new SRegister().setVisible(true);
	this.setVisible(false);
	}
	
	if(e.getSource()==b3)
	{
		new Login().setVisible(true);
		this.setVisible(false);
	}
	}
	public static void main(String args[])
	{
	ProjectBookStore f1=new ProjectBookStore();
	
	}
}
