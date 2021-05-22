package projet;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import org.omg.CORBA.TIMEOUT;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextHitInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;


public class PanelHome extends JPanel {

	/**
	 * Create the panel.
	 */
	private int x,y,width=1004,height=703;

	DefaultTableModel model=new DefaultTableModel();
	Object[] columns={"ID","CIN","NOM","PRENOM","DATE","HEUR"};

	private JTable liste;
	JScrollPane list=new JScrollPane();

	private JPanel panelEntet = new JPanel();
	private JLabel lblLesRdv = new JLabel("Les RDV");
	private JLabel lblDateNow=new JLabel("dd/mm/yyyy");
	private JButton btnRecherche= new JButton("recherche");
	private JLabel lblDate = new JLabel("Date:");
	private JLabel lblCin = new JLabel("CIN:");
	private JButton btnSupp=new JButton("supprimer");
	private JButton btnModif=new JButton("modifier");
	private JLabel textCinModif=new JLabel("XXXXXXXX");
	private JLabel lblNomModif =new JLabel("Nom : ");
	private JLabel textNomModif=new JLabel("NNNNNNNNNN");
	private JLabel lblPrenomModif=new JLabel("Prenom : ");
	private JLabel textPrenomModif=new JLabel("PPPPPPPPPP");
	private JPanel panelFormModif=new JPanel();
	private JButton ButtonModif=new JButton("Modifier");
	private JButton ButtonAnnuler=new JButton("Terminer");
	private JLabel lblCinModif = new JLabel("Cin : ");
	private JLabel lblDateModif=new JLabel("Date:");
	private JDateChooser textDateModif=new JDateChooser();

	private JLabel lblH=new JLabel("H:");
	private JDateChooser textDate;
	private JTextField textCin;
	private JLabel lblM=new JLabel("m:");
	private JComboBox<String> heur;
	private JComboBox<String> minute;
	private JLabel lblmsg=new JLabel();
	private JCheckBox affich=new JCheckBox();

	private JLabel lblConnect=new JLabel("acun RDVs trouver");
	int indexH=height/15;
	int indexW=width/10; 

	String modelHeur1[]= {"08","09","10","11","13","14","15"};
	String[] modelMinute=new String[60];

	public PanelHome(int x,int y,int w,int h) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		setBounds(x, y, width, height);
		setLayout(null);
		//width=w;
		//height=h;
		textDate = new JDateChooser();
		textCin = new JTextField();


		liste = new JTable();
		liste.setModel(model);
		model.setColumnIdentifiers(columns);
		liste.setFont(new Font("Dialog", Font.BOLD, 24));
		liste.setRowHeight(60);
		liste.setBorder(null);
		entet();


		textDateModif.setDateFormatString("yyyy-MM-dd");
		textDate.setDateFormatString("yyyy-MM-dd");


		for(int i=0;i<60;i++) {
			if(i<10) {modelMinute[i]="0"+String.valueOf(i);}
			else{modelMinute[i]=String.valueOf(i);}}
		heur=new JComboBox<String>(modelHeur1);
		minute=new JComboBox<String>(modelMinute);
		list.setToolTipText("");
		list.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		list.setBackground(Color.white);
		list.setViewportView(liste);
		add(list);
		lblConnect.setForeground(Color.LIGHT_GRAY);
		lblConnect.setFont(new Font("Dialog", Font.BOLD, 30));
		add(lblConnect);
		lblConnect.setVisible(false);


		lblH.setFont(new Font("Dialog", Font.BOLD, 16));
		connet();
		addElement();

		affich.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				if(affich.isSelected()) {textDate.setEnabled(true);}
				else {textDate.setEnabled(false);}	
			}
		});


		liste.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				int indexRDV=liste.getSelectedRow();
				String cin=model.getValueAt(indexRDV, 1).toString();
				String nom=model.getValueAt(indexRDV, 2).toString();
				String prenom=model.getValueAt(indexRDV, 3).toString();
				String date=model.getValueAt(indexRDV, 4).toString();
				String heure=model.getValueAt(indexRDV, 5).toString();	
				lblmsg.setText("");
				String dt=date.replace('-', '/');	
				Date d=null;
				try {
					d=new Date(dt);
					textDateModif.setDate(d);
				}catch(Exception ex) {}
				textCinModif.setText(cin);
				textNomModif.setText(nom);
				textPrenomModif.setText(prenom);
				ArrayList<String> valH=new ArrayList<String>(Arrays.asList(heure.split(":")));
				System.out.println("***************** $$"+valH);
				try {
					int h=Integer.parseInt(valH.get(0));
					int m=Integer.parseInt(valH.get(1));
					if(h<12) {heur.setSelectedIndex(h-8);}
					else{heur.setSelectedIndex(h-9);}
					minute.setSelectedIndex(m+1);
				}catch (Exception ex) {
					// TODO: handle exception
					System.out.println("***************** list select");
				}

			}
		});

		//btnButtonAnnul.addMouseListener(new panelButtonMouseAdapter(btnButtonAnnul));
		panelFormModif.add(ButtonAnnuler);


		panelFormModif.setVisible(false);

		btnModif.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				int indexRDV=liste.getSelectedRow();
				if(indexRDV>=0) {
					btnSupp.setVisible(false);
					btnModif.setVisible(false);
					list.setBounds(12, panelEntet.getY()+panelEntet.getHeight()+5, width-24, indexH*9);
					panelFormModif.setBounds(12, list.getY()+list.getHeight()+5, list.getWidth(),indexH*4-20 );
					panelFormModif.setVisible(true);	

				}
			}
		});

		ButtonAnnuler.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				panelFormModif.setVisible(false);

				list.setBounds(12, panelEntet.getY()+panelEntet.getHeight()+5, width-24, indexH*11);

				btnSupp.setBounds(indexW/4, list.getY()+list.getHeight()+12, indexW*2, indexH*2-40);
				btnSupp.setVisible(true);

				btnModif.setBounds(width-indexW/4-btnSupp.getWidth(), btnSupp.getY(), indexW*2, indexH*2-40);
				btnModif.setVisible(true);
			}
		});
		ButtonModif.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				lblmsg.setText("");
				int index =liste.getSelectedRow();
				try {
					String id=liste.getValueAt(index, 0).toString();
					String df=new SimpleDateFormat("yyyy-MM-dd").format(textDateModif.getDate());
					String h=heur.getSelectedItem().toString()+":"+minute.getSelectedItem().toString();
					System.out.println(textDateModif.getDate().getDay());
					if(textDateModif.getDate().getDay()==0) {
						JOptionPane.showMessageDialog(null, "les rdv de lundi à samedi");}
					else
						if(textDateModif.getDate().getDay()==6 &&(heur.getSelectedIndex()>3 ||(heur.getSelectedIndex()==3 && minute.getSelectedIndex()>30))) {
							JOptionPane.showMessageDialog(null, "Samedi le dernier rdv à 11h:30m");}
						else 
							if(heur.getSelectedIndex()==6 && minute.getSelectedIndex()>30){
								JOptionPane.showMessageDialog(null, "le dernier rdv à 15h:30m");}
							else
							{
								updateRdv(id,df,h);
								String hModif=heur.getSelectedItem().toString()+":"+minute.getSelectedItem().toString();
								String cin=liste.getValueAt(index, 1).toString();
								String msg="votre rendez-vous le "+liste.getValueAt(index, 4).toString()+" à "+liste.getValueAt(index, 5).toString()+" à été modifier neveau date est "+df+" à "+hModif+" \n pour plus d'information coctacter le séquritaire \t num :xxxxxxxx";
								sendEmail.sendMail(getEmail(cin), "modification Rendez-Vous",msg );
								
								liste.setValueAt(hModif, index, 5);
								liste.setValueAt(df, index, 4);
								lblmsg.setForeground(new Color(0, 128, 0));
								lblmsg.setText("Update Succsufull" );
							}
				}catch(Exception ex) {
					lblmsg.setForeground(new Color(255, 0, 0));
					lblmsg.setText("erreur !!! update not failed" );
				}


			}
		});

		btnSupp.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				int indexRDV=liste.getSelectedRow();
				if(indexRDV>=0) {
					if(JOptionPane.showConfirmDialog(null, "are you sure you want to delete this RDV?","Supprimer",JOptionPane.YES_NO_OPTION)==0) {
						if(SuppRdv(liste.getValueAt(indexRDV, 0).toString())) {
							String cin=liste.getValueAt(indexRDV, 1).toString();
							String msg="votre rendez-vous le "+liste.getValueAt(indexRDV, 4).toString()+" à "+liste.getValueAt(indexRDV, 5).toString()+" à été annuller \n pour plus d'information coctacter le séquritaire \t num :xxxxxxxx";
							sendEmail.sendMail(getEmail(cin), "Annulation Rendez-Vous",msg );
							model.removeRow(indexRDV);
							if(liste.getRowCount()>0)
							{
								list.setVisible(true);
								lblConnect.setVisible(false);
								btnSupp.setEnabled(true);
								btnModif.setEnabled(true);
							}
							else
							{
								list.setVisible(false);
								lblConnect.setVisible(true);
								btnSupp.setEnabled(false);
								btnModif.setEnabled(false);	
							}	
						}
						else {JOptionPane.showMessageDialog(null, "probleme de connection base donnéé ");	}
					}
				}
				else{JOptionPane.showMessageDialog(null, "aucun Rows selected !!");	}
			}
		});

		setBoundsElement();
		setFontElement();
	}

	public void setBoundsElement() {
		int xModif=panelFormModif.getWidth()-(panelFormModif.getWidth()/10*2+12);
		int yModif=panelFormModif.getHeight()/7;
		list.setBounds(12, panelEntet.getY()+panelEntet.getHeight()+5, width-24, indexH*11);
		lblConnect.setBounds(lblLesRdv.getWidth(), panelEntet.getY()+panelEntet.getHeight()+5, width-24, indexH*11);
		liste.setBounds(5,5,list.getWidth()-5,list.getHeight()-5);
		btnSupp.setBounds(indexW/4, list.getY()+list.getHeight()+12, indexW*2, indexH*2-40);
		btnModif.setBounds(width-indexW/4-btnSupp.getWidth(), btnSupp.getY(), indexW*2, indexH*2-40);
		panelFormModif.setBounds(12, list.getY()+list.getHeight()+5, list.getWidth(),indexH*4-20 );
		lblCinModif.setBounds(12, 12, panelFormModif.getWidth()/15, panelFormModif.getHeight()/7*2);
		textCinModif.setFont(new Font("Dialog", Font.PLAIN, 15));
		textCinModif.setBounds(lblCinModif.getX()+lblCinModif.getWidth(),lblCinModif.getY(),panelFormModif.getWidth()/10,lblCinModif.getHeight());
		lblNomModif.setBounds(textCinModif.getX()+textCinModif.getWidth(),lblCinModif.getY(),panelFormModif.getWidth()/13,lblCinModif.getHeight());
		textNomModif.setFont(new Font("Dialog", Font.PLAIN, 15));
		textNomModif.setBounds(lblNomModif.getX()+lblNomModif.getWidth(),lblCinModif.getY(),panelFormModif.getWidth()/10,lblCinModif.getHeight());
		lblPrenomModif.setBounds(textNomModif.getX()+textNomModif.getWidth(),lblCinModif.getY(),panelFormModif.getWidth()/9,lblCinModif.getHeight());
		textPrenomModif.setFont(new Font("Dialog", Font.PLAIN, 15));
		textPrenomModif.setBounds(lblPrenomModif.getX()+lblPrenomModif.getWidth(),lblCinModif.getY(),panelFormModif.getWidth()/10,lblCinModif.getHeight());

		lblDateModif.setBounds(20, lblCinModif.getY()+lblCinModif.getHeight()+20, panelFormModif.getWidth()/10*1,lblCinModif.getHeight() );
		textDateModif.setBounds(lblDateModif.getX()+lblDateModif.getWidth(), lblDateModif.getY()+3,panelFormModif.getWidth()/10*2 , lblDateModif.getHeight()-6);
		lblH.setBounds(textDateModif.getX()+textDateModif.getWidth()+14, lblDateModif.getY(), 30, lblDateModif.getHeight());
		heur.setBounds(lblH.getX()+lblH.getWidth(), textDateModif.getY(), textDateModif.getWidth()/2, textDateModif.getHeight());
		lblM.setBounds(heur.getX()+heur.getWidth()+14, lblH.getY(), lblH.getWidth(), lblH.getHeight());
		minute.setBounds(lblM.getX()+lblM.getWidth(), heur.getY(), heur.getWidth(), heur.getHeight());
		ButtonModif.setBounds(panelFormModif.getWidth()-(panelFormModif.getWidth()/10*2+12), panelFormModif.getHeight()/7, panelFormModif.getWidth()/10*2, panelFormModif.getHeight()/7*2);
		ButtonAnnuler.setBounds(ButtonModif.getX(), panelFormModif.getHeight()/7*4, ButtonModif.getWidth(), ButtonModif.getHeight());
	}

	public void entet() {
		panelEntet.setBackground(Color.WHITE);
		panelEntet.setBounds(12, 12, width-24, indexH*2);
		panelEntet.setLayout(null);
		add(panelEntet);


		lblLesRdv.setFont(new Font("aakar", Font.BOLD, 40));
		lblLesRdv.setBounds(2, 2, panelEntet.getWidth()/10*4, panelEntet.getHeight()/3*2);
		panelEntet.add(lblLesRdv);

		lblDateNow.setBounds(panelEntet.getWidth()-(panelEntet.getWidth()/10+12), panelEntet.getHeight()/7, panelEntet.getWidth()/10,panelEntet.getHeight()/7*2 );
		panelEntet.add(lblDateNow);

		btnRecherche.setForeground(Color.WHITE);
		btnRecherche.setBounds(panelEntet.getWidth()-(panelEntet.getWidth()/20*3+12), panelEntet.getHeight()/7*4, panelEntet.getWidth()/20*3, lblDateNow.getHeight()+10);
		btnRecherche.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		//panelRecherche.addMouseListener(new panelButtonMouseAdapter(panelRecherche));
		panelEntet.add(btnRecherche);


		LocalDate dt=LocalDate.now();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String dte=df.format(dt);
		Date dtN=new Date(dte);
		textDate.setDate(dtN);
		lblDateNow.setText(dte);
		btnRecherche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				DefaultTableModel modelR=new DefaultTableModel();
				modelR.setColumnIdentifiers(columns);
				model=null;
				model=modelR;
				liste.setModel(model);
				recherche();		
			}
		});


		textDate.setBounds(btnRecherche.getX()-129, btnRecherche.getY()+5, 114, lblDateNow.getHeight());
		panelEntet.add(textDate);
		//textDate.setColumns(10);

		lblDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDate.setBounds(textDate.getX()-(80), textDate.getY(), 70, textDate.getHeight());
		panelEntet.add(lblDate);
		affich.setBounds(lblDate.getX()+lblDate.getWidth()-15,textDate.getY()+3,20,20);
		panelEntet.add(affich);
		textDate.setEnabled(false);

		textCin.setBounds(lblDate.getX()-(textDate.getWidth()+12), lblDate.getY(), textDate.getWidth(),textDate.getHeight());
		panelEntet.add(textCin);
		textCin.setColumns(10);

		lblCin.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCin.setBounds(textCin.getX()-(lblDate.getWidth()+10), textCin.getY(), lblDate.getWidth(), lblDate.getHeight());
		panelEntet.add(lblCin);
	}
	public void addElement() {

		add(btnSupp);
		add(btnModif);
		panelFormModif.setLayout(null);
		add(panelFormModif);		
		panelFormModif.add(lblCinModif);
		panelFormModif.add(lblmsg);
		panelFormModif.add(lblDateModif);
		panelFormModif.add(textDateModif);
		panelFormModif.add(lblH);
		panelFormModif.add(heur);
		panelFormModif.add(lblM);
		panelFormModif.add(minute);
		panelFormModif.add(ButtonModif);
		panelFormModif.add(textCinModif);
		panelFormModif.add(lblNomModif);
		panelFormModif.add(textNomModif);
		panelFormModif.add(lblPrenomModif);
		panelFormModif.add(textPrenomModif);
	}
	public void connet(){
		try {
			LocalDate dt=LocalDate.now();
			DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String dte=df.format(dt);
			
			String qr="select r.id_rdv,c.cin ,c.nom,c.prenom,r.date_rdv,r.horaire from client c,rdv r where c.id_client=r.id_client and r.date_rdv='"+dte+"'";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
			Statement st= (Statement) con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			Object[] rows=new Object[6];
			while(rs.next())
			{
				rows[0]=rs.getString(1);
				rows[1]=rs.getString(2);
				rows[2]=rs.getString(3);
				rows[3]=rs.getString(4);
				rows[4]=rs.getString(5);
				ArrayList<String> va=new ArrayList<String>(Arrays.asList(rs.getString(6).split(":")));
				rows[5]=va.get(0)+":"+va.get(1);
				model.addRow(rows);
				rows[0]="";
				rows[1]="";
				rows[2]="";
				rows[3]="";
				rows[4]="";
				rows[5]="";
			}
			if(liste.getRowCount()>0)
			{
				list.setVisible(true);
				lblConnect.setVisible(false);
				btnSupp.setEnabled(true);
				btnModif.setEnabled(true);
			}
			else
			{
				list.setVisible(false);
				lblConnect.setVisible(true);
				btnSupp.setEnabled(false);
				btnModif.setEnabled(false);	
			}


			con.close();

		} catch (ClassNotFoundException ex) {
			//Logger.getLogger(authentification.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");
		}
		catch(SQLException ex){
			// Logger.getLogger(authentification.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");
		}             
	}
	public void recherche(){
		try {
			Date d=textDate.getDate();
			String date=new SimpleDateFormat("YYYY-MM-dd").format(d);

			String qr="";
			if(affich.isSelected()) {
				qr="select r.id_rdv,c.cin ,c.nom,c.prenom,r.date_rdv,r.horaire from client c,rdv r where c.id_client=r.id_client and r.date_rdv='"+date+"' and c.cin like '%"+textCin.getText()+"%'";
			}
			else {
				qr="select r.id_rdv,c.cin ,c.nom,c.prenom,r.date_rdv,r.horaire from client c,rdv r where c.id_client=r.id_client and c.cin like '%"+textCin.getText()+"%'";
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
			Statement st= (Statement) con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			Object[] rows=new Object[6];
			while(rs.next())
			{
				rows[0]=rs.getString(1);
				rows[1]=rs.getString(2);
				rows[2]=rs.getString(3);
				rows[3]=rs.getString(4);
				rows[4]=rs.getString(5);
				ArrayList<String> va=new ArrayList<String>(Arrays.asList(rs.getString(6).split(":")));
				rows[5]=va.get(0)+":"+va.get(1);
				model.addRow(rows);
				rows[0]="";
				rows[1]="";
				rows[2]="";
				rows[3]="";
				rows[4]="";
				rows[5]="";
			}
			if(liste.getRowCount()>0)
			{
				list.setVisible(true);
				lblConnect.setVisible(false);
				btnSupp.setEnabled(true);
				btnModif.setEnabled(true);
			}
			else
			{
				list.setVisible(false);
				lblConnect.setVisible(true);
				btnSupp.setEnabled(false);
				btnModif.setEnabled(false);	
			}
			con.close();
		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");}             
	}
	public void setFontElement() {
		Font font=new Font("",1,22);

		btnSupp.setForeground(new Color(255, 255, 255));
		btnSupp.setFont(new Font("Dialog", Font.BOLD, 18));
		btnModif.setFont(new Font("Dialog", Font.BOLD, 18));
		btnModif.setForeground(new Color(255, 255, 255));
		panelFormModif.setBackground(Color.WHITE);
		ButtonModif.setForeground(new Color(255, 255, 255));
		ButtonAnnuler.setForeground(new Color(255, 255, 255));
		ButtonAnnuler.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCinModif.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNomModif.setFont(new Font("Dialog", Font.BOLD, 19));
		lblPrenomModif.setFont(new Font("Dialog", Font.BOLD, 19));
		lblDateModif.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSupp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModif.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModif.setBackground(new Color(178, 34, 34));
		btnSupp.setBackground(new Color(178, 34, 34));
		ButtonAnnuler.setBackground(new Color(178, 34, 34));
		ButtonModif.setBackground(new Color(178, 34, 34));
		btnRecherche.setBackground(new Color(0, 153, 255));
		ButtonModif.setBorder(new LineBorder(new Color(0, 0, 0)));
		ButtonAnnuler.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		affich.setBackground(Color.white);
	}

	public boolean updateRdv(String...strings ) {
		try {
			String qr="update rdv set date_rdv=? ,horaire=? where id_rdv=? ";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
			java.sql.PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1,strings[1] );
			ps.setString(2,strings[2] );
			ps.setString(3,strings[0] );
			ps.executeUpdate();
			return true;
		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"probleme de connexion N:°1");return false;}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"probleme de connexion N:°2");return false;}   

	}
	public boolean SuppRdv(String id) {
		try {
			String qr="delete from rdv where id_rdv=?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test", null, null);
			java.sql.PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1,id );
			ps.executeUpdate();	
			return true;
		} 
		catch (ClassNotFoundException ex) {JOptionPane.showMessageDialog(null,"Supp : probleme de connexion N:°1");return false;}
		catch(SQLException ex){JOptionPane.showMessageDialog(null,"Supp : probleme de connexion N:°2");return false;}  
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

}
