import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VendorDAO {

	 public static Connection getConnection() throws Exception { 
		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/property_manager","root", "");
	  
	  return con;
	  }
	 
	 public static void selectAllVendors() throws Exception{
		 Connection con = getConnection();
		  
		  // create JDBC statement object 
		  Statement st = con.createStatement();
		  
		  String query = "SELECT * FROM vendor";
		  
		  ResultSet rs = st.executeQuery(query);
		  
		  // process the ResultSet object 
		  boolean flag = false; 
		  while (rs.next()) {
		  flag = true;
		  
		
		  System.out.println("Vendor ID: " + rs.getInt(1) + "\n Vendor name: " + rs.getString(2) + "\n Vendor Type: " +
		  rs.getString(3) + "\n Vendor Phone Number: " + rs.getString(4) + "\n" ); 
		  
		  }
		 
		  
		  if (flag == true) { 
			  System.out.println("\nRecords retrieved and displayed\n");
		  
		  } else { 
			  System.out.println("\nRecord not found\n"); 
		  }
		  
		  // close JDBC objects
		  rs.close(); 
		  st.close();
		  con.close();
	 }
	 
	 
	  public static boolean insertVendor(Vendor vendor) throws Exception{
		  	Connection connection = getConnection();
			boolean result = false; 
			String sqlStatement = new String("INSERT INTO vendor VALUES (?, ?, ?, ?)"); 
			PreparedStatement prepSqlStatement = null;
			try {
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setInt(1, vendor.getVendorID());
				prepSqlStatement.setString(2, vendor.getVendorName());
				prepSqlStatement.setString(3, vendor.getVendorType());
				prepSqlStatement.setString(4, vendor.getVendorPhone());
			
				int rowCount = prepSqlStatement.executeUpdate();
				if (rowCount != 1){
					result =  false; 
				}
				else {
						result = true;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				result =  false;
			}	
			return result; 
		}
	  
	  public static boolean deleteVendor(String vendorID) throws Exception {
		  boolean result = false; 
			String sqlStatement = new String("DELETE FROM vendor WHERE vendor_id = ?"); 
			PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, vendorID);
				int rowCount = prepSqlStatement.executeUpdate();
				if (rowCount != 1){
					result = false; 
				} 
				else {
					result = true;
				}
			}
			catch (SQLException ex){
				ex.printStackTrace();
				result = false;
			}
			return result;
	  }
	  
	  public static boolean updateVendor(Vendor vendor) throws Exception{
		  boolean result = false;
		  String sqlStatement = new String("UPDATE vendor SET vendor_name = ?, vendor_type = ?, phone = ? WHERE vendor_id = ?");
		  
		  PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, vendor.getVendorName());
				prepSqlStatement.setString(2, vendor.getVendorType());
				prepSqlStatement.setString(3, vendor.getVendorPhone());
				prepSqlStatement.setInt(4, vendor.getVendorID());
				
				int rowCount = prepSqlStatement.executeUpdate();
				if (rowCount != 1){
					result = false; 
				}
				else {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				result = false;
			}	
			return result;
		  
	  }
	  
	  public static void showVendorMaintenance() throws Exception {
		  Connection con = getConnection();
		  
		  Statement st = con.createStatement();
		  String query = "SELECT vendor.vendor_name, maintenance.maintenance_performed, maintenance.maintenance_date FROM vendor INNER JOIN maintenance ON vendor.vendor_id = maintenance.vendor_id AND vendor.vendor_id != 0";
		  		  
		  ResultSet rs = st.executeQuery(query);
		  
		  // process the ResultSet object 
		  boolean flag = false; 
		  while (rs.next()) {
		  flag = true;
		  
		
		  System.out.println("Vendor name: " + rs.getString(1) + "\nMaintenance performed: " + rs.getString(2) + "\nDate performed: " +
		  rs.getDate(3) + "\n" ); 
		  
		  }
		  
		  if (flag == true) { 
			  System.out.println("\nRecords retrieved and displayed\n");
		  
		  } else { 
			  System.out.println("\nRecord not found\n"); 
		  }
		  
		  // close JDBC objects
		  rs.close(); 
		  st.close();
		  con.close();
	  }
}
