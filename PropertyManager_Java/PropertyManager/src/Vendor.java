
public class Vendor {
	private int vendorID;
	private String vendorName;
	private String vendorType;
	private String vendorPhone;
	
	public Vendor(int newVendorID, String newVendorName, String newVendorType, String newVendorPhone) {
		this.vendorID = newVendorID;
		this.vendorName = newVendorName;
		this.vendorType = newVendorType;
		this.vendorPhone = newVendorPhone;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getVendorPhone() {
		return vendorPhone;
	}

	public void setVendorPhone(String vendorPhone) {
		this.vendorPhone = vendorPhone;
	}
	
	
	

}
