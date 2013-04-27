package net.umc.ludumdare.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.umc.ludumdare.common.Constants;
import net.umc.ludumdare.common.ResourceTypes;

public class DataService {

	public static Connection connection = null;
	
	private static void initialize() {		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:resources/data/GAMEDATA");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static boolean isConnected() {
		if (connection == null) {
			initialize();
		}
		return connection != null;
	}
	
	public static String getResourcePath(String resourceId, ResourceTypes type) {
		if (!isConnected()) {
			// throw error?
			return Constants.EMPTY;
		}
		
		String sqlStatement = Constants.EMPTY;
		if (type == ResourceTypes.IMAGE) {
			sqlStatement = String.format(SQLStatements.GET_IMAGE, resourceId);
		}
		else if (type == ResourceTypes.SOUND) {
			sqlStatement = String.format(SQLStatements.GET_SOUND, resourceId);
		}
		else if (type == ResourceTypes.MAP) {
			sqlStatement = String.format(SQLStatements.GET_MAP, resourceId);
		}
		
	    try {
	    	Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery(sqlStatement);
			if (rs.next())
			{
				return rs.getString("PATH");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	    return Constants.EMPTY;
	}
	
	public static String getGlobalByKey(String key) {
		if (!isConnected()) {
			// throw error?
			return Constants.EMPTY;
		}
		
		String sqlStatement = String.format(SQLStatements.GET_GLOBAL, key);
		
	    try {
	    	Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery(sqlStatement);
			if (rs.next())
			{
				return rs.getString("VALUE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	    return Constants.EMPTY;
	}
	
	public static ArrayList<String> getAllResourceIDs() {
		ArrayList<String> resources = new ArrayList<String>();
		if (!isConnected()) {
			// throw error?
			return resources;
		}
		
		String sqlStatement = SQLStatements.GET_ALL_RESOURCE_IDS;
		
	    try {
	    	Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery(sqlStatement);
			if (rs.next())
			{
				resources.add(rs.getString("RESOURCE_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	    return resources;
	}
	
	public static void updateGlobal(String key, String value) {
		if (!isConnected()) {
			// throw error?
			return ;
		}
		
		String sqlStatement = String.format(SQLStatements.GET_GLOBAL, key);
		
	    try {
	    	Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery(sqlStatement);
			if (rs.next()){
				statement.executeUpdate(String.format(SQLStatements.UPDATE_GLOBAL, key, value));
			}
			else {
				statement.executeUpdate(String.format(SQLStatements.CREATE_GLOBAL, key, value));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	
	public static void insertResource(String res, String type, String path) {
		updateResource(res, type, path);
	}
	
	public static void updateResource(String res, String type, String path) {
		if (!isConnected()) {
			// throw error?
			return ;
		}
		
		String sqlStatement = String.format(SQLStatements.GET_RESOURCE, res);
		
	    try {
	    	Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery(sqlStatement);
			if (rs.next()){
				statement.executeUpdate(String.format(SQLStatements.UPDATE_RESOURCE, type, path, res));
			}
			else {
				statement.executeUpdate(String.format(SQLStatements.INSERT_RESOURCE, res, type, path));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
}
