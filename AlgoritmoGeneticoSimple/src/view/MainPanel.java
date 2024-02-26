package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.auxPanels.CenterPanel;
import view.auxPanels.LeftPanel;
import view.auxPanels.TopPanel;

public class MainPanel extends JPanel{
	
	private JButton menuButton;
	
	private int windowsWidth;
	private int windowsHeight;
	
	private boolean menuVisible;
	
	private TopPanel topPanel;
	private LeftPanel leftPanel;
	private CenterPanel centerPanel;
	
	public static final int LEFTPANELWIDTH = 270; //To adjust the size of the left panel
	
	public MainPanel(int windowsWidth, int windowsHeight) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.windowsWidth = windowsWidth;
		this.windowsHeight = windowsHeight;
		menuVisible = true;
		
		initGUI();
	}
	
	private void initGUI() {
		
		//Left panel and the menu button
		menuButton = new JButton();
		leftPanel = new LeftPanel(menuButton, LEFTPANELWIDTH, windowsHeight);
		
		add(leftPanel);
		
		JPanel restPanel = new JPanel(new BorderLayout());
		restPanel.setMinimumSize(new Dimension(windowsWidth - LEFTPANELWIDTH -13 , windowsHeight));
		
		topPanel = new TopPanel("Función 1", windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * 0.1));
		restPanel.add(topPanel, BorderLayout.PAGE_START);
		
		//Action listener of the button menu
		menuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuVisible = !menuVisible;
				leftPanel.setPanelsVisible(menuVisible);
				
				if(menuVisible) {
					leftPanel.setPreferredSize(new Dimension(LEFTPANELWIDTH - leftPanel.getBorderSize(), windowsHeight - leftPanel.getBorderSize()));
					restPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, windowsHeight));
					topPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * 0.1)));
					centerPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * 0.9)));
				}
				else {
					leftPanel.setPreferredSize(new Dimension(50 - leftPanel.getBorderSize(), windowsHeight - leftPanel.getBorderSize()));
					restPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, windowsHeight));
					topPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, (int) Math.round(windowsHeight * 0.1)));
					centerPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, (int) Math.round(windowsHeight * 0.9)));
				}
			}
		});
		
		//Center panel
		centerPanel = new CenterPanel(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * 0.9) - 13);
		centerPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * 0.9)));
		restPanel.add(centerPanel, BorderLayout.CENTER);
		
		restPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, windowsHeight));
		add(restPanel);
	}
}
