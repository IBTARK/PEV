package model;

import java.util.ArrayList;

import model.representation.Representation;

public interface Selection {

	public ArrayList<Representation> select(ArrayList<Representation> population);
}
