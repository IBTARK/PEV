package view.mower;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.treeRep.trees.MowerTree;
import resources.Pair;

public class Garden extends JPanel implements GenAlgObserver{
	
	private Controller ctr;
	private int height;
	private int numCols;
	private int numRows;
	private JPanel gardenGrid;
	
	private ArrayList<ArrayList<Boolean>> garden; //false: not cut; true: cut
	private ArrayList<Pair<Integer, Integer>> path; //path followed by the mower
	private ArrayList<Integer> orientationPath; //orientation path followed by the mower
	
	public Garden(Controller ctr, int width, int height){ //only used to paint the garden the first time
		this.ctr = ctr;
		this.height = height;
		this.numCols = 8;
		this.numRows = 8;
		
		initGUI(width, height);
	}

	public Garden(Controller ctr, int width, int height, int numCols,int  numRows){
		this.ctr = ctr;
		this.height = height;
		this.numCols = numCols;
		this.numRows = numRows;
		
		initGUI(width, height);
		ctr.addObserver(this);
	}
	
	public void initGUI(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		gardenGrid = new JPanel();
		gardenGrid.setBackground(Color.BLACK);
		gardenGrid.setLayout(new GridLayout(numCols, numRows, 1, 1));
		for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.GREEN.darker().darker());
                cell.setPreferredSize(new Dimension((height/Math.max(numCols, numRows))-5, (height/Math.max(numCols, numRows))-5));
                gardenGrid.add(cell);
            }
        }
		add(gardenGrid);
	}
	
	//*****************************************************************************
//Observer interface 
	
	@Override
	public void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration) {
	
	}

	@Override
	public void onRegister() {
		
	}

	@Override
	public void onAlgFinished(Representation c) {
		garden = ((MowerTree) c).getGarden();
		path = ((MowerTree) c).getPath();
		orientationPath = ((MowerTree) c).getOrientationPath();

		remove(gardenGrid);
		gardenGrid = new JPanel();
		gardenGrid.setBackground(Color.BLACK);
		gardenGrid.setLayout(new GridLayout(numCols, numRows, 1, 1));
		for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                JPanel cell = new JPanel();
                
                if(garden.get(j).get(i)) { // Has been cut
                	cell.add(createLabel(orientationPath.get(i)));
                	cell.setBackground(Color.GRAY.darker().darker());
		        } else {
		        	cell.setBackground(Color.GREEN.darker().darker());
		        }
                cell.setPreferredSize(new Dimension((height/Math.max(numCols, numRows))-5, (height/Math.max(numCols, numRows))-5));
                gardenGrid.add(cell);
            }
        }
		add(gardenGrid);
	}

	@Override
	public void onFirstGen() {
		
	}

	@Override
	public void remove() {
		ctr.removeObserver(this);
	}
	
	/**
	 * creates a label
	 * @param text text that will be displayed in the label
	 * @return the label
	 */
	private JLabel createLabel(int orientation) {
		String text = "";
		if(orientation == 0) {
			text = "^";
		}
		else if(orientation == 1) {
			text = "<";
		}
		else if(orientation == 2) {
			text = "v";
		}
		else if(orientation == 3) {
			text = "v";
		}
		JLabel label = new JLabel(text);
		label.setFont(new Font(Font.SERIF, Font.BOLD, 14));
		//label.setForeground(Color.WHITE);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		return label;
	}
}
