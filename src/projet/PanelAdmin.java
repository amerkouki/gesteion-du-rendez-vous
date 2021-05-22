package projet;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PanelAdmin extends JPanel {

	/**
	 * Create the panel.
	 */
	private int x,y,width,height;
	public PanelAdmin(int x,int y,int w,int h) {
		setBackground(Color.white);
		setBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)));
		setBounds(x, y, 1003, 702);
		
		setLayout(null);
		
		JLabel lblHome = new JLabel("Ad");
		lblHome.setFont(new Font("Dialog", Font.BOLD, 22));
		lblHome.setBounds(152, 67, 245, 188);
		add(lblHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(68, 284, 498, 180);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0,0,600,600);
		scrollPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label  \n gdzdg \n hggdlblNewLabel_1hdsj \n hgsdqs\n hgsqydgeqdqsdsqd zedzadzq zaesdsqd zed sqdsq azdsq zdsq azr fsqdqs sdsqf fs \n hgqshdshd");
		lblNewLabel_1.setBounds(0,0,600,600);
		scrollPane.setViewportView(lblNewLabel_1);
	}

}
