package view.mower;

import java.util.ArrayList;
import javax.swing.JPanel;
import model.GenAlgObserver;
import control.Controller;
import model.representation.Representation;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

import org.math.plot.Plot2DPanel;

public class Garden extends JPanel implements GenAlgObserver{
	
	private Controller ctr;
	private int width;
	private int height;
	Graphics g;

	public Garden(Controller ctr, int width, int height){
		this.ctr = ctr;
		this.width = width;
		this.height = height;
		initGUI(width, height);
		ctr.addObserver(this);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
      this.g = g;
    }
	
	public void initGUI(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void setPreferredSizeGraph(int width, int height) {
		setPreferredSize(new Dimension(width, height));
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
		super.paintComponent(g);
		//obtener el garden
		ArrayList<ArrayList<Boolean>> garden;

        for (int fila = 0; fila < height; fila++) {
            for (int columna = 0; columna < width; columna++) {
            	/*
				if(garden.get(fila).get(columna) == false){
					g.setColor(Color.GRAY);
	                g.fillRect(columna * 50, fila * 50, 50, 50);
	                g.setColor(Color.BLACK);
	                g.drawRect(columna * 50, fila * 50, 50, 50);
				}
				else if(garden.get(fila).get(columna) == true) {
					g.setColor(Color.GREEN);
	                g.fillRect(columna * 50, fila * 50, 50, 50);
	                g.setColor(Color.BLACK);
	                g.drawRect(columna * 50, fila * 50, 50, 50);
				} 
				*/
            }
        }
	}

	@Override
	public void onFirstGen() {
		
	}

	@Override
	public void remove() {
		ctr.removeObserver(this);
	}
	
	

}
