package view.mower;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.treeRep.trees.MowerTree;

public class Garden extends JPanel implements GenAlgObserver{
	
	private Controller ctr;
	private int height;
	private int numCols;
	private int numRows;
	private JPanel gardenGrid;
	
	private ArrayList<ArrayList<Boolean>> garden; //false: not cut; true: cut
	
	public Garden(Controller ctr, int width, int height){
		this.ctr = ctr;
		this.height = height;
		this.numCols = 8;
		this.numRows = 8;
		
		initGUI(width, height);
		ctr.addObserver(this);
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
		//gardenGrid.setPreferredSize(new Dimension(height, height));
		gardenGrid.setLayout(new GridLayout(numCols, numRows, 1, 1));
		for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.GREEN.darker().darker());
                cell.setPreferredSize(new Dimension((height/Math.max(numCols, numRows))-numCols, (height/Math.max(numCols, numRows))-numRows));
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

		remove(gardenGrid);
		gardenGrid = new JPanel();
		gardenGrid.setBackground(Color.BLACK);
		//gardenGrid.setPreferredSize(new Dimension(height, height));
		gardenGrid.setLayout(new GridLayout(numCols, numRows, 1, 1));
		for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                JPanel cell = new JPanel();
                if(garden.get(j).get(i)) { // Has been cut
                	cell.setBackground(Color.GRAY.darker().darker());
		        } else {
		        	cell.setBackground(Color.GREEN.darker().darker());
		        }
                cell.setPreferredSize(new Dimension((height/Math.max(numCols, numRows))-numCols, (height/Math.max(numCols, numRows))-numRows));
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
}
