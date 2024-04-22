package view.mower;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.treeRep.trees.MowerTree;

public class Garden extends JPanel implements GenAlgObserver{
	
	private Controller ctr;
	private int width;
	private int height;
	private int numCols;
	private int numRows;
	
	private ArrayList<ArrayList<Boolean>> garden; //false: not cut; true: cut
	
	public Garden(Controller ctr, int width, int height){
		this.ctr = ctr;
		this.width = width;
		this.height = height;
		this.numCols = 8;
		this.numRows = 8;
		
		garden = new ArrayList<ArrayList<Boolean>>();
		for(int i = 0; i < numCols; i++) {
			ArrayList<Boolean> newCol = new ArrayList<Boolean>();
			for(int j = 0; j < numRows; j++) {
				newCol.add(false);
			}
			garden.add(newCol);
		}
		
		initGUI(width, height);
		ctr.addObserver(this);
	}

	public Garden(Controller ctr, int width, int height, int numCols,int  numRows){
		this.ctr = ctr;
		this.width = width;
		this.height = height;
		this.numCols = numCols;
		this.numRows = numRows;
		
		garden = new ArrayList<ArrayList<Boolean>>();
		for(int i = 0; i < numCols; i++) {
			ArrayList<Boolean> newCol = new ArrayList<Boolean>();
			for(int j = 0; j < numRows; j++) {
				newCol.add(false);
			}
			garden.add(newCol);
		}
		
		initGUI(width, height);
		ctr.addObserver(this);
	}
	
	public void initGUI(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int cellWidth = width / numCols;
		int cellHeight = height / numRows;

		// Garden
		Color c;
				
		for(int i = 0; i < numRows; i++) {
		    for(int j = 0; j < numCols; j++) {
		        if(garden.get(j).get(i)) { // Has been cut
		            c = Color.GRAY;
		        } else {
		            c = Color.GREEN;
		        }
		        g.setColor(c);
		        g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
		    }
		}

		// Paint the rows
		c = Color.BLACK;
		g.setColor(c);
		for(int i = 0; i <= numRows; i++) {
		    g.drawLine(0, i * cellHeight - 1, width, i * cellHeight - 1);
		}

		// Paint the columns
		for(int i = 0; i <= numCols; i++) {
		    g.drawLine(i * cellWidth - 1, 0, i * cellWidth - 1, height);
		}
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
		
		repaint();
	}

	@Override
	public void onFirstGen() {
		
	}

	@Override
	public void remove() {
		ctr.removeObserver(this);
	}
	
	

}
