package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class autentification extends JFrame {

	public static user user; 
	private JLabel lblExit=new JLabel("X");
	private JLabel lblIcon=new JLabel("lbl");
	private JTextField username=new JTextField();
	private JLabel lblUsername=new JLabel();
	private JLabel lblPassword=new JLabel();
	private JPasswordField password=new JPasswordField();
	private JButton btnConnect=new JButton("Connexion");
	private JLabel iconAffich=new JLabel();
	private JLabel lblAffich=new JLabel();

	int x,y;
	public autentification() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension sc=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(((int)sc.getWidth()/12*3),((int) sc.getHeight()/6*3));
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setUndecorated(true);

		x=this.getWidth()/6;
		y=this.getHeight()/14;

		lblExit.setBounds(this.getWidth()-30,10,25,25);
		lblExit.setFont(new Font("Dialog", Font.BOLD, 24));
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(JOptionPane.showConfirmDialog(null, "are you sure you want to close this application?","confirmation",JOptionPane.YES_NO_OPTION)==0) {
					System.exit(0);
				}	
			}
			@Override
			public void mouseEntered(MouseEvent e) {lblExit.setForeground(Color.red);}

			@Override
			public void mouseExited(MouseEvent e) {lblExit.setForeground(Color.black);}
		}); 

		lblIcon.setBounds(this.getWidth()/7*2,y,this.getWidth()/7*3,y*5);
		ImageIcon logoImage=new ImageIcon(autentification.class.getResource("/images/logo6.png"));
		Image image=logoImage.getImage();
		Image tmp_Image=image.getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_SMOOTH);
		logoImage=new ImageIcon(tmp_Image);
		lblIcon.setIcon(logoImage);

		lblUsername.setBounds(x,lblIcon.getY()+lblIcon.getHeight()+y/2,x/2,y);
		logoImage=new ImageIcon(autentification.class.getResource("/images/user3.png"));
		image=logoImage.getImage();
		tmp_Image=image.getScaledInstance(lblUsername.getWidth(), lblUsername.getHeight(), Image.SCALE_SMOOTH);
		logoImage=new ImageIcon(tmp_Image);
		lblUsername.setIcon(logoImage);
		username.setBounds(lblUsername.getX()+lblUsername.getWidth(),lblUsername.getY(),x*3+x/5*3,y);

		lblPassword.setBounds(lblUsername.getX(),lblUsername.getY()+lblUsername.getHeight()+y/2,lblUsername.getWidth(),lblUsername.getHeight());
		logoImage=new ImageIcon(autentification.class.getResource("/images/password3.png"));
		image=logoImage.getImage();
		tmp_Image=image.getScaledInstance(lblPassword.getWidth(), lblPassword.getHeight(), Image.SCALE_SMOOTH);
		logoImage=new ImageIcon(tmp_Image);
		lblPassword.setIcon(logoImage);
		password.setBounds(username.getX(),lblPassword.getY(),username.getWidth(),username.getHeight());
		btnConnect.setForeground(Color.WHITE);
		btnConnect.setBackground(new Color(51, 204, 0));

		btnConnect.setBounds(x,lblPassword.getY()+lblPassword.getHeight()+y/3*2,lblPassword.getWidth()+password.getWidth(),y);

		iconAffich.setBounds(lblPassword.getX(),btnConnect.getY()+btnConnect.getHeight()+y/2,lblPassword.getWidth(),lblPassword.getHeight());
		logoImage=new ImageIcon(autentification.class.getResource("/images/showPassword2.png"));
		image=logoImage.getImage();
		tmp_Image=image.getScaledInstance(iconAffich.getWidth(), iconAffich.getHeight(), Image.SCALE_SMOOTH);
		logoImage=new ImageIcon(tmp_Image);
		iconAffich.setIcon(logoImage);
		lblAffich.setBounds(84,300,155,27);
		lblAffich.setText("afficher password");
		char echoChar=password.getEchoChar();
		lblAffich.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {	

				if(lblAffich.getText().equals("afficher password")) {
					ImageIcon logoImage=new ImageIcon(autentification.class.getResource("/images/showPassword1.png"));
					Image image=logoImage.getImage();
					Image tmp_Image=image.getScaledInstance(iconAffich.getWidth(), iconAffich.getHeight(), Image.SCALE_SMOOTH);
					logoImage=new ImageIcon(tmp_Image);
					iconAffich.setIcon(logoImage);
					lblAffich.setText("cacher password");
					password.setEchoChar((char)0);
				}
				else if(lblAffich.getText().equals("cacher password")) {
					ImageIcon logoImage=new ImageIcon(autentification.class.getResource("/images/showPassword2.png"));
					Image image=logoImage.getImage();
					Image tmp_Image=image.getScaledInstance(iconAffich.getWidth(), iconAffich.getHeight(), Image.SCALE_SMOOTH);
					logoImage=new ImageIcon(tmp_Image);
					iconAffich.setIcon(logoImage);
					lblAffich.setText("afficher password");
					password.setEchoChar(echoChar);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {lblAffich.setForeground(new Color(51, 204, 0));}

			@Override
			public void mouseExited(MouseEvent e) {lblAffich.setForeground(Color.black);}

		});

		btnConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				connet();
			}
		});
		getContentPane().add(lblExit);
		getContentPane().add(lblIcon);
		getContentPane().add(lblUsername);
		getContentPane().add(username);
		getContentPane().add(lblPassword);
		getContentPane().add(password);
		getContentPane().add(btnConnect);
		getContentPane().add(iconAffich);
		getContentPane().add(lblAffich);	

	}

	public void connet(){
		try {
			String usrname=username.getText();
			String pass=password.getText();
			if(usrname.equals("")||pass.equals(""))
			{
				JOptionPane.showMessageDialog(null,"champ vide !!!\n verifier username et password");
			}
			else
			{
				String qr="select * from user where username='"+usrname+"' and password='"+pass+"'";
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
				Statement st=con.createStatement();
				ResultSet rs= st.executeQuery(qr);
				if(rs.next()){
					String state=rs.getString("state");
					if(state.equals("B"))
					{
						JOptionPane.showMessageDialog(null,"vous aver blocker pour le mement !!!\n pour plus d'information contacte leur responsable"); 
					}
					else{
						String type=rs.getString("type");
						String id=rs.getString("id");
						String cin=rs.getString("cin");
						String nom=rs.getString("nom");
						String prenom=rs.getString("prenom");
						String tel=rs.getString("tel");
						String addresse=rs.getString("addresse");
						user=new user(id,cin,nom,prenom,usrname,tel,addresse,type);
						principale p=new principale();
						p.setVisible(true);
						this.dispose();
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"donner incorrect");
				}
				con.close();
			}

		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");}             
	}


	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					autentification frame = new autentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
