package src.Technopoly;

public class BuildingInfo {
	private int numHq;
	private int numOffice;

	// constructor
	public BuildingInfo() {
		numHq=0;
		numOffice = 0;
	}
	

	// getters and setters
	public int getNumHq() {
		return this.numHq;
	}
	public void setNumHq(int numHq1) {
		numHq = numHq1;
	}
	public int getNumOffice() {
		return numOffice;
	}
	public void setNumOffice(int numOffice1) {
		numOffice = numOffice1;
	}
}
