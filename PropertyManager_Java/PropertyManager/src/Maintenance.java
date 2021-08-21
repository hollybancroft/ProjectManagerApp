import java.time.LocalDate;

public class Maintenance {
private int maintenanceID;
private String maintenancePerformed;
private LocalDate maintenanceDate;
private double maintenanceAmount;
private int vendorID;

public Maintenance(int newMaintenanceID, String newMaintenancePerformed, LocalDate newMaintenanceDate, double newMaintenanceAmount, int newVendorID) {
	this.maintenanceID = newMaintenanceID;
	this.maintenancePerformed = newMaintenancePerformed;
	this.maintenanceDate = newMaintenanceDate;
	this.maintenanceAmount = newMaintenanceAmount;
	this.vendorID = newVendorID;
}

public int getMaintenanceID() {
	return maintenanceID;
}
public void setMaintenanceID(int maintenanceID) {
	this.maintenanceID = maintenanceID;
}
public String getMaintenancePerformed() {
	return maintenancePerformed;
}
public void setMaintenancePerformed(String maintenancePerformed) {
	this.maintenancePerformed = maintenancePerformed;
}
public LocalDate getMaintenanceDate() {
	return maintenanceDate;
}
public void setMaintenanceDate(LocalDate maintenanceDate) {
	this.maintenanceDate = maintenanceDate;
}
public double getMaintenanceAmount() {
	return maintenanceAmount;
}
public void setMaintenanceAmount(double maintenanceAmount) {
	this.maintenanceAmount = maintenanceAmount;
}
public int getVendorID() {
	return vendorID;
}
public void setVendorID(int vendorID) {
	this.vendorID = vendorID;
}
}
