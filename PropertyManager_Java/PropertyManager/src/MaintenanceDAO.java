import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaintenanceDAO {

	 public static Connection getConnection() throws Exception { 
		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/property_manager","root", "");
	  
	  return con;
	  }
	 
	 public static void selectMaintPerformed(int maintID) throws Exception{
		  Connection con = getConnection();
	  
		  String sqlStatement = new String("SELECT * FROM maintenance WHERE maintenance_id = ?"); 
		  PreparedStatement prepSqlStatement = null;
			ResultSet rsFindMaintenance = null;
			
			try{
				prepSqlStatement = con.prepareStatement(sqlStatement);
				prepSqlStatement.setInt(1, maintID);
				rsFindMaintenance = prepSqlStatement.executeQuery();
				if (rsFindMaintenance == null) { //the maintenance record wasn't found. TO-DO figure out why this isn't working
					System.out.println("No maintenance records found");
				}
				
				while (rsFindMaintenance.next()){					
					System.out.println("Maintenance ID: " + rsFindMaintenance.getInt(1) + "\nMaintenance Performed: " + rsFindMaintenance.getString(2) + "\nMaintenance Date: " + rsFindMaintenance.getDate(3) +
					"\nMaintenance Cost: " + rsFindMaintenance.getDouble(4) + "\nVendor ID: " + rsFindMaintenance.getInt(5));
			}
				
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
	  
	  }
	 public static boolean insertMaintenance(Maintenance maintenance) throws Exception{
		  	Connection connection = getConnection();
			boolean result = false; 
			String sqlStatement = new String("INSERT INTO maintenance VALUES (?, ?, ?, ?, ?)"); 
			PreparedStatement prepSqlStatement = null;
			try {
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setInt(1, maintenance.getMaintenanceID());
				prepSqlStatement.setString(2, maintenance.getMaintenancePerformed());
				prepSqlStatement.setDate(3, Date.valueOf(maintenance.getMaintenanceDate()));
				prepSqlStatement.setDouble(4, maintenance.getMaintenanceAmount());
				prepSqlStatement.setInt(5, maintenance.getVendorID());
				
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
	 
	 public static boolean deleteMaintenance(String maintenanceID) throws Exception {
		  boolean result = false; 
			String sqlStatement = new String("DELETE FROM maintenance WHERE maintenance_id = ?"); 
			PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, maintenanceID);
				int rowCount = prepSqlStatement.executeUpdate();
				if (rowCount != 1){
					result = false; // To-do. Throw exception
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
	 public static boolean updateMaintenance(Maintenance maintenance) throws Exception{
		  boolean result = false;
		  String sqlStatement = new String("UPDATE maintenance SET maintenance_performed = ?, maintenance_date = ?, maintenance_cost = ?, vendor_id = ? WHERE maintenance_id = ?");
		  
		  PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, maintenance.getMaintenancePerformed());
				prepSqlStatement.setDate(2, Date.valueOf(maintenance.getMaintenanceDate()));
				prepSqlStatement.setDouble(3, maintenance.getMaintenanceAmount());
				prepSqlStatement.setInt(4, maintenance.getVendorID());
				prepSqlStatement.setInt(5, maintenance.getMaintenanceID());
				
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
}
