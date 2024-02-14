package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import view.auxPanels.LeftPanel;
import view.auxPanels.TopPanel;

public class MainPanel extends JPanel{
	
	private int windowsWidth;
	private int windowsHeight;
	
	private TopPanel topPanel;
	private LeftPanel leftPanel;
	
	public static final double LEFTPCTG = 0.3; //To adjust the size of the left panel
	public static final double RIGHTPCTG = 0.69; //To adjust the size of the right panel
	
	public MainPanel(int windowsWidth, int windowsHeight) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.windowsWidth = windowsWidth;
		this.windowsHeight = windowsHeight;
		
		initGUI();
	}
	
	private void initGUI() {
		leftPanel = new LeftPanel((int) Math.round(windowsWidth * LEFTPCTG), windowsHeight);
		add(leftPanel);
		
		JPanel restPanel = new JPanel(new BorderLayout());
		restPanel.setMinimumSize(new Dimension((int) Math.round(windowsWidth * RIGHTPCTG), windowsHeight));
		
		topPanel = new TopPanel("Función 1", (int)Math.round(windowsWidth * RIGHTPCTG)- 3, (int) Math.round(windowsHeight * 0.1));
		restPanel.add(topPanel, BorderLayout.PAGE_START);
		
		add(restPanel);
	}
}
