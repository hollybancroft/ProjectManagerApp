import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropertyDAO {
	
	 public static Connection getConnection() throws Exception { 
		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/property_manager","root", "");
	  
	  return con;
	  }
	  
	  public static void selectAllProperties() throws Exception{
	  
	  Connection con = getConnection();
	  
	  // create JDBC statement object 
	  Statement st = con.createStatement();
	  
	  String query = "SELECT * FROM property";
	  
	  ResultSet rs = st.executeQuery(query);
	  
	  // process the ResultSet object 
	  boolean flag = false; 
	  while (rs.next()) {
	  flag = true;
	  
	
	  System.out.println("Property ID: " + rs.getInt(1) + "\n Address: " + rs.getString(2) + "\n City: " +
	  rs.getString(3) + "\n State: " + rs.getString(4) + "\n Zip: " + rs.getInt(5) + "\n Purchase Date " +
	  rs.getDate(6) + "\n Mortgage Amount: " + rs.getDouble(7) + "\n Mortgage Due: " + rs.getDate(8) + "\n Unit Type: " +
	  rs.getString(9) + "\n Maintenance ID: " + rs.getInt(10) + "\n Occupied: " + rs.getBoolean(11) + "\n\n" ); 
	  
	  }
	 
	  
	  if (flag == true) { 
		  System.out.println("\nRecords retrieved and displayed");
	  
	  } else { 
		  System.out.println("Record not found"); 
	  }
	  
	  // close JDBC objects
	  rs.close(); 
	  st.close();
	  con.close();
	  
	  }

	  
	  public static boolean insertProperty(Property property) throws Exception{
		  	Connection connection = getConnection();
			boolean result = false; 
			String sqlStatement = new String("INSERT INTO property VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
			PreparedStatement prepSqlStatement = null;
			try {
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setInt(1, property.getPropertyID());
				prepSqlStatement.setString(2, property.getAddress());
				prepSqlStatement.setString(3, property.getCity());
				prepSqlStatement.setString(4, property.getState());
				prepSqlStatement.setInt(5, property.getZip());
				prepSqlStatement.setDate(6, Date.valueOf(property.getPurchaseDate()));
				prepSqlStatement.setDouble(7, property.getMortgageAmt());
				prepSqlStatement.setDate(8, Date.valueOf(property.getMortgageDue()));
				prepSqlStatement.setString(9, property.getUnitType());
				prepSqlStatement.setInt(10, property.getMaintenanceID());
				prepSqlStatement.setBoolean(11, property.isOccupied());
				
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
	  
	  public static boolean deleteProperty(String propertyID) throws Exception {
		  boolean result = false; 
			String sqlStatement = new String("DELETE FROM property WHERE propertyid = ?"); 
			PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, propertyID);
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
	  
	  public static boolean updateProperty(Property property) throws Exception{
		  boolean result = false;
		  String sqlStatement = new String("UPDATE property SET address = ?, city = ?, state = ?, zip = ?, purchase_date = ?, mortgage_amount = ?, mortgage_due = ?, unit_type = ?, maintenance_id = ?, occupied = ? ");
		  sqlStatement += "WHERE propertyid = ?";
		  
		  PreparedStatement prepSqlStatement = null;
			try {
				Connection connection = getConnection();
				prepSqlStatement = connection.prepareStatement(sqlStatement);
				prepSqlStatement.setString(1, property.getAddress());
				prepSqlStatement.setString(2, property.getCity());
				prepSqlStatement.setString(3, property.getState());
				prepSqlStatement.setInt(4, property.getZip());
				prepSqlStatement.setDate(5, Date.valueOf(property.getPurchaseDate()));
				prepSqlStatement.setDouble(6, property.getMortgageAmt());
				prepSqlStatement.setDate(7, Date.valueOf(property.getMortgageDue()));
				prepSqlStatement.setString(8, property.getUnitType());
				prepSqlStatement.setInt(9, property.getMaintenanceID());
				prepSqlStatement.setBoolean(10, property.isOccupied());
				prepSqlStatement.setInt(11, property.getPropertyID());
				
				int rowCount = prepSqlStatement.executeUpdate();
				if (rowCount != 1){
					result = false; // To-do. Throw exception
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
