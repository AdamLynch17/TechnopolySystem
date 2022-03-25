package src.Technopoly;

public class PropertySq extends BoardSquare{
	private final int buyValue;
	private int rentValue[];
	private BuildingInfo details;
	private int ownerID;
	private boolean fullSetOwned;

	public PropertySq(int newID, String newName, String newField, int newBuyValue,int newRentValue[], BuildingInfo newDetails) {
		super(newID, newName, newField);
		buyValue = newBuyValue;
		rentValue = newRentValue;
		details = newDetails;
		ownerID = -1;
		fullSetOwned = false;
	}

	// setters

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public void setNumHQs(int setHQ){
		details.setNumHq(setHQ);
	}

	public void setNumOffices(int setOffice){
		details.setNumOffice(setOffice);
	}

	public void setFullSetOwned(boolean owned){
		this.fullSetOwned = owned;
	}


	// getters

	public int getOwnerID() {
		return ownerID;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public int getRentValue() {
		int rentNum = 0;
		if (fullSetOwned){
			rentNum = getNumBuildings() + 1;
		}
		return rentValue[rentNum];
	}

	public BuildingInfo getDetails() {
		return details;
	}

	public int getNumHQs(){
		return details.getNumHq();
	}

	public int getNumOffices(){
		return details.getNumOffice();
	}

	public boolean getFullSetOwned(){
		return fullSetOwned;
	}

	// created methods

	public int getNumBuildings() {
		int numHqs = details.getNumHq();
		int numOffices = details.getNumOffice();

		return numHqs*5 + numOffices;
	}

	public int calcOfficePrice() {
		return (int) Math.round(((getBuyValue()*1.25)/10.0))*10;
	}
	public int calcHQPrice() {
		return (int) Math.round(((getBuyValue()*1.5)/10.0))*10;
	}
}
