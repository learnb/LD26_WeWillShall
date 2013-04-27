package net.umc.ludumdare.data;

public class SQLStatements {

	// RESOURCES TABLE
	public static final String GET_RESOURCE = "SELECT TYPE,PATH FROM RESOURCES WHERE RESOURCE_ID='%s'";
	public static final String GET_IMAGE = "SELECT PATH FROM RESOURCES WHERE RESOURCE_ID='%s' AND TYPE='IMAGE';";
	public static final String GET_SOUND = "SELECT PATH FROM RESOURCES WHERE RESOURCE_ID='%s' AND TYPE='SOUND';";
	public static final String INSERT_RESOURCE = "INSERT INTO RESOURCES (RESOURCE_ID, TYPE, PATH) VALUES ('%s','%s','%s');";
	public static final String UPDATE_RESOURCE = "UPDATE RESOURCES SET TYPE='%s',PATH='%s' WHERE RESOURCE_ID='%s';";
	public static final String GET_ALL_RESOURCE_IDS = "SELECT RESOURCE_ID FROM RESOURCES;";
	
	// GLOBALS TABLE
	public static final String GET_GLOBAL = "SELECT VALUE FROM GLOBALS WHERE KEY='%s'";
	public static final String CREATE_GLOBAL = "INSERT INTO GLOBALS (KEY,VALUE) VALUES ('%s','%s')";
	public static final String UPDATE_GLOBAL = "UPDATE GLOBALS SET VALUE='%s' WHERE KEY='%s'";
}
