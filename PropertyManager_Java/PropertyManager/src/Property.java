import java.time.LocalDate;
public class Property {
	
private int propertyID;
private String address;
private String city;
private String state;
private int zip;
private LocalDate purchaseDate;
private double mortgageAmt;
private LocalDate mortgageDue;
private String unitType;
private int maintenanceID;
private boolean occupied;

public Property(int newPropertyID, String newAddress, String newCity, String newState, int newZip, 
		LocalDate newPurchaseDate, double newMortgageAmt, LocalDate newMortgageDue, String newUnitType, 
		int newMaintenanceID, boolean newOccupied) {
	
	this.propertyID = newPropertyID;
	this.address = newAddress;
	this.city = newCity;
	this.state = newState;
	this.zip = newZip;
	this.purchaseDate = newPurchaseDate;
	this.mortgageAmt = newMortgageAmt;
	this.mortgageDue = newMortgageDue;
	this.unitType = newUnitType;
	this.maintenanceID = newMaintenanceID;
	this.occupied = newOccupied;
}

public int getPropertyID() {
	return propertyID;
}

public void setPropertyID(int propertyID) {
	this.propertyID = propertyID;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public int getZip() {
	return zip;
}

public void setZip(int zip) {
	this.zip = zip;
}

public LocalDate getPurchaseDate() {
	return purchaseDate;
}

public void setPurchaseDate(LocalDate purchaseDate) {
	this.purchaseDate = purchaseDate;
}

public double getMortgageAmt() {
	return mortgageAmt;
}

public void setMortgageAmt(float mortgageAmt) {
	this.mortgageAmt = mortgageAmt;
}

public LocalDate getMortgageDue() {
	return mortgageDue;
}

public void setMortgageDue(LocalDate mortgageDue) {
	this.mortgageDue = mortgageDue;
}

public String getUnitType() {
	return unitType;
}

public void setUnitType(String unitType) {
	this.unitType = unitType;
}

public int getMaintenanceID() {
	return maintenanceID;
}

public void setMaintenanceID(int maintenanceID) {
	this.maintenanceID = maintenanceID;
}

public boolean isOccupied() {
	return occupied;
}

public void setOccupied(boolean occupied) {
	this.occupied = occupied;
}


}
