package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelContainer extends JPanel{
	
	JLabel label;
	
	String text;
	
	public LabelContainer(){
		text = "";
		label = createLabel("");
	}
	
	public void initGUI() {
		//setPreferredSize(new Dimension(width, height));
		add(label);
	}
	
	/**
	 * creates a label
	 * @param text text that will be displayed in the label
	 * @return the label
	 */
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font(Font.SERIF, Font.BOLD, 14));
		label.setForeground(Color.WHITE);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		return label;
	}
	
	public void addTextLabel(int text, int orientation) {
		this.remove(label);
		
		String orientationString = "";
		if(orientation == 0) {
			orientationString = "↑";
		}
		else if(orientation == 1) {
			orientationString = "←";
		}
		else if(orientation == 2) {
			orientationString = "↓";
		}
		else if(orientation == 3) {
			orientationString = "→";
		}
		
		
		if(this.text.equals("")) {
			label = createLabel("" + text + orientationString);
			this.text = "" + text + orientationString;
		}
		else {
			label = createLabel(this.text + "," + text + orientationString);
			this.text = this.text + "," + text + orientationString;
		}
		
		this.add(label);
	}
}
