package projet;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class panelModif extends JPanel {

	/*
	 * Create the panel.
	 */
	JLabel lblId,lblExit,lblAcode,lblCode,lblDsgn,lblUnite,lblStock,lblCout,lblConsommation,lblRef,lblFour;
	JTextField Acode,code,dsgn,unite,stock,cout,consommation,ref,four;
	JButton btnModif,btnAdd;
	JCheckBox checkAdd;
	public panelModif() {
		
		
		
		lblId=new JLabel();
		lblExit=new JLabel("X");
		lblAcode=new JLabel("AnCode");
		lblCode=new JLabel("Code");
		lblDsgn=new JLabel("designation");
		lblUnite=new JLabel("unite");
		setBackground(Color.red);
		lblStock=new JLabel("stock");
		//lblCout=new JLabel("Cout");
		lblConsommation=new JLabel("Consommation");
		lblRef=new JLabel("ref");
		lblFour=new JLabel("fournisseur");
		
		Acode=new JTextField();
		code=new JTextField();
		dsgn=new JTextField();
		unite=new JTextField();
		stock=new JTextField();
		cout=new JTextField();
		consommation=new JTextField();
		ref=new JTextField();
		four=new JTextField();
		
		btnModif=new JButton("modifier");
		btnAdd=new JButton("ajouter");
		checkAdd=new JCheckBox();
		
		
	}

}
