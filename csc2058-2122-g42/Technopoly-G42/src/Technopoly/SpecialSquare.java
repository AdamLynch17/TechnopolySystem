package src.Technopoly;

public class SpecialSquare extends BoardSquare{

	private String action;

	public SpecialSquare(int newID, String newName, String newField, String newAction) {
		super(newID, newName,newField);
		action = newAction;	
	}

	
	public String getAction() {
		return action;
	}

	
}
