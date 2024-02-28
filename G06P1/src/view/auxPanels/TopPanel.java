package view.auxPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel{
	private JLabel titleLabel;
	
	private int borderSize = 3;
	
	public TopPanel(String title, int width, int height){
		super(new FlowLayout(FlowLayout.CENTER));
		initGUI(title, width, height);
	}
	
	private void initGUI(String title, int width, int height) {
		//The title label is created and added to the panel
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		titleLabel.setForeground(Color.WHITE);
		add(titleLabel);
		
		//The background and a border is set
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createMatteBorder(0, 0, borderSize, 0, Color.BLACK));
		
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * sets the title of the panel
	 * 
	 * @param title string to be displayed on the panel
	 */
	public void setTitle(String title) {
		titleLabel.setText(title);
	}
}
