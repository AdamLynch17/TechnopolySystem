package src.Technopoly;

public class BoardSquare {
	protected int sqID;
	protected String name;
	protected String field;
	
	public BoardSquare(int newID, String newName, String newField) {
		sqID = newID;
		name = newName;
		field = newField;
	}

	public int getSqID() {
		return sqID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getField(){return field;}
	
}
