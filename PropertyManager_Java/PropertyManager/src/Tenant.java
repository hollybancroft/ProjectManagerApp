import java.time.LocalDate;

public class Tenant {
	
	private int tenantID;
	private String firstName;
	private String lastName;
	private int propertyID; // foreign key. Before deleting a tenant, there should be a query determining if there is a relationship
	private String phone;
	private LocalDate rentDue;
	private double rentAmount;
	private boolean missingRent;
	private boolean active;
	
	public Tenant(int newTenantID, String newFirstName, String newLastName, int newPropertyID, String newPhone, LocalDate newRentDue, double newRentAmount, boolean newMissingRent, boolean newIsActive)
	{
		this.tenantID = newTenantID;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.propertyID = newPropertyID;
		this.phone = newPhone;
		this.rentDue = newRentDue;
		this.rentAmount = newRentAmount;
		this.missingRent = newMissingRent;
		this.active = newIsActive;
		
	}
	public int getTenantID() {
		return tenantID;
	}
	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getRentDue() {
		return rentDue;
	}
	public void setRentDue(LocalDate rentDue) {
		this.rentDue = rentDue;
	}
	public double getRentAmount() {
		return rentAmount;
	}
	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}
	public boolean isMissingRent() {
		return missingRent;
	}
	public void setMissingRent(boolean missingRent) {
		this.missingRent = missingRent;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	

}
