package view.mower;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.treeRep.trees.MowerTree;
import resources.Pair;
import view.LabelContainer;

public class Garden extends JPanel implements GenAlgObserver{
	
	private Controller ctr;
	private int height;
	private int numCols;
	private int numRows;
	private JPanel gardenGrid;
	
	private ArrayList<ArrayList<Boolean>> garden; //false: not cut; true: cut
	private ArrayList<Pair<Integer, Integer>> path; //path followed by the mower
	private ArrayList<Integer> orientationPath; //orientation path followed by the mower
	private ArrayList<ArrayList<JPanel>> celdas;
	
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
		
		celdas = new ArrayList<ArrayList<JPanel>>();
		
		for (int i = 0; i < numCols; i++) {
			ArrayList<JPanel> row = new ArrayList<JPanel>();
            for (int j = 0; j < numRows; j++) {
                LabelContainer cell = new LabelContainer();
                cell.setBackground(Color.GREEN.darker().darker());
                cell.setPreferredSize(new Dimension((height/Math.max(numCols, numRows))-5, (height/Math.max(numCols, numRows))-5));
                gardenGrid.add(cell);
                row.add(cell);
            }
            celdas.add(row);
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

		//changes
		for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                if(garden.get(i).get(j)) { // Has been cut
                	celdas.get(i).get(j).setBackground(Color.GRAY.darker().darker());
                	Pair<Integer, Integer> par = new Pair<Integer, Integer>(i, j);
                	for(int k = 0; k < path.size(); k++) {
                		if(path.get(k).getFirst()==i && path.get(k).getSecond()==j) {
                			((LabelContainer) celdas.get(i).get(j)).addTextLabel((k), orientationPath.get(k));
                		}
                	}
                }
            }
        }
		
		printGarden();
	}
	
	/*private void printPath() {
		
		for(int k = 0; k < path.size(); k++) {
			celdas.get(path.get(k).getFirst()).get(path.get(k).getSecond()).setBackground(Color.GRAY.darker().darker());;
    		celdas.get(path.get(k).getFirst()).get(path.get(k).getSecond()).add(createLabel("O"));
    		printGarden();
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		celdas.get(path.get(k).getFirst()).get(path.get(k).getSecond()).add(createLabel(""));
		}
	    
		
	}*/
	
	private void printGarden() {
		remove(gardenGrid);
		gardenGrid = new JPanel();
		gardenGrid.setBackground(Color.BLACK);
		gardenGrid.setLayout(new GridLayout(numRows, numCols, 1, 1));
		
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
                gardenGrid.add(celdas.get(i).get(j));
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
