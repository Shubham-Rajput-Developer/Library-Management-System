import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Adminpage extends JFrame implements ActionListener 
{
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
	JTextField t1, t2, t3, t4, t5, t6, t7;
	JButton b1, b2,b3;
	JTable jt;
	JScrollPane sp;

	Adminpage()
	{
		ImageIcon i1 = new ImageIcon("admin.jpg");
		Image image = i1.getImage();
		Image newimg = image.getScaledInstance(1500, 1500, java.awt.Image.SCALE_SMOOTH);
		i1 = new ImageIcon(newimg);
		l9 = new JLabel(i1);
		add(l9);

		Font f1 = new Font("Arial", Font.BOLD, 80);
		Font f2 = new Font("Arial", Font.BOLD, 20);

		l1 = new JLabel("**Books Information**");
		l3 = new JLabel("Book_Name:");
		l4 = new JLabel("Book_Author:");
		l5 = new JLabel("Book_Category:");
		l6 = new JLabel("Book_Language:");
		l7 = new JLabel("Book_Publisher:");
		l8 = new JLabel("Book_Price:");

		t2 = new JTextField(20);
		t3 = new JTextField(20);
		t4 = new JTextField(20);
		t5 = new JTextField(20);
		t6 = new JTextField(20);
		t7 = new JTextField(20);
		b1 = new JButton("Add");
		b2 = new JButton("Delete");
		b3 = new JButton("Exit");

		l1.setFont(f1);
		l3.setFont(f2);
		l4.setFont(f2);
		l5.setFont(f2);
		l6.setFont(f2);
		l7.setFont(f2);
		l8.setFont(f2);
		t2.setFont(f2);
		t3.setFont(f2);
		t4.setFont(f2);
		t5.setFont(f2);
		t6.setFont(f2);
		t7.setFont(f2);
		b1.setFont(f2);
		b2.setFont(f2);

		l1.setBounds(100, 50, 1000, 80);
		l3.setBounds(50, 300, 250, 40);
		l4.setBounds(50, 350, 250, 40);
		l5.setBounds(50, 400, 250, 40);
		l6.setBounds(50, 450, 250, 40);
		l7.setBounds(50, 500, 250, 40);
		l8.setBounds(50, 550, 250, 40);
		t2.setBounds(350, 300, 150, 40);
		t3.setBounds(350, 350, 150, 40);
		t4.setBounds(350, 400, 150, 40);
		t5.setBounds(350, 450, 150, 40);
		t6.setBounds(350, 500, 150, 40);
		t7.setBounds(350, 550, 150, 40);

		l1.setForeground(Color.red);
		l3.setForeground(Color.white);
		l4.setForeground(Color.white);
		l5.setForeground(Color.white);
		l6.setForeground(Color.white);
		l7.setForeground(Color.white);
		l8.setForeground(Color.white);

		b1.setBounds(200, 600, 150, 40);
		b2.setBounds(360, 600, 150, 40);
		b3.setBounds(50, 700, 150, 40);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		ShowTable();
		l9.add(l1);
		l9.add(l3);
		l9.add(l4);
		l9.add(l5);
		l9.add(l6);
		l9.add(l7);
		l9.add(l8);
		l9.add(t2);
		l9.add(t3);
		l9.add(t4);
		l9.add(t5);
		l9.add(t6);
		l9.add(t7);
		l9.add(b1);
		l9.add(b2);
		l9.add(b3);

		// setLayout(null);
		setSize(1400, 1000);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1)
		{
			String s2 = t2.getText();
			String s3 = t3.getText();
			String s4 = t4.getText();
			String s5 = t5.getText();
			String s6 = t6.getText();
			String s7 = t7.getText();
			if ((s2.equals("")) || (s3.equals("")) || (s4.equals("")) || (s5.equals("")) || (s6.equals(""))|| (s7.equals("")))
			{
				JOptionPane.showMessageDialog(this, "Enter all Data");
			}
			else 
			{
				try 
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT count(*) AS total from ADMIN_BOOKS");
					rs.next();
					int id = rs.getInt("total");
					id++;
					st.executeQuery("INSERT INTO ADMIN_BOOKS VALUES ('" + id + "','" + s2 + "','" + s3 + "','" + s4+ "','" + s5 + "','" + s6 + "','" + s7 + "')");
				} 
				catch (Exception ex) 
				{
					System.out.println(ex);
				}
				JOptionPane.showMessageDialog(this, "Book Added Successfully");
                dispose();
                new Adminpage().setVisible(true);
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");
				t7.setText("");
              ShowTable();
			}
		}
		if (e.getSource() == b2) 
		{
			if (jt.getSelectedRow() == -1) 
			{
				JOptionPane.showMessageDialog(this, "plz Select Row");
			}
			else 
			{
			   	    Object R = jt.getModel().getValueAt(jt.getSelectedRow(),0);
					String S = R.toString();
					int index = Integer.parseInt(S);
					try 
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
					Statement st = conn.createStatement();
					st.executeQuery("Delete from ADMIN_BOOKS where id=" + index);
					JOptionPane.showMessageDialog(this, "Book Delete Successfully");
					ShowTable();
					dispose();
					new Adminpage().setVisible(true);
				} 
				catch(Exception ex)
				{
					System.out.println(ex);
				}

			}
		}
		if(e.getSource()==b3)
			{
				dispose();
				new ProjectBookStore().setVisible(true);
			}
	}
	public void ShowTable() 
	{
		String col[] = { "Book_ID", "Book_Name", "Book_Author", "Book_Category", "Book_Language", "Book_Publisher","Book_Price" };
		String data[][] = new String[100][7];
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT  * from ADMIN_BOOKS");
			int j = 0;
			while (rs.next()) 
			{
				for (int i = 0; i < 7; i++) 
				{
					int k = i + 1;
					data[j][i] = rs.getString(k);
				}
				j++;
			}
			jt = new JTable(data, col);
			sp = new JScrollPane(jt);
			sp.setBounds(600, 200, 700, 500);
			l9.add(sp);
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	public static void main(String args[]) 
	{
		Adminpage f = new Adminpage();
	}
}