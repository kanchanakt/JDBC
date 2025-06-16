package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyConnection {
	
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/kanch", "root", "");//enter your password here
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(ResultSet res, Statement stmt, Connection con) {
		try {
			if(res!=null) {
				System.out.println("resultset is closed");
			}
			else {
				System.out.println(" no need to close resultset");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt!=null) {
				System.out.println("statement is closed");
			}
			else {
				System.out.println(" no need to close statement");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(con!=null) {
				System.out.println("connection is closed");
			}
			else {
				System.out.println(" no need to close connection");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
