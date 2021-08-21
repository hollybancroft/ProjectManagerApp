// Holly Bancroft
// Property Manager Project
// 8/1/2021
// Main Menu

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		int userInput = 0;
		// Main Menu
		while(userInput != 5) {
		System.out.println("***Welcome to the Property Manager. Please select a menu item by entering the number***");
	
		System.out.println("1. Properties \n2. Tenants \n3. Maintenance \n4. Vendors \n5. Exit");
		
		userInput = input.nextInt();
		
		if(userInput == 1) {
			//Property Sub Menu
			System.out.println("**Properties** \n1. View properties \n2. Create new property \n3. Delete properties \n4. Update properties ");
			
			int userPropertyInput = input.nextInt();
			// Call method selectAllProperties which displays all the properties in the database
			
			if(userPropertyInput == 1) {
				PropertyDAO.selectAllProperties();
				
			}
			else if(userPropertyInput == 2) { // Create a new property
				
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy"); // Will need to convert dates to SQL dates
				// Collect the information from the user to create a new property
				System.out.println("Enter a Property ID: ");
				int tempID = input.nextInt();
				System.out.println("Enter an address: ");
				input.nextLine();
				String tempAddress = input.nextLine();
				System.out.println("Enter a city: ");
				String tempCity = input.nextLine();
				System.out.println("Enter a state: ");
				String tempState = input.nextLine();
				System.out.println("Enter the zip code: ");
				int tempZip = input.nextInt();
				System.out.println("Enter the purchase date (format m/d/yyyy): ");
				input.nextLine();
				String unformattedDate = input.nextLine();
				LocalDate tempPurchDate = LocalDate.parse(unformattedDate, dateFormat);
				System.out.println("Enter the mortgage amount: ");
				double tempMortgage = input.nextDouble();
				System.out.println("Enter the next mortgage monthly due date (format m/d/yyyy): ");
				input.nextLine();
				String unformattedDueDate = input.nextLine();
				LocalDate tempDueDate = LocalDate.parse(unformattedDueDate, dateFormat);
				System.out.println("Enter the unit type ");
				String tempUnitType = input.nextLine();
				System.out.println("Enter a maintenance ID, or 0 if there hasn't been any maintenance done: ");
				int tempMainID = input.nextInt();
				System.out.println("Enter true or false regarding if the unit is tenant occupied: ");
				boolean tempIsOccupied = input.nextBoolean();
				// Create property object
				Property property = new Property(tempID, tempAddress, tempCity, tempState, tempZip, tempPurchDate, tempMortgage, tempDueDate, tempUnitType, tempMainID, tempIsOccupied);				
				
				boolean processed = PropertyDAO.insertProperty(property); // Call insertProperty method to add the property to the database.
				// Message to indicate if the property was succesfully added or not.
				if(processed) {
					System.out.println("The property was succesfully added.");
				}
				else {
					System.out.println("There was an error processing the property information.");
				}
			}
			
			else if(userPropertyInput == 3) { // Option to delete a property from the database
				System.out.println("Enter the property ID of the address you would like to delete: ");
				input.nextLine();
				String deleteProperty = input.nextLine();
				
				boolean processed = PropertyDAO.deleteProperty(deleteProperty);
				if(processed) {
					System.out.println("The property was succesfully deleted.");
					System.out.println();
				}
				else {
					System.out.println("There was an error deleting the property.");
					System.out.println();
					}
				}
			
			else if(userPropertyInput == 4) { // Option to update information on an existing property
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
				System.out.println("Enter the Property ID of the property you would like to update: "); 
				int tempID = input.nextInt();
				System.out.println("Enter an updated address: ");
				input.nextLine();
				String tempAddress = input.nextLine();
				System.out.println("Enter an updated city: ");
				String tempCity = input.nextLine();
				System.out.println("Enter an updated state: ");
				String tempState = input.nextLine();
				System.out.println("Enter the updated zip code: ");
				int tempZip = input.nextInt();
				System.out.println("Enter the updated purchase date (format m/d/yyyy): ");
				input.nextLine();
				String unformattedDate = input.nextLine();
				LocalDate tempPurchDate = LocalDate.parse(unformattedDate, dateFormat);
				System.out.println("Enter the updated mortgage amount: ");
				double tempMortgage = input.nextDouble();
				System.out.println("Enter the next mortgage monthly due date (format m/d/yyyy): ");
				input.nextLine();
				String unformattedDueDate = input.nextLine();
				LocalDate tempDueDate = LocalDate.parse(unformattedDueDate, dateFormat);
				System.out.println("Enter the updated unit type ");
				String tempUnitType = input.nextLine();
				System.out.println("Enter a maintenance ID, or 0 if there hasn't been any maintenance done: ");
				int tempMainID = input.nextInt();
				System.out.println("Enter true or false regarding if the unit is tenant occupied: ");
				boolean tempIsOccupied = input.nextBoolean();
				
				Property property = new Property(tempID, tempAddress, tempCity, tempState, tempZip, tempPurchDate, tempMortgage, tempDueDate, tempUnitType, tempMainID, tempIsOccupied);				
				
				boolean processed = PropertyDAO.updateProperty(property);
				if(processed) {
					System.out.println("The property was succesfully updated.");
					System.out.println();
				}
				else {
					System.out.println("There was an error updating the property.");
					System.out.println();
				}
			}
		}
		if(userInput == 2) {
			// Tenant Sub Menu
			System.out.println("**Tenants**\n1. Look up tenant\n2. Add new tenant\n3. Delete tenant\n4. Update tenant ");
			int userTenantInput = input.nextInt();
			
			if(userTenantInput == 1) { // Look up a tenant menu option
				System.out.println("Please enter the tenant's ID: ");
				int userTenantIDInput = input.nextInt();
				
				TenantDAO.selectTenant(userTenantIDInput);
			}
			
			else if(userTenantInput == 2) { // Add a tenant menu option
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
				
				System.out.println("Enter a tenant ID: ");
				int tempID = input.nextInt();
				System.out.println("Enter tenant's first name (less than 30 characters): ");
				input.nextLine();
				String tempFN = input.nextLine();
				System.out.println("Enter tenant's last name (less than 30 characters): ");
				String tempLN = input.nextLine();
				System.out.println("Enter the property ID of the property the tenant is occupying: ");
				int tempPropertyID = input.nextInt();
				System.out.println("Enter a phone number for the tenant: ");
				input.nextLine();
				String tempPhone = input.nextLine();
				System.out.println("Enter the due date for the monthly rent payment (format m/d/yyyy): ");
				String unformattedDate = input.nextLine();
				LocalDate tempRentDueDate = LocalDate.parse(unformattedDate, dateFormat);
				System.out.println("Enter the rent amount: ");
				double tempRentAmount = input.nextDouble();
				System.out.println("Is there a missing rent payment from this tenant? Enter true or false ");
				boolean tempMissingRent = input.nextBoolean();
				System.out.println("Is this tenant actively renting a property? Enter true or false ");
				boolean tempActive = input.nextBoolean();
				
				Tenant tenant = new Tenant(tempID, tempFN, tempLN, tempPropertyID, tempPhone, tempRentDueDate, tempRentAmount, tempMissingRent, tempActive);
				
				boolean processed = TenantDAO.insertTenant(tenant);
				if(processed) {
					System.out.println("The new tenant was succesfully added.");
					System.out.println();
				}
				else {
					System.out.println("There was an error adding the tenant.");
					System.out.println();
				}
			}
			else if(userTenantInput == 3) { // Delete a tenant menu option
				System.out.println("Enter the ID of tenant you would like to delete: ");
				input.nextLine();
				String tenantID = input.nextLine();
				boolean processed = TenantDAO.deleteTenant(tenantID);
				if(processed) {
					System.out.println("The tenant was succesfully deleted.");
					System.out.println();
				}
				else {
					System.out.println("There was an error deleting the tenant.");
					System.out.println();
					}
			}
			else if(userTenantInput == 4) { // Update the tenant menu option
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
				
				System.out.println("Enter the tenant ID of the tenant you would like to update: ");
				int tempID = input.nextInt();
				System.out.println("Enter the updated tenant's first name (less than 30 characters): ");
				input.nextLine();
				String tempFN = input.nextLine();
				System.out.println("Enter the updated tenant's last name (less than 30 characters): ");
				String tempLN = input.nextLine();
				System.out.println("Enter the updated property ID of the property the tenant is occupying: ");
				int tempPropertyID = input.nextInt();
				System.out.println("Enter an updated phone number for the tenant: ");
				input.nextLine();
				String tempPhone = input.nextLine();
				System.out.println("Enter the updated due date for the monthly rent payment (format m/d/yyyy): ");
				String unformattedDate = input.nextLine();
				LocalDate tempRentDueDate = LocalDate.parse(unformattedDate, dateFormat);
				System.out.println("Enter the updated rent amount: ");
				double tempRentAmount = input.nextDouble();
				System.out.println("Is there a missing rent payment from this tenant? Enter true or false ");
				boolean tempMissingRent = input.nextBoolean();
				System.out.println("Is this tenant actively renting a property? Enter true or false ");
				boolean tempActive = input.nextBoolean();
				
				Tenant tenant = new Tenant(tempID, tempFN, tempLN, tempPropertyID, tempPhone, tempRentDueDate, tempRentAmount, tempMissingRent, tempActive);
				
				boolean processed = TenantDAO.updateTenant(tenant); // call the method that is under the TenantDAO class
				if(processed) {
					System.out.println("The new tenant was succesfully updated.");
					System.out.println();
				}
				else {
					System.out.println("There was an error updating the tenant.");
					System.out.println();
				}
			}
		}
		if(userInput == 3) {
			System.out.println("**Maintenance** \n1. View maintenace performed \n2. Add new maintenace performed \n3. Delete maintenance performed \n4. Update maintenance performed");
			int userMaintInput = input.nextInt();
			
			if(userMaintInput == 1) {
				System.out.println("Please enter the maintenance ID: ");
				int userMaintIDInput = input.nextInt();
				
				MaintenanceDAO.selectMaintPerformed(userMaintIDInput);
			}
			else if(userMaintInput == 2) {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
				
				System.out.println("Enter a maintenace ID: ");
				int tempID = input.nextInt();
				System.out.println("Enter a short description of the maintenance performed (less than 60 characters): ");
				input.nextLine();
				String tempMaintPerformed = input.nextLine();
				System.out.println("Enter maintenance date (format m/d/yyyy: ");
				String unformattedDate = input.nextLine();
				LocalDate tempMaintDate = LocalDate.parse(unformattedDate, dateFormat);
				System.out.println("Enter the maintenance cost: ");
				double tempMaintCost = input.nextDouble();
				System.out.println("Enter a vendor ID: ");
				input.nextLine();
				int tempVendorID = input.nextInt();
				
				Maintenance maintenance = new Maintenance(tempID, tempMaintPerformed, tempMaintDate, tempMaintCost, tempVendorID);
				
				boolean processed = MaintenanceDAO.insertMaintenance(maintenance);
				if(processed) {
					System.out.println("The maintenance performed was succesfully added.");
					System.out.println();
				}
				else {
					System.out.println("There was an error adding the maintenance performed.");
					System.out.println();
				}
			}
			else if (userMaintInput == 3) {
				System.out.println("Enter the ID of maintenance performed you would like to delete: ");
				input.nextLine();
				String maintID = input.nextLine();
				boolean processed = MaintenanceDAO.deleteMaintenance(maintID);
				if(processed) {
					System.out.println("The maintenance performed was succesfully deleted.");
					System.out.println();
				}
				else {
					System.out.println("There was an error deleting the maintenance performed.");
					System.out.println();
					}
			}
			else if(userMaintInput == 4) { // Update the maintenance menu option
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
				
				System.out.println("Enter the maintenance ID you would like to update: ");
				int tempID = input.nextInt();
				System.out.println("Enter the updated maintenance performed description: ");
				input.nextLine();
				String tempMaintPerformed = input.nextLine();
				System.out.println("Enter the updated maintenance date: ");
				String unformattedDate = input.nextLine();
				LocalDate tempMaintDate = LocalDate.parse(unformattedDate, dateFormat);
				System.out.println("Enter the updated cost of the maintenance performed: ");
				double tempMaintAmount = input.nextDouble();
				System.out.println("Enter an updated vendor ID of the vendor who performed the maintenance: ");
				int tempVendorID = input.nextInt();
				
				Maintenance maintenance = new Maintenance(tempID, tempMaintPerformed, tempMaintDate, tempMaintAmount, tempVendorID);
				
				boolean processed = MaintenanceDAO.updateMaintenance(maintenance); // call the method that is under the TenantDAO class
				if(processed) {
					System.out.println("The maintenance performed was succesfully updated.");
					System.out.println();
				}
				else {
					System.out.println("There was an error updating the maintenance performed.");
					System.out.println();
				}
			}
			}
		if(userInput == 4) {
			System.out.println("**Vendors** \n1. View vendors \n2. Add new vendor \n3. Delete vendor \n4. Update vendor information \n5. List all vendors with maintenance performed");
			int userVendorInput = input.nextInt();
			
			if(userVendorInput == 1) {
				VendorDAO.selectAllVendors();
			}
			
			if(userVendorInput == 2) {
				System.out.println("Enter a vendor ID: ");
				int newVendorID = input.nextInt();
				
				System.out.println("Enter the vendor's name (less than 60 characters): ");
				input.nextLine();
				String newVendorName = input.nextLine();
				
				System.out.println("Enter the vendor type (less than 60 characters): ");
				String newVendorType = input.nextLine();
				
				System.out.println("Enter the vendor's phone number: ");
				String newVendorPhone = input.nextLine();
				
				Vendor vendor = new Vendor(newVendorID, newVendorName, newVendorType, newVendorPhone);
				
				boolean processed = VendorDAO.insertVendor(vendor);
				
				if(processed){
					System.out.println("The vendor was succesfully added.");
					System.out.println();
				}
				else {
					System.out.println("There was problem adding the vendor.");
					System.out.println();
				}
			}
			if(userVendorInput == 3) {
				System.out.println("Enter the vendor ID you would like to delete: ");
				input.nextLine();
				String vendorID = input.nextLine();
				boolean processed = VendorDAO.deleteVendor(vendorID);
				if(processed) {
					System.out.println("The vendor was succesfully deleted.");
					System.out.println();
				}
				else {
					System.out.println("There was an error deleting the vendor.");
					System.out.println();
					}
			}
			if(userVendorInput == 4) {
				
				System.out.println("Enter the vendor ID you would like to update: ");
				int tempID = input.nextInt();
				System.out.println("Enter the vendor's updated name (less than 60 characters): ");
				input.nextLine();
				String tempName = input.nextLine();
				System.out.println("Enter the updated vendor type (less than 60 characters): ");
				String tempType = input.nextLine();
				System.out.println("Enter the updated vendor phone number: ");
				String tempPhone = input.nextLine();
				
				Vendor vendor = new Vendor(tempID, tempName, tempType, tempPhone);
				
				boolean processed = VendorDAO.updateVendor(vendor); // call the method that is under the VendorDAO class
				if(processed) {
					System.out.println("The vendor was succesfully updated.");
					System.out.println();
				}
				else {
					System.out.println("There was an error updating the vendor.");
					System.out.println();
				}
			}
			
			if(userVendorInput == 5) {
				VendorDAO.showVendorMaintenance();
			}
		}
			
		}
		
		System.out.println("Thank you for using the Property Manager!");
		input.close();
	}

	
	 
}
