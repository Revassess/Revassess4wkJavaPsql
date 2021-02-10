package com.revature.config;

import java.sql.Connection;

/**
 *         This is a class to hold the configurations of your db
 *         connection. You should change the url, username, and password. DO NOT
 *         CHANGE THE MODIFIERS OR THE NAMES OF THE VARIABLES. These are used to
 *         test your db schema.
 */
public class ConnectionUtil {

	/////Tier 3
	// add your jdbc url, use jdbc connection url format not just the host url.
	public static final String URL = "";
	// add your jdbc username
	public static final String USERNAME = "";
	// add your jdbc password
	public static final String PASSWORD = "";
	// name of the created sequence in tier 3
	public static final String TIER_3_SEQUENCE_NAME = "";
	/////


	/////Tier 4
	// implement this method to connect to the db and return the connection object
	public Connection connect(){
		return null;
	}


	//implement this method with a callable statement that calls the absolute value sql function
	public long callAbsoluteValueFunction(long value){
		return value;
	}
	/////






















	//do not edit below this line



	//for singleton instance
	private static ConnectionUtil instance;

	private ConnectionUtil(){
		super();
	}

	public static ConnectionUtil getInstance(){
		if(instance == null){
			instance = new ConnectionUtil();
		}
		return instance;
	}
}
