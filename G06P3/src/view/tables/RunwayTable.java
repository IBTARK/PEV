package view.tables;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import control.Controller;
import model.GenAlgObserver;
import model.chromosomes.AirportChromosome;
import model.chromosomes.Chromosome;
import model.genes.FlightGene;
import model.genes.Gene;

public class RunwayTable extends AbstractTableModel{
	
	private static final String[] COLUMN_NAMES = {"Vuelo", "Nombre", "TLA"};
	
	private ArrayList<Gene> geneList;
	
	HashMap<Integer, ArrayList<String>> flightsInfo;
	
	int track;
	
	public RunwayTable(int track, HashMap<Integer, ArrayList<String>> flightsInfo) {
		geneList = new ArrayList<Gene>();
		this.flightsInfo = flightsInfo;
		this.track = track;
	}

	public String getColumnName(int col) {
		return COLUMN_NAMES[col];
	}
	
	@Override
	public int getRowCount() {
		return geneList.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FlightGene fg = (FlightGene) geneList.get(rowIndex);
		
		if(columnIndex == 0) {
			return fg.getAllele(0);
		}
		else if(columnIndex == 1) {
			return flightsInfo.get(fg.getAllele(0)).get(0);
		}
		else {
			return fg.getTLA();
		}
	}
	
	public void setElems(Chromosome c) {
		geneList = new ArrayList<Gene>();
		
		for(FlightGene flight : ((AirportChromosome) c).getTracks().get(track - 1)) {
			geneList.add(flight);
		}
		
		//The data of the table has changed
		fireTableDataChanged();
	}

}
