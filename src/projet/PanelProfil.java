package projet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Modifier;

import javax.security.auth.callback.TextInputCallback;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class PanelProfil extends JPanel {

	/**
	 * Create the panel.
	 */

	JPanel panelEntet=new JPanel();
	JLabel textEntet=new JLabel("Profile");

	JLabel lblId=new JLabel();
	JLabel lblCin=new JLabel("CIN : ");
	JLabel lblNom=new JLabel("Nom : ");
	JLabel lblPrenom=new JLabel("Prenom : ");
	JLabel lblType=new JLabel("Type : ");
	JLabel lblUsername=new JLabel("Username : ");
	JLabel lblPassword=new JLabel("password : ");
	JLabel lblNewPassword=new JLabel("nouveau Password");
	JLabel lblConfirmPassword=new JLabel("Confirmer Password");
	JLabel lblMsg=new JLabel("chaque modification obliger de montre leur mot de passe(password)");

	JLabel textCin=new JLabel();
	JLabel textNom=new JLabel();
	JLabel textPrenom=new JLabel();
	JLabel textType=new JLabel();
	JTextField textNouveauPassword=new JTextField();
	JPasswordField textConfirmPasswor=new JPasswordField();

	JLabel lblModif=new JLabel("modifier");
	JLabel lblAnnulModif=new JLabel("Annuler");

	JTextField textUsername=new JTextField();
	JPasswordField textPassword=new JPasswordField();

	JButton btnModifier=new JButton("modifier");


	private int x,y,width=1004,height=703;
	int indexX=width/10;
	int indexY=height/14;

	public PanelProfil(int x,int y,int w,int h) {
		setBackground(Color.white);
		setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		setBounds(x, y, width, height);
		setLayout(null);

		int indexX=width/10;
		int indexY=height/14;
		entet();
		lblId.setText(autentification.user.getId());
		textCin.setText(autentification.user.getCin());
		textNom.setText(autentification.user.getNom());
		textPrenom.setText(autentification.user.getPrenom());
		
		if(autentification.user.getType().equals("A"))
		{
			textType.setText("admin user");
		}
		else if(autentification.user.getType().equals("U"))
		{
			textType.setText("simple user");
		}

		btnModifier.setForeground(new Color(255, 255, 255));
		btnModifier.setBackground(new Color(178, 34, 34));

		String txtid="**";
		String txt=lblId.getText();
		for(int i=0;i<5-txtid.length();i++) {txt="0"+txt;}
		lblId.setText("ID : "+txt);
		lblId.setBounds(panelEntet.getWidth()-20-(panelEntet.getWidth()/10),20,panelEntet.getWidth()/10,panelEntet.getHeight()/4);
		panelEntet.add(lblId);

		lblModif.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblModif.setForeground(new Color(60,179,117));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblModif.setForeground(Color.black);
			}

			@Override
			public void mousePressed(MouseEvent e) {

				lblNewPassword.setVisible(true);
				lblConfirmPassword.setVisible(true);
				textNouveauPassword.setVisible(true);
				textConfirmPasswor.setVisible(true);
				lblAnnulModif.setVisible(true);
				lblModif.setVisible(false);

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblModif.setForeground(Color.black);
			}
		});

		lblAnnulModif.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {lblAnnulModif.setForeground(new Color(60,179,117));}
			@Override
			public void mouseExited(MouseEvent e) {lblAnnulModif.setForeground(Color.black);}
			@Override
			public void mousePressed(MouseEvent e) {
				lblNewPassword.setVisible(false);
				lblConfirmPassword.setVisible(false);
				textNouveauPassword.setVisible(false);
				textConfirmPasswor.setVisible(false);
				lblAnnulModif.setVisible(false);
				lblModif.setVisible(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {lblAnnulModif.setForeground(Color.black);}
		});


		lblNewPassword.setVisible(false);
		lblConfirmPassword.setVisible(false);
		textNouveauPassword.setVisible(false);
		textConfirmPasswor.setVisible(false);
		lblAnnulModif.setVisible(false);

		setBoundesElement();
		setFontElement();
		addElement();



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
		textEntet.setBounds(iconEntet.getX()+iconEntet.getWidth()+panelTextEntet.getWidth()/16, 0, panelTextEntet.getWidth()/4*3-panelTextEntet.getWidth()/16, panelTextEntet.getHeight());
		panelTextEntet.add(textEntet);
	}

	public void setBoundesElement() {
		lblCin.setBounds(indexX*1,panelEntet.getY()+panelEntet.getHeight()+ indexY-10, indexX*2, indexY);
		lblNom.setBounds(lblCin.getX(), lblCin.getY()+lblCin.getHeight()+10, lblCin.getWidth(), lblCin.getHeight());
		lblPrenom.setBounds(lblNom.getX(), lblNom.getY()+lblCin.getHeight()+10, lblCin.getWidth(), lblCin.getHeight());
		lblType.setBounds(lblCin.getX(),lblPrenom.getY()+lblCin.getHeight()+10,lblCin.getWidth(),lblCin.getHeight());
		lblUsername.setBounds(lblCin.getX(),lblType.getY()+lblCin.getHeight()+10,lblCin.getWidth(),lblCin.getHeight());
		lblPassword.setBounds(lblCin.getX(),lblUsername.getY()+lblCin.getHeight()+10,lblCin.getWidth(),lblCin.getHeight());

		textCin.setBounds(lblCin.getX()+lblCin.getWidth()+indexX-15, lblCin.getY(), indexX*2, indexY/10*8);
		textNom.setBounds(textCin.getX(), lblNom.getY(), indexX*2, indexY/10*8);
		textPrenom.setBounds(textCin.getX(), lblPrenom.getY(), indexX*2, indexY/10*8);
		textType.setBounds(textCin.getX(), lblType.getY(), indexX*2, indexY/10*8);
		textUsername.setBounds(textCin.getX(), lblUsername.getY(), indexX*2, indexY/10*8);
		textPassword.setBounds(textCin.getX(), lblPassword.getY(), indexX*2, indexY/10*8);
		btnModifier.setBounds(getWidth()-indexX*4-10,getHeight()-indexY*2+30,getWidth()/20*3,getHeight()/20);
		lblMsg.setBounds(lblCin.getX(), btnModifier.getY(), btnModifier.getX()-btnModifier.getWidth(), btnModifier.getHeight());

		lblModif.setBounds(textPassword.getX()+textPassword.getWidth()+20,lblPassword.getY(),textPassword.getWidth()/3,lblPassword.getHeight());

		lblNewPassword.setBounds(lblCin.getX(),lblPassword.getY()+lblCin.getHeight()+10,lblCin.getWidth(),lblCin.getHeight());
		lblConfirmPassword.setBounds(lblCin.getX(),lblNewPassword.getY()+lblCin.getHeight()+10,lblCin.getWidth(),lblCin.getHeight());
		textNouveauPassword.setBounds(textCin.getX(), lblNewPassword.getY(), indexX*2, indexY/10*8);
		textConfirmPasswor.setBounds(textCin.getX(), lblConfirmPassword.getY(), indexX*2, indexY/10*8);
		lblAnnulModif.setBounds(textConfirmPasswor.getX()+textConfirmPasswor.getWidth()+20,lblConfirmPassword.getY(),textConfirmPasswor.getWidth()/3,lblConfirmPassword.getHeight());

	}

	public void setFontElement() {
		Font fontlbl=new Font("Dialog", Font.BOLD, 20);
		Font fontText=new Font("Dialog", Font.BOLD, 18);
		textEntet.setFont(new Font("Dialog", Font.BOLD, 26));
		lblMsg.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCin.setFont(fontlbl);
		lblNom.setFont(fontlbl);
		lblPrenom.setFont(fontlbl);
		lblType.setFont(fontlbl);
		lblUsername.setFont(fontlbl);
		lblPassword.setFont(fontlbl);

		textCin.setFont(new Font("FreeSans", Font.PLAIN, 16));
		textNom.setFont(new Font("FreeSans", Font.PLAIN, 16));
		textPrenom.setFont(new Font("FreeSans", Font.PLAIN, 16));
		textType.setFont(new Font("FreeSans", Font.PLAIN, 16));
		lblNewPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblConfirmPassword.setFont(new Font("Dialog", Font.BOLD, 16));
	}
	public void addElement() {
		add(lblMsg);
		add(btnModifier);

		add(lblNewPassword);
		add(lblConfirmPassword);
		add(textNouveauPassword);
		add(textConfirmPasswor);
		add(lblAnnulModif);

		add(textCin);
		add(textNom);
		add(textPrenom);
		add(textType);
		add(textUsername);
		add(textPassword);

		add(lblModif);
		add(lblCin);
		add(lblNom);
		add(lblPrenom);
		add(lblType);
		add(lblUsername);
		add(lblPassword);

	}
}
