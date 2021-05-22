package projet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.CardLayout;

public class principale extends JFrame {

	private JPanel contentPane;
	private PanelHome Home;
	private PanelAjout Ajout;
	private PanelProfil Profil;
	private PanelAdmin Admin;

	public principale() {
		if(autentification.user==null) {System.exit(0);}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension sc=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)sc.getWidth(),(int) sc.getHeight());
		this.setLocationRelativeTo(null);

		this.setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelMenu.setBounds(12, 12, 272, getHeight()-48);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		JLabel labelExit = new JLabel("X");
		labelExit.setForeground(Color.black);
		labelExit.setFont(new Font("Dialog", Font.BOLD, 24));
		labelExit.setBounds(this.getWidth()-100,5,25,25);
		labelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				lblexit();
			}
			@Override
			public void mouseEntered(MouseEvent e) {labelExit.setForeground(Color.red);}

			@Override
			public void mouseExited(MouseEvent e) {labelExit.setForeground(Color.black);}
		});
		contentPane.add(labelExit);

		int x=panelMenu.getX()+panelMenu.getWidth()+3;
		int y=labelExit.getY()+labelExit.getHeight()-5;
		int width=this.getWidth()-panelMenu.getWidth()-90;
		int height=panelMenu.getHeight()-labelExit.getHeight()+12;	

		Home=new PanelHome(x, y, width, height);
		contentPane.add(Home);
		Home.setLayout(null);
		Ajout=new PanelAjout(x, y, width, height);
		Profil=new PanelProfil(x, y, width, height);
		Admin=new PanelAdmin(x, y, width, height);

		int yPanelMenu=panelMenu.getHeight()/17;
		JLabel labelLogo = new JLabel("");
		labelLogo.setBounds(1, 1, panelMenu.getWidth()-2, yPanelMenu*5-2);
		ImageIcon logoImage=new ImageIcon(principale.class.getResource("/images/logo6.png"));
		Image image=logoImage.getImage();
		Image tmp_Image=image.getScaledInstance(labelLogo.getWidth(), labelLogo.getHeight(), Image.SCALE_SMOOTH);
		logoImage=new ImageIcon(tmp_Image);
		labelLogo.setIcon(logoImage);
		panelMenu.add(labelLogo);

		JPanel panelHome = new JPanel();
		panelHome.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		panelHome.setBackground(Color.WHITE);
		JPanel panelAjoutRDV = new JPanel();
		panelAjoutRDV.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		panelAjoutRDV.setBackground(Color.WHITE);
		JPanel panelEspacePersonnel = new JPanel();
		JPanel panelDeconnect = new JPanel();
		JPanel panelConsulterCabine = new JPanel();

		panelEspacePersonnel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		panelEspacePersonnel.setBackground(Color.WHITE);
		panelDeconnect.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		panelDeconnect.setBackground(Color.WHITE);
		panelDeconnect.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		panelDeconnect.setBackground(Color.WHITE);

		int yLogo=labelLogo.getY();
		int hLogo=labelLogo.getHeight();
		panelHome.setBounds(1, yLogo+hLogo+15, panelMenu.getWidth()-2, yPanelMenu*2-2);
		panelMenu.add(panelHome);
		panelHome.addMouseListener(new panelButtonMouseAdapter(panelHome));

		panelHome.setLayout(null);

		JLabel iconHome = new JLabel("");
		iconHome.setBackground(Color.WHITE);
		iconHome.setBounds(12, 12, 107, 58);
		ImageIcon homeImage=new ImageIcon(principale.class.getResource("/images/home2.png"));
		Image imageHome=homeImage.getImage();
		Image tmp_ImageHome=imageHome.getScaledInstance(iconHome.getWidth(), iconHome.getHeight(), Image.SCALE_SMOOTH);
		homeImage=new ImageIcon(tmp_ImageHome);
		iconHome.setIcon(homeImage);
		panelHome.add(iconHome);

		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Dyuthi", Font.BOLD, 28));
		lblHome.setBounds(131, 23, 124, 36);
		panelHome.add(lblHome);

		panelAjoutRDV.setBounds(1, 300, panelMenu.getWidth()-2, yPanelMenu*2-2);
		panelAjoutRDV.addMouseListener(new panelButtonMouseAdapter(panelAjoutRDV));
		panelMenu.add(panelAjoutRDV);

		JLabel iconAjoutRDV = new JLabel("");
		iconAjoutRDV.setBounds(12, 12, 107, 58);
		homeImage=new ImageIcon(principale.class.getResource("/images/ajout1.jpg"));
		imageHome=homeImage.getImage();
		tmp_ImageHome=imageHome.getScaledInstance(iconHome.getWidth(), iconHome.getHeight(), Image.SCALE_SMOOTH);
		homeImage=new ImageIcon(tmp_ImageHome);
		panelAjoutRDV.setLayout(null);
		iconAjoutRDV.setIcon(homeImage);

		JLabel lblAjoutRDV = new JLabel("Ajout RDV");
		lblAjoutRDV.setBounds(131, 23, 139, 36);
		lblAjoutRDV.setFont(new Font("Dyuthi", Font.BOLD, 25));
		lblAjoutRDV.setBounds(131, 23, 124, 36);

		panelAjoutRDV.add(iconAjoutRDV);
		panelAjoutRDV.add(lblAjoutRDV);

		panelEspacePersonnel.setBounds(1, panelAjoutRDV.getY()+panelAjoutRDV.getHeight()+2, panelMenu.getWidth()-2, yPanelMenu*2-2);
		panelEspacePersonnel.addMouseListener(new panelButtonMouseAdapter(panelEspacePersonnel));

		JLabel iconEP = new JLabel("");
		iconEP.setBounds(12, 12, 107, 58);
		homeImage=new ImageIcon(principale.class.getResource("/images/parametre.png"));
		imageHome=homeImage.getImage();
		tmp_ImageHome=imageHome.getScaledInstance(iconHome.getWidth(), iconHome.getHeight(), Image.SCALE_SMOOTH);
		homeImage=new ImageIcon(tmp_ImageHome);
		panelEspacePersonnel.setLayout(null);
		iconEP.setIcon(homeImage);

		JLabel lblEP = new JLabel("Pérsonnel");
		lblEP.setBounds(131, 12, 124, 47);
		lblEP.setFont(new Font("Dyuthi", Font.BOLD, 25));
		lblEP.setBounds(131, 23, 124, 36);

		panelEspacePersonnel.add(iconEP);
		panelEspacePersonnel.add(lblEP);

		panelMenu.add(panelEspacePersonnel);




		JLabel iconDeconnect = new JLabel("");
		iconDeconnect.setBounds(12, 12, 107, 58);
		homeImage=new ImageIcon(principale.class.getResource("/images/deconnect2.png"));
		imageHome=homeImage.getImage();
		tmp_ImageHome=imageHome.getScaledInstance(iconHome.getWidth(), iconHome.getHeight(), Image.SCALE_SMOOTH);
		homeImage=new ImageIcon(tmp_ImageHome);
		panelDeconnect.setLayout(null);
		iconDeconnect.setIcon(homeImage);

		JLabel lblDeconnect = new JLabel("Déconnect");
		lblDeconnect.setBounds(131, 23, 139, 36);
		lblDeconnect.setFont(new Font("Dyuthi", Font.BOLD, 25));
		lblDeconnect.setBounds(131, 23, 124, 36);

		panelDeconnect.add(iconDeconnect);
		panelDeconnect.add(lblDeconnect);

		int xx=11;
		if(autentification.user.getType().equals("U"))
		{		
			panelDeconnect.setBounds(1, panelEspacePersonnel.getY()+panelEspacePersonnel.getHeight()+2, panelMenu.getWidth()-2, yPanelMenu*2-2);
			panelDeconnect.addMouseListener(new panelButtonMouseAdapter(panelDeconnect));

			panelMenu.add(panelDeconnect);	
		}
		else if(autentification.user.getType().equals("A"))
		{
			panelConsulterCabine.setBounds(1, panelEspacePersonnel.getY()+panelEspacePersonnel.getHeight()+2, panelMenu.getWidth()-2, yPanelMenu*2-2);
			panelConsulterCabine.addMouseListener(new panelButtonMouseAdapter(panelConsulterCabine));
			panelConsulterCabine.setBackground(Color.WHITE);
			panelMenu.add(panelConsulterCabine);

			panelDeconnect.setBounds(1, panelConsulterCabine.getY()+panelConsulterCabine.getHeight()+2, panelMenu.getWidth()-2, yPanelMenu*2-2);
			panelDeconnect.addMouseListener(new panelButtonMouseAdapter(panelDeconnect));

			JLabel iconCabine = new JLabel("");
			iconCabine.setBounds(12, 12, 107, 58);
			homeImage=new ImageIcon(principale.class.getResource("/images/parametreGeneral.jpg"));
			imageHome=homeImage.getImage();
			tmp_ImageHome=imageHome.getScaledInstance(iconHome.getWidth(), iconHome.getHeight(), Image.SCALE_SMOOTH);
			homeImage=new ImageIcon(tmp_ImageHome);
			panelConsulterCabine.setLayout(null);
			iconCabine.setIcon(homeImage);

			JLabel lblCabine = new JLabel("Réspnsable");
			lblCabine.setBounds(131, 23, 139, 36);
			lblCabine.setFont(new Font("Dyuthi", Font.BOLD, 25));
			lblCabine.setBounds(131, 23, 124, 36);

			panelConsulterCabine.add(iconCabine);
			panelConsulterCabine.add(lblCabine);
			panelMenu.add(panelDeconnect);
		}

		panelHome.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.setVisible(false);
				Ajout.setVisible(false);
				Profil.setVisible(false);
				Admin.setVisible(false);
				Home=new PanelHome(x,y,width,height);
				contentPane.add(Home);
			}
		});
		panelAjoutRDV.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.setVisible(false);
				Ajout.setVisible(false);
				Profil.setVisible(false);
				Admin.setVisible(false);
				Ajout=new PanelAjout(x,y,width,height);
				contentPane.add(Ajout);
			}
		});
		panelConsulterCabine.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.setVisible(false);
				Ajout.setVisible(false);
				Profil.setVisible(false);
				Admin.setVisible(false);
				Admin=new PanelAdmin(x,y,width,height);
				contentPane.add(Admin);
			}
		});
		panelEspacePersonnel.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.setVisible(false);
				Ajout.setVisible(false);
				Profil.setVisible(false);
				Admin.setVisible(false);
				Profil=new PanelProfil(x,y,width,height);
				contentPane.add(Profil);
			}
		});
		panelDeconnect.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				deconnect();
			}
		});
	}
	public void lblexit() {
		if(JOptionPane.showConfirmDialog(null, "are you sure you want to close this application?","confirmation",JOptionPane.YES_NO_OPTION)==0) {
			autentification.user=null;
			autentification a=new autentification();
			a.setVisible(true);
			this.dispose();
		}
	}
	public void deconnect() {
		autentification.user=null;
		autentification a=new autentification();
		a.setVisible(true);
		this.dispose();

	}
}
