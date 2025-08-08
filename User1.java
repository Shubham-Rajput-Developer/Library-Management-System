import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class User1 extends JFrame
{
	JTextField t1, t2, t3, t4, t5, t6, t7;
	JLabel l1, l2, l3,error, l9;
	JButton b1;
	JTable jt;
	JScrollPane sp;
	JFrame frame;
	User1()
	{
		frame = this;
		ImageIcon i1 = new ImageIcon("User1Book.jpg");
		Image image = i1.getImage();
		Image newimg = image.getScaledInstance(1500, 1500, java.awt.Image.SCALE_SMOOTH);
		i1 = new ImageIcon(newimg);
		l9 = new JLabel(i1);
		getContentPane().add(l9);

		Font f1 = new Font("Arial", Font.BOLD, 50);
		Font f2 = new Font("Arial", Font.BOLD, 30);

		l1 = new JLabel("*****BOOK STORE MANAGEMENT SYSTEM****");
		l2 = new JLabel("Search Here:");
		l3 = new JLabel("Book_ID:");

		t1 = new JTextField(20);//search

		//searching Books in textField
		t1.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke){
				String value=t1.getText();
				if(value.equals("")){
					ShowTable();
				}
				else{
					jt=null;
					sp=null;
		String col[] = { "Book_ID", "Book_Name", "Book_Author", "Book_Category", "Book_Language", "Book_Publisher","Book_Price" };
		String data[][] = new String[100][7];

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","Shubham@500");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT  * from ADMIN_BOOKS where BOOKS_NAME	 LIKE '%"+value+"%'");
			int j = 0;
			while (rs.next()) {
				for (int i = 0; i < 7; i++) {
					int k = i + 1;
					data[j][i] = rs.getString(k);
				}
				j++;
			}
			jt = new JTable(data, col);
			sp = new JScrollPane(jt);
			jt.setEnabled(true);
			sp.setBounds(600, 200, 700, 500);
			l9.add(sp);
		}
		catch (Exception e) 
		{
				System.out.println(e);
		}
				}
			}
		});


		t2 = new JTextField(20);//Book Id

		b1 = new JButton("Buy");

		l1.setFont(f1);
		l2.setFont(f2);
		l3.setFont(f2);

		t1.setFont(f2);
		t2.setFont(f2);
		b1.setFont(f2);

		l1.setBounds(100, 50, 1200, 80);
		l2.setBounds(50, 180, 200, 40);
		l3.setBounds(50, 300, 250, 40);
	

		t1.setBounds(350, 180, 150, 40);
		t2.setBounds(350, 300, 150, 40);
		b1.setBounds(350, 600, 100, 50);

		l1.setForeground(Color.white);
		l2.setForeground(Color.white);
		l3.setForeground(Color.white);

		//b1.addActionListener(this);
		l9.add(l1);
		l9.add(l2);
		l9.add(l3);
	
		l9.add(t1);
		l9.add(t2);
		l9.add(b1);
		jt = new JTable();
		jt.setEnabled(true);
		
		
		ShowTable();
		setSize(1500,1000);
		setVisible(true);
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setBounds(350,330,150,30);
		l9.add(error);
		
		
		
		t2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {

				String value = t2.getText();
				int l = value.length();

				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_UP
						|| ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_LEFT) {
					t2.setEditable(true);
					error.setText("");
				} else {
					t2.setEditable(false);
					error.setText("Enter only numeric digits");
				}
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","Shubham@500");
					Statement st = conn.createStatement();
					int id=Integer.parseInt(t2.getText());
					ResultSet rs = st.executeQuery("SELECT  count(*) as total from ADMIN_BOOKS where ID='"+id+"'");
					rs.next();
					if(rs.getInt("total")>=1){
						new Buy_Book(id);
					}
					else{
						JOptionPane.showMessageDialog(frame, "Book Not Found");
					}
				}
				catch (Exception e) 
				{
						
				}
			}
		});
		
		
		
		
	}

		public void ShowTable() 
		{
			jt=null;
			sp=null;
		String col[] = { "Book_ID", "Book_Name", "Book_Author", "Book_Category", "Book_Language", "Book_Publisher","Book_Price" };
		String data[][] = new String[100][7];

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","Shubham@500");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT  * from ADMIN_BOOKS");
			int j = 0;
			while (rs.next()) {
				for (int i = 0; i < 7; i++) {
					int k = i + 1;
					data[j][i] = rs.getString(k);
				}
				j++;
			}
			jt = new JTable(data, col);
			jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jt.setRowSelectionAllowed(false);
			sp = new JScrollPane(jt);
			jt.setEnabled(false);
			sp.setBounds(600, 200, 700, 500);
			l9.add(sp);
		}
		catch (Exception e) 
		{
				
		}
	}
	public static void main(String args[]) {
		User1 f = new User1();
		
	}
}
