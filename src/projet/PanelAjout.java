package projet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextMeasurer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class PanelAjout extends JPanel {

	/**
	 * Create the panel.
	 */


	JPanel panelEntet=new JPanel();
	Color red=new Color(255, 0, 0);
	Color green= new Color(0, 128, 0);



	JLabel lblCin=new JLabel("CIN : ");
	JLabel lblNom=new JLabel("NOM : ");
	JLabel lblPrenm=new JLabel("Prenom : ");
	JLabel lblEmail=new JLabel("Email : ");
	JLabel lblTel=new JLabel("numéro du Tél : ");
	JLabel lblDN=new JLabel("Date de N° : ");
	JLabel lblDate=new JLabel("Date RDV :");
	JLabel lblH=new JLabel("H :");
	JLabel lblM=new JLabel("m :");

	JLabel label1 = new JLabel("*");
	JLabel label2 = new JLabel("*");
	JLabel label3 = new JLabel("*");
	JLabel label4 = new JLabel("*");
	JLabel label5 = new JLabel("*");
	JLabel label6 = new JLabel("*");
	JLabel label7 = new JLabel("*");
	JLabel label8 = new JLabel("*");

	JCheckBox typePatient=new JCheckBox("nouveau Patient");

	JTextField textCin=new JTextField();
	JTextField textNom=new JTextField();
	JTextField textPrenom=new JTextField();
	JTextField textEmail=new JTextField();
	JDateChooser textDN=new JDateChooser();
	JTextField textTel=new JTextField();
	JDateChooser textDate=new JDateChooser();

	JComboBox<String> heur;
	JComboBox<String> minute;

	JButton btnAjou=new JButton("ajouter ");
	JButton btnVider=new JButton("vider");
	JLabel lblMsg=new JLabel();


	private int x,y,width=1004,height=703;
	int indexX=width/10;
	int indexY=height/14;

	public PanelAjout(int x,int y,int w,int h) {
		setBackground(Color.white);
		setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		setBounds(x, y, 1004, 703);
		setLayout(null);

		String modelHeur1[]= {"08","09","10","11","13","14","15","16"};
		heur=new JComboBox<String>(modelHeur1);
		String[] modelMinute=new String[60];

		for(int i=0;i<60;i++) {
			if(i<10) {modelMinute[i]="0"+String.valueOf(i);}
			else{modelMinute[i]=String.valueOf(i);}}
		minute=new JComboBox<String>(modelMinute);
		minute.setSelectedIndex(0);
		heur.setSelectedIndex(0);

		textDate.setDateFormatString("yyyy-MM-dd");
		textDN.setDateFormatString("yyyy-MM-dd");

		LocalDate dt=LocalDate.now();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String dte=df.format(dt);
		Date dtN=new Date(dte);
		Date dtRDV=new Date(dte);
		dtN.setYear(dtN.getYear()-6);
		Date d=new Date();
		textDate.setDate(dtRDV);
		textDN.setDate(dtN);

		entet();
		setFontElement();
		setBoundsElement();
		addElement();


		btnAjou.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					if(!checkVide()) {
						lblMsg.setText("");
						String cin=textCin.getText();
						if(typePatient.isSelected()) {
							String nom=textNom.getText();
							String prenom=textPrenom.getText();
							String tel=textTel.getText();
							String mail=textEmail.getText();
							String dateN=new SimpleDateFormat("yyyy-MM-dd").format(textDN.getDate());
							if(ajoutClient(cin,nom,prenom,tel,mail,dateN)) {
								String dateRDV=new SimpleDateFormat("yyyy-MM-dd").format(textDate.getDate());
								String h=heur.getSelectedItem().toString()+":"+minute.getSelectedItem().toString();
								if(ajoutRDV(cin,dateRDV,h))
								{
									String msg="votre rendez vous à effecter le "+dateRDV+" à "+h;
									sendEmail.sendMail(mail, "rendez vous",msg );
									lblMsg.setForeground(new Color(0, 128, 0));
									lblMsg.setText("ajout patient "+nom+" "+prenom+" RDV à "+dateRDV+" "+h);
									textCin.setText("");
									textNom.setText("");
									textPrenom.setText("");
									textTel.setText("");
									textEmail.setText("");
									textDate.setDate(dtRDV);
									textDN.setDate(dtN);
									heur.setSelectedIndex(0);
									minute.setSelectedIndex(0);
								}
							}
							else {typePatient.setSelected(false);}
						}
						else 
						{
							String dateRDV=new SimpleDateFormat("yyyy-MM-dd").format(textDate.getDate());
							String h=heur.getSelectedItem().toString()+":"+minute.getSelectedItem().toString();
							if(ajoutRDV(cin,dateRDV,h))
							{
								String msg="votre rendez vous à effecter le "+dateRDV+" à "+h;
								sendEmail.sendMail(getEmail(cin), "rendez vous",msg );
								lblMsg.setForeground(new Color(0, 128, 0));
								lblMsg.setText("patient de CIN : "+cin+" leur RDV à"+dateRDV+" "+h);
								textCin.setText("");
								textNom.setText("");
								textPrenom.setText("");
								textTel.setText("");
								textEmail.setText("");
								textDate.setDate(dtRDV);
								textDN.setDate(dtN);
								heur.setSelectedIndex(0);
								minute.setSelectedIndex(0);
							}
						}	
							
					}
				}
				catch (Exception e1) {
					lblMsg.setForeground(new Color(255, 0, 0));
					lblMsg.setText("erreur !!!" );
				}

			}
		});
		btnVider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblMsg.setText("");
				textCin.setText("");
				textNom.setText("");
				textPrenom.setText("");
				textTel.setText("");
				textEmail.setText("");
				textDate.setDate(dtRDV);
				textDN.setDate(dtN);
				heur.setSelectedIndex(0);
				minute.setSelectedIndex(0);

			}
		});
	}

	public void entet() {
		panelEntet.setBackground(Color.WHITE);
		panelEntet.setBounds(12, 12, width-24, indexY*2);
		add(panelEntet);
		panelEntet.setLayout(null);
		JPanel panelTextEntet=new JPanel();
		panelTextEntet.setBackground(Color.WHITE);
		panelTextEntet.setBounds(1, 1, panelEntet.getWidth()/2, panelEntet.getHeight()-2);
		panelEntet.add(panelTextEntet);
		panelTextEntet.setLayout(null);

		JLabel iconEntet=new JLabel();
		iconEntet.setBounds(24, 3, panelTextEntet.getWidth()/4, panelTextEntet.getHeight());
		ImageIcon logoImage=new ImageIcon(principale.class.getResource("/images/ajout1.jpg"));
		Image image=logoImage.getImage();
		Image tmp_Image=image.getScaledInstance(iconEntet.getWidth(), iconEntet.getHeight()-24, Image.SCALE_SMOOTH);
		logoImage=new ImageIcon(tmp_Image);
		iconEntet.setIcon(logoImage);

		panelTextEntet.add(iconEntet);

		JLabel textEntet=new JLabel("Ajouter Des RDVs");

		textEntet.setFont(new Font("Dialog", Font.BOLD, 26));
		textEntet.setBounds(iconEntet.getX()+iconEntet.getWidth()+panelTextEntet.getWidth()/16, 0, panelTextEntet.getWidth()/4*3-panelTextEntet.getWidth()/16, panelTextEntet.getHeight());
		panelTextEntet.add(textEntet);

	}

	public void setBoundsElement() {

		lblCin.setBounds(indexX*1,panelEntet.getY()+panelEntet.getHeight()+ indexY, indexX*2, indexY);
		label1.setBounds(lblCin.getX()-10, lblCin.getY()+10, 18, 15);
		textCin.setBounds(lblCin.getX()+lblCin.getWidth()+indexX-15, lblCin.getY(), indexX*2, indexY/10*8);
		typePatient.setBounds(textCin.getX()+textCin.getWidth()+5, textCin.getY(),indexX*3 , textCin.getHeight());
		lblNom.setBounds(lblCin.getX(), lblCin.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());
		lblPrenm.setBounds(lblNom.getX(), lblNom.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());
		lblEmail.setBounds(lblNom.getX(), lblPrenm.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());
		lblTel.setBounds(lblNom.getX(), lblEmail.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());
		lblDN.setBounds(lblNom.getX(), lblTel.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());		

		label2.setBounds(lblCin.getX()-10, lblNom.getY()+10, 18, 15);
		label3.setBounds(lblCin.getX()-10, lblPrenm.getY()+10, 18, 15);
		label4.setBounds(lblCin.getX()-10, lblEmail.getY()+10, 18, 15);
		label5.setBounds(lblCin.getX()-10, lblTel.getY()+10, 18, 15);
		label6.setBounds(lblCin.getX()-10, lblDN.getY()+10, 18, 15);
		textNom.setBounds(textCin.getX(), lblNom.getY(), indexX*2, indexY/10*8);
		textPrenom.setBounds(textNom.getX(), lblPrenm.getY(), indexX*2, indexY/10*8);
		textEmail.setBounds(textPrenom.getX(), lblEmail.getY(), indexX*2, indexY/10*8);
		textTel.setBounds(textEmail.getX(), lblTel.getY(), indexX*2, indexY/10*8);
		textDN.setBounds(textTel.getX(), lblDN.getY(), indexX*2, indexY/10*8);

		lblDate.setBounds(indexX, lblCin.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());
		textDate.setBounds(lblCin.getX()+lblCin.getWidth()+indexX-15, lblDate.getY(), indexX*2, indexY/10*8);
		lblH.setBounds(typePatient.getX()+20, textDate.getY(), 50, lblDate.getHeight());
		label8.setBounds(lblH.getX()-10, lblH.getY()+10, 18, 15);
		heur.setBounds(lblH.getX()+lblH.getWidth(), textDate.getY(), textDate.getWidth()/2, textDate.getHeight());
		lblM.setBounds(heur.getX()+heur.getWidth()+10, lblH.getY(), 50, lblDate.getHeight());
		minute.setBounds(lblM.getX()+lblM.getWidth(), heur.getY(), heur.getWidth(), heur.getHeight());
		label7.setBounds(lblCin.getX()-10, lblDate.getY()+10, 18, 15);

		btnAjou.setBounds(getWidth()-indexX-(getWidth()/20*3)+10, getHeight()-(indexY*2)+10, getWidth()/20*3, getHeight()/20);
		btnVider.setBounds(btnAjou.getX()-btnAjou.getWidth()-20,btnAjou.getY(),getWidth()/20*2,btnAjou.getHeight());
		lblMsg.setBounds(lblCin.getX(),btnVider.getY(),btnVider.getX()-btnVider.getWidth()-20,btnVider.getHeight());

	}
	public void setFontElement() {

		Font fontlbl=new Font("Dialog", Font.BOLD, 20);

		lblCin.setFont(fontlbl);
		label1.setForeground(Color.RED);
		label1.setFont(new Font("Dialog", Font.BOLD, 16));
		typePatient.setFont(new Font("Dialog", Font.BOLD, 15));
		typePatient.setBackground(Color.white);
		lblNom.setFont(fontlbl);
		lblPrenm.setFont(fontlbl);
		lblEmail.setFont(fontlbl);
		lblTel.setFont(fontlbl);
		lblDN.setFont(fontlbl);

		label2.setForeground(Color.RED);
		label2.setFont(new Font("Dialog", Font.BOLD, 16));
		label3.setForeground(Color.RED);
		label3.setFont(new Font("Dialog", Font.BOLD, 16));
		label5.setForeground(Color.RED);
		label4.setForeground(Color.RED);
		label4.setFont(new Font("Dialog", Font.BOLD, 16));
		label5.setFont(new Font("Dialog", Font.BOLD, 16));
		label6.setForeground(Color.RED);
		label6.setFont(new Font("Dialog", Font.BOLD, 16));

		lblDate.setFont(fontlbl);
		label7.setForeground(Color.RED);
		label7.setFont(new Font("Dialog", Font.BOLD, 16));
		lblH.setFont(fontlbl);
		label8.setForeground(Color.RED);
		label8.setFont(new Font("Dialog", Font.BOLD, 16));
		lblM.setFont(fontlbl);

		btnAjou.setBackground(new Color(174,34,34));
		btnAjou.setForeground(Color.white);
		btnVider.setBackground(new Color(174,34,34));
		btnVider.setForeground(Color.white);
		lblMsg.setForeground(new Color(0, 0, 0));// green new Color(0, 128, 0) red new Color(255, 0, 0)
		lblMsg.setFont(new Font("Dialog", Font.PLAIN, 12));
	}
	public void addElement() {
		add(lblCin);
		add(label1);
		add(textCin);
		add(typePatient);
		typePatient.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if(typePatient.isSelected()) {



					lblDate.setBounds(lblDN.getX(), lblDN.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());
					label7.setBounds(lblCin.getX()-10, lblDate.getY()+10, 18, 15);
					textDate.setBounds(lblCin.getX()+lblCin.getWidth()+indexX-15, lblDate.getY(), indexX*2, indexY/10*8);
					lblH.setBounds(typePatient.getX()+20, textDate.getY(), 50, lblDate.getHeight());
					label8.setBounds(lblH.getX()-10, lblH.getY()+10, 18, 15);
					heur.setBounds(lblH.getX()+lblH.getWidth(), textDate.getY(), textDate.getWidth()/2, textDate.getHeight());
					lblM.setBounds(heur.getX()+heur.getWidth()+10, lblH.getY(), 50, lblDate.getHeight());
					minute.setBounds(lblM.getX()+lblM.getWidth(), heur.getY(), heur.getWidth(), heur.getHeight());

					add(lblNom);
					add(lblPrenm);
					add(lblEmail);
					add(lblTel);
					add(lblDN);
					add(label2);
					add(label3);
					add(label4);
					add(label5);
					add(label6);
					add(textNom);
					add(textPrenom);
					add(textEmail);
					add(textTel);
					add(textDN);

					lblNom.setVisible(true);
					textNom.setVisible(true);
					lblPrenm.setVisible(true);
					textPrenom.setVisible(true);
					lblEmail.setVisible(true);
					textEmail.setVisible(true);
					lblTel.setVisible(true);
					textTel.setVisible(true);
					lblDN.setVisible(true);
					textDN.setVisible(true);

					label2.setVisible(true);
					label3.setVisible(true);
					label4.setVisible(true);
					label5.setVisible(true);
					label6.setVisible(true);
				}
				else
				{
					lblDate.setBounds(lblCin.getX(), lblCin.getY()+lblCin.getHeight()+12, lblCin.getWidth(), lblCin.getHeight());
					textDate.setBounds(lblCin.getX()+lblCin.getWidth()+indexX-15, lblDate.getY(), indexX*2, indexY/10*8);
					lblH.setBounds(typePatient.getX()+20, textDate.getY(), 50, lblDate.getHeight());
					label8.setBounds(lblH.getX()-10, lblH.getY()+10, 18, 15);
					heur.setBounds(lblH.getX()+lblH.getWidth(), textDate.getY(), textDate.getWidth()/2, textDate.getHeight());
					lblM.setBounds(heur.getX()+heur.getWidth()+10, lblH.getY(), 50, lblDate.getHeight());
					minute.setBounds(lblM.getX()+lblM.getWidth(), heur.getY(), heur.getWidth(), heur.getHeight());
					label7.setBounds(lblCin.getX()-10, lblDate.getY()+10, 18, 15);

					lblNom.setVisible(false);
					textNom.setVisible(false);
					lblPrenm.setVisible(false);
					textPrenom.setVisible(false);
					lblEmail.setVisible(false);
					textEmail.setVisible(false);
					lblTel.setVisible(false);
					textTel.setVisible(false);
					lblDN.setVisible(false);
					textDN.setVisible(false);

					label2.setVisible(false);
					label3.setVisible(false);
					label4.setVisible(false);
					label5.setVisible(false);
					label6.setVisible(false);

				}

			}
		});

		add(lblDate);
		add(label7);
		add(textDate);
		add(lblH);
		add(label8);
		add(heur);
		add(lblM);
		add(minute);

		add(btnAjou);
		add(btnVider);
		add(lblMsg);
	}

	public boolean ajoutRDV(String...strings ) {
		try {
			int id_client=checkClient(strings[0]);
			if(id_client>0) {
				if(!checkRDVt(strings[1], strings[2])) {
					String qr="INSERT INTO `rdv`(`id_client`, `date_rdv`, `horaire`) VALUES(?,?,?)";
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
					java.sql.PreparedStatement ps=con.prepareStatement(qr);
					ps.setInt(1, id_client);
					ps.setString(2,strings[1] );
					ps.setString(3,strings[2] );
					ps.executeUpdate();
					
					return true;
				}
				else
				{JOptionPane.showMessageDialog(null,"cette date à été occupée chosir un autre ");
				return false; 
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"il n'existe pas un patient avec cette nim Cin \n clicke sur nouveau patient pour ajouter le/la");
				return false;
			}
		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");return false;}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");return false;}


	}
	public boolean ajoutClient(String...strings ) {
		try {
			int id=checkClient(strings[0]);
			if(id==0)
			{
				String qr="INSERT INTO `client`( `cin`, `nom`, `prenom`, `tel`, `email`, `dateN`) VALUES(?,?,?,?,?,?)";
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
				java.sql.PreparedStatement ps=con.prepareStatement(qr);
				ps.setString(1,strings[0] );
				ps.setString(2,strings[1] );
				ps.setString(3,strings[2] );
				ps.setString(4,strings[3] );
				ps.setString(5,strings[4] );
				ps.setString(6,strings[5] );
				ps.executeUpdate();
				return true;
			}
			else if(id>0)
			{
				JOptionPane.showMessageDialog(null,"cet Patient est déja eregestrer dans la base ");	
				return false;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");
				return false;
			}
		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");return false;}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");return false;}   

	}
	public int checkClient(String cin){
		try {
			String qr="select id_client from client  where cin ='"+cin+"'";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
			Statement st= (Statement) con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			if(rs.next()) {

				return rs.getInt(1);}
			else {
				con.close();
				return 0;}
		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");return -1;}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");return -1;}
	}

	public boolean checkRDVt(String date,String h){
		try {
			String qr="select id_rdv from rdv  where date_rdv='"+date+"' and horaire='"+h+"'";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
			Statement st= (Statement) con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			if(rs.next()) {
				con.close();
				return true;}
			else {
				con.close();
				return false;}

		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");return false;}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");return false;}
	}
	private String getEmail(String Cin)
	{
		try {
			String qr="select email from client  where cin='"+Cin+"'";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
			Statement st= (Statement) con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			while(rs.next())
			{
				return rs.getString(1);
			}

		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");return null;}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");return null;}
	return null;
	}
	public boolean checkCin(String cin) {return true;}
	public boolean checkNum(String num) {return true;}
	public boolean checkmail(String mail) {return true;}
	public boolean checkVide() {
		if(textCin.getText().equals(""))
		{ 
			JOptionPane.showMessageDialog(null,"(*)Cin: le champ Cin obligatoire ");
			return true;
		}
		else
		{
			if(typePatient.isSelected()) 
			{
				if(textNom.getText().equals(""))
				{ 
					JOptionPane.showMessageDialog(null,"(*)Nom: le champ Nom obligatoire ");
					return true;
				}
				else
				{
					if(textPrenom.getText().equals(""))
					{ 
						JOptionPane.showMessageDialog(null,"(*)Prenom: le champ Prenom obligatoire ");
						return true;
					}
					else
					{
						if(textEmail.getText().equals(""))
						{ 
							JOptionPane.showMessageDialog(null,"(*)Email: le champ Email obligatoire ");
							return true;
						}
						else
						{
							if(textTel.getText().equals(""))
							{ 
								JOptionPane.showMessageDialog(null,"(*)Tel: le champ Tel obligatoire ");
								return true;
							}
							else
							{
								return false;
							}
						}
					}
				}
			}

		}
		return false;

	}


}
