import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantDAO {
	
	 public static Connection getConnection() throws Exception { 
		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/property_manager","root", "");
	  
	  return con;
	  }
	 
	 public static void selectTenant(int tenantID) throws Exception{
		  Connection con = getConnection();
	  
		  String sqlStatement = new String("SELECT * FROM tenant WHERE tenant_id = ?"); 
		  PreparedStatement prepSqlStatement = null;
			ResultSet rsFindTenant = null;
			
			try{
				prepSqlStatement = con.prepareStatement(sqlStatement);
				prepSqlStatement.setInt(1, tenantID);
				rsFindTenant = prepSqlStatement.executeQuery();
				if (rsFindTenant == null) { //the tenant wasn't found. TO-DO figure out why this isn't working
					System.out.println("No tenants found");
				}
				
				while (rsFindTenant.next()){					
					System.out.println("Tenant ID: " + rsFindTenant.getInt(1) + "\nFirst name: " + rsFindTenant.getString(2) + "\nLast name: " + rsFindTenant.getString(3) +
					"\nProperty ID: " + rsFindTenant.getInt(4) + "\nPhone: " + rsFindTenant.getString(5) + "\nRent Due Date: " + rsFindTenant.getDate(6) + "\nMonthy Rent Amount: " + rsFindTenant.getDouble(7) +
					"\nMissing rent? " + rsFindTenant.getBoolean(8) + "\nActive Tenant? " + rsFindTenant.getBoolean(9));
			}
				
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
	  
	  }

	  public static boolean insertTenant(Tenant tenant) throws Exception{
		  	Connection connection = getConnection();
			boolean result = false; 
			String sqlStatement = new String("INSERT INTO tenant VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
			PreparedStatement prepSqlStatement = null;
			try {
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setInt(1, tenant.getTenantID());
				prepSqlStatement.setString(2, tenant.getFirstName());
				prepSqlStatement.setString(3, tenant.getLastName());
				prepSqlStatement.setInt(4, tenant.getPropertyID());
				prepSqlStatement.setString(5, tenant.getPhone());
				prepSqlStatement.setDate(6, Date.valueOf(tenant.getRentDue()));
				prepSqlStatement.setDouble(7, tenant.getRentAmount());
				prepSqlStatement.setBoolean(8, tenant.isMissingRent());
				prepSqlStatement.setBoolean(9, tenant.isActive());
				
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
	  
	  public static boolean deleteTenant(String tenantID) throws Exception {
		  boolean result = false; 
			String sqlStatement = new String("DELETE FROM tenant WHERE tenant_id = ?"); 
			PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, tenantID);
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
	  public static boolean updateTenant(Tenant tenant) throws Exception{
		  boolean result = false;
		  String sqlStatement = new String("UPDATE tenant SET first_name = ?, last_name = ?, propertyid = ?, phone = ?, rent_due = ?, rent_amount = ?, missing_rent = ?, active = ? WHERE tenant_id = ?");
		  
		  PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, tenant.getFirstName());
				prepSqlStatement.setString(2, tenant.getLastName());
				prepSqlStatement.setInt(3, tenant.getPropertyID());
				prepSqlStatement.setString(4, tenant.getPhone());
				prepSqlStatement.setDate(5, Date.valueOf(tenant.getRentDue()));
				prepSqlStatement.setDouble(6, tenant.getRentAmount());
				prepSqlStatement.setBoolean(7, tenant.isMissingRent());
				prepSqlStatement.setBoolean(8, tenant.isActive());
				prepSqlStatement.setInt(9, tenant.getTenantID());
				
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
