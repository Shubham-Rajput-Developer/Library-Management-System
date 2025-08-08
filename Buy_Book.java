import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Buy_Book extends JFrame implements ActionListener
{
	private final JPanel contentPanel = new JPanel();
	public JTextField textField[];
	public JFrame frame;
	String user_name;
	public Buy_Book(int id,String user_name) 
	{
		frame=this;
		this.user_name=user_name;
		setSize(609,553);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Buy Book");
		getContentPane().setLayout(null);
		setBounds(100, 100, 609, 553);
		getContentPane().setLayout(null);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel lblNewLabel = new JLabel("Book_Id");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(49, 101, 98, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblBookname = new JLabel("Book_Name");
		lblBookname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookname.setBounds(49, 144, 118, 18);
		getContentPane().add(lblBookname);
		
		JLabel lblBookname_1 = new JLabel("Book_Author");
		lblBookname_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookname_1.setBounds(49, 186, 118, 18);
		getContentPane().add(lblBookname_1);
		
		JLabel lblBook = new JLabel("Book_Category");
		lblBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBook.setBounds(49, 229, 132, 18);
		getContentPane().add(lblBook);
		
		JLabel lblBook_1 = new JLabel("Book_Language");
		lblBook_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBook_1.setBounds(49, 267, 132, 18);
		getContentPane().add(lblBook_1);
		
		JLabel lblBook_2 = new JLabel("Book_Publiser");
		lblBook_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBook_2.setBounds(49, 306, 132, 18);
		getContentPane().add(lblBook_2);
		
		JLabel lblBookprice = new JLabel("Book_Price");
		lblBookprice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookprice.setBounds(49, 344, 118, 18);
		getContentPane().add(lblBookprice);
		
		JButton Buy = new JButton("Buy");
		Buy.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Buy.setBounds(229, 426, 85, 21);
		getContentPane().add(Buy);
		Buy.addActionListener(this);
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT  * from ADMIN_BOOKS where ID='"+id+"'");
		rs.next();
		textField = new JTextField[7];
		int y=40;
		for(int i=0;i<textField.length;i++) {
			int k=i+1;
			textField[i]=new JTextField("");
			textField[i].setBounds(240,60+y,185,20);
			textField[i].setText(rs.getString(k));
			y+=40;
			getContentPane().add(textField[i]);
			
		}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
			Statement st = conn.createStatement();
			int book_id=Integer.parseInt(textField[0].getText());
			String book_name=textField[1].getText();
			int book_price=Integer.parseInt(textField[6].getText());
			int count=1;
			ResultSet rs = st.executeQuery("SELECT  count(*) as total from BUY_HISTORY where USER_NAME='"+user_name+"' AND BOOK_ID='"+book_id+"'");
			rs.next();
			System.out.println(rs.getInt("total"));
			if(rs.getInt("total")>=1) 
			{
				ResultSet rs1 = st.executeQuery("SELECT  count as total from BUY_HISTORY where  USER_NAME='"+user_name+"' AND BOOK_ID='"+book_id+"'");
				rs1.next();
				count=rs1.getInt("total")+1;
				st.executeQuery("UPDATE BUY_HISTORY SET COUNT='"+count+"' where  USER_NAME='"+user_name+"' AND BOOK_ID='"+book_id+"'");
				System.out.println("if");
			}
			else 
			{
				st.executeQuery("INSERT INTO BUY_HISTORY VALUES ('"+user_name+"','"+book_id+"','"+book_name+"','"+book_price+"','"+count+"')");
				System.out.println("else");
			}
			JOptionPane.showMessageDialog(frame, "You Buyed Book Successfully");
			frame.dispose();
			}
			catch(Exception ex) 
			{
				System.out.println(ex);
			}
		
	}
}
